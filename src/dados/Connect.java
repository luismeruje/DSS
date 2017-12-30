/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Grupo34
 */
public class Connect {
    private static final String URL = "localhost:3306";
    private static final String SCHEMA = "gestorturnosdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://"+URL+"/"+SCHEMA+"?autoReconnect=true&useSSL=false&user="+USERNAME+"&password="+PASSWORD);
            return cn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            // nada para fechar
        }
    }
}