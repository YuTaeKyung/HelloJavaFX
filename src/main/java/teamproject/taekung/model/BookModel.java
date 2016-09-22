package teamproject.taekung.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by taeku on 2016-09-14.
 */
public class BookModel {
    private SimpleStringProperty bno;
    private SimpleStringProperty bname;
    private SimpleStringProperty genre;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private SimpleStringProperty bimg;


    public BookModel(String bno, String bname, String genre, String author, String publisher, String bimg) {
        this.bno = new SimpleStringProperty(bno);
        this.bname = new SimpleStringProperty(bname);
        this.genre = new SimpleStringProperty(genre);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.bimg = new SimpleStringProperty(bimg);
    }

    public String getBno() {
        return bno.get();
    }


    public void setBno(String bno) {
        this.bno.set(bno);
    }

    public String getBname() {
        return bname.get();
    }

    public void setBname(String bname) {
        this.bname.set(bname);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getAuthor() {
        return author.get();
    }


    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public String getBimg() {
        return bimg.get();
    }


    public void setBimg(String bimg) {
        this.bimg.set(bimg);
    }
}
