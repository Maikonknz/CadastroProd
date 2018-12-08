package com.cadprod.cadastroprod;

public class ScriptPROD {

    public static String getCreateTableProduto() {

        StringBuilder sqlProd = new StringBuilder();
        sqlProd.append("CREATE TABLE IF NOT EXISTS PRODUTO ( ");
        sqlProd.append("    COD                  INTEGER       PRIMARY KEY NOT NULL,, ");
        sqlProd.append("    NOME                 VARCHAR(100)   NOT NULL DEFAULT(''), ");
        sqlProd.append("    CATEGORIA            VARCHAR(20)    NOT NULL DEFAULT(''), ");
        sqlProd.append("    DESCRICAO            VARCHAR(300)   NOT NULL DEFAULT(''), ");
        sqlProd.append("    QUANTIDADE           VARCHAR(20)    NOT NULL DEFAULT(''), ");
        sqlProd.append("    UNIDADE              VARCHAR(2)     NOT NULL DEFAULT(''), ");
        sqlProd.append("    VALOR                VARCHAR(20)    NOT NULL DEFAULT(''), ");
        sqlProd.append("    FORNECEDOR           VARCHAR(300)   NOT NULL DEFAULT('') ) ");


        return sqlProd.toString();
    }
}
