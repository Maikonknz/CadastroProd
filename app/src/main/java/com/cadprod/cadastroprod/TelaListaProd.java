package com.cadprod.cadastroprod;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class TelaListaProd extends Activity implements AdapterView.OnItemClickListener {


    Cursor cursor;
    SimpleCursorAdapter ad;
    ListView listViewProdutos;
    RepositorioProduto repositorioProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telalistaprod);

        BuscarDados();

        CriarListagem();

    }
    public void BuscarDados(){

    try {

        cursor = repositorioProduto.buscarProduto();
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("AVISO");
        dlg.setMessage("Dados carregados com sucesso!!");
        dlg.show();
        finish();

    }catch(Exception e){

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("ERRO");
        dlg.setMessage("Não foi possivel buscar os dados!!");
        dlg.show();
        finish();
    }

    }

    public void CriarListagem(){

        try {
            listViewProdutos = (ListView) findViewById(R.id.List);

            String[] from = {"codigo", "nome", "descricao", "fornecedor", "quantidade", "unidade", "valor"};

            int[] to = {R.id.textcodigo, R.id.textnome, R.id.textdescricao, R.id.textfornecedor, R.id.textquantidade, R.id.textunidade};
            ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.telalistaprod, cursor, from, to);

            listViewProdutos.setOnItemClickListener(this);

            listViewProdutos.setAdapter(ad);
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(e.getMessage());
            dlg.setMessage("Não foi possivel efetuar conexão!");
            dlg.setNeutralButton(R.string.Lbl_OK, null);
            dlg.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        SQLiteCursor sqlcursor = (SQLiteCursor) ad.getItem(position);
        String nome = sqlcursor.getString(sqlcursor.getColumnIndex("nome"));

        Toast.makeText(getApplicationContext(),"Selecionou o nome: " + nome, Toast.LENGTH_SHORT).show();
    }
}