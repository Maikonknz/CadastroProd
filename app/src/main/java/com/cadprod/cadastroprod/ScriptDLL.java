package com.cadprod.cadastroprod;

public class ScriptDLL {

    public static String getCreateTableUsuario() {

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS USUARIO ( ");
        sql.append("    ID                VARCHAR(20)  PRIMARY KEY NOT NULL , ");
        sql.append("    NOME              VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    EMAIL             VARCHAR(255) NOT NULL DEFAULT(''), ");
        sql.append("    DATANASCIMENTO    VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("    SENHA             VARCHAR(20)  NOT NULL DEFAULT('') ) ");

        return sql.toString();
    }
}