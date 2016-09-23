package teamproject.taekung.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import teamproject.taekung.dao.BookDAO;
import teamproject.taekung.model.BookModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class BookController implements Initializable {
    @FXML BorderPane main;
    @FXML TextField bno,bname,genre,author,publisher,img;
    @FXML TableView bookTable;
    @FXML TableColumn bnoTC,bnameTC,genreTC,authorTC,publisherTC,imgTC;



    private ObservableList<BookModel> blist=null;
    List<BookModel> bl = null;


    private static String findbookname = "select * from book where bname = ? ";
    private static String findBno = "select * from book where bno = ? ";
    public static boolean d = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bnoTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bno"));
        bnameTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bname"));
        genreTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("genre"));
        authorTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("author"));
        publisherTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("publisher"));
        imgTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bimg"));

        blist= FXCollections.observableArrayList();

        List<BookModel> bl = BookDAO.selectBookAll();

        for(BookModel b : bl){

            blist.add(b);
        }

        bookTable.setItems(blist);

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

    public void goToRent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rent.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("대여관리");
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

    public void selectBook(ActionEvent event) {
        if(bno.getText().equals("")&&bname.getText().equals("")&&genre.getText().equals("")&&
                author.getText().equals("")&&publisher.getText().equals("")){
            bl = BookDAO.selectBookAll();
        }
        else if(bname.getText().length()!=0) {bl = BookDAO.selectBook(findbookname, bname.getText());}
        else if(bno.getText().length()>0){bl = BookDAO.selectBook(findBno, bno.getText());
        }

        blist.clear();

        for(BookModel b : bl){
            blist.add(b);
        }

        bookTable.setItems(blist);
    }

    public void updateBook(ActionEvent event) {
        BookModel bm = new BookModel(bno.getText(),bname.getText(),genre.getText(),author.getText(),publisher.getText(),img.getText());
        BookDAO.updateBook(bm,bno.getText());

        blist.clear();
        for(BookModel b : BookDAO.selectBookAll()){
            blist.add(b);
        }
    }

    public void addBook(ActionEvent event) {

        List<BookModel> lb = BookDAO.selectBook(findBno, bno.getText());


        if(lb.size()>0){
            alert("이미 등록된 도서입니다!!");
        } else if(bno.getText().equals("")||bname.getText().equals("")||genre.getText().equals("")||author.getText().equals("")||
                publisher.getText().equals("")) {
            alert("각 항목을 다 입력하세요");
        }


        else {
                BookModel bm = new BookModel(bno.getText(), bname.getText(), genre.getText(), author.getText(), publisher.getText(), img.getText());
                BookDAO.addBook(bm);
        }


            blist.clear();

            for (BookModel b : BookDAO.selectBookAll()) {

                blist.add(b);
            }


    }

    public void deleteBook(ActionEvent event) {
        BookController.alertDelete(event,null);
        if (d) {
            BookDAO.deleteMember(bno.getText());

            blist.clear();

            for (BookModel b : BookDAO.selectBookAll()) {
                blist.add(b);
            }
            d = false;
        }
    }

    public void reset(ActionEvent event) {
        bno.setText("");
        bname.setText("");
        genre.setText("");
        author.setText("");
        publisher.setText("");
        img.setText("");
    }

    public void showBook(MouseEvent e) {
        int num = bookTable.getSelectionModel().getSelectedIndex();

        try {
            if (e.getClickCount() == 2) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showBookData.fxml"));
                try {
                    Parent root = loader.load();

                    showBookDataController sbd = loader.getController();

                    sbd.sendData(blist, num, img.getText());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(main.getScene().getWindow());
                    stage.getIcons().add(new Image("/img/새글쓰기.png"));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getClickCount() == 1) {
                bno.setText(blist.get(num).getBno());
                bname.setText(blist.get(num).getBname());
                genre.setText(blist.get(num).getGenre());
                author.setText(blist.get(num).getAuthor());
                publisher.setText(blist.get(num).getPublisher());
                img.setText(blist.get(num).getBimg());


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

    public static void alertDelete(ActionEvent ae,WindowEvent we){
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setTitle("데이터 삭제");
        alt.setHeaderText(null);
        alt.setContentText("데이터를 삭제하시겠습니까?");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        alt.getButtonTypes().setAll(ok,no);


        String text = alt.showAndWait().get().getText();

        if(text.equals("OK")){
            d = true;
        } else {
            if(ae !=null) ae.consume();
            if(we !=null) we.consume();
        }
    }
}
