import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection con = null;
        String db = "jdbc:hsqldb:mem:.";
        String user = "SA";
        String password = "";

        con = DriverManager.getConnection(db, user, password);
        Statement stat = con.createStatement();
        ResultSet rs =  stat.executeQuery("SELECT * FROM \"PUBLIC\".\"TEST\"");
        while(rs.next()) {
            System.out.println("Customer Name: " + rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME"));
        }
        rs.close();
        stat.close();
        con.close();





    }



}

