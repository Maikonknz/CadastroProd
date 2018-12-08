package com.cadprod.cadastroprod;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProduto {
    private SQLiteDatabase conex;

    public RepositorioProduto(SQLiteDatabase conex){

        this.conex = conex;
    }

    public void inserirprod(Produto prod){

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("NOME", prod.Nome);
        contentValues2.put("COD",prod.Cod);
        contentValues2.put("CATEGORIA", prod.Categoria);
        contentValues2.put("DESCRICAO", prod.Descricao);
        contentValues2.put("QUANTIDADE", prod.Quantidade);
        contentValues2.put("UNIDADE", prod.Unidade);
        contentValues2.put("VALOR",prod.Valor);
        contentValues2.put("FORNECEDOR", prod.Fornecedor);

        conex.insert("PRODUTO", null, contentValues2);
        conex.close();

    }

    public void excluir(String cod){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cod);

        conex.delete("PRODUTO", "COD = ?", parametros);


    }

    public void alterar(Produto produto){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", produto.Nome);
        contentValues.put("CATEGORIA", produto.Categoria);
        contentValues.put("DESCRICAO", produto.Descricao);
        contentValues.put("QUANTIDADE", produto.Quantidade);
        contentValues.put("UNIDADE", produto.Unidade);
        contentValues.put("VALOR", produto.Valor);
        contentValues.put("FORNECEDOR", produto.Fornecedor);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(produto);

        conex.update("PRODUTO", contentValues, "COD = ?", parametros);


    }
    public Cursor buscarProduto(){

      List<Produto>  produto = new ArrayList<Produto>();

        StringBuilder sqlp = new StringBuilder();
        sqlp.append(" SELECT NOME, COD, CATEGORIA, DESCRICAO, QUANTIDADE, UNIDADE, VALOR, FORNECEDOR");
        sqlp.append(" FROM PRODUTO");

        Cursor result2 = conex.rawQuery(sqlp.toString(), null);


        if(result2.getCount() > 0 ){

            result2.moveToFirst();
            do {

                Produto prod = new Produto();
                prod.Nome = result2.getString(result2.getColumnIndexOrThrow("NOME"));
                prod.Cod = result2.getInt(result2.getColumnIndexOrThrow("COD"));
                prod.Categoria = result2.getString(result2.getColumnIndexOrThrow("CATEGORIA"));
                prod.Descricao = result2.getString(result2.getColumnIndexOrThrow("DESCRICAO"));
                prod.Quantidade = result2.getFloat(result2.getColumnIndexOrThrow("QUANTIDADE"));
                prod.Unidade = result2.getString(result2.getColumnIndexOrThrow("UNIDADE"));
                prod.Valor = result2.getFloat(result2.getColumnIndexOrThrow("VALOR"));
                prod.Fornecedor = result2.getString(result2.getColumnIndexOrThrow("FORNECEDOR"));
            }while (result2.moveToNext());

        }
        return result2;
    }

}
