/*   FALTA TESTAR!!!!!
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.jdbc.Beans.Cliente;
import br.com.projeto.jdbc.Beans.Fornecedor;
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
public class FornecedorDAO {
    
    public FornecedorDAO() throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    private Connection connection;
    
    private String insertStatement = "INSERT INTO public.fornecedor(\n" 
            + "forn_nome_empresa, "
            + "forn_nome_fantasia, "
            + "forn_cnpj, " 
            + "forn_ie, "
            + "forn_email, "
            + "forn_tel)" 
            + "  VALUES (?, ?, ?, ?, ?, ?);";
    
    private String updateStatement = "UPDATE public.fornecedor\n" 
            + " SET "
            + "forn_nome_empresa=?, "
            + "forn_nome_fantasia=?, "
            + "forn_cnpj=?, "
            + "forn_ie=?, "
            + "forn_email=?, "
            + "forn_tel=? "
         //   + "id_endereco=? " 
            + " WHERE ";
    
    private String selectStatement = "SELECT "
            + "id_fornecedor, "
            + "forn_nome_empresa, "
            + "forn_nome_fantasia, "
            + "forn_email, "
            + "forn_cnpj, " 
            + "forn_ie, "
            + "forn_tel, "
            + "id_endereco\n"  
            + "  FROM public.fornecedor ";
    
    private String deleteStatement = "DELETE FROM public.fornecedor\n" +
                                    " WHERE ";
    
    public void cadastraFornecedor(Fornecedor input){
        try {
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(insertStatement);
            // executa
            stmt.setString(1, input.getForn_nome_empresa());
            stmt.setString(2, input.getForn_nome_fantasia());
            stmt.setLong(3, input.getForn_cnpj());
            stmt.setLong(4, input.getForn_ie());
            stmt.setString (5, input.getForn_email());
            stmt.setLong (6, input.getForn_tel());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    /**
     * returna um único fornecedor para o telefone aplicado
     * @param tel
     * @return 
     */
    public Fornecedor consultaFornecedor(String tel){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + " where forn_tel = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setString (1, tel);
   
            ResultSet resultSet = (stmt.executeQuery());
   
            
            if(resultSet.next())
                return configuraFornecedor(resultSet);
           // stmt.close();
           
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return new Fornecedor();
    }
    
    
    public Fornecedor consultaFornecedorByID(String ID){
        try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + "where id_fornecedor = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setLong (1, Long.parseLong(ID));
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next())
                return configuraFornecedor(resultSet);
            stmt.close();
           
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return new Fornecedor();
    }
    
    /**
     * retorna uma array de fornecedores
     * @param tel
     * @return 
     */
    public List<Fornecedor> consultaFornecedorLista(String tel){
       try {
            //Preparando a conexão
            String tmpSelectStatement = selectStatement + " where forn_tel = ?";
            PreparedStatement stmt = connection.prepareStatement(tmpSelectStatement);
            // executa
            stmt.setString (1, tel);
   
            ResultSet resultSet = (stmt.executeQuery());
            
            List<Fornecedor> lista = new ArrayList<Fornecedor>();
            
            
            while(resultSet.next())
                 lista.add(configuraFornecedor(resultSet));
            
           return lista;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
            return new ArrayList<Fornecedor>();
    }
    
    
    
     public Fornecedor configuraFornecedor(ResultSet input) throws SQLException{

         return new Fornecedor(
            input.getLong(1),
            input.getString(2),
            input.getString(3),
            input.getString (4),
            input.getLong(5),
            input.getLong(6),
            input.getLong (7)
         );

    }
    
    public void excluiFornecedor(Fornecedor input){
        try {
            String tmpDeleteStatement = deleteStatement
                      + "id_fornecedor = ? and "
                      + "forn_nome_empresa = ? and  "
                      + "forn_nome_fantasia= ? and  "
                      + "forn_email= ? and  "
                      + "forn_cnpj= ? and  " 
                      + "forn_ie= ? and  "
                      + "forn_tel= ? and  "
                      + "id_endereco = ?\n" ;
                    
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(tmpDeleteStatement);
            // executa
            stmt.setLong(1, input.getId_fornecedor());
            stmt.setString(2, input.getForn_nome_empresa());
            stmt.setString(3, input.getForn_nome_fantasia());
            stmt.setString (4, input.getForn_email());
            stmt.setLong(5, input.getForn_cnpj());
            stmt.setLong(6, input.getForn_ie());
            stmt.setLong (7, input.getForn_tel());
            stmt.setLong (8, input.getForn_endereco().getEnderecoID());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    public void excluirFornecedorByID(Long id){
        try {
            String tmpDeleteStatement = deleteStatement
                      + " id_fornecedor = ?;\n" ;
                    
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(tmpDeleteStatement);
            // executa
            stmt.setLong(1, id);
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    
    public void alteraFornecedor(Fornecedor fornecedorOldest, Fornecedor fornecedorNew){
        try {
            //Preparando a conexão
            String tmpupdateStatement = updateStatement  
                      + "id_fornecedor = ? and "
                      + "forn_nome_empresa = ? and  "
                      + "forn_nome_fantasia= ? and  "
                      + "forn_email= ? and  "
                      + "forn_cnpj= ? and  " 
                      + "forn_ie= ? and  "
                      + "forn_tel= ? and  "
                      + "id_endereco = ?\n" ; ;
            
            PreparedStatement stmt = connection.prepareStatement(tmpupdateStatement);
            // executa
            stmt.setString(1, fornecedorNew.getForn_nome_empresa());
            stmt.setString(2, fornecedorNew.getForn_nome_fantasia());
            stmt.setLong(3, fornecedorNew.getForn_cnpj());
            stmt.setLong(4, fornecedorNew.getForn_ie());
            stmt.setString (5, fornecedorNew.getForn_email());
            stmt.setLong (6, fornecedorNew.getForn_tel());
            stmt.setLong (7, fornecedorNew.getForn_endereco().getEnderecoID());
            
              
            //cliente Anterior
            stmt.setLong(1, fornecedorOldest.getId_fornecedor());
            stmt.setString(2, fornecedorOldest.getForn_nome_empresa());
            stmt.setString(3, fornecedorOldest.getForn_nome_fantasia());
            stmt.setString (4, fornecedorOldest.getForn_email());
            stmt.setLong(5, fornecedorOldest.getForn_cnpj());
            stmt.setLong(6, fornecedorOldest.getForn_ie());
            stmt.setLong (7, fornecedorOldest.getForn_tel());
            stmt.setLong (8, fornecedorOldest.getForn_endereco().getEnderecoID());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
    
    
    
    public void alteraFornecedorByID(Long id, Fornecedor fornecedorNew){
        try {
            //Preparando a conexão
            String tmpupdateStatement = updateStatement  
                      + " id_fornecedor = ? ;\n" ; ;
            
            PreparedStatement stmt = connection.prepareStatement(tmpupdateStatement);
            // executa
            stmt.setString(1, fornecedorNew.getForn_nome_empresa());
            stmt.setString(2, fornecedorNew.getForn_nome_fantasia());
            stmt.setLong(3, fornecedorNew.getForn_cnpj());
            stmt.setLong(4, fornecedorNew.getForn_ie());
            stmt.setString (5, fornecedorNew.getForn_email());
            stmt.setLong (6, fornecedorNew.getForn_tel());
            //stmt.setLong (7, fornecedorNew.getForn_endereco().getEnderecoID());
            
            stmt.setLong(7, id);
           
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
}

/**
 *  private Long      id_fornecedor;
    private String   forn_nome_empresa,
                     forn_nome_fantasia,
                     forn_email;
    private Long     forn_cnpj,
                     forn_ie,
                     forn_tel;
    private Endereco forn_endereco;
 */