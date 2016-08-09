/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Repositorio;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.ProdutoEstoque;
import projetosgce2.com.br.sgce2.Models.Produto;
import projetosgce2.com.br.sgce2.Repositorio.ProdutoRepositorio;


/**
 *
 * @author Danilo
 */
public class EstoqueRepositorio {

    public static final int PRODUTO_EM_ESTOQUE = 1;
    public static final int PRODUTO_FORA_DE_ESTOQUE = 2;

    public static final int PRODUTO_CADASTRADO = 3;
    public static final int PRODUTO_NAO_CADASTRADO = 4;


    private MasterSQLHelper helper;

    public EstoqueRepositorio() {
        //this.connection = new ConnectionFactory().getConnection();
        //      helper = new MasterSQLHelper(ctx);
    }

    public EstoqueRepositorio(Context ctx) {
        //this.connection = new ConnectionFactory().getConnection();
        helper = new MasterSQLHelper(ctx);
    }


    public void adicionaProdutosEstoque(ProdutoEstoque input, String dataVenda) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.EST_DATA_VALIDADE, input.getData_validade());
        cv.put(MasterSQLHelper.EST_PRD_COD_BARRAS, input.getPrd_cod_barras());
        cv.put(MasterSQLHelper.EST_PRD_DATA_COMPRA, input.getData_compra());
        cv.put(MasterSQLHelper.EST_PRD_DATA_VENDA, input.getData_venda());
        cv.put(MasterSQLHelper.EST_PRECO_COMPRA, input.getPreco_compra());
        cv.put(MasterSQLHelper.EST_PRECO_VENDA, input.getPreco_venda());
        //nao usar por enquanto
        //cv.put(MasterSQLHelper.EST_QUANTIDADE, input.getQuantidade());


        for (int i = 0; i < Integer.parseInt(input.getQuantidade()); i++) {
            db.insert(MasterSQLHelper.TABELA_ESTOQUE, null, cv);
        }
    }

    ///retorna a quantidade de produtos em estoque
    public int qtdEmEstoque(ProdutoEstoque input) {
        ProdutoRepositorio produto = new ProdutoRepositorio();
         List<Produto> tmpReturn = produto.buscarProduto(input.getPrd_cod_barras());
        return tmpReturn.size();
    }

    public Integer quantidadeEmEstoque(String filtroCodBarras) throws Exception{

        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            String sql = "SELECT * FROM " +
                    MasterSQLHelper.TABELA_ESTOQUE;

            String[] argumentos = null;

            if (filtroCodBarras != null) {
                sql += " WHERE " + MasterSQLHelper.EST_PRD_COD_BARRAS + " = ? AND " + MasterSQLHelper.EST_PRD_DATA_VENDA + " is not null ";
                argumentos = new String[]{filtroCodBarras};
            }

            Cursor cursor = db.rawQuery(sql, argumentos);


            Integer tmpIntReteurn = 0;

            if(cursor.moveToNext())
              tmpIntReteurn = cursor.getCount();

            cursor.close();
            db.close();

            return tmpIntReteurn;
        }catch(Exception e){

            throw new Exception(e.getMessage());
        }


    }


    public ProdutoEstoque retiraProdutosEstoque (String filtroCodBarras) throws  Exception{

        //autorização para escrever
        SQLiteDatabase db = helper.getWritableDatabase();

        //iniciação da criação do SQL
        String sql = "SELECT * FROM " +
                MasterSQLHelper.TABELA_ESTOQUE;

        String[] argumentos = null;

        if(filtroCodBarras != null){

            sql += " WHERE " + MasterSQLHelper.EST_PRD_COD_BARRAS + " = ?";
            argumentos = new String[]{filtroCodBarras};

        }
        sql += " LIMIT 1 ";

        Cursor cursor = db.rawQuery(sql, argumentos);

        ProdutoEstoque produto = null;

        while(cursor.moveToNext()){
            Long id = cursor.getLong(
                    cursor.getColumnIndex(MasterSQLHelper.EST_ID_ESTOQUE)
            );

            String prd_cod_barras = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_PRD_COD_BARRAS)
            );

            String data_compra = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_PRD_DATA_COMPRA)
            );

            String data_venda= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_PRD_DATA_VENDA)
            );

            String data_validade= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_DATA_VALIDADE)
            );

            String preco_venda= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_PRECO_VENDA)
            );

            String preco_compra= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.EST_PRECO_COMPRA)
            );

            //EM DESUSO POR ENQUANTO
            //String quantidade= cursor.getString(
            //        cursor.getColumnIndex(MasterSQLHelper.EST_QUANTIDADE)
            //);


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            String n = dateFormat.format(date);

            produto = new ProdutoEstoque (id, prd_cod_barras, data_compra, data_venda, data_validade, preco_venda, preco_compra,
                    n);

            ContentValues cv= new ContentValues();

            cv.put(MasterSQLHelper.EST_DATA_VALIDADE, produto.getData_venda());

            try {
                int tmp = db.update(
                        MasterSQLHelper.TABELA_FORNECEDOR,
                        cv,
                        MasterSQLHelper.EST_ID_ESTOQUE + "?",
                        new String[]{String.valueOf(produto.getId())}
                );
            }
                catch(Exception e){

            }
            finally {
                db.close();
                cv.clear();
            }


        }

         return produto;



          //ProdutoEstoque retorno = retiraProdutosEstoque(idprdestoque);


    }

    public Long coletaIdProduto(String codigoBarras) throws  Exception{

       // String sql = "select first 1 " + helper.EST_ID_ESTOQUE + " from " +
        //        helper.TABELA_ESTOQUE + " where " + helper.EST_PRD_COD_BARRAS + " = " + codigoBarras;


        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            String sql = "SELECT  first 1 " + helper.EST_ID_ESTOQUE +  " FROM " +
                    MasterSQLHelper.TABELA_ESTOQUE;

            String[] argumentos = null;

            if (codigoBarras != null) {
                sql += " WHERE " + MasterSQLHelper.EST_PRD_COD_BARRAS + " = ?";
                argumentos = new String[]{codigoBarras};
            }

            sql += " ORDER BY " + MasterSQLHelper.EST_ID_ESTOQUE;

            Cursor cursor = db.rawQuery(sql, argumentos);

            Long tmpLongReturn = 0l;
            //List<Produto> Produtos = new ArrayList<Produto>();
            while(cursor.moveToNext()) {
                long id = cursor.getLong(
                        cursor.getColumnIndex(MasterSQLHelper.EST_ID_ESTOQUE)
                );
                tmpLongReturn = id;
            }



            cursor.close();
            db.close();

            return tmpLongReturn;
        }catch(Exception e){

            throw new Exception(e.getMessage());
        }

        //return 0l;
    }

    public int retiraProdutosEstoque(ProdutoEstoque input) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.EST_DATA_VALIDADE, input.getData_validade());
        cv.put(MasterSQLHelper.EST_PRD_COD_BARRAS, input.getPrd_cod_barras());
        cv.put(MasterSQLHelper.EST_PRD_DATA_COMPRA, input.getData_compra());
        cv.put(MasterSQLHelper.EST_PRD_DATA_VENDA, input.getData_venda());
        cv.put(MasterSQLHelper.EST_PRECO_COMPRA, input.getPreco_compra());
        cv.put(MasterSQLHelper.EST_PRECO_VENDA, input.getPreco_venda());
        cv.put(MasterSQLHelper.EST_PRECO_VENDA, input.getData_venda());

        for (int i = 0; i < Integer.parseInt(input.getQuantidade()); i++) {
            db.insert(MasterSQLHelper.TABELA_ESTOQUE, null, cv);
        }
        return 0;
    }


    public int quantidadeItensEstoque(Produto produto) {
        return 0;

    }
}
/*

    ///metodo para cadastrar clientes no banco local
    private long inserirCliente(Cliente input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.CLI_TELEFONE, input.getTelefone());
        cv.put(MasterSQLHelper.CLI_CPF, input.getCpf());
        cv.put(MasterSQLHelper.CLI_IE, input.getInscricao_estadual());
        cv.put(MasterSQLHelper.CLI_CNPJ, input.getCnpj());
        cv.put(MasterSQLHelper.CLI_ENDERECO, input.getEndereco());
        cv.put(MasterSQLHelper.CLI_NOME_PRINC, input.getNomePrinc());
        cv.put(MasterSQLHelper.CLI_NOME_SEC, input.getNomeSec());

        long id = db.insert(MasterSQLHelper.TABELA_CLIENTE, null, cv);

        if(id!= -1){
            input.setId_cliente(id);
        }
        return id;

    }

    //metodo para excluir clientes.
    public long excluirCliente(Cliente input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(
                MasterSQLHelper.TABELA_CLIENTE,
                MasterSQLHelper.CLI_CPF + " = ? AND " +
                        MasterSQLHelper.CLI_TELEFONE + " = ? ",
                new String[]{String.valueOf(input.getCpf()),
                        String.valueOf(input.getTelefone())}

        );

        return linhasAfetadas;

    }


    //metodo para alterar clientes
    private int alterarCliente(Cliente input) {
       /*
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.CLI_TELEFONE, input.getTelefone());
        cv.put(MasterSQLHelper.CLI_CPF, input.getCpf());
        cv.put(MasterSQLHelper.CLI_IE, input.getInscricao_estadual());
        cv.put(MasterSQLHelper.CLI_CNPJ, input.getCnpj());
        cv.put(MasterSQLHelper.CLI_ENDERECO, input.getEndereco());
        cv.put(MasterSQLHelper.CLI_NOME_PRINC, input.getNomePrinc());
        cv.put(MasterSQLHelper.CLI_NOME_SEC, input.getNomeSec());

        int linhasAfetadas = db.update(
                MasterSQLHelper.TABELA_CLIENTE,
                cv,
                MasterSQLHelper.ID_CLIENTE + " = ? ",
                new String[]{String.valueOf(input.getId_cliente())}

        );

        cv.clear();
        return linhasAfetadas;
        */


   /*     ///////usar daqui para baixo como referencia
    public void salvar(Cliente input){
/*
        //busca a primeira ocorrencia de cliente
        List<Cliente> tmp = //buscarCliente(input.getTelefone());

        //configura um ponteiro para cliente
        Cliente tmpCliente;

        //verifica se a lista trouxe resultados
        if( !tmp.isEmpty() ) {
            //configura tmpCliente como a primeira instancia
            tmpCliente = tmp.get(0);
            //recupera o ID do cliente temporario
            input.setId_cliente(tmpCliente.getId_cliente());
            alterarCliente(input);
        }
        else {
            inserirCliente(input);
        }
            //configura tmpCliente como uma variavel nula
*/





    

