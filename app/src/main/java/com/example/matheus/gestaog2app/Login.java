package com.example.matheus.gestaog2app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ServiceAgendamento.UsuarioDAO;
import MODEL.Usuario;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        assert btnEntrar != null;



        btnEntrar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {


                EditText login = (EditText) findViewById(R.id.login_usuario);
                EditText senha = (EditText) findViewById(R.id.senha_usuario);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario[] usuarios = usuarioDAO.lista();


                boolean encontrou = false;
                for (Usuario user: usuarios) {

                    if(user.getUsuario().toString().equals(login.getText().toString()) && user.getSenha().toString().equals(senha.getText().toString())){

                        Toast toast = Toast.makeText(getApplicationContext(), "Bem vindo " + user.getNome().toString(), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();

                        Log.w("User", login.getText().toString());
                        Intent intent = new Intent();
                        intent.setClass(Login.this, consultas.class);

                        startActivity(intent);
                        finish();
                        encontrou = true;

                    }
                }

                if(!encontrou){
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario ou senha invalidos ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();

                    login.setText("");
                    senha.setText("");
                }











                }
        });

    }
}
