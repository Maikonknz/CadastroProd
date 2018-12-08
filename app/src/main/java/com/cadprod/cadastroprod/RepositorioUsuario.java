package com.cadprod.cadastroprod;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioUsuario {

    private SQLiteDatabase conexao;

    public RepositorioUsuario(SQLiteDatabase conexao){

        this.conexao = conexao;
    }

    public void inserir(Usuario user){

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", user.Id);
        contentValues.put("NOME", user.Nome);
        contentValues.put("EMAIL", user.Email);
        contentValues.put("DATANASCIMENTO", user.Nasc);
        contentValues.put("SENHA", user.Senha);

        conexao.insert("USUARIO", null, contentValues);
        conexao.close();

    }

    public void excluir(String id){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("USUARIO", "ID = ?", parametros);


    }

    public void alterar(Usuario usuario){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", usuario.Nome);
        contentValues.put("EMAIL", usuario.Email);
        contentValues.put("DATANASCIMENTO", usuario.Nasc);
        contentValues.put("SENHA", usuario.Senha);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(usuario.Id);

        conexao.update("USUARIO", contentValues, "ID = ?", parametros);


    }
    public String buscarUsuario(String cod){
        Usuario usuario = new Usuario();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ID, NOME, EMAIL, DATANASCIMENTO, SENHA");
        sql.append(" FROM USUARIO");
        sql.append(" WHERE ID = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cod);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0 ){

            resultado.moveToFirst();

            usuario.Id = resultado.getString(resultado.getColumnIndexOrThrow("ID"));
            usuario.Nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            usuario.Email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
            usuario.Nasc = resultado.getString(resultado.getColumnIndexOrThrow("DATANASCIMENTO"));
            usuario.Senha = resultado.getString(resultado.getColumnIndexOrThrow("SENHA"));

            return String.valueOf(usuario.Id);
        }
        return null;
    }
    public String buscarSenha(String idt){
        Usuario key = new Usuario();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ID, NOME, EMAIL, DATANASCIMENTO, SENHA");
        sql.append(" FROM USUARIO");
        sql.append(" WHERE ID = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(idt);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);


        if(resultado.getCount() > 0 ){

            resultado.moveToFirst();


            key.Id = resultado.getString(resultado.getColumnIndexOrThrow("ID"));
            key.Nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            key.Email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
            key.Nasc = resultado.getString(resultado.getColumnIndexOrThrow("DATANASCIMENTO"));
            key.Senha = resultado.getString(resultado.getColumnIndexOrThrow("SENHA"));

            return String.valueOf(key.Senha);
        }
        return null;
    }


}
