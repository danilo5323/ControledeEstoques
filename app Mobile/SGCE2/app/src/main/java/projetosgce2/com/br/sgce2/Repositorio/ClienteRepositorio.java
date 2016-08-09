/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Repositorio;


import android.content.ContentResolver;
import android.content.CursorLoader;
import android.database.sqlite.SQLiteDatabase;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.*;
import projetosgce2.com.br.sgce2.Provider.ClienteProvider;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;


/**
 *
 * @author Danilo
 */
public class ClienteRepositorio {


    private MasterSQLHelper helper ;

    Context ctx;

    public ClienteRepositorio(){
        //this.connection = new ConnectionFactory().getConnection();
        //      helper = new MasterSQLHelper(ctx);
    }

    public ClienteRepositorio(Context ctx){
        //this.connection = new ConnectionFactory().getConnection();

        this.ctx = ctx;

        helper = new MasterSQLHelper(ctx);

    }


    public long inserir(Cliente cliente){
        cliente.status = Cliente.Status.INSERIR;
        long id = inserirLocal(cliente, ctx.getContentResolver());
        return id;
    }

    public int atualizar(Cliente cliente){
        cliente.status = Cliente.Status.ATUALIZAR;
        int linhasAfetadas = atualizarLocal(cliente, ctx.getContentResolver());
        return linhasAfetadas;
    }



    public void salvarN(Cliente cliente){

        //busca a primeira ocorrencia de cliente
        List<Cliente> tmp = buscarCliente(cliente.getTelefone());

        //configura um ponteiro para cliente
        Cliente tmpCliente;

        //verifica se a lista trouxe resultados
        if( !tmp.isEmpty() ) {
            //configura tmpCliente como a primeira instancia
            tmpCliente = tmp.get(0);
            //recupera o ID do cliente temporario
            cliente.setId_cliente(tmpCliente.getId_cliente());
            atualizar(cliente);
        }
        else {
            inserir(cliente);
        }
        //configura tmpCliente como uma variavel nula

        /*
        if(cliente.getId_cliente() ==0 ){
            inserir(cliente);
        }
        else{
            atualizar(cliente);
        }
        */
    }

    public int excluir(Cliente cliente){
        cliente.status = Cliente.Status.EXCLUIR;
        int linhasAfetadas = excluirLocal(cliente, ctx.getContentResolver());
        return linhasAfetadas;
    }

    public CursorLoader buscar(Context ctx, String s){
        String where = null;
        String whereArgs[] = null;
        if(s!= null){
            where = MasterSQLHelper.CLI_CPF + " = ? ";
            whereArgs = new String[]{ where };
        }
        return new CursorLoader(
                ctx,
                ClienteProvider.CONTENT_URI, ///implementar este;
                null,
                where,
                whereArgs,
                MasterSQLHelper.CLI_CPF
        );
    }

    private static ContentValues getValues(Cliente input){
        ContentValues cv = new ContentValues();
        cv.put(MasterSQLHelper.CLI_TELEFONE, input.getTelefone());
        cv.put(MasterSQLHelper.CLI_CPF, input.getCpf());
        cv.put(MasterSQLHelper.CLI_IE, input.getInscricao_estadual());
        cv.put(MasterSQLHelper.CLI_CNPJ, input.getCnpj());
        cv.put(MasterSQLHelper.CLI_ENDERECO, input.getEndereco());
        cv.put(MasterSQLHelper.CLI_NOME_PRINC, input.getNomePrinc());
        cv.put(MasterSQLHelper.CLI_NOME_SEC, input.getNomeSec());
        cv.put(MasterSQLHelper.CLI_EMAIL, input.getEmail());
        cv.put(MasterSQLHelper.CLI_COLUNA_STATUS, input.status.ordinal());
        if(input.idServidor != 0){
            cv.put(MasterSQLHelper.CLI_COLUNA_ID_SERVIDOR, input.idServidor);
        }
        return cv;
    }

    public static Cliente clienteFromCursor(Cursor cursor){
        long id = cursor.getLong(
                cursor.getColumnIndex(  MasterSQLHelper.ID_CLIENTE                    )
        );
        String cli_nome_princ = cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_NOME_PRINC)
        );
        String cli_nome_sec = cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_NOME_SEC)
        );
        String cli_telefone = cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_TELEFONE)
        );
        String cli_cnpj = cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_CNPJ)
        );
        String cli_email = cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_EMAIL)
        );
        String cli_cpf= cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_CPF)
        );
        String cli_endereco= cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_ENDERECO)
        );
        String cli_ie= cursor.getString(
                cursor.getColumnIndex(MasterSQLHelper.CLI_IE)
        );

        int cli_status = cursor.getInt(
                cursor.getColumnIndex(MasterSQLHelper.CLI_COLUNA_STATUS)
        );
        long cli_idservidor= cursor.getLong(
                cursor.getColumnIndex(MasterSQLHelper.CLI_COLUNA_ID_SERVIDOR)
        );

        Cliente cliente = new Cliente(id, cli_nome_princ, cli_nome_sec,
                cli_telefone, cli_email, cli_cpf, cli_cnpj,
                cli_ie, cli_endereco, Cliente.Status.values()[cli_status], cli_idservidor);

        return cliente;
    }

    public long inserirLocal(Cliente cliente, ContentResolver cr){

        Uri uri = cr.insert(
                ClienteProvider.CONTENT_URI,
                getValues(cliente)
        );

        long id = Long.parseLong(uri.getLastPathSegment());
        if(id!=-1){
            cliente.setId_cliente(id);
        }
        return id;

    }

    public int atualizarLocal(Cliente client5e, ContentResolver cr){
        Uri uri;
        int linhasAfetadas = 0;
        try {
            uri = Uri.withAppendedPath(
                    ClienteProvider.CONTENT_URI, String.valueOf(client5e.id_cliente));
            linhasAfetadas = cr.update(
                    uri, getValues(client5e), null, null);
        }
        catch (Exception e){

        }
        try{
            alterarCliente(client5e);
        }catch (Exception e ){}



        return linhasAfetadas;
    }

    public int excluirLocal(Cliente cliente, ContentResolver cr){
        Uri uri;
        int linhasAfetadas = 0;

        try{
       uri = Uri.withAppendedPath(ClienteProvider.CONTENT_URI,
                        String.valueOf(cliente.id_cliente));
            linhasAfetadas = cr.delete(uri, null, null);
        }
        catch (Exception exception){

        }
        try{
            linhasAfetadas = excluirCliente(cliente);
        }
        catch (Exception exception){

        }

        return linhasAfetadas;
    }

    /*
    public void salvar(Cliente cliente){
        if(cliente.getId_cliente() == 0){
            inserir(cliente);
        }
        else{
            atualizar(cliente);
        }
    }
    */
    // metodos em uso;

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
        cv.put(MasterSQLHelper.CLI_EMAIL, input.getEmail());

        long id = db.insert(MasterSQLHelper.TABELA_CLIENTE, null, cv);

        if(id!= -1){
            input.setId_cliente(id);
        }
        return id;

    }




    //metodo para recuperar clientes
    public List<Cliente> buscarCliente(String filtro){

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " +
                MasterSQLHelper.TABELA_CLIENTE;

        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE " + MasterSQLHelper.CLI_TELEFONE + " LIKE ?";
            argumentos = new String[]{filtro};

        }

        sql += " ORDER BY " + MasterSQLHelper.CLI_NOME_PRINC;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Cliente> clientes = new ArrayList<Cliente>();

        while(cursor.moveToNext()){
            long id = cursor.getLong(
                    cursor.getColumnIndex(  MasterSQLHelper.ID_CLIENTE                    )
            );
            String cli_nome_princ = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_NOME_PRINC)
            );
            String cli_nome_sec = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_NOME_SEC)
            );
            String cli_telefone = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_TELEFONE)
            );
            String cli_cnpj = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_CNPJ)
            );
            String cli_email = cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_EMAIL)
            );
            String cli_cpf= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_CPF)
            );
            String cli_endereco= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_ENDERECO)
            );
            String cli_ie= cursor.getString(
                    cursor.getColumnIndex(MasterSQLHelper.CLI_IE)
            );

            Cliente clienteADD = new Cliente(id, cli_nome_princ, cli_nome_sec,
                    cli_telefone, cli_email, cli_cpf, cli_cnpj,
                    cli_ie, cli_endereco);

            clientes.add(clienteADD);
        }

        cursor.close();

        db.close();

        return clientes;
    }


    //metodo para excluir clientes.
    public int excluirCliente(Cliente input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(
                MasterSQLHelper.TABELA_CLIENTE,
                MasterSQLHelper.CLI_CPF + " = ? AND " +
                        MasterSQLHelper.CLI_TELEFONE + " = ? ",
                new String[] {String.valueOf(input.getCpf()),
                        String.valueOf(input.getTelefone())}

        );

        return linhasAfetadas;

    }


    //metodo para alterar clientes
    private int alterarCliente(Cliente input) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MasterSQLHelper.CLI_TELEFONE, input.getTelefone());
        cv.put(MasterSQLHelper.CLI_CPF, input.getCpf());
        cv.put(MasterSQLHelper.CLI_IE, input.getInscricao_estadual());
        cv.put(MasterSQLHelper.CLI_CNPJ, input.getCnpj());
        cv.put(MasterSQLHelper.CLI_EMAIL, input.getEmail());
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
    }

    ///////usar daqui para baixo como referencia
    public void salvar(Cliente input){

        //busca a primeira ocorrencia de cliente
        List<Cliente> tmp = buscarCliente(input.getTelefone());

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
    }
//
}
