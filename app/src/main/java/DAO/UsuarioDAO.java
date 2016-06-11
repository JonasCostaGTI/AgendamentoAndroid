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
import MODEL.Usuario;

/**
 * Created by jonascosta on 27/05/16.
 */
public class UsuarioDAO {

    public Usuario[] lista(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/usuarios").path("/Lista");
        String usuarioJson = webTarget.request().get(String.class);

        //close a conexao
        Response response = webTarget.path("service").request().get();
        response.readEntity(String.class);
        response.close();

        Usuario[] usuarios;

        Gson gson = new Gson();
        Usuario usuario = new Usuario();
        usuarios = gson.fromJson(usuarioJson, Usuario[].class);


        return usuarios;

    }

    public void salvar(Usuario usuarioSalvar){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/usuarios").path("/Salvar");

        //feixa conexao
        Response response = webTarget.path("service").request().get();
        response.readEntity(String.class);
        response.close();


        Gson gson = new Gson();
        String usuarioJson = gson.toJson(usuarioSalvar);


        Log.w("Usuario", usuarioJson);
        webTarget.request().post(Entity.json(usuarioJson));



    }

    public void deletar(Usuario usuarioDeletar){

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/usuarios");
        WebTarget webTargetExcluir = webTarget.path("{codigo}").resolveTemplate("codigo", usuarioDeletar.getId());

        //requisicao para deletar
        webTargetExcluir.request().delete();

    }

//    public Usuario buscaPorId(Usuario usuarioId){
//
//
//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://192.168.1.102:8081/Agendamento/service/usuarios");
//        WebTarget usuarioWebTarget = webTarget.path("{codigo}").resolveTemplate("codigo", usuarioId.getId());
//
//
//        String usuarioJson =  webTarget.request().get(String.class);
//
//        Gson gson = new Gson();
//        Usuario usuario = new Usuario();
//        usuario = gson.fromJson(usuarioJson, Usuario.class);
//
//        Log.w("Usuario", usuario.getNome().toString());
//
//
//        return usuario;
//
//    }



}
