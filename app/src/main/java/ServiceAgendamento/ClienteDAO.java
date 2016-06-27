package ServiceAgendamento;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import MODEL.Cliente;
import Utils.Constants;

/**
 * Created by jonascosta on 27/05/16.
 */
public class ClienteDAO {

    public Cliente[] lista(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        //192.168.1.102 casa
        WebTarget webTarget = client.target(Constants.URL+"/clientes").path("/Lista");
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

    public boolean salvar(Cliente cliente){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(Constants.URL + "/clientes").path("/Salvar");

        Gson gson = new Gson();
        String clienteJson = gson.toJson(cliente);
        Response retorno = webTarget.request().post(Entity.json(clienteJson));


        String logStatus =  String.valueOf(retorno.getStatus());
        String info =  retorno.getStatusInfo().toString();

        if (info.equals("OK")){
            return true;
        }else{
            return false;
        }





    }

    public boolean deletar(Cliente clienteDeletar){

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(Constants.URL + "/clientes");
        WebTarget webTargetExcluir = webTarget.path("{codigo}").resolveTemplate("codigo", clienteDeletar.getId());

        Response retorno = webTargetExcluir.request().delete();

        String logStatus =  String.valueOf(retorno.getStatus());
        String info =  retorno.getStatusInfo().toString();

        if (info.equals("OK")){
            return true;
        }else{
            return false;
        }

    }


}
