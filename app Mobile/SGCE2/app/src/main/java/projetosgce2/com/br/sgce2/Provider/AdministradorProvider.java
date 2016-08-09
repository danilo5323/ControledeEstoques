package projetosgce2.com.br.sgce2.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.IllegalFormatCodePointException;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;

/**
 * Created by Danilo on 22/06/2016.
 */
public class AdministradorProvider extends ContentProvider {

    private static final String AUTHORITY = "";
    private static final String PATH = "Administradores";
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
                id = sqlDB.insertWithOnConflict(MasterSQLHelper.TABELA_ADMINISTRADOR, null, values, SQLiteDatabase.CONFLICT_REPLACE);
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
        return 0;
    }

    @Override
    public int delete(Uri uri,  String selection, String[] selectionArgs)
    {//implementar
    return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder  = new SQLiteQueryBuilder();
        queryBuilder.setTables(MasterSQLHelper.TABELA_ADMINISTRADOR);
        Cursor cursor = null;

        switch (uriType){
            case TIPO_GERAL:
                cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TIPO_ADMINISTRADOR_ESPECIFICO:
                String n=  MasterSQLHelper.ADM_ID_ADMIN;
                queryBuilder.appendWhere( n += " ? ");
                cursor = queryBuilder.query(db, projection, selection, new String[]{uri.getLastPathSegment()}, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
}
