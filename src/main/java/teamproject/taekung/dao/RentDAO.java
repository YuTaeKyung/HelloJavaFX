package teamproject.taekung.dao;

import teamproject.taekung.controller.SqlExceptionAlert;
import teamproject.taekung.model.RentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-19.
 */
public class RentDAO extends InterfaceDAO{

    private static String insertRent = "insert into rent (rno,mno,bno) VALUES (rno.nextval,?,?)";
    private static String selectAll = "select rno,mno,bno, regdate, (regdate+7) as duedate from rent order by rno desc";
    private static String updateRent = "update rent set rno=?, mno=?, bno = ? , regdate = ? where "  +
            "  rno = ?";
    private static String viewRent = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) where rno = ?";
    private static String dropRent = "delete from rent where rno = ?";


    public static List<RentModel> selectRentAll(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RentModel> rl = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectAll);
            rs = pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),
                        rs.getString("regdate").substring(0,10),rs.getString("duedate").substring(0,10),"","");

                rl.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return rl;
    }




    public static void addRent(RentModel r){
        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertRent);
            pstmt.setInt(1, r.getMno());
            pstmt.setString(2,r.getBno());

            try {
                pstmt.executeUpdate();
            }
            catch (SQLIntegrityConstraintViolationException ex){
                SqlExceptionAlert.alert("없는 회원 번호");
            }
            catch (SQLException e){

                SqlExceptionAlert.alert("글자수초과");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }


    public static void updateRent(RentModel rm, String s) {
        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(updateRent);

            pstmt.setInt(1,rm.getRno());
            pstmt.setInt(2,rm.getMno());
            pstmt.setString(3,rm.getBno());
            pstmt.setString(4,rm.getRegdate());
            pstmt.setString(5,s);

            try {
                pstmt.executeUpdate();
            }
            catch (SQLIntegrityConstraintViolationException ex){
                SqlExceptionAlert.alert("없는 회원 번호");
            }
            catch (SQLException e){
                SqlExceptionAlert.alert("글자수초과");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }

    public static RentModel selectOne(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RentModel r = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(viewRent);
            pstmt.setString(1,s);
            rs = pstmt.executeQuery();

            while (rs.next()){
                r = new RentModel(rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),rs.getString("regdate"),
                        rs.getString("duedate"),rs.getString("name"),rs.getString("bname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return r;
    }

    public static List<RentModel> selectNo(String f, String s) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<RentModel> rl = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(f);
            pstmt.setString(1,s);
            rs = pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),
                        rs.getString("regdate"),rs.getString("duedate"),"","");
                rl.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return rl;
    }



    public static void deleteRent(String s) {
        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(dropRent);

            pstmt.setString(1,s);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }
}