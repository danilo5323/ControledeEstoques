/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.Beans.Administrador;
import br.com.projeto.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Danilo
 */
public class AdministradorDAO {

    public static Administrador validaLogin(String email, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Connection connection;
    
    public AdministradorDAO() throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
    }
     
    
    public Administrador ConsultaAdministrador(String email, String senha){
      String selectStatement = 
              "SELECT id_administrador, usuario, email, senha  FROM administrador where email = ? and senha = ?";
      
       try {
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(selectStatement);
            // executa
            stmt.setString (1, email);
            stmt.setString (2, senha);
            
            ResultSet resultSet = stmt.executeQuery();
            
            String n = "n";
            if(resultSet.next())
                return configuraFornecedor(resultSet);
            
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       
        return new Administrador(0l, "", "", "");
    }
    
    public Administrador CadastraAdministrador(String id, String senha){
    
        return new Administrador();
    }

    private Administrador configuraFornecedor(ResultSet input) throws SQLException {
         return new Administrador(
                 input.getLong(1),
                 input.getString(2),
                 input.getString(3),
                 input.getString(4)
         );

    }
    
}
