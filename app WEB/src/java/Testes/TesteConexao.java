/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import br.com.projeto.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class TesteConexao {

  public static void main(String[] args) throws ClassNotFoundException{
      
          
          try (Connection con = new ConnectionFactory().getConnection()) {
              PreparedStatement stmt = con.prepareStatement("select * from produtos");
              stmt.execute();
              stmt.close();
              System.out.println("Gravado!");
          
          
          JOptionPane.showMessageDialog(null, "inserido com sucesso");
       } catch (SQLException e) {
          e.printStackTrace();
       }
      
   }

      
 }