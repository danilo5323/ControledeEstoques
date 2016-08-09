/*  AINDA NÃO FOI TESTADA!!!!!!!!!!!!!!!!
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.jdbc.Beans.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class EnderecoDAO {
    
    /**
     * Cadastra o endereço do cliente no banco de dados.
     * @param input 
     */
    
    private String insertStatement = "INSERT INTO public.endereco(\n" 
            + "cep, "
            + "logradouro, "
            + "numero, "
            + "bairro, "
            + "cidade, "
            + "estado, " 
            + "\"país\")" 
            + "complemento = ?"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private String updateStatement = "UPDATE  public.endereco\n" 
            + " SET cep=?, "
            + "logradouro=?, "
            + "numero=?, "
            + "bairro=?, "
            + "cidade=?, " 
            + "estado=?, "
            + "\"país\"=?" 
            + "complemento = ?"
            + " WHERE ";
    
    private String selectStatement = "SELECT "
            + "cep, "
            + "logradouro, "
            + "numero, "
            + "bairro, "
            + "cidade, "
            + "estado, "
            + "\"país\"\n"
            + "complemento = ?"
            + "FROM public.endereco";
    
    private String deleteStatement = "DELETE FROM public.endereco\n where " ;
    
    
    public EnderecoDAO() throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    private Connection connection;
    
    
    /**
     * metodo para cadastrar endereco
     * @param input 
     */
    public void cadastraEndereco(Endereco input){
        
        try {
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(insertStatement);
            // executa
            stmt.setLong   (1, input.getCep());
            stmt.setString(2, input.getLogradouro());
            stmt.setString(3, input.getNumero());
            stmt.setString(4, input.getBairro());
            stmt.setString(5, input.getCidade());
            stmt.setString(6, input.getEstado());
            stmt.setString(7, input.getPais());
            stmt.setString(8, input.getPais());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
    }
    
    /**
     * metodo para consultar enderecos
     * @param input
     * @return 
     */
    public Endereco consultaEndereco(Endereco input){
        Endereco endereco;
        try {
            //Preparando a conexão
            
            // executa
            String whereStatement = selectStatement 
                    + " where "
                    + " cep= ? and"
                    + "logradouro= ?  and"
                    + "numero= ? and"
                    + "bairro= ? and"
                    + "cidade= ? and" 
                    + "estado= ? and"
                    + "\"país\"= ? ;" 
                    + "complemento = ?";
            
            PreparedStatement stmt = connection.prepareStatement(whereStatement);
            
            stmt.setLong   (1, input.getCep());
            stmt.setString(2, input.getLogradouro());
            stmt.setString(3, input.getNumero());
            stmt.setString(4, input.getBairro());
            stmt.setString(5, input.getCidade());
            stmt.setString(6, input.getEstado());
            stmt.setString(7, input.getPais());
            stmt.setString(8, input.getComplemento());
            
            
            stmt.close();
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next())
                return configuraEndereco(resultSet);
            

            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
        
        return new Endereco();
    }
    
    
    
    
    public Endereco consultaEndereco(String inputCEP){
        Endereco endereco;
        try {
            //Preparando a conexão
            
            // executa
            String whereStatement = selectStatement 
                    + " where "
                    + " cep= ? ;" ;
            
            PreparedStatement stmt = connection.prepareStatement(whereStatement);
            stmt.setString   (1, inputCEP);
            stmt.close();
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next())
                return configuraEndereco(resultSet);
            

            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
        
        return new Endereco();
    }
    
    /**
     * configura o endereco com os dados do ResultSet
     * @param input
     * @return
     * @throws SQLException 
     */
    public Endereco configuraEndereco(ResultSet input) throws SQLException{
    
        return new Endereco(
                input.getLong(1),
                input.getString(2),
                input.getString(3),
                input.getString(4),
                input.getString(5),
                input.getString(6),
                input.getString(7),
                input.getString(8)
        );
    }
    
    public void excluiEndereco(Endereco input){
        
        try {
            String tmpDeleteStatement = deleteStatement
                    + " cep= ? and"
                    + "logradouro= ?  and"
                    + "numero= ? and"
                    + "bairro= ? and"
                    + "cidade= ? and" 
                    + "estado= ? and"
                    + "\"país\"= ? ;" 
                    + "complemento = ?";
                    
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(tmpDeleteStatement);
            // executa
            stmt.setLong   (1, input.getCep());
            stmt.setString(2, input.getLogradouro());
            stmt.setString(3, input.getNumero());
            stmt.setString(4, input.getBairro());
            stmt.setString(5, input.getCidade());
            stmt.setString(6, input.getEstado());
            stmt.setString(7, input.getPais());
            stmt.setString(8, input.getPais());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
    }
    
    public void alteraEndereco(Endereco inputOldest, Endereco inputNew){
        try {
            //Preparando a conexão
            String tmpupdateStatement = updateStatement + 
                    " where "
                    + " cep= ? and"
                    + "logradouro= ?  and"
                    + "numero= ? and"
                    + "bairro= ? and"
                    + "cidade= ? and" 
                    + "estado= ? and"
                    + "\"país\"= ? ;" 
                    + "complemento = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpupdateStatement);
            // executa
            stmt.setLong   (1, inputNew.getCep());
            stmt.setString(2, inputNew.getLogradouro());
            stmt.setString(3, inputNew.getNumero());
            stmt.setString(4, inputNew.getBairro());
            stmt.setString(5, inputNew.getCidade());
            stmt.setString(6, inputNew.getEstado());
            stmt.setString(7, inputNew.getPais());
            stmt.setString(8, inputNew.getPais());
            stmt.setLong   (9, inputOldest.getCep());
            stmt.setString(10, inputOldest.getLogradouro());
            stmt.setString(11, inputOldest.getNumero());
            stmt.setString(12, inputOldest.getBairro());
            stmt.setString(13, inputOldest.getCidade());
            stmt.setString(14, inputOldest.getEstado());
            stmt.setString(15, inputOldest.getPais());
            stmt.setString(16, inputOldest.getPais());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
}
