package teamproject.taekung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import teamproject.taekung.VO.UserVO;
import teamproject.taekung.dao.FindIDDao;

import java.util.List;

/**
 * Created by java on 2016-09-12.
 */
public class FindIDController {

    @FXML
    TextField email;
    @FXML TextField phone;
    @FXML
    Label id;
    @FXML
    TextField id1;
    @FXML TextField email1;
    @FXML TextField phone1;
    @FXML Label pwd;


    public void findID(ActionEvent event) {
        List list = FindIDDao.selectID(email.getText(),phone.getText());


        if(email.getText().equals("")){id.setText("이메일을 입력해주세요");}
        else if(phone.getText().equals("")){id.setText("전화번호를 입력해주세요");}
        else if(list.size()!=0){
            UserVO user = (UserVO)list.get(0);
            id.setText(user.getId());
        } else if(list.size()==0)id.setText("해당하는 아이디가 없습니다 ");


    }

    public void findPWD(ActionEvent event) {
        List list = FindIDDao.selectPWD(id1.getText(),email1.getText(),phone1.getText());

        if(id1.getText().equals("")){pwd.setText("아이디를 입력해주세요");}
        else if(email1.getText().equals("")){pwd.setText("이메일을 입력해주세요");}
        else if(phone1.getText().equals("")){pwd.setText("전화번호를 입력해주세요");}
        else if(list.size()!=0){
            UserVO user = (UserVO)list.get(0);
            pwd.setText(user.getPwd());
        } else if(list.size()==0)pwd.setText("정보확인후 재입력하세요");
    }
}
