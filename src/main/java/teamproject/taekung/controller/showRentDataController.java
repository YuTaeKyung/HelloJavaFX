package teamproject.taekung.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import teamproject.taekung.dao.RentDAO;
import teamproject.taekung.model.RentModel;

/**
 * Created by taeku on 2016-09-19.
 */
public class showRentDataController {

    @FXML Label rno;
    @FXML Label mno;
    @FXML Label bno;
    @FXML Label regdate;
    @FXML Label duedate;
    @FXML Label mname;
    @FXML Label bname;

    public void sendData(ObservableList<RentModel> rlist, int num) {
        RentModel rm = rlist.get(num);

        rm = RentDAO.selectOne(String.valueOf(rm.getRno()));

        rno.setText(String.valueOf(rm.getRno()));
        mno.setText(String.valueOf(rm.getMno()));
        bno.setText(rm.getBno());
        regdate.setText(rm.getRegdate().substring(0,10));
        duedate.setText(rm.getDuedate().substring(0,10));
        mname.setText(rm.getName());
        bname.setText(rm.getBname());
    }
}
