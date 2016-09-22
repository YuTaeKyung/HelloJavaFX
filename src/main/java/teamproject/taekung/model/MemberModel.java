package teamproject.taekung.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by taeku on 2016-09-14.
 */
public class MemberModel {

    private SimpleIntegerProperty mno;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty cellphone;
    private SimpleStringProperty birthdate;
    private SimpleStringProperty addr;
    private SimpleStringProperty email;


    public MemberModel(int mno, String name, String phone,
                       String cellphone, String birthdate, String addr,
                       String email) {
        this.mno = new SimpleIntegerProperty(mno);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.cellphone = new SimpleStringProperty(cellphone);
        this.birthdate = new SimpleStringProperty(birthdate);
        this.addr = new SimpleStringProperty(addr);
        this.email = new SimpleStringProperty(email);
    }

    public int getMno() {
        return mno.get();
    }

    public void setMno(int mno) {
        this.mno.set(mno);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }


    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getCellphone() {
        return cellphone.get();
    }


    public void setCellphone(String cellphone) {
        this.cellphone.set(cellphone);
    }

    public String getBirthdate() {
        return birthdate.get();
    }


    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public String getAddr() {
        return addr.get();
    }

    public void setAddr(String addr) {
        this.addr.set(addr);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
