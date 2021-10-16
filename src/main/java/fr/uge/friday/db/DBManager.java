package fr.uge.friday.db;

import java.sql.*;

public class DBManager {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.hsqldb.jdbcDriver");
        Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:./lib/database", "Xhavit",  "");

        Statement statement = connexion.createStatement();

        /*statement.executeUpdate("CREATE TABLE test (colonne1 INT , colonne2 INT)");
        statement.executeUpdate("INSERT INTO test (colonne1, colonne2) VALUES (99, 00)");*/

        ResultSet resultat = statement.executeQuery("SELECT * FROM user");
        while(resultat.next()){
            System.out.println(resultat.getInt("id")) ;
            System.out.println(resultat.getString("name")) ;
        }

        //Statement statement = connexion.createStatement();
        statement.executeQuery("SHUTDOWN");
        statement.close();
        connexion.close() ;
    }
}

