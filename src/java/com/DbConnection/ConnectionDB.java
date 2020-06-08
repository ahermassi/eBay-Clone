/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DbConnection;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wissal
 */
public class ConnectionDB {
   
//    private Connection connection=null;
//    protected PreparedStatement ps=null;
//    protected ResultSet rs= null;
    
    
    public Connection connect() throws SQLException
    {
         final String url = "jdbc:mysql://localhost:3306/vente_electronique?zeroDateTimeBehavior=convertToNull";
     final String user = "root";
     final String pass="";
     try{
        DriverManager.registerDriver(new Driver());
        Connection connection = (Connection) DriverManager.getConnection(url, user, pass);
        return connection;
     }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
//    public void disconnect() throws SQLException
//    {   try{
//        connection.close();
//        ps.close();
//        rs.close();
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
    
}
