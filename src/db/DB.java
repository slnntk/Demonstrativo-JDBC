package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn;


    public static Connection getConnection(){
        if (conn == null){
            try {
                Properties ps = loadProperties();
                String dburl = ps.getProperty("dburl");
                conn = DriverManager.getConnection(dburl, ps);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties ps = new Properties();
            ps.load(fs);
            return ps;
        } catch (FileNotFoundException e) {
            throw new DbException("File not found");
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection(){
        if (conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
