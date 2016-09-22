package teamproject.taekung.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teamproject.taekung.dao.FindBookDAO;
import teamproject.taekung.dao.FindMemberDAO;
import teamproject.taekung.dao.FindRentDAO;
import teamproject.taekung.file.SaveData;
import teamproject.taekung.model.BookModel;
import teamproject.taekung.model.MemberModel;
import teamproject.taekung.model.RentModel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by taeku on 2016-09-11.
 */
public class IndexController extends Indexfxml implements Initializable  {


    private ObservableList<MemberModel> mblist = null;
    private ObservableList<BookModel> blist = null;
    private ObservableList<RentModel> rlist = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mno.setCellValueFactory(new PropertyValueFactory<MemberModel,Integer>("mno"));
        name.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("phone"));
        cellphone.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("cellphone"));
        birthdate.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("birthdate"));
        email.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("email"));
        addr.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("addr"));

        mblist = FXCollections.observableArrayList();

        List<MemberModel> ml = FindMemberDAO.listMember();

        for(MemberModel m : ml){
            mblist.add(m);
        }

        showMember.setItems(mblist);


        ISBN.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bno"));
        bnameTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bname"));
        authorTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("author"));
        publisherTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("publisher"));
        bimgTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bimg"));


        blist = FXCollections.observableArrayList();
        List<BookModel> bl = FindBookDAO.listBook();
        for(BookModel b : bl){
            blist.add(b);
        }


        rno.setCellValueFactory(new PropertyValueFactory<RentModel,Integer>("rno"));
        bnoRTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("bno"));
        mnoRTC.setCellValueFactory(new PropertyValueFactory<RentModel,Integer>("mno"));
        regdateTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("regdate"));
        duedateTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("duedate"));
        rNameTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("name"));
        rBnoTC.setCellValueFactory(new PropertyValueFactory<RentModel,String>("bname"));

        rlist = FXCollections.observableArrayList();
        List<RentModel> rl = FindRentDAO.listRent();
        for(RentModel r : rl){
            rlist.add(r);
        }

    }


    public void exit(ActionEvent event) {

        MainController.alertConfirm(event,null);

    }


    public void findMember(ActionEvent event) {
        showMember.setVisible(true);
        showBook.setVisible(false);
        showRent.setVisible(false);
        bookText.setText("");
        rentText.setText("");


        eLabel.setVisible(true);
        eLabel.setText("이메일");
        bdLabel.setText("생년월일");
        bdLabel.setVisible(true);
        emailLB.setVisible(true);

        birthdateLB.setVisible(true);

        nLabel.setText("이름");
        pLabel.setText("전화번호");
        cpLabel.setText("휴대폰");
        aLabel.setText("주소");

        dLabel.setVisible(false);
        ddLB.setVisible(false);

        emailLB.setText("");
        birthdateLB.setText("");
        nameLB.setText("");
        phoneLB.setText("");
        cellphoneLB.setText("");
        addrLB.setText("");
        ddLB.setText("");



        if(memberText.getText().equals("")) {
            List<MemberModel> ml = FindMemberDAO.listMember();


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);
        } else if (memberSelect.getText().equals("이름")){

            List<MemberModel> ml = FindMemberDAO.listMemberForName(memberText.getText());


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);


        } else if (memberSelect.getText().equals("전화번호")){


            List<MemberModel> ml = FindMemberDAO.listMemberForPhone(memberText.getText());


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);
        }


    }

    public void findBook(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(true);
        showRent.setVisible(false);
        memberText.setText("");
        rentText.setText("");

        eLabel.setVisible(false);
        bdLabel.setVisible(false);
        emailLB.setVisible(false);
        birthdateLB.setVisible(false);

        nLabel.setText("바코드");
        pLabel.setText("도서명");
        cpLabel.setText("저자");
        aLabel.setText("출판사");

        emailLB.setText("");
        birthdateLB.setText("");
        nameLB.setText("");
        phoneLB.setText("");
        cellphoneLB.setText("");
        addrLB.setText("");
        ddLB.setText("");

        dLabel.setVisible(false);
        ddLB.setVisible(false);

        if(bookText.getText().equals("")) {
            List<BookModel> bl = FindBookDAO.listBook();


            blist.clear();

            for (BookModel b : bl) {
                blist.add(b);
            }

            showBook.setItems(blist);
        } else if (bookselect.getText().equals("도서명")){

            List<BookModel> bl = FindBookDAO.listBookForBname(bookText.getText());


            blist.clear();

            for (BookModel b : bl) {
                blist.add(b);
            }

            showBook.setItems(blist);


        } else if (bookselect.getText().equals("저자")){


            List<BookModel> bl = FindBookDAO.listBookForAuthor(bookText.getText());

            blist.clear();

            for (BookModel b : bl) {
                blist.add(b);
            }

            showBook.setItems(blist);
        } else if(bookselect.getText().equals("도서번호")){
            List<BookModel> bl = FindBookDAO.listBookForBno(bookText.getText());
            blist.clear();
            for(BookModel b : bl){
                blist.add(b);
            }
        }

    }

    public void findRent(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(false);
        showRent.setVisible(true);
        eLabel.setVisible(true);
        bdLabel.setVisible(true);
        emailLB.setVisible(true);
        birthdateLB.setVisible(true);
        memberText.setText("");
        bookText.setText("");

        dLabel.setVisible(true);
        ddLB.setVisible(true);

        eLabel.setText("회원번호");
        bdLabel.setText("회원이름");
        nLabel.setText("대여번호");
        pLabel.setText("도서번호");
        cpLabel.setText("도서명");
        aLabel.setText("대여일");
        dLabel.setText("반납일");

        emailLB.setText("");
        birthdateLB.setText("");
        nameLB.setText("");
        phoneLB.setText("");
        cellphoneLB.setText("");
        addrLB.setText("");
        ddLB.setText("");


        if(rentText.getText().equals("")) {
            List<RentModel> rl = FindRentDAO.listRent();


            rlist.clear();

            for (RentModel r : rl) {
                rlist.add(r);
            }

            showRent.setItems(rlist);
        } else if (selectrent.getText().equals("도서번호")){

            List<RentModel> rl = FindRentDAO.listRentForBno(rentText.getText());


            rlist.clear();

            for (RentModel r : rl) {
                rlist.add(r);
            }

            showRent.setItems(rlist);


        } else if (selectrent.getText().equals("회원번호")){


            List<RentModel> rl = FindRentDAO.listrentForMno(rentText.getText());


            rlist.clear();

            for (RentModel r : rl) {
                rlist.add(r);
            }

            showRent.setItems(rlist);
        } else if(selectrent.getText().equals("대여일")){
            List<RentModel> rl = FindRentDAO.listRentForRegdate(rentText.getText());
            rlist.clear();
            for(RentModel r : rl){
                rlist.add(r);
            }
        } else if(selectrent.getText().equals("반납일")){
            List<RentModel> rl = FindRentDAO.listRentForDuedate(rentText.getText());
            rlist.clear();
            for(RentModel r : rl){
                rlist.add(r);
            }
        }

    }

    public void goHome(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(false);
        showRent.setVisible(false);
        memberText.setText("");
        bookText.setText("");
        rentText.setText("");

        eLabel.setVisible(true);
        bdLabel.setVisible(true);
        emailLB.setVisible(true);
        birthdateLB.setVisible(true);

        nLabel.setText("이름");
        pLabel.setText("전화번호");
        cpLabel.setText("휴대폰");
        aLabel.setText("주소");

        emailLB.setText("");
        birthdateLB.setText("");
        nameLB.setText("");
        phoneLB.setText("");
        cellphoneLB.setText("");
        addrLB.setText("");
        ddLB.setText("");

        dLabel.setVisible(false);
        ddLB.setVisible(false);


    }

    public void goToMember(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/member.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle("회원관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void goToBook(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("도서관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToRent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rent.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("대여관리");

            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void memberLabel(MouseEvent e) {
        int num = showMember.getSelectionModel().getSelectedIndex();
try {
    if (e.getClickCount() == 1) {

        nameLB.setText(mblist.get(num).getName());
        phoneLB.setText(mblist.get(num).getPhone());
        cellphoneLB.setText(mblist.get(num).getCellphone());
        birthdateLB.setText(mblist.get(num).getBirthdate());
        addrLB.setText(mblist.get(num).getEmail());
        //addrLB.setText(mblist.get(num).getAddr());
        emailLB.setText(mblist.get(num).getAddr());
        //emailLB.setText(mblist.get(num).getEmail());
    } else if (e.getClickCount() == 2) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showMemberData.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            showMemberDataController smd = loader.getController();
            smd.sendData(mblist, num);

            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
} catch (Exception ex){}
    }

    public void selectOption(ActionEvent event) {
        memberSelect.setText(selectName.getText());
    }

    public void selectOption2(ActionEvent event) {
        memberSelect.setText(selectPhone.getText());
    }

    public void selectbookname(ActionEvent event) {
        bookselect.setText(bookname.getText());
    }

    public void selectauthor(ActionEvent event) {
        bookselect.setText(author.getText());
    }

    public void selectbnumber(ActionEvent event) {
        bookselect.setText(bnumber.getText());
    }

    public void selectbnoR(ActionEvent event) {
        selectrent.setText(bnoR.getText());
    }

    public void selectmnoR(ActionEvent event) {
        selectrent.setText(mnoR.getText());
    }

    public void selectregdateR(ActionEvent event) {
        selectrent.setText(regdateR.getText());
    }

    public void selectduedateR(ActionEvent event) {
        selectrent.setText(duedateR.getText());
    }


    public void bookLabel(MouseEvent e) {
        int num = showBook.getSelectionModel().getSelectedIndex();



        try {

            if (e.getClickCount() == 1) {

                nameLB.setText(blist.get(num).getBno());
                phoneLB.setText(blist.get(num).getBname());
                cellphoneLB.setText(blist.get(num).getAuthor());

                addrLB.setText(blist.get(num).getPublisher());

            } else if (e.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showBookData.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    showBookDataController smd = loader.getController();
                    smd.sendData(blist, num, ("/img/" + blist.get(num).getBimg()));

                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(main.getScene().getWindow());
                    stage.getIcons().add(new Image("/img/새글쓰기.png"));
                    stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception ex){}
    }

    public void rentLabel(MouseEvent e) {

        eLabel.setVisible(true);
        emailLB.setVisible(true);
        bdLabel.setVisible(true);
        birthdateLB.setVisible(true);
        int num = showRent.getSelectionModel().getSelectedIndex();
try {
    if (e.getClickCount() == 1) {

        emailLB.setText(String.valueOf(rlist.get(num).getMno()));
        birthdateLB.setText(rlist.get(num).getName());
        nameLB.setText(String.valueOf(rlist.get(num).getRno()));
        phoneLB.setText(rlist.get(num).getBno());
        cellphoneLB.setText(rlist.get(num).getBname());
        addrLB.setText(rlist.get(num).getRegdate().substring(0, 10));
        ddLB.setText(rlist.get(num).getDuedate().substring(0, 10));


    } else if (e.getClickCount() == 2) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showRentData.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            showRentDataController smd = loader.getController();
            smd.sendData(rlist, num);

            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
} catch (Exception ex){}
    }

    public void info(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("INFORMATION");

            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/새글쓰기.png"));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveData(ActionEvent event) {

        SaveData.saveMember(mblist);
        SaveData.saveBook(blist);
        SaveData.saveRent(rlist);

    }

}
