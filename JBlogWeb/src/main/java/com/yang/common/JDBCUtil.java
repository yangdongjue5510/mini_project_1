package com.yang.common;

import java.sql.*;

public class JDBCUtil {

    public static Connection getConnection()  {
        Connection conn = null;

        try{
            //JDBC 1단계 : 드라이버 로딩
            DriverManager.registerDriver(new org.h2.Driver());
            //JDBC 2단계 : Connection 연결(획득) (고속도로)
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt, Connection conn){
        //JDBC 5단계 : Connection 종료
        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        //JDBC 5단계 : Connection 종료
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
