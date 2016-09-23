package teamproject.taekung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import teamproject.taekung.controller.MainController;

/**
 * Created by taeku on 2016-09-11.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        Parent root = loader.load();
        stage.setTitle("도서대여프로그램");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("teamproject/taekung/img/새글쓰기.png"));

        stage.setOnCloseRequest(event -> MainController.alertConfirm(null,event) );

        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
