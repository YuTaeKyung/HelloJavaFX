package teamproject.taekung.dao;

import teamproject.taekung.controller.SqlExceptionAlert;
import teamproject.taekung.model.BookModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-19.
 */
public class BookDAO extends InterfaceDAO{

    private static String selectAll ="select * from book order by bno desc";
    private static String insertBook = "insert into book (bno,bname,genre,author,publisher,bimg) values (?,?,?,?,?,?)";
    private static String dropBook = "delete from book where bno = ? ";
    private static String updateBook = "update book set bno=?, bname=?, genre = ? , author = ?, publisher = ?, bimg = ? where" +
            " bno = ?";
    private static String showBookOne = "select * from book where bno = ?";

    public static List<BookModel> selectBookAll(){
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookModel> blist = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectAll);
            rs = pstmt.executeQuery();


            while (rs.next()){
                BookModel b = new BookModel(rs.getString("bno"),rs.getString("bname"),rs.getString("genre"),rs.getString("author"),
                        rs.getString("publisher"),rs.getString("bimg"));

                blist.add(b);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return blist;

    }


    public static void addBook(BookModel b){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertBook);
            pstmt.setString(1, b.getBno());
            pstmt.setString(2,b.getBname());
            pstmt.setString(3,b.getGenre());
            pstmt.setString(4,b.getAuthor());
            pstmt.setString(5,b.getPublisher());
            pstmt.setString(6,b.getBimg());

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

    public static void deleteMember(String s) {

        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(dropBook);

            pstmt.setString(1,s);

            try {
                pstmt.executeUpdate();
            }
            catch (SQLException e){
                SqlExceptionAlert.alert("해당 도서는 현재 대여중이라 삭제할수 없습니다");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }

    public static void updateBook(BookModel bm, String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(updateBook);
            pstmt.setString(1,bm.getBno());
            pstmt.setString(2,bm.getBname());
            pstmt.setString(3,bm.getGenre());
            pstmt.setString(4,bm.getAuthor());
            pstmt.setString(5,bm.getPublisher());
            pstmt.setString(6,bm.getBimg());
            pstmt.setString(7,s);

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

    public static List<BookModel> selectBook(String s, String a) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<BookModel> bl = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,a);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(rs.getString("bno"),rs.getString("bname"),rs.getString("genre"),
                        rs.getString("author"),rs.getString("publisher"),rs.getString("bimg"));
                bl.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return bl;
    }



    public static BookModel viewBook(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BookModel b = null;
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(showBookOne);
            pstmt.setString(1,s);

            rs = pstmt.executeQuery();

            while (rs.next()){
                b = new BookModel(rs.getString("bno"),rs.getString("bname"),rs.getString("genre"),
                        rs.getString("author"),rs.getString("publisher"),rs.getString("bimg"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return b;
    }
}