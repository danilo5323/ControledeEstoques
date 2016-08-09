/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class ConnectionFactory {
    public ConnectionFactory(){}

    
     public Connection getConnection() throws ClassNotFoundException {
        
    //public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {  
                Class.forName("org.postgresql.Driver");  
                return DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/GerenciadordeEstoques",
                        
                        //"jdbc:postgresql://192.168.0.176:5432/GerenciadordeEstoques",
                        "postgres", 
                        "admin");
        }  
        catch (Exception e) {  
           System.out.println ("erro:" + e.getMessage());
        }
        finally{
            
        }
            return null; 
        
    
     }
   }
    

