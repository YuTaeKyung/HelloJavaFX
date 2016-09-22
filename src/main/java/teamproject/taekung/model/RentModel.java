package teamproject.taekung.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by taeku on 2016-09-14.
 */
public class RentModel {
    private SimpleIntegerProperty rno;
    private SimpleIntegerProperty mno;
    private SimpleStringProperty bno;
    private SimpleStringProperty regdate;
    private SimpleStringProperty duedate;

    private SimpleStringProperty name;
    private SimpleStringProperty bname;


    public RentModel(int rno, int mno, String bno, String regdate, String duedate, String name, String bname) {
        this.rno = new SimpleIntegerProperty(rno);
        this.mno = new SimpleIntegerProperty(mno);
        this.bno = new SimpleStringProperty(bno);
        this.regdate = new SimpleStringProperty(regdate);
        this.duedate = new SimpleStringProperty(duedate);

        this.name = new SimpleStringProperty(name);
        this.bname = new SimpleStringProperty(bname);
    }


    public int getRno() {
        return rno.get();
    }

    public void setRno(int rno) {
        this.rno.set(rno);
    }

    public int getMno() {
        return mno.get();
    }


    public void setMno(int mno) {
        this.mno.set(mno);
    }

    public String getBno() {
        return bno.get();
    }


    public void setBno(String bno) {
        this.bno.set(bno);
    }

    public String getRegdate() {
        return regdate.get();
    }

    public void setRegdate(String regdate) {
        this.regdate.set(regdate);
    }

    public String getDuedate() {
        return duedate.get();
    }


    public void setDuedate(String duedate) {
        this.duedate.set(duedate);
    }


    public String getBname() {
        return bname.get();
    }

    public void setBname(String bname) {
        this.bname.set(bname);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
