package teamproject.taekung.dao;

import teamproject.taekung.model.MemberModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-17.
 */
public class FindMemberDAO extends InterfaceDAO{

    private static String selectMember = "select mno,name,phone,cellphone,birthdate,addr,email from member order by mno DESC ";
    private static String selectMemberForName = "select mno,name,phone,cellphone,birthdate,addr,email from member " +
            " where name = ? order by mno desc";
    private static String selectMemberForPhone = "select mno,name,phone,cellphone,birthdate,addr,email from member " +
            " where phone = ? order by mno desc";

    public static Connection openConn(){
        Connection conn = null;

        try {
            Class.forName(DRV);
            conn= DriverManager.getConnection(URL, USR, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public static void closeConn(Connection c, PreparedStatement p, ResultSet r){
        if(r !=null) try{r.close(); r=null;} catch (Exception ex){}
        if(p !=null) try{p.close(); p=null;} catch (Exception ex){}
        if(c !=null) try{c.close(); c=null;} catch (Exception ex){}
    }



    public static List<MemberModel> listMember(){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<MemberModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectMember);

            rs=pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(
                rs.getInt("mno"),rs.getString("name"),
                rs.getString("phone"),rs.getString("cellphone"),
                rs.getString("birthdate").substring(0,10),rs.getString("email"),rs.getString("addr"));

                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             closeConn(conn,pstmt,rs);
        }

        return result;
    }




    public static List<MemberModel> listMemberForName(String s){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<MemberModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectMemberForName);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(
                        rs.getInt("mno"),rs.getString("name"),
                        rs.getString("phone"),rs.getString("cellphone"),
                        rs.getString("birthdate").substring(0,10),rs.getString("email"),rs.getString("addr"));

                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }


    public static List<MemberModel> listMemberForPhone(String s){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<MemberModel> result = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectMemberForPhone);
            pstmt.setString(1,s);

            rs=pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(
                        rs.getInt("mno"),rs.getString("name"),
                        rs.getString("phone"),rs.getString("cellphone"),
                        rs.getString("birthdate").substring(0,10),rs.getString("email"),rs.getString("addr"));

                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }


}
