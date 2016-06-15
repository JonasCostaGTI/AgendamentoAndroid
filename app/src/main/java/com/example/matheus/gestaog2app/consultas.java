package com.example.matheus.gestaog2app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import DAO.ClienteDAO;
import MODEL.Cliente;

public class consultas extends AppCompatActivity {

    public static final int RETURNCODE = 200 ;
    public  ListView listaClientes;
    public static Cliente[] clientes;

    public static Cliente getCliente(int position){
        return clientes[position];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        mostraLista();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Cadastrar Cliente");
        menu.add(0, 1, 0, "Cadastrar Usuario");
        menu.add(0, 2, 0, "Tela Login");
        menu.add(0, 3, 0, "Menu Inicial");
        menu.add(0, 4, 0, "Lista de Usuarios");


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

            case 4:
                Intent m = new Intent(this, ListaUsuarios.class);
                startActivityForResult(m, RETURNCODE);
                return true;
        }

        return false;
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            this.mostraLista();
            super.onActivityResult(requestCode, resultCode, data);
        }



    private void mostraLista() {

        //Cliente cliente = new Cliente();
        ClienteDAO clienteDao = new ClienteDAO();
        listaClientes = (ListView)findViewById(R.id.listView);
        clientes = clienteDao.lista();
        ArrayAdapter<Cliente> dataClientes =  new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);

        //pega item selecionado
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //pega cliente na posicao
                Cliente cliente = consultas.getCliente(position);

                Intent i = new Intent(view.getContext(), cadastramento.class);
                i.putExtra("ID", cliente.getId().toString());
                i.putExtra("NOME", cliente.getNome().toString());
                i.putExtra("TELEFONE", cliente.getTelefone().toString());
                i.putExtra("CPF", cliente.getCpf().toString());
                i.putExtra("DATA", cliente.getDia().toString());
                i.putExtra("HORA", cliente.getHorario().toString());
                i.putExtra("EMAIL", cliente.getEmail().toString());
                i.putExtra("SERVICO", cliente.getServico().toString());

                startActivityForResult(i, RETURNCODE);


            }
        });

        listaClientes.setAdapter(dataClientes);

    }

}
