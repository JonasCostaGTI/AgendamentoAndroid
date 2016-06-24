package com.example.matheus.gestaog2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ServiceAgendamento.UsuarioDAO;
import MODEL.Usuario;

public class CadastroUsuario extends AppCompatActivity {

    private static final int RETURNCODE = 200 ;
    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    private boolean alterado = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        if (this.getIntent().hasExtra("ID")) {
            alterado = true;
            this.id = Integer.parseInt(this.getIntent().getStringExtra("ID"));

            this.mostraUsuarios(id);
        } else {
            alterado = false;
        }

        Button salvar = (Button) findViewById(R.id.btn_salvar_usuario);
        Button apagar = (Button) findViewById(R.id.btn_apagar_usuario);


        if (alterado) {
            if (apagar != null) {
                apagar.setVisibility(View.VISIBLE);
            }
        } else {
            if (apagar != null) {
                apagar.setVisibility(View.INVISIBLE);
            }
        }


        assert salvar != null;
        salvar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome = (EditText) findViewById(R.id.nome_usuario);
                EditText login = (EditText) findViewById(R.id.login_usuario);
                EditText senha = (EditText) findViewById(R.id.senha_usuario);


                if (!campos(nome, login, senha)) {

                    if (alterado){
                        usuario.setId((long) id);
                    }

                    usuario.setNome(nome.getText().toString());
                    usuario.setUsuario(login.getText().toString());
                    usuario.setSenha(senha.getText().toString());

                    usuarioDAO.salvar(usuario);
                    usuarioDAO.lista();
                    finish();
                }


            }


            //verifica se os campos estao vazios
            private boolean campos(EditText... fields) {
                boolean empty = false;

                for (EditText field : fields) {

                    if (TextUtils.isEmpty(field.getText().toString())) {
                        empty = true;
                        field.setError("campo deve ser preexido");
                    }

                }

                return empty;
            }

        });


        if (apagar != null) {
            apagar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    usuario.setId((long) id);
                    usuarioDAO.deletar(usuario);
                    usuarioDAO.lista();
                    finish();

                }
            });
        }



    }

    private void mostraUsuarios(int id) {


        EditText nome =  (EditText) findViewById(R.id.nome_usuario);
        nome.setText(this.getIntent().getStringExtra("NOME"));

        EditText login =  (EditText) findViewById(R.id.login_usuario);
        login.setText(this.getIntent().getStringExtra("LOGIN"));

        EditText senha =  (EditText) findViewById(R.id.senha_usuario);
        senha.setText(this.getIntent().getStringExtra("SENHA"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Cadastrar Cliente");
        menu.add(0, 2, 0, "Tela Login");
        menu.add(0, 3, 0, "Menu Inicial");


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case 0:
                Intent i = new Intent(this, cadastramento.class);
                startActivityForResult(i, RETURNCODE);
                return true;
            
            case 2:
                Intent k = new Intent(this, Login.class);
                startActivityForResult(k, RETURNCODE);
                return true;

            case 3:
                Intent l = new Intent(this, Navigation.class);
                startActivityForResult(l, RETURNCODE);
                return true;
        }

        return false;
    }

}
