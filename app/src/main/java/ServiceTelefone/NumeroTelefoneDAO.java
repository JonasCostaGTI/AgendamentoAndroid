package ServiceTelefone;

import android.os.StrictMode;
import android.widget.EditText;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import ServiceEmail.VerificadorEmail;

/**
 * Created by jonascosta on 23/06/16.
 */
public class NumeroTelefoneDAO {

    public NumeroTelefone formatoValido(EditText numero){

        String url = "http://apilayer.net/api/validate?access_key=571f5ff4394f11f35f34cf153167da64&number=55" + numero.getText().toString() + "&country_code=&format=1";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();


        WebTarget webTarget = client.target(url);
        String telefoneJson = webTarget.request().get(String.class);

        NumeroTelefone numeroTelefone = new NumeroTelefone();
        Gson gson = new Gson();
        numeroTelefone = gson.fromJson(telefoneJson, NumeroTelefone.class);

        return numeroTelefone;

    }
}
