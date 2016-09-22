package teamproject.taekung.controller;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * Created by taeku on 2016-09-21.
 */
public class Indexfxml {
    @javafx.fxml.FXML
     TableView showMember,showBook,showRent;


    @javafx.fxml.FXML
     TextField memberText, bookText,rentText;


    @javafx.fxml.FXML
     BorderPane main;

    @javafx.fxml.FXML
     TableColumn mno,name,phone,cellphone,birthdate,email,addr,ISBN,bnameTC,authorTC,
            publisherTC,bimgTC,rno,bnoRTC,mnoRTC,regdateTC,duedateTC,rBnoTC,rNameTC;


    @javafx.fxml.FXML
     Label nameLB,phoneLB,cellphoneLB,addrLB,emailLB,birthdateLB,eLabel,bdLabel,nLabel,pLabel,cpLabel,aLabel,dLabel,ddLB;


    @javafx.fxml.FXML
     MenuItem selectName, selectPhone, bookname, author,bnumber,bnoR,mnoR,regdateR,duedateR;
    @javafx.fxml.FXML
     SplitMenuButton memberSelect,bookselect,selectrent;

}
