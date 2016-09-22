package teamproject.taekung.dao;

import teamproject.taekung.model.BookModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-20.
 */
public class FindBookDAO extends InterfaceDAO{

    private static String selectBook = "select bno,bname,author, publisher,bimg from book order by bno DESC ";
    private static String selectBookForBname = "select bno,bname,author, publisher, BIMG from book " +
            " where bname = ? order by bno desc";
    private static String selectBookForAuthor = "select bno,bname,author, publisher,bimg from book " +
            " where author = ? order by bno desc";
    private static String selectBookForBno = "select bno,bname,author, publisher,bimg from book " +
            " where bno = ? order by bno desc";

    public static List<BookModel> listBookForBname(String s) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectBookForBname);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(
                        rs.getString("bno"),rs.getString("bname"),"",rs.getString("author"),
                        rs.getString("publisher"),""
                        );

                result.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;

    }

    public static List<BookModel> listBookForAuthor(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectBookForAuthor);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(
                        rs.getString("bno"),rs.getString("bname"),"",rs.getString("author"),
                        rs.getString("publisher"),rs.getString("bimg")
                );

                result.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    public static List<BookModel> listBook() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectBook);

            rs=pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(
                        rs.getString("bno"),rs.getString("bname"),"",rs.getString("author"),
                        rs.getString("publisher"),rs.getString("bimg"));

                result.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;

    }

    public static List<BookModel> listBookForBno(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectBookForBno);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(
                        rs.getString("bno"),rs.getString("bname"),"",rs.getString("author"),
                        rs.getString("publisher"),rs.getString("bimg")
                );

                result.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;

    }
}
