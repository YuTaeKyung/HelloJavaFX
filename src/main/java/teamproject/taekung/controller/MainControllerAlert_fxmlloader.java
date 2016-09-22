package teamproject.taekung.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * Created by taeku on 2016-09-21.
 */
public class MainControllerAlert_fxmlloader {
    /** 경고창 메서드 */


    public static void alertConfirm(ActionEvent ae,WindowEvent we){
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setTitle("프로그램 종료");
        alt.setHeaderText(null);
        alt.setContentText("프로그램을 종료하시겠습니까?");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        alt.getButtonTypes().setAll(ok,no);


        String text = alt.showAndWait().get().getText();

        if(text.equals("OK")){
            Platform.exit();
        } else {
            if(ae !=null) ae.consume();
            if(we !=null) we.consume();
        }
    }


    public void alert(String a, String s){
        Alert alt = new Alert(Alert.AlertType.WARNING);
        alt.setTitle("아이디/패스워드 입력 오류!!");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }


    public void alertLogin(ActionEvent event) {

        alert("로그인 메세지!","로그인 후 이용하실 수 있습니다!!");

    }

    public void okAlert(){
        Alert alt = new Alert(Alert.AlertType.INFORMATION);
        alt.setTitle("로그인 성공 ");
        alt.setHeaderText(null);
        alt.setContentText("로그인 되었습니다! ");
        alt.showAndWait();

    }


    /** 경고창 메서드  end*/


    /**  FXMLLoader 메서드*/
    public FXMLLoader fxmlload(String s){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        return loader;
    }
    /**  FXMLLoader 메서드*/
}
