package teamproject.taekung.dao;

import teamproject.taekung.controller.SqlExceptionAlert;
import teamproject.taekung.model.MemberModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-17.
 */
public class MemberDAO extends InterfaceDAO{

    private static String insertMember = "insert into member (mno,name,phone,cellphone,birthdate,addr,email) " +
                                        " values (mno.nextval, ?,?,?,?,?,?)";
    private static String findMember = "select * from member ORDER BY mno DESC ";
    private static String showMemberOne = "select * from member where mno = ?";
    private static String modifyMember = "UPDATE member set name = ?, phone = ?, cellphone = ?, " +
            " birthdate = ?, addr = ? , email = ? where mno = ?";
    private static String dropMember = "delete from member where mno = ?";

    public static void addMember(MemberModel m){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertMember);
            pstmt.setString(1,m.getName());
            pstmt.setString(2,m.getPhone());
            pstmt.setString(3,m.getCellphone());
            pstmt.setString(4,m.getBirthdate());
            pstmt.setString(5,m.getAddr());
            pstmt.setString(6,m.getEmail());

            try {
                pstmt.executeUpdate();
            }
            catch (SQLException e){
                SqlExceptionAlert.alert("글자수초과");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }




    public static List<MemberModel> selectMemberAll() {

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<MemberModel> ml = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(findMember);

            rs = pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("cellphone"),rs.getString("birthdate").substring(0,10),rs.getString("addr"),rs.getString("email"));
                ml.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return ml;
    }




    public static MemberModel viewMember(String mno){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberModel m = null;
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(showMemberOne);
            pstmt.setString(1,mno);

            rs = pstmt.executeQuery();

            while (rs.next()){
                 m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),rs.getString("cellphone"),
                        rs.getString("birthdate").substring(0,10),rs.getString("addr"),rs.getString("email"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return m;
    }



    public static void updateMember(MemberModel m, String num) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt  =conn.prepareStatement(modifyMember);
            pstmt.setString(1,m.getName());
            pstmt.setString(2,m.getPhone());
            pstmt.setString(3,m.getCellphone());
            pstmt.setString(4,m.getBirthdate());
            pstmt.setString(5,m.getAddr());
            pstmt.setString(6,m.getEmail());
            pstmt.setString(7,num);

            try {
                pstmt.executeUpdate();
            }
            catch (SQLException e){
                SqlExceptionAlert.alert("글자수초과");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }


    }


    public static List<MemberModel> selectMember(String s, String a) {

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<MemberModel> ml = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,a);
            rs = pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("cellphone"),rs.getString("birthdate").substring(0,10),rs.getString("addr"),rs.getString("email"));
                ml.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return ml;
    }


    public static void deleteMember(String s){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(dropMember);
            pstmt.setString(1,s);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }
}
