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

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.Fornecedor;


/**
 *
 * @author Danilo
 */
public class FornecedorRepositorio {


    private MasterSQLHelper helper ;

    public FornecedorRepositorio(){
        //this.connection = new ConnectionFactory().getConnection();

    }

    ///instancia um novo fornecedor
    public FornecedorRepositorio(Context ctx){
        helper = new MasterSQLHelper(ctx);
    }



   ///metodo para cadastrar clientes no banco local
    private long inserirFornecedor(Fornecedor input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();



        cv.put(MasterSQLHelper.FORN_NOME_EMPRESA, input.getForn_nome_empresa());
        cv.put(MasterSQLHelper.FORN_NOME_FANTASIA, input.getForn_nome_fantasia());
        cv.put(MasterSQLHelper.FORN_CNPJ, input.getForn_cnpj());
        cv.put(MasterSQLHelper.FORN_INSCRICAO_ESTADUAL, input.getForn_ie());
        cv.put(MasterSQLHelper.FORN_EMAIL, input.getForn_email());
        cv.put(MasterSQLHelper.FORN_TEL, input.getForn_tel());
        cv.put(MasterSQLHelper.FORN_ENDERECO, input.getForn_endereco());

        long id = db.insert(MasterSQLHelper.TABELA_FORNECEDOR, null, cv);

        if(id!= -1){
            input.setId_fornecedor(id);
        }
        return id;

    }

    //metodo para recuperar clientes
    public List<Fornecedor> buscarFornecedorCNPJ(String filtro){

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " + MasterSQLHelper.TABELA_FORNECEDOR;

        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE " + MasterSQLHelper.FORN_CNPJ + " LIKE ? ";
            argumentos = new String[]{filtro};

        }

        sql += " ORDER BY " + MasterSQLHelper.FORN_NOME_EMPRESA;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

        while(cursor.moveToNext()){

            long id = cursor.getLong(
                    cursor.getColumnIndex( MasterSQLHelper.ID_FORNECEDOR )
            );


            String forn_nome_empresa = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_NOME_EMPRESA)
            );

            String forn_nome_fantasia = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_NOME_FANTASIA)
            );

            String forn_cnpj = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_CNPJ)
            );

            String forn_inscricao_estadual = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_INSCRICAO_ESTADUAL)
            );

            String forn_email = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_EMAIL)
            );

            String forn_tel= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_TEL)
            );

            String forn_endereco= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.FORN_ENDERECO)
            );



            Fornecedor fornecedorADD = new Fornecedor(id, forn_nome_empresa, forn_nome_fantasia,
                                             forn_email, forn_cnpj, forn_inscricao_estadual,
                                             forn_tel, forn_endereco);

            fornecedores.add(fornecedorADD);
        }

        cursor.close();

        db.close();

        return fornecedores;
    }


    //metodo para excluir clientes.
    public long excluirFornecedor(Fornecedor input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(
                MasterSQLHelper.TABELA_FORNECEDOR,
                MasterSQLHelper.FORN_CNPJ + " = ? ",
                new String[] {String.valueOf(input.getForn_cnpj())}

        );

        return linhasAfetadas;

    }


    //metodo para alterar clientes
    private int alterarFornecedor(Fornecedor input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.FORN_NOME_EMPRESA, input.getForn_nome_empresa());
        cv.put(MasterSQLHelper.FORN_NOME_FANTASIA, input.getForn_nome_fantasia());
        cv.put(MasterSQLHelper.FORN_CNPJ, input.getForn_cnpj());
        cv.put(MasterSQLHelper.FORN_INSCRICAO_ESTADUAL, input.getForn_ie());
        cv.put(MasterSQLHelper.FORN_EMAIL, input.getForn_email());
        cv.put(MasterSQLHelper.FORN_TEL, input.getForn_tel());
        cv.put(MasterSQLHelper.FORN_ENDERECO, input.getForn_endereco());
        
        int linhasAfetadas = db.update(
                MasterSQLHelper.TABELA_FORNECEDOR,
                cv,
                MasterSQLHelper.ID_FORNECEDOR + " = ? ",
                new String[]{String.valueOf(input.getId_fornecedor())}

        );

        cv.clear();
        return linhasAfetadas;
    }

        ///////usar daqui para baixo como referencia
    public void salvar(Fornecedor input){

        inserirFornecedor(input);
        //busca a primeira ocorrencia de cliente
        /*List<Fornecedor> tmp = buscarFornecedorCNPJ(input.getForn_cnpj());

        //configura um ponteiro para cliente
        Fornecedor tmpFornecedor;

        //verifica se a lista trouxe resultados
        if( !tmp.isEmpty() ) {
            //configura tmpCliente como a primeira instancia
            tmpFornecedor = tmp.get(0);
            //recupera o ID do cliente temporario
            input.setId_fornecedor(tmpFornecedor.getId_fornecedor());
            alterarFornecedor(input);
        }
        else {
            inserirFornecedor(input);
        }
        */
            //configura tmpCliente como uma variavel nula




    }

    
}
