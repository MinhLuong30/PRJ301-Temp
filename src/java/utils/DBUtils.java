/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC
 */
public class DBUtils implements Serializable {
    public static Connection getConnection() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url =("jdbc:sqlserver://MIB\\MINHLUONG:58889;;databaseName=ProductManagement");
            Connection con = DriverManager.getConnection(url, "sa","12345");
            return con;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
