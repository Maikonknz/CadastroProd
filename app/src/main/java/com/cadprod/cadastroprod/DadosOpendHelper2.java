package com.cadprod.cadastroprod;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOpendHelper2 extends SQLiteOpenHelper {

    public DadosOpendHelper2(Context context) {
        super(context, "DADOS2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {

        db2.execSQL(ScriptPROD.getCreateTableProduto());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {

    }
}
