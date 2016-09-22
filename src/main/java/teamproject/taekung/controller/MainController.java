package teamproject.taekung.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import teamproject.taekung.dao.MainDao;
import java.io.IOException;
import java.util.List;


/**
 * Created by taeku on 2016-09-11.
 */
public class MainController extends MainControllerAlert_fxmlloader {
    @FXML  BorderPane main;
    @FXML TextField uid;
    @FXML PasswordField pwd;


    public void exitProgram(ActionEvent event) {
        alertConfirm(event,null);

    }




    public void loginAlert() throws Exception{

        List user = MainDao.setLogin(uid.getText(),pwd.getText());





        if (uid.getText().equals("")){
            alert("아이디/패스워드 입력 오류!!","아이디를 입력하세요");
        } else if(pwd.getText().equals("")){
            alert("아이디/패스워드 입력 오류!!","비밀번호를 입력하세요");
        } else if(user.size()!=0){
            okLogin();
        } else alert("아이디/패스워드 입력 오류!!","아이디와 비밀번호가 일치하지 않습니다");



//        okLogin();

    }

    public void okLogin()throws Exception{

        okAlert();



        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/Index.fxml"));
        Parent root =loader.load();


        Stage stage = (Stage)main.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("도서대여프로그램");
        stage.setResizable(false);
        stage.show();

    }







    public void loginProgram(ActionEvent event)throws Exception{
        loginAlert();

    }

    public void joinProgram(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Join.fxml"));

        Parent root = (Parent)loader.load();

        Stage stage = new Stage();
        stage.setTitle("회원가입");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getScene().getWindow());
        stage.show();

    }




    public void findInfo(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindID.fxml"));
        Parent root = loader.load();

        Stage stage= new Stage();
        stage.setTitle("아이디/비번 찾기");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getScene().getWindow());
        stage.show();


    }


    public void exit(ActionEvent event) {
        alertConfirm(event,null);
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
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
