package DAO;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import MODEL.Cliente;

/**
 * Created by jonascosta on 27/05/16.
 */
public class ClienteDAO {

    public Cliente[] lista(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/clientes").path("/Lista");
        String clienteJson = webTarget.request().get(String.class);

        //close a conexao
        Response response = webTarget.path("service").request().get();
        response.readEntity(String.class);
        response.close();

        Cliente[] clientes;

        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        clientes = gson.fromJson(clienteJson, Cliente[].class);


        return clientes;

    }

    public void salvar(Cliente clienteSalvar){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/clientes").path("/Salvar");

        //feixa conexao
        Response response = webTarget.path("service").request().get();
        response.readEntity(String.class);
        response.close();


        Gson gson = new Gson();
        String clienteJson = gson.toJson(clienteSalvar);


        Log.w("Cliente", clienteJson);
        webTarget.request().post(Entity.json(clienteJson));



    }

    public void deletar(Cliente clienteDeletar){

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/clientes");
        WebTarget webTargetExcluir = webTarget.path("{codigo}").resolveTemplate("codigo", clienteDeletar.getId());

        //requisicao para deletar
        webTargetExcluir.request().delete();

    }


}
