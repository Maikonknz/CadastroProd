package com.cadprod.cadastroprod;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaLogin extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DadosOpendHelper dadosOpendHelper;
    private RepositorioUsuario repositorioUsuario;
    private String senha;
    private String id;

    EditText editID, editSenha1;
    Button  btnLogar;
    TextView txtCadastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = (EditText)findViewById(R.id.editID);
        editSenha1 = (EditText)findViewById(R.id.editSenha1);
        btnLogar = (Button)findViewById(R.id.btnLogar);
        txtCadastro = (TextView)findViewById(R.id.txtCadastro);
        id = new String();
        senha = new String();

        criarConexao();

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(abreCadastro);
            }
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = editID.getText().toString();
                senha = editSenha1.getText().toString();

                String id2 = repositorioUsuario.buscarUsuario(id);

                if(id2 != null && id2.equals(id)) {

                    String senha2 = repositorioUsuario.buscarSenha(id);

                    if(senha2 != null && senha2.equals(senha)){
                        Intent abreProduto = new Intent(TelaLogin.this, TelaProduto.class);
                        startActivity(abreProduto);
                    }else{
                        AlertDialog.Builder dlg = new AlertDialog.Builder(TelaLogin.this);
                        dlg.setTitle("AVISO!");
                        dlg.setMessage("Senha incorreta!");
                        dlg.setNeutralButton(R.string.Lbl_OK, null);
                        dlg.show();
                    }
                }else{

                    AlertDialog.Builder dlg = new AlertDialog.Builder(TelaLogin.this);
                    dlg.setTitle("AVISO!");
                    dlg.setMessage("Id não cadastrado ou incorreto!");
                    dlg.setNeutralButton(R.string.Lbl_OK, null);
                    dlg.show();
                }
            }
        });
            }
    private void criarConexao(){

        try {

            dadosOpendHelper = new DadosOpendHelper(this);

            conexao = dadosOpendHelper.getWritableDatabase();

            repositorioUsuario = new RepositorioUsuario(conexao);

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
