package teamproject.taekung.dao;

import teamproject.taekung.model.RentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-20.
 */
public class FindRentDAO extends InterfaceDAO{

    private static String selectRent = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) order by rno DESC ";
    private static String selectRentForBno = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) " +
            " where bno = ? order by rno desc";
    private static String selectRentForMno = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) " +
            " where mno = ? order by rno desc";
    private static String selectRentForRegdate = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) " +
            " where regdate = ? order by rno desc";
    private static String selectRentForDuedate = "select rno, mno, bno,regdate,(regdate+7) as duedate, name ,bname from rent join member USING (mno) join book USING (bno) " +
            " where (regdate+7)  = ? order by rno desc";

    public static List<RentModel> listRent() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<RentModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectRent);

            rs=pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(
                        rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),
                        rs.getString("regdate").substring(0,10),rs.getString("duedate").substring(0, 10),rs.getString("name"),rs.getString("bname")
                        );

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    public static List<RentModel> listRentForBno(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<RentModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectRentForBno);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(
                        rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),rs.getString("regdate").substring(0, 10),
                        rs.getString("duedate").substring(0, 10),rs.getString("name"),rs.getString("bname")
                );

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    public static List<RentModel> listrentForMno(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<RentModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectRentForMno);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(
                        rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),
                        rs.getString("regdate").substring(0, 10),rs.getString("duedate").substring(0, 10),rs.getString("name"),rs.getString("bname")
                );

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    public static List<RentModel> listRentForRegdate(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<RentModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectRentForRegdate);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(
                        rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),rs.getString("regdate").substring(0, 10),
                        rs.getString("duedate").substring(0, 10),rs.getString("name"),rs.getString("bname")
                );

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    public static List<RentModel> listRentForDuedate(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<RentModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectRentForDuedate);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                RentModel r = new RentModel(
                        rs.getInt("rno"),rs.getInt("mno"),rs.getString("bno"),rs.getString("regdate").substring(0, 10),
                        rs.getString("duedate").substring(0,10),rs.getString("name"),rs.getString("bname")
                );

                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }
}
