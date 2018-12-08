package com.cadprod.cadastroprod;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class TelaCadProd extends AppCompatActivity {

    private RepositorioProduto repositorioProduto;
    private Produto produto;
    private SQLiteDatabase conexao;
    private DadosOpendHelper dadosOpendHelper;


    EditText editnome;
    EditText editcategoria;
    EditText editdescricao;
    EditText editquantidade;
    EditText editunidade;
    EditText editvalor;
    EditText editfornecedor;
    Integer codigo = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadprod);

        editnome = (EditText) findViewById(R.id.editnome);
        editcategoria = (EditText) findViewById(R.id.editcategoria);
        editdescricao = (EditText) findViewById(R.id.editdescricao);
        editquantidade = (EditText) findViewById(R.id.editquantidade);
        editunidade = (EditText) findViewById(R.id.editunidade);
        editvalor = (EditText) findViewById(R.id.editvalor);
        editfornecedor = (EditText) findViewById(R.id.editfornecedor);


        criarConexao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_usuario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        switch (id) {
            case R.id.actionOK:

                confirmarProd();

                break;
            case R.id.actionCancelar:

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void criarConexao(){

        try {

            dadosOpendHelper = new DadosOpendHelper(this);

            conexao = dadosOpendHelper.getWritableDatabase();

            repositorioProduto = new RepositorioProduto(conexao);

        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setMessage("Não foi possivel efetuar conexão!");
            dlg.setNeutralButton(R.string.Lbl_OK, null);
            dlg.show();


        }

    }

    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());

        return resultado;
    }

    private void confirmarProd(){

        produto = new Produto();

        if (!validaCampos()) {

                try {
                    repositorioProduto.inserirprod(produto);

                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("AVISO");
                    dlg.setMessage("Produto cadastrado com sucesso!!");
                    dlg.show();
                    finish();

                } catch (Exception ex) {

                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("ERRO");
                    dlg.setMessage(ex.getMessage());
                    dlg.setMessage("Não foi possivel cadastrar produto!");
                    dlg.show();
                }

            }
        }
    private boolean validaCampos(){

        boolean res = false;

        String nome = editnome.getText().toString();
        String categoria = editcategoria.getText().toString();
        String descricao = editdescricao.getText().toString();
        String quantidade = editquantidade.getText().toString();
        String unidade = editunidade.getText().toString();
        String valor = editvalor.getText().toString();
        String fornecedor = editfornecedor.getText().toString();

        produto.Nome = nome;
        produto.Cod = codigo;
        codigo++;
        produto.Categoria = categoria;
        produto.Descricao = descricao;
        produto.Quantidade = Float.valueOf(quantidade);
        produto.Unidade = unidade;
        produto.Valor = Float.valueOf(valor);
        produto.Fornecedor = fornecedor;

        if (res = isCampoVazio(nome)) {
            editfornecedor.requestFocus();
        } else if (res = isCampoVazio(categoria)) {
            editcategoria.requestFocus();
        } else if (res = isCampoVazio(quantidade)) {
            editquantidade.requestFocus();
        } else if (res = isCampoVazio(unidade)) {
            editunidade.requestFocus();
        }else if (res = isCampoVazio(valor)) {
            editvalor.requestFocus();
        }else if (res = isCampoVazio(fornecedor)) {
            editfornecedor.requestFocus();
        }

        if (res == true) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.Title_aviso);
            dlg.setMessage(R.string.Message_campos_invalidos_brancos);
            dlg.setNeutralButton(R.string.Lbl_OK, null);
            dlg.show();
            return res;
        }
        return res;
    }

}
