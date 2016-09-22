package teamproject.taekung.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


    /**
     * @author mkhj1113
     * @date 2016-09-13.
     */
    public class SplashController implements Initializable {
        @FXML
        ProgressBar pgbar;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            pgbar.setStyle("-fx-accent: green ;");

            Thread task = new Thread( () -> {
                for ( double i=0; i<=1; i+=0.001) {
                    pgbar.setProgress( i );
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    ((Stage) pgbar.getScene().getWindow()).close();
                    showMainStage();

                });
            } );

            task.setDaemon(true);
            task.start();



        }

        private void showMainStage() {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            try {
                Parent root = loader.load();

                stage.setScene( new Scene( root ));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.getIcons().add(new Image("/img/새글쓰기.png"));
                stage.setOnCloseRequest(event -> MainController.alertConfirm(null, event) );
                stage.show();

            } catch (Exception ex) {

            }

        }
    }




