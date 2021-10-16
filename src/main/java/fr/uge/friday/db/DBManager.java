package fr.uge.friday.db;

import java.sql.*;

public class DBManager {

    public static void main(String[] args) throws SQLException {

        Connection con = null;
        String db = "jdbc:hsqldb:file:./lib/database";
        String user = "Test";
        String password = "";

        con = DriverManager.getConnection(db, user, password);
        Statement stat = con.createStatement();
        ResultSet rs =  stat.executeQuery("SELECT * FROM user");
        while(rs.next()) {
            System.out.println("Customer Name: " + rs.getInt("id") + " " + rs.getString("name"));
        }
        rs.close();
        stat.close();
        con.close();

    }
}
