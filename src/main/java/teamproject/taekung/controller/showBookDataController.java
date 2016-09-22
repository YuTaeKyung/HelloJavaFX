package teamproject.taekung.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import teamproject.taekung.dao.BookDAO;
import teamproject.taekung.model.BookModel;

/**
 * Created by taeku on 2016-09-19.
 */
public class showBookDataController {
    @FXML Label bno;
    @FXML Label bname;
    @FXML Label genre;
    @FXML Label author;
    @FXML Label publisher;
    @FXML ImageView img;




    public void sendData(ObservableList<BookModel> blist, int num, String s) {

        BookModel b = (BookModel)blist.get(num);


        b = BookDAO.viewBook(b.getBno());
        bno.setText(b.getBno());
        bname.setText(b.getBname());
        genre.setText(b.getGenre());
        author.setText(b.getAuthor());
        publisher.setText(b.getPublisher());


        try{
            img.setImage(new Image("/img/" +b.getBimg()));

        } catch(Exception ex) {
        img.setImage(new Image("/img/x.png"));
        }


    }
}
