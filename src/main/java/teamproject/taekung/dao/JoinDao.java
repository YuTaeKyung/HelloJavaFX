package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;
import teamproject.taekung.controller.SqlExceptionAlert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-14.
 */
public class JoinDao extends InterfaceDAO{

    private static String insertManager = "insert into manager (id, pwd, email, phone, address, storename)" +
                                        " VALUES (?,?,?,?,?,?)";
    private static String selectMID = "select id from manager where id =? ";

    public static boolean s = false;


    public static void joinManager(UserVO user){
        Connection conn = null;
        PreparedStatement pstmt =null;


        try {
            conn=openConn();
            pstmt = conn.prepareStatement(insertManager);

            pstmt.setString(1,user.getId());
            pstmt.setString(2,user.getPwd());
            pstmt.setString(3,user.getEmail());
            pstmt.setString(4,user.getPhone());
            pstmt.setString(5,user.getAddress());
            pstmt.setString(6,user.getStorename());

            try {
                pstmt.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
                SqlExceptionAlert.alert("글자수초과");
                s = true;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }


    public static List<UserVO> selectID(String mid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> result = new ArrayList<>();

        conn= openConn();
        try {
            pstmt=conn.prepareStatement(selectMID);
            pstmt.setString(1,mid);
            rs=pstmt.executeQuery();

            while (rs.next()){
                UserVO user = new UserVO(
                        rs.getString("id"),"","","","",""
                );
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConn(conn,pstmt,rs);
        }
        return result;
    }



}
