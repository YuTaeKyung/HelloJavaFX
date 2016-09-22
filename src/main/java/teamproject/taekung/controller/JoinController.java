package teamproject.taekung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teamproject.taekung.VO.UserVO;
import teamproject.taekung.dao.JoinDao;

import java.util.List;

/**
 * Created by taeku on 2016-09-11.
 */
public class JoinController {
    @FXML CheckBox checkBox1;
    @FXML CheckBox checkBox2;
    @FXML VBox agreement;
    @FXML
    BorderPane joinPage;
    @FXML TextField mid;
    @FXML PasswordField pwd;
    @FXML PasswordField repwd;
    @FXML TextField email;
    @FXML TextField phone;
    @FXML TextField addr;
    @FXML TextField sname;
    @FXML StackPane main;


    public void nextPage(ActionEvent event) {

        if(checkBox1.isSelected()==false){
            alert("이용약관에 동의해주세요");
        } else if(checkBox2.isSelected()==false){
            alert("개인정보 수집에 동의해주세요");
        } else {
            agreement.setVisible(false);
            joinPage.setVisible(true);
        }
    }



    public void backPage(ActionEvent event) {
        agreement.setVisible(true);
        joinPage.setVisible(false);
    }

    public void joinBtn(ActionEvent event) {


        if(!pwd.getText().equals(repwd.getText())){
            alertID("비밀번호 불일치!!");
        }else if(mid.getText().equals("")|| pwd.getText().equals("")||repwd.getText().equals("")||
                email.getText().equals("")||phone.getText().equals("")||addr.getText().equals("")||
                sname.getText().equals("")){alertID("하나도 빠짐없이 입력하세요");}

        else {


            UserVO user = new UserVO(mid.getText(), pwd.getText(), email.getText(),phone.getText(), addr.getText(), sname.getText());

            JoinDao.joinManager(user);

            if (JoinDao.s) {
                alertID("가입실패!!");

            } else {
                alertID("가입완료!!");
                Stage stage = (Stage) joinPage.getScene().getWindow();
                stage.close();
            }

        }

    }

    public void checkid(ActionEvent event) {
        List user = JoinDao.selectID(mid.getText());



        if(mid.getText().equals("")){
            alertID("아이디입력하세요");

        }else if(user.size()!=0){
            alertID(mid.getText()+" 는 이미 있는 아이디 입니다");



        } else {alertID("사용가능한 아이디 입니다");}
    }






    private void alert(String s){
        Alert alt =new Alert(Alert.AlertType.WARNING);
        alt.setTitle("이용약관 동의");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }
    private void alertID(String s) {
        Alert alt = new Alert(Alert.AlertType.INFORMATION);
        alt.setTitle("회원가입");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }
}
