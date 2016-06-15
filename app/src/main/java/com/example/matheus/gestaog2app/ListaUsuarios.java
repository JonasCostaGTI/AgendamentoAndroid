package com.example.matheus.gestaog2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import DAO.UsuarioDAO;
import MODEL.Cliente;
import MODEL.Usuario;

public class ListaUsuarios extends AppCompatActivity {

    private static final int RETURNCODE = 200;
    public  ListView listaDeUsuarios;
    public static Usuario[] usuarios;

    public static Usuario getUsuario(int position){
        return usuarios[position];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listaUsuarios();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Cadastrar Cliente");
        menu.add(0, 1, 0, "Cadastrar Usuario");
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

            case 1:
                Intent j = new Intent(this, CadastroUsuario.class);
                startActivityForResult(j, RETURNCODE);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.listaUsuarios();
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void listaUsuarios(){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        listaDeUsuarios = (ListView) findViewById(R.id.lista_usuarios);
        usuarios = usuarioDAO.lista();


        ArrayAdapter<Usuario> dataUsuario =  new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, usuarios);



        //pega item selecionado
        listaDeUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //pega cliente na posicao
                Usuario usuario = ListaUsuarios.getUsuario(position);

                Intent i = new Intent(view.getContext(), CadastroUsuario.class);
                i.putExtra("ID", usuario.getId().toString());
                i.putExtra("NOME", usuario.getNome().toString());
                i.putExtra("LOGIN", usuario.getUsuario().toString());
                i.putExtra("SENHA", usuario.getSenha().toString());


                startActivityForResult(i, RETURNCODE);


            }
        });


        listaDeUsuarios.setAdapter(dataUsuario);





    }

}
