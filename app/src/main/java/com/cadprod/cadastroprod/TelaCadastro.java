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

public class TelaCadastro extends AppCompatActivity {

    private RepositorioUsuario repositorioUsuario;
    private Usuario user;
    private SQLiteDatabase conexao;
    private DadosOpendHelper dadosOpendHelper;

    EditText editNome;
    EditText editEmail;
    EditText editSenha;
    EditText editNasc;
    EditText editID;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastro);

        editNome = (EditText) findViewById(R.id.editNome);
        editNasc = (EditText) findViewById(R.id.editNasc);
        editID = (EditText) findViewById(R.id.editID);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha2);

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

                confirmar();

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

            repositorioUsuario = new RepositorioUsuario(conexao);

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

    private boolean isEmailValido(String email){

        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private void confirmar(){

        user = new Usuario();

        if (!validaCampos()) {

                String aux = repositorioUsuario.buscarUsuario(user.Id);

                if (aux == null || !aux.equals(user.Id)) {

                    try {
                        repositorioUsuario.inserir(user);

                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("AVISO");
                        dlg.setMessage("Usuario cadastrado com sucesso!!");
                        dlg.show();
                        finish();

                    } catch (Exception ex) {

                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("ERRO");
                        dlg.setMessage(ex.getMessage());
                        dlg.setMessage("Não foi possivel cadastrar usuario!");
                        dlg.show();
                    }

                } else {

                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("AVISO");
                    dlg.setMessage("ID já possui cadastro!");
                    dlg.show();
                    finish();
                }
        }
    }

    private boolean validaCampos(){

        boolean res = false;

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String cod = editID.getText().toString();
        String nasc = editNasc.getText().toString();
        String senha = editSenha.getText().toString();

        user.Nome = nome;
        user.Email = email;
        user.Id = cod;
        user.Nasc = nasc;
        user.Senha = senha;

        if (res = isCampoVazio(nome)) {
            editNome.requestFocus();
        } else if (res = !isEmailValido(email)) {
            editEmail.requestFocus();
        } else if (res = isCampoVazio(cod)) {
            editID.requestFocus();
        } else if (res = isCampoVazio(senha)) {
            editSenha.requestFocus();
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
