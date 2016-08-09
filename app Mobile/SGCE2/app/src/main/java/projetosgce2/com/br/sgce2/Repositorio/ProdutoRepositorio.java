/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Repositorio;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import projetosgce2.com.br.sgce2.Repositorio.ProdutoRepositorio;
import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.Produto;


/**
 *
 * @author Danilo
 */
public class ProdutoRepositorio {


    private MasterSQLHelper helper ;

    public ProdutoRepositorio(){
        //this.connection = new ConnectionFactory().getConnection();
  //      helper = new ProdutoMasterSQLHelper(ctx);
    }

    public ProdutoRepositorio(Context ctx){
        //this.connection = new ConnectionFactory().getConnection();
        helper = new MasterSQLHelper(ctx);
    }



   ///metodo para cadastrar Produtos no banco local
    private long inserirProduto(Produto input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.PRD_COD_BARRAS, input.getCod_barras());
        cv.put(MasterSQLHelper.PRD_MARCA, input.getMarca());
        cv.put(MasterSQLHelper.PRD_NOME_PADRAO, input.getNome_padrao());
        cv.put(MasterSQLHelper.PRD_NOME_QUALIFICADOR, input.getNome_qualificador());
        cv.put(MasterSQLHelper.PRD_MEDIDA, input.getQuantia());
        cv.put(MasterSQLHelper.PRD_UNIDADE_MEDIDA, input.getUnidade_medida());


        long id = db.insert(MasterSQLHelper.TABELA_PRODUTO, null, cv);

        if(id!= -1){
            input.setId_produto(id);
        }
        return id;

    }

    //metodo para recuperar Produtos
    public List<Produto> buscarProduto(String filtro){

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " +
                    MasterSQLHelper.TABELA_PRODUTO;

        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE " + MasterSQLHelper.PRD_COD_BARRAS + " = ?";
            argumentos = new String[]{filtro};

        }

        sql += " ORDER BY " + MasterSQLHelper.PRD_COD_BARRAS;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Produto> Produtos = new ArrayList<Produto>();

        while(cursor.moveToNext()){

            long id = cursor.getLong(
                    cursor.getColumnIndex(  MasterSQLHelper.ID_PRODUTO)
            );

            String prd_nome_padrao = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_NOME_PADRAO)
            );

            String prd_cod_barras = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_COD_BARRAS)
            );

            String prd_nome_qualificador = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_NOME_QUALIFICADOR)
            );

            String prd_marca = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_MARCA)
            );

            String prd_quantia= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_MEDIDA)
            );

            String prd_unidade_medida= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_UNIDADE_MEDIDA)
            );


            Produto ProdutoADD = new Produto(id, prd_nome_padrao, prd_nome_qualificador,
                                             prd_unidade_medida, prd_quantia, prd_marca,
                                             prd_cod_barras);

            Produtos.add(ProdutoADD);
        }

        cursor.close();

        db.close();

        return Produtos;
    }

    public Produto buscarProdutoUnico(String filtro){

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " + MasterSQLHelper.TABELA_PRODUTO;

        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE " + MasterSQLHelper.PRD_COD_BARRAS + " = ?";
            argumentos = new String[]{filtro};

        }

        sql += " ORDER BY " + MasterSQLHelper.PRD_COD_BARRAS;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Produto> Produtos = new ArrayList<Produto>();

        Produto ProdutoADD = null;

        if(cursor.moveToNext()){

            long id = cursor.getLong(
                    cursor.getColumnIndex(MasterSQLHelper.ID_PRODUTO)
            );

            String prd_nome_padrao = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_NOME_PADRAO)
            );

            String prd_cod_barras = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_COD_BARRAS)
            );

            String prd_nome_qualificador = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_NOME_QUALIFICADOR)
            );

            String prd_marca = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_MARCA)
            );

            String prd_quantia= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_MEDIDA)
            );

            String prd_unidade_medida= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.PRD_UNIDADE_MEDIDA)
            );


            ProdutoADD = new Produto(id, prd_nome_padrao, prd_nome_qualificador,
                    prd_unidade_medida, prd_quantia, prd_marca,
                    prd_cod_barras);


        }

        cursor.close();
        db.close();

        return ProdutoADD;
    }

    //metodo para excluir Produtos.
    public long excluirProduto(Produto input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(
                MasterSQLHelper.TABELA_PRODUTO,
                MasterSQLHelper.PRD_COD_BARRAS + " = ? " ,
                new String[] {String.valueOf(input.getCod_barras())}

        );

        return linhasAfetadas;

    }


    //metodo para alterar Produtos
    private int alterarProduto(Produto input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MasterSQLHelper.PRD_COD_BARRAS, input.getCod_barras());
        cv.put(MasterSQLHelper.PRD_MARCA, input.getMarca());
        cv.put(MasterSQLHelper.PRD_NOME_PADRAO, input.getNome_padrao());
        cv.put(MasterSQLHelper.PRD_NOME_QUALIFICADOR, input.getNome_qualificador());
        cv.put(MasterSQLHelper.PRD_MEDIDA, input.getQuantia());
        cv.put(MasterSQLHelper.PRD_UNIDADE_MEDIDA, input.getUnidade_medida());
        
        int linhasAfetadas = db.update(
                MasterSQLHelper.TABELA_PRODUTO,
                cv,
                MasterSQLHelper.ID_PRODUTO + " = ? ",
                new String[]{String.valueOf(input.getId_produto())}

        );

        cv.clear();
        return linhasAfetadas;
    }

        ///////usar daqui para baixo como referencia
    public void salvar(Produto input){

        //busca a primeira ocorrencia de Produto
        List<Produto> tmp = buscarProduto(input.getCod_barras());

        //configura um ponteiro para Produto
        Produto tmpProduto;

        //verifica se a lista trouxe resultados
        if( !tmp.isEmpty() ) {
            //configura tmpProduto como a primeira instancia
            tmpProduto = tmp.get(0);
            //recupera o ID do Produto temporario
            input.setId_produto(tmpProduto.getId_produto());
            alterarProduto(input);
        }
        else {
            inserirProduto(input);
        }
            //configura tmpProduto como uma variavel nula
    }
}
