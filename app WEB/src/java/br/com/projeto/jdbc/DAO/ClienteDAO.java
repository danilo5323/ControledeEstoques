/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.jdbc.Beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class ClienteDAO {
    
    
    public ClienteDAO() throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
    }
    private Connection connection;
    
    private String insertStatement = "INSERT INTO public.cliente(\n" 
            + "cli_telefone, "
            + "cli_cpf, "
            + "cli_ie, "
            + "cli_cnpj, "
            + "id_endereco,"
            + "cli_nome_princ,"
            + "cli_nome_sec) \n" 
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    
    private String updateStatement = "UPDATE public.cliente\n" 
            +"   SET "
            + "cli_telefone=?, "
            + "cli_cpf=?, "
            + "cli_ie=?, "
            + "cli_cnpj=?, "
            + "id_endereco = ?,"
            + "cli_nome_princ = ?,"
            + "cli_nome_sec = ? \n" 
            + " WHERE ";
    
    private String selectStatement = "SELECT "
            + "id_cliente, "
            + "cli_telefone, "
            + "cli_cpf, "
            + "cli_ie, "
            + "cli_cnpj, "
            + "id_endereco,"
            + "cli_nome_princ,"
            + "cli_nome_sec \n" 
            + "  FROM public.cliente "
            + "where";
    
    private String deleteStatement = "DELETE FROM public.cliente\n" 
            + " WHERE ";
    
    /**
     * Metodo para cadastrar novos clientes
     */
    
    public void cadastrarCliente(Cliente input){
        try {
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(insertStatement);
            // executa
                  
            stmt.setLong(1, input.getTelefone());
            stmt.setLong(2, input.getCpf());
            stmt.setLong(3, input.getInscricao_estadual());
            stmt.setLong(4, input.getCnpj());
            stmt.setLong (5, input.getEndereco().getCep());
            stmt.setString (6, input.getNomePrinc());
            stmt.setString (7, input.getNomeSec());
         
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    
    
    
    
    /**
     * consulta cliente listado no banco de dados usando o CPF
     * @param CPF
     * @return 
     */
    public Cliente consultaCliente(String CPF){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + "where cli_cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setString (1, CPF);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next())
                return configuraCliente(resultSet);
            
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return new Cliente();
    }

    
    
    public Cliente consultaClienteTelNome(String TEL, String nomePrincipal){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + "where cli_telefone = ? "
                    + "and cli_nome_princ = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setString (1, TEL);
            stmt.setString(2, nomePrincipal);
            
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery();
            
                if(resultSet.next())
                return (configuraCliente(resultSet));
            
            stmt.close();
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }
    
    public List<Cliente> consultaClienteTel(String TEL){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + " cli_telefone = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setLong (1, Long.parseLong(TEL));
           
            ResultSet resultSet = stmt.executeQuery();
            
            List<Cliente> result = new ArrayList<Cliente>();
            
            while(resultSet.next())
                result.add(configuraCliente(resultSet));
            
            stmt.close();
            
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }
    
    
    
    public Cliente consultaClientebyID(String ID){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + " id_cliente = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setLong (1, Long.parseLong(ID));
           
            ResultSet resultSet = stmt.executeQuery();
            
            Cliente result = new Cliente();
            
            if(resultSet.next())
                result = configuraCliente(resultSet);
            
            stmt.close();
            
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }

        
        
        
        
    public Cliente configuraCliente(ResultSet input) throws SQLException{
         return new Cliente(
                 input.getLong(1),
                 input.getLong(2),
                 input.getLong(3),
                 input.getLong(4),
                 input.getLong(5),
                 input.getString(7),
                 input.getString(8)
         );

    }
    /**
     * Metodo para alterar clientes
     */
    public void alteraCliente(Cliente clienteOldest, Cliente clienteNew){
        try {
            //Preparando a conexão
            String tmpupdateStatement = updateStatement  
                     + "cli_telefone=? and "
                     + "cli_cpf=? and "
                     + "cli_ie=? and "
                     + "cli_cnpj=? and "
                     + "id_endereco = ? and"
                     + "cli_nome_princ = ? and"
                     + "cli_nome_sec = ? ;\n" ;
            
            PreparedStatement stmt = connection.prepareStatement(tmpupdateStatement);
            // executa
            
            
            stmt.setLong   (1, clienteNew.getTelefone());
            stmt.setLong   (2, clienteNew.getCpf());
            stmt.setLong   (3, clienteNew.getInscricao_estadual());
            stmt.setLong   (4, clienteNew.getCnpj());
            stmt.setLong    (5, clienteNew.getEndereco().getEnderecoID());
            stmt.setString (6, clienteNew.getNomePrinc());
            stmt.setString (7, clienteNew.getNomeSec());
            
            stmt.setLong    (8, clienteOldest.getTelefone());
            stmt.setLong   (9, clienteOldest.getCpf());
            stmt.setLong   (10, clienteOldest.getInscricao_estadual());
            stmt.setLong   (11, clienteOldest.getCnpj());        
            stmt.setLong    (12, clienteOldest.getEndereco().getEnderecoID());
            stmt.setString (13, clienteOldest.getNomePrinc());
            stmt.setString (13, clienteOldest.getNomeSec());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    
    /**
     * Altera o cliente por um novo utilizando o ID 
     * @param id
     * @param clienteNew 
     */
    
    public void alteraClienteByID(Long id, Cliente clienteNew){
        try {
            //Preparando a conexão
            String tmpupdateStatement = updateStatement  
                     + " id_cliente =? ;\n" ;
            
            PreparedStatement stmt = connection.prepareStatement(tmpupdateStatement);
            // executa
            
            
            stmt.setLong   (1, clienteNew.getTelefone());
            stmt.setLong   (2, clienteNew.getCpf());
            stmt.setLong   (3, clienteNew.getInscricao_estadual());
            stmt.setLong   (4, clienteNew.getCnpj());
            stmt.setLong    (5, clienteNew.getEndereco().getEnderecoID());
            stmt.setString (6, clienteNew.getNomePrinc());
            stmt.setString (7, clienteNew.getNomeSec());
            
            stmt.setLong    (8, id);
            
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    /**
     * Metodo para excluir clientes
     */
    public void excluirCliente(Cliente cliente){
         try {
            String tmpDeleteStatement = deleteStatement
                     + "cli_telefone=? and "
                     + "cli_nome_princ = ? and "
                     + "cli_nome_sec = ? ;\n" ;;
                    
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(tmpDeleteStatement);
            // executa
            stmt.setLong    (1, cliente.getTelefone());
            stmt.setString (2, cliente.getNomePrinc());
            stmt.setString (3, cliente.getNomeSec());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    public void excluirClienteByID(String ID){
         try {
            String tmpDeleteStatement = deleteStatement
                     + " id_cliente = ? ;";
                    
                    
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(tmpDeleteStatement);
            // executa
            stmt.setLong    (1, Long.parseLong(ID));
           
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
}
