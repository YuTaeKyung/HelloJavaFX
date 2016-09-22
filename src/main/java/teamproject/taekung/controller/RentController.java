package teamproject.taekung.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teamproject.taekung.dao.RentDAO;
import teamproject.taekung.model.RentModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class RentController implements Initializable {
    @FXML
    BorderPane main;
    @FXML TextField rno;
    @FXML TextField mno;
    @FXML TextField bno;
    @FXML TextField regdate;
    @FXML TextField duedate;
    @FXML TableView rentTable;
    @FXML TableColumn rnoTC;
    @FXML TableColumn mnoTC;
    @FXML TableColumn bnoTC;
    @FXML TableColumn regdateTC;
    @FXML TableColumn duedateTC;



    private ObservableList<RentModel> rlist = null;
    List<RentModel> rl= null;

    private static String findmno = "select rno,mno,bno, regdate, (regdate+7) as duedate from rent where mno = ?";
    private static String findbno = "select rno,mno,bno , regdate, (regdate+7) as duedate from rent where bno = ?";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rnoTC.setCellValueFactory(new PropertyValueFactory<RentModel,Integer>("rno"));
        mnoTC.setCellValueFactory(new PropertyValueFactory<RentModel,Integer>("mno"));
        bnoTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("bno"));
        regdateTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("regdate"));
        duedateTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("duedate"));

        rlist = FXCollections.observableArrayList();
        List<RentModel> rl = RentDAO.selectRentAll();

        for(RentModel r : rl){
            rlist.add(r);
        }
        rentTable.setItems(rlist);
    }



    public void goToMember(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/member.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("회원관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goToBook(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("도서관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitModal(ActionEvent event) {
        Stage stage =(Stage)main.getScene().getWindow();
        stage.close();

    }

    public void selectRent(ActionEvent event) {
        if(rno.getText().equals("")&&mno.getText().equals("")&&bno.getText().equals("")&&
                regdate.getText().equals("")&&duedate.getText().equals("")){
            rl = RentDAO.selectRentAll();
        }
        else if(mno.getText().length()!=0) {rl = RentDAO.selectNo(findmno, mno.getText());}
        else if(bno.getText().length()>0){rl = RentDAO.selectNo(findbno, bno.getText());
        }

        rlist.clear();

        for(RentModel r : rl){
            rlist.add(r);
        }

        rentTable.setItems(rlist);

    }

    public void updateRent(ActionEvent event) {

        RentModel rm = new RentModel(Integer.parseInt(rno.getText()),Integer.parseInt(mno.getText()),bno.getText(),
                                    regdate.getText(),duedate.getText(),"","");
        RentDAO.updateRent(rm, rno.getText());

        rlist.clear();
        for(RentModel r : RentDAO.selectRentAll()){
            rlist.add(r);
        }



    }

    public void addRent(ActionEvent event) {
        List<RentModel> lr = RentDAO.selectNo(findbno, bno.getText());
        List<RentModel> lr2 = RentDAO.selectNo(findmno, bno.getText());

        if(lr.size()>0){
            alert("해당 도서는 지금 대여중입니다!!");
        } else if(mno.getText().equals("")||bno.getText().equals("")){
            alert("회원번호와 도서번호를 확인해주세요");
        } else if(lr2.size()==0){
            alert("입력한 회원번호는 없는 번호입니다");
        }

        else
        {
            RentModel r = new RentModel(0, Integer.parseInt(mno.getText()), bno.getText(), "", "", "", "");
            RentDAO.addRent(r);

        }

        rlist.clear();

        for(RentModel rm : RentDAO.selectRentAll()){
            rlist.add(rm);
        }
    }



    public void deleteRent(ActionEvent event) {

        RentDAO.deleteRent(rno.getText());

        rlist.clear();

        for(RentModel r : RentDAO.selectRentAll()) {
            rlist.add(r);
        }

    }

    public void reset(ActionEvent event) {
        rno.setText("");
        mno.setText("");
        bno.setText("");
        regdate.setText("");
        duedate.setText("");
    }

    public void showRent(MouseEvent e) {
        int num = rentTable.getSelectionModel().getSelectedIndex();
try {
    if (e.getClickCount() == 2) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showRentData.fxml"));
        try {
            Parent root = loader.load();
            showRentDataController srd = loader.getController();
            srd.sendData(rlist, num);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    } else if (e.getClickCount() == 1) {
        rno.setText(String.valueOf(rlist.get(num).getRno()));
        mno.setText(String.valueOf(rlist.get(num).getMno()));
        bno.setText(rlist.get(num).getBno());
        regdate.setText(rlist.get(num).getRegdate());
        duedate.setText(rlist.get(num).getDuedate());
    }
} catch (Exception ex){}
    }

    private void alert(String s) {
        Alert alt =new Alert(Alert.AlertType.WARNING);
        alt.setTitle("데이터 입력 오류!");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }
}
