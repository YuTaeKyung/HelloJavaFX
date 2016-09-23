package teamproject.taekung.dao;

import java.sql.*;

/**
 * Created by kimhyeongjin on 2016. 9. 22..
 */
public class InterfaceDAO {


    static final String DRV = "oracle.jdbc.OracleDriver";
 /*   static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    static final String USR = "taekung";
    static final String PWD = "123456";*/



    static final String URL = "jdbc:oracle:thin:@//192.168.0.35/xe";
    static final String USR = "mkjh1113";
    static final String PWD = "MKJH1113";

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

}
