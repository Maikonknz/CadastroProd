package com.cadprod.cadastroprod;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



/**
 *
 */
public class TelaProduto extends AppCompatActivity {

    private SQLiteDatabase conex;
    private DadosOpendHelper2 dadosOpendHelper2;
    private RepositorioProduto repositorioProduto;

    Button CadProd;
    Button ListProd;
    Button ModProd;
    Button ExcProd;
    Button Perf;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaproduto);


        CadProd = (Button)findViewById(R.id.CadProd);
        ListProd = (Button)findViewById(R.id.ListProd);
        ModProd = (Button)findViewById(R.id.ModProd);
        ExcProd = (Button)findViewById(R.id.ExcProd);
        Perf = (Button)findViewById(R.id.Perf);

        criarConex();

        CadProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadProd = new Intent(TelaProduto.this, TelaCadProd.class);
                startActivity(abreCadProd);
            }
        });

        ListProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrelistprod = new Intent(TelaProduto.this, TelaListaProd.class);
                startActivity(abrelistprod);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_prod, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        switch (id) {
            case R.id.actionVoltar:

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void criarConex(){

        try {

            dadosOpendHelper2 = new DadosOpendHelper2(this);

             conex = dadosOpendHelper2.getWritableDatabase();

            repositorioProduto = new RepositorioProduto(conex);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setMessage("Não foi possivel efetuar conexão!");
            dlg.setNeutralButton(R.string.Lbl_OK, null);
            dlg.show();


        }
    }
}
