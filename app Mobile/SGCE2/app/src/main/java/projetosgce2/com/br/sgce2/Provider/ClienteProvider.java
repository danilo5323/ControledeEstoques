package projetosgce2.com.br.sgce2.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;

/**
 * Created by Danilo on 22/06/2016.
 */
public class ClienteProvider extends ContentProvider {

    private static final String AUTHORITY = "projetosgce2.com.br.sgce2";
    private static final String PATH = "clientes";
    private static final int TIPO_GERAL = 1;
    private static final int TIPO_ADMINISTRADOR_ESPECIFICO=2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);

    private MasterSQLHelper mHelper;
    private static final UriMatcher sUriMatcher;

    static{

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, PATH, TIPO_GERAL);
        sUriMatcher.addURI(AUTHORITY, PATH + "#", TIPO_ADMINISTRADOR_ESPECIFICO);
    }

    @Override
    public boolean onCreate(){

        mHelper = new MasterSQLHelper((getContext()));
        return true;
    }

    @Override
    public String getType(Uri uri){

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        long id = 0;
        switch(uriType){
            case TIPO_GERAL:
                 id = sqlDB.insert(
                        MasterSQLHelper.TABELA_CLIENTE,
                        null, values);

                /*id = sqlDB.insertWithOnConflict(
                        MasterSQLHelper.TABELA_CLIENTE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE);
                        */
                break;
            default:
                throw new IllegalArgumentException("URI não suportada"  + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        //implementar
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();

        int linhasAfetadas = 0;

        switch (uriType){
            case TIPO_GERAL:
                linhasAfetadas = sqlDB.update(MasterSQLHelper.TABELA_CLIENTE, values, selection, selectionArgs);

                break;
            case TIPO_ADMINISTRADOR_ESPECIFICO:
                String id = uri.getLastPathSegment();
                linhasAfetadas = sqlDB.update(MasterSQLHelper.TABELA_CLIENTE, values, MasterSQLHelper.ID_CLIENTE + " = ? ",
                        new String[]{id});
                break;
            default:
                throw  new IllegalArgumentException(
                        "Unknow URI " + uri
                );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return linhasAfetadas;

    }

    @Override
    public int delete(Uri uri,  String selection, String[] selectionArgs)
    {//implementar
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        int rowsDeleted= 0;
        long id = 0;
        switch(uriType){
            case TIPO_GERAL:
                rowsDeleted = sqlDB.delete(MasterSQLHelper.TABELA_CLIENTE, selection, selectionArgs);
                break;
            case TIPO_ADMINISTRADOR_ESPECIFICO:
                String id2 = uri.getLastPathSegment();
                rowsDeleted = sqlDB.delete(MasterSQLHelper.TABELA_CLIENTE, MasterSQLHelper.ID_CLIENTE + " = ? ",
                        new String[]{id2});
                break;

            default:
                throw new IllegalArgumentException("URI não suportada"  + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;


    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder  = new SQLiteQueryBuilder();
        queryBuilder.setTables(MasterSQLHelper.TABELA_CLIENTE);

        Cursor cursor = null;

        switch (uriType){
            case TIPO_GERAL:
                cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TIPO_ADMINISTRADOR_ESPECIFICO:
                String n=  MasterSQLHelper.ID_CLIENTE;

                queryBuilder.appendWhere( n += " = ? ");

                cursor = queryBuilder.query(db, projection, selection, new String[]{uri.getLastPathSegment()}, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
}
