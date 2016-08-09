/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.jdbc.Beans.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe destinada para cadatro dos produtos únicos 
 * que serao comercializados
 * @author Danilo
 */

public class ProdutoDAO {
    

   private Connection connection;
   
   public ProdutoDAO() throws ClassNotFoundException{
       this.connection = new ConnectionFactory().getConnection();
   }
   
   private String selectStatement =  
                     "SELECT "
                    + "id_produto, "
                    + "prd_nome_padrao, "
                    + "prd_nome_qualificador, "
                    + "prd_unidade_medida, \n" 
                    + "prd_cod_barras,"
                    + "prd_marca"
                    + " from public.produto ";
   
   private String insertStatement =  
                    "INSERT INTO public.produto(\n" 
                    + "prd_nome_padrao, "
                    + "prd_nome_qualificador, "
                    + "prd_unidade_medida, \n" 
                    + "prd_cod_barras, "
                    + "prd_marca)\n" 
                    + "    VALUES (?, ?, ?, ?, ?);" ;
   
   String deleteStatement = 
                    "delete from public.produto "
                    + "WHERE ";
   
   String updateStatement = 
                    "UPDATE public.produto\n" +
                    "   SET prd_nome_padrao= ?, "
           + "              prd_nome_qualificador= ?,"
           + "              prd_unidade_medida= ?, \n" +
                    "       prd_cod_barras= ?,"
                         + "prd_marca = ? \n" +
                    " WHERE ";
            
    /**
     * Função para cadastrar o produto
     * @param produto 
     */
     public void cadastraProduto(Produto produto){
        try {
            //Preparando a conexão
            PreparedStatement stmt = connection.prepareStatement(insertStatement);
            // executa
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getNome_qualificador());
            stmt.setString(3, produto.getUnidade_medida());
            stmt.setString(4, produto.getCod_barras());
            stmt.setString(5, produto.getMarca());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
    }
   
     /**
      * O produto deve ser selecionado apenas pelo código de barras
      * @param cod_barras
      * @return 
      */
    public Produto selecionaProduto(String cod_barras){
        Produto produto = new Produto();
        
        try {
            String SQLQuery = selectStatement
                    + "WHERE prd_cod_barras = ? limit 1";
            
            //Prepara o envio
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);
            
            //setando as datas de busca
            stmt.setString(1, cod_barras);
            
            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Produto tmp = configuraProduto(resultSet);
                return tmp;
               
            }
            stmt.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return null;
    }
    
     public List<Produto> consultaProdutoLista(String nome){
        Produto produto = new Produto();
        
        try {
            String SQLQuery = selectStatement
                    + "WHERE prd_nome_padrao = ? ";
            
            //Prepara o envio
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);
            
            //setando as datas de busca
            stmt.setString(1, nome);
            
            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();
            
            List<Produto> lista = new ArrayList<Produto>();
            while(resultSet.next()){
                
                lista.add (configuraProduto(resultSet));
               
            }
            stmt.close();
            return lista;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return null;
    }
    
    
    public Produto consultaProdutoByID(Long id){
        Produto produto = new Produto();
        
        try {
            String SQLQuery = selectStatement
                    + "WHERE id_produto = ? ";
            
            //Prepara o envio
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);
            
            //setando as datas de busca
            stmt.setLong(1, id);
            
            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                
                return configuraProduto(resultSet);
               
            }
            stmt.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return null;
    }
    
    
    /**
     * Configura o produto a ser retornado, tendo como parâmetro o ResultSet do SQL
     * @param resultSet
     * @return Produto manipulavel
     */
    private Produto configuraProduto(ResultSet resultSet){
        try {
            Long produto_id = Long.parseLong(resultSet.getString(1)) ;
            return new Produto(  produto_id,
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6)
                      );
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }
   
    
   
    /**
     * Exclui um produto do banco de dados
     * @param produto
     * @return 
     */
    public boolean excluir(Produto produto){
        
        try {
            
            //prepara o envio
            PreparedStatement stmt = this.connection.prepareStatement(deleteStatement);
            
            //setando as datas de busca
            stmt.setString(1, produto.getCod_barras());

            //coletando os dados
            stmt.execute();
            
            stmt.close();
            return true;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
                throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return false;
    }
    
    
    public Boolean excluirProdutoByID(Long ID){
        
        try {
            
            deleteStatement += "  id_produto = ? ";
            //prepara o envio
            PreparedStatement stmt = this.connection.prepareStatement(deleteStatement);
            
            //setando as datas de busca
            stmt.setLong(1, ID);

            //coletando os dados
            stmt.execute();
            
            stmt.close();
            return true;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
                throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return false;
    }
    
    
    
    /**
     * Atualiza o produto no banco de dados. Entra como produto e deve possuir o mesmo código de barras
     * @param produto
     * @return 
     */
    public boolean atualizar(Produto produto){
        
        
         try {
         
                    
            
            PreparedStatement stmt = this.connection.prepareStatement(updateStatement);
            //asldfj
            //setando as datas de busca
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getNome_qualificador());
            stmt.setString(3, produto.getUnidade_medida());
            stmt.setString(4, produto.getCod_barras());
            stmt.setString(5, produto.getCod_barras());
            stmt.setString(6, produto.getMarca());
            //coletando os dados
            stmt.execute();
            
            stmt.close();
            
            return true;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return false;

    }
    
    public boolean alteraProdutoByID(Long id, Produto produto){
        
        
         try {
         
                    
            this.updateStatement+= " id_produto = ?; ";
            PreparedStatement stmt = this.connection.prepareStatement(updateStatement);
            
            //setando as datas de busca
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getNome_qualificador());
            stmt.setString(3, produto.getUnidade_medida());
            stmt.setString(4, produto.getCod_barras());
            stmt.setString(5, produto.getMarca());
            stmt.setLong(6, id);
            //coletando os dados
            stmt.execute();
            
            stmt.close();
            
            return true;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
       return false;

    }
    
    
    /**
     * Nunca será usada, mas deixa aqui
     * @param dataInicial
     * @param dataFinal
     * @return 
     
    public List<Produto> consultarListaBetweenDataCompra(Date dataInicial, Date dataFinal){
        List<Produto> lista = new ArrayList<Produto>();
        try {
            String SQLQuery = selectStatement
                    + " WHERE prd_data_compra between ? and ?";
            this.connection  = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);
            
            //setando as datas de busca
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);
            
            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Long produto_id = Long.parseInt(resultSet.getString(1)) ;
                
                lista.add(configuraProduto(resultSet));
                
            }
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
        return lista;
    }
     */
    /**
     * Nunca será utilizado
     * @param dataInicial
     * @param dataFinal
     * @return 
    
    
    public List<Produto> consultarListaBetweenDataVenda(Date dataInicial, Date dataFinal){
        List<Produto> lista = new ArrayList<Produto>();
        try {
            String SQLQuery = "SELECT id_produto, prd_nome_padrao, prd_nome_qualificador, prd_unidade_medida, prd_medida, \n" +
                                "     prd_data_compra, prd_preco_compra, prd_preco_venda,  \n" +
                                "      prd_data_validade"
                    + " from public.produto WHERE prd_data_compra between ? and ?";
            this.connection  = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);
            
            //setando as datas de busca
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);
            
            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Long produto_id = Long.parseInt(resultSet.getString(1)) ;
                
                lista.add(new Produto(  produto_id,
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getFloat(5),
                                        resultSet.getDate(6),
                                        resultSet.getDouble(7),
                                        resultSet.getDouble(8),
                                        resultSet.getDate(9)
                                      )
                          );
                
            }
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        
        return lista;
    }
    *  */
    
    
        
}   
  

