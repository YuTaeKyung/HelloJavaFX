package teamproject.taekung.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import teamproject.taekung.dao.MemberDAO;
import teamproject.taekung.model.MemberModel;

/**
 * Created by taeku on 2016-09-18.
 */
public class showMemberDataController {
    @FXML
    Label mno;
    @FXML Label name;
    @FXML Label phone;
    @FXML Label cellphone;
    @FXML Label birthdate;
    @FXML Label addr;
    @FXML Label email;




    public void sendData(ObservableList mblist, int num) {

        MemberModel m = (MemberModel)mblist.get(num);


        m = MemberDAO.viewMember(String.valueOf(m.getMno()));
        mno.setText(String.valueOf(m.getMno()));
        name.setText(m.getName());
        phone.setText(m.getPhone());
        cellphone.setText(m.getCellphone());
        birthdate.setText(m.getBirthdate().substring(0,10));
        addr.setText(m.getAddr());
        email.setText(m.getEmail());


    }




}
