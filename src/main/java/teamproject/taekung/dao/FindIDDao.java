package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-16.
 */
public class FindIDDao extends InterfaceDAO{

    private static String findid = "select id from manager where EMAIL = ? and phone = ?";
    private static String findpwd = "select pwd from manager where id = ? and email = ? and phone = ?";

    public static List<UserVO> selectID(String e, String p){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> list = new ArrayList<>();
        conn = openConn();

        try {
            pstmt = conn.prepareStatement(findid);
            pstmt.setString(1,e);
            pstmt.setString(2,p);

            rs = pstmt.executeQuery();

            while (rs.next()){
              UserVO  user = new UserVO(rs.getString("id"),"","","","","");

                list.add(user);

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return list;
    }

    public static List<UserVO> selectPWD(String i, String em, String pw){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> list = new ArrayList<>();
        conn = openConn();
        try {
            pstmt=conn.prepareStatement(findpwd);
            pstmt.setString(1,i);
            pstmt.setString(2,em);
            pstmt.setString(3,pw);
            rs=pstmt.executeQuery();

            while (rs.next()){
                UserVO user = new UserVO("",rs.getString("pwd"),"","","","");
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
