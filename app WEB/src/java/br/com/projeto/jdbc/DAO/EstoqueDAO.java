/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.DAO;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.jdbc.Beans.Produto;
import br.com.projeto.jdbc.Beans.ProdutoEstoque;
import java.sql.Connection;
import java.sql.Date;
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
public class EstoqueDAO {

    private Connection connection;

    //insere novos produtos no estoque, a data deve ser a data corrente
    public void insereProdutos(ProdutoEstoque produto, Long quantidade) {

        String SQLQuery = "INSERT INTO public.estoque("
                + "prd_data_compra, "
                + "prd_data_validade, "
                + "prd_preco_compra, "
                + "prd_preco_venda, "
                + "prd_medida, "
                + "prd_cod_barras)"
                + "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            //usar leftjoin ou criar uma view
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

           //arrumar o bug da data
            //setando as datas de busca
            stmt.setDate(1, produto.getData_compra());
            stmt.setDate(2, produto.getData_validade());
            stmt.setDouble(3, produto.getPreco_compra());
            stmt.setDouble(4, produto.getPreco_venda());
            stmt.setDouble(5, produto.getMedida());
            stmt.setString(6, produto.getProduto().getCod_barras());
            //coletando os dados

            for (int i = 0; i < quantidade; i++) {
                stmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ProdutoEstoque produtoEstoque = new ProdutoEstoque();
    }

    //deve retirar produto e setar a data de venda como a data atual
    public boolean retiraProduto(Produto produto, Long quantidade) {
        String SQLQuery = "update public.estoque "
                + "set prd_data_venda = current_date "
                + "WHERE "
                + " prd_cod_barras = ? AND "
                + " id_prd_estoque = (select id_prd_estoque from public.estoque where prd_cod_barras = ? and  prd_data_venda is null  limit (1) "
                + "                                             )";

        try {
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

            //setando as datas de busca
            stmt.setString(1, new String(produto.getCod_barras()));
            stmt.setString(2, new String(produto.getCod_barras()));
            //coletando os dados 
            for (int i = 0; i < quantidade; i++) {
                stmt.execute();
                //inserir codigo para verificar se existe estoque
            }
            return true;
        } catch (SQLException e) {
            return false;
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return false;
    }

    public List<String> listarProdutosComprados() {
        List<String> lista = new ArrayList<String>();
        try {
            //usar leftjoin ou criar uma view
            String SQLQueryVendidos = "SELECT count(1), prd.prd_nome_padrao || ' ' || prd.prd_nome_qualificador, (est.prd_preco_venda), (est.prd_preco_compra), prd.prd_cod_barras\n" +
"	FROM public.estoque est inner join public.produto prd on prd.prd_cod_barras = est.prd_cod_barras\n" +
"	and est.prd_data_venda is null\n" +
"	group by prd.prd_cod_barras, prd.prd_nome_padrao || ' ' || prd.prd_nome_qualificador, (est.prd_preco_venda), (est.prd_preco_compra);\n" +
"";
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQueryVendidos);
            //setando as datas de busca            //coletando os dados
            ResultSet resultSet1 = stmt.executeQuery();

            while (resultSet1.next()) {
                lista.add(ConfigStringEstoque(resultSet1));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return lista;
    }
    
    public List<String> listarProdutosVendidos() {

        List<String> lista = new ArrayList<String>();
        try {

            String SQLQueryComprados = "SELECT prd.prd_nome_padrao || ' ' || prd.prd_nome_qualificador, est.prd_preco_venda, est.prd_preco_compra, count(*) \n" +
"	FROM public.estoque est inner join public.produto prd on prd.prd_cod_barras = est.prd_cod_barras\n" +
"	and est.prd_data_venda is not null\n" +
"	group by prd.prd_cod_barras, prd.prd_nome_padrao || ' ' || prd.prd_nome_qualificador, est.prd_preco_venda, est.prd_preco_compra;\n" ;

            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQueryComprados);
            //setando as datas de busca            //coletando os dados
            ResultSet resultSet1 = stmt.executeQuery();

            while (resultSet1.next()) {
                lista.add(ConfigStringLucro(resultSet1));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return lista;

    }

    /**
     * Padrão para retornar os valores da classe ProdutoEstoque
     *
     * @param input
     * @return
     * @throws ClassNotFoundException
     */
    private ProdutoEstoque produtoestoque(ResultSet input) throws ClassNotFoundException {
        try {

            return new ProdutoEstoque(
                    new ProdutoDAO().selecionaProduto(input.getString(1)),
                    input.getDate(2),
                    input.getDate(3),
                    input.getDate(4),
                    input.getDouble(5),
                    input.getDouble(6),
                    input.getDouble(7)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }

    private String ConfigStringLucro(ResultSet input) throws ClassNotFoundException {
        try {

            return new String("\n" + input.getString(1) + ":-" + input.getString(2)
                    + ":-" + input.getString(3) + ":-" + (input.getDouble(2) - input.getDouble(3)) + ":-" + input.getString(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }

    private String ConfigStringEstoque(ResultSet input) throws ClassNotFoundException {
        try {

            return new String("\n" + input.getString(1) + ":-" + input.getString(2) + ":-" + input.getString(3)
                    + ":-" + input.getString(4) + ":-" + (input.getString(5)) + "");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }
        return null;
    }

    /// Seção usada para relatórios
    /**
     * retona lista com Longervalo entre as datas setadas
     *
     * @return
     */
    
    /**
     * retona lista com Longervalo entre as datas setadas
     * @param produto
     * @return
     */
    public ProdutoEstoque consultaPreco(Produto produto){
       ProdutoEstoque retorno = new ProdutoEstoque();
        try {
            //usar leftjoin ou criar uma view
            String SQLQuery = "SELECT prd_cod_barras, "
                    + "       prd_data_compra, "
                    + "       prd_data_venda, "
                    + "       prd_data_validade, "
                    + "       prd_preco_venda, "
                    + "       prd_preco_compra, "
                    + "       prd_medida "
                    + "  FROM public.estoque WHERE prd_cod_barras = ? ";
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

            //setando as datas de busca
            stmt.setString(1, produto.getCod_barras());
            

            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                retorno = (produtoestoque(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return retorno;
    }
            
    public List<ProdutoEstoque> listaEstoqueBetweenDataVenda(Date dataInicial, Date dataFinal) {
        List<ProdutoEstoque> lista = new ArrayList<ProdutoEstoque>();
        try {
            //usar leftjoin ou criar uma view
            String SQLQuery = "SELECT prd_cod_barras, "
                    + "       prd_data_compra, "
                    + "       prd_data_venda, "
                    + "       prd_data_validade, "
                    + "       prd_preco_venda, "
                    + "        prd_preco_compra, "
                    + "       prd_medida "
                    + "  FROM public.estoque WHERE prd_data_Venda between ? and ?";
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

            //setando as datas de busca
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);

            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                lista.add(produtoestoque(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return lista;
    }

    public List<ProdutoEstoque> listaEstoqueBetweenDataCompra(Date dataInicial, Date dataFinal) {
        List<ProdutoEstoque> lista = new ArrayList<ProdutoEstoque>();
        try {
            //usar leftjoin ou criar uma view
            String SQLQuery = "SELECT prd_cod_barras, "
                    + "       prd_data_compra, "
                    + "       prd_data_venda, "
                    + "       prd_data_validade, "
                    + "       prd_preco_venda, "
                    + "        prd_preco_compra, "
                    + "       prd_medida "
                    + " WHERE prd_data_compra between ? and ?";
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

            //setando as datas de busca
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);

            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Long produto_id = Long.parseLong(resultSet.getString(1));

                lista.add(produtoestoque(resultSet));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return lista;

    }

    public List<ProdutoEstoque> listaEstoqueBetweenDataValidade(Date dataInicial, Date dataFinal) {
        List<ProdutoEstoque> lista = new ArrayList<ProdutoEstoque>();
        try {
            //usar leftjoin ou criar uma view
            String SQLQuery = "SELECT prd_cod_barras, "
                    + "       prd_data_compra, "
                    + "       prd_data_venda, "
                    + "       prd_data_validade, "
                    + "       prd_preco_venda, "
                    + "       prd_preco_compra, "
                    + "       prd_medida "
                    + " WHERE prd_data_validade between ? and ?";
            this.connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(SQLQuery);

            //setando as datas de busca
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);

            //coletando os dados
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Long produto_id = Long.parseLong(resultSet.getString(1));

                lista.add(produtoestoque(resultSet));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getCause() + "\n" + e.getMessage());
        }

        return lista;

    }

    public List<ProdutoEstoque> listaEstoquePadrao(String[] whereStatement) {
        List<ProdutoEstoque> listaTMP = new ArrayList<ProdutoEstoque>();

        //definição do codigo SQL
        return listaTMP;
    }
}
