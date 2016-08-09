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



/**
 *
 * @author Danilo
 */
public class MainRepositorio {


    private MasterSQLHelper helper ;

    public MainRepositorio(){
        //this.connection = new ConnectionFactory().getConnection();

    }

    public MainRepositorio(Context ctx){
        //this.connection = new ConnectionFactory().getConnection();
        helper = new MasterSQLHelper(ctx);
    }
}
