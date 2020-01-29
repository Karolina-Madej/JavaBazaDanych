package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {

    public static Connection Connector(){

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/karolinamadej/Desktop/PWR/Semestr6/JAVA/BazyDanych/Bazadanych/Baza1.db");
            return conn;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
