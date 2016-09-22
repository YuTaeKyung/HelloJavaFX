package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-16.
 */
public class MainDao extends InterfaceDAO{
    private static String login = "select id ,pwd from manager where id = ? and pwd = ?";

    public static List<UserVO> setLogin (String mid, String pwd){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO user = null;
        List<UserVO> result = new ArrayList<>();

        conn=openConn();
        try {
            pstmt=conn.prepareStatement(login);
            pstmt.setString(1,mid);
            pstmt.setString(2,pwd);
            rs=pstmt.executeQuery();

            while (rs.next()){
                user =new UserVO(rs.getString("id"),rs.getString("pwd"),"","","","");
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,rs);
        }
        return result;
    }






}
