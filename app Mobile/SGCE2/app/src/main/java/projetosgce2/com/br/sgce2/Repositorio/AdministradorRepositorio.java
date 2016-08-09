package projetosgce2.com.br.sgce2.Repositorio;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.Administrador;
import projetosgce2.com.br.sgce2.Provider.AdministradorProvider;

/**
 * Created by Danilo on 21/06/2016.
 */
public class AdministradorRepositorio {

    private MasterSQLHelper helper ;

    private Context ctx;

    //instancia o contexto
    public AdministradorRepositorio(Context ctx){
        this.ctx = ctx;

    }

    ///trecho para enviar para o webservices

    private long inserir(Administrador adm){

        adm.setStatus(adm.status.INSERIR);

        long id = inserirLocal(adm, ctx.getContentResolver());
        return id;
    }

    private int atualizar(Administrador adm){
        adm.setStatus(adm.status.ATUALIZAR);
        int linhasAfetadas = atualizarLocal(adm, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public void salvar(Administrador adm){
        if(adm.getId() == 0){
            inserir(adm);
        }
        else{
            atualizar(adm);
        }
    }

    public int excluir(Administrador adm){
        adm.setStatus(adm.status.EXCLUIR);
        int linhasAfetadas = atualizarLocal(adm, ctx.getContentResolver());
        return linhasAfetadas;
    }

    public CursorLoader buscar(Context ctx, String s){
        String where = null;
        String[] whereArgs = null;
        if(s!= null){
            where = MasterSQLHelper.ADM_EMAIL + " like ? " ;
            whereArgs = new String[]{ "%" + s + "%"};
        }
        return new CursorLoader(
                ctx,
                AdministradorProvider.CONTENT_URI,
                null,
                where,
                whereArgs,
                MasterSQLHelper.ADM_EMAIL
        );


    }

    private static ContentValues getValues(Administrador adm){
        ContentValues cv = new ContentValues();


        cv.put(MasterSQLHelper.ADM_EMAIL        , adm.getEmail());
        cv.put(MasterSQLHelper.ADM_SENHA, adm.getSenha());
        cv.put(MasterSQLHelper.ADM_USUARIO, adm.getUsuario());

        cv.put(MasterSQLHelper.ADM_COLUNA_STATUS, adm.getStatus().ordinal());
        if(adm.getIdServidor() !=0){
            cv.put(MasterSQLHelper.ADM_COLUNA_ID_SERVIDOR, adm.getIdServidor());
        }
        return cv;
    }

    public static Administrador administradorFromCursor(Cursor cursor){
        Long id = cursor.getLong(cursor.getColumnIndex(MasterSQLHelper.ADM_ID_ADMIN));
        String email = cursor.getString(cursor.getColumnIndex(MasterSQLHelper.ADM_EMAIL));
        String senha = cursor.getString(cursor.getColumnIndex(MasterSQLHelper.ADM_SENHA));
        String usuario = cursor.getString(cursor.getColumnIndex(MasterSQLHelper.ADM_USUARIO));
        int status = cursor.getInt(cursor.getColumnIndex(MasterSQLHelper.ADM_COLUNA_STATUS));
        long idSerivdor = cursor.getLong(cursor.getColumnIndex(MasterSQLHelper.ADM_COLUNA_ID_SERVIDOR));
        Administrador administrador = new Administrador(id, usuario, email,senha, idSerivdor, Administrador.Status.values()[status]);
        return administrador;
    }



    ///metodo para cadastrar Administradors no banco local
    public long inserirLocal(Administrador input, ContentResolver cr) {

        Uri uri = cr.insert(AdministradorProvider.CONTENT_URI,
                getValues(input));

        long id = Long.parseLong(uri.getLastPathSegment());

        if (id != -1) {
            input.setId(id);
        }
        return id;
    }

    public int atualizarLocal(Administrador input, ContentResolver cr){
        Uri uri = Uri.withAppendedPath(AdministradorProvider.CONTENT_URI, String.valueOf(input.getId()));

        int linhasAfetadas = cr.update(uri, getValues(input), null, null);

        return linhasAfetadas;
    }

    public int excluirLocal(Administrador input , ContentResolver cr){
        Uri uri = Uri.withAppendedPath(
                AdministradorProvider.CONTENT_URI , String.valueOf(input.getId()));

        int linhasAfetadas = cr.delete(uri, null, null);
        return linhasAfetadas;


    }


    }



