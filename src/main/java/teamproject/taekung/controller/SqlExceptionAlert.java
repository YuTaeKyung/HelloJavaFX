package teamproject.taekung.controller;

import javafx.scene.control.Alert;

/**
 * Created by taeku on 2016-09-22.
 */
public class SqlExceptionAlert {
    public static void alert(String s) {
        Alert alt =new Alert(Alert.AlertType.WARNING);
        alt.setTitle("데이터 입력 오류!");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }
}
