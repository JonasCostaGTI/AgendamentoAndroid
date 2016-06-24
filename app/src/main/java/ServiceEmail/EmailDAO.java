package ServiceEmail;

import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;

import javax.annotation.Generated;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import ServiceTempo.Tempo;

/**
 * Created by jonascosta on 23/06/16.
 */

@Generated("org.jsonschema2pojo")
public class EmailDAO {

    public VerificadorEmail verifica(EditText email){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        String url = "http://apilayer.net/api/check?access_key=71d70963d7ab28e9234d567920a46f86&email="+email.getText().toString();

        WebTarget webTarget = client.target(url);
        String emailJson = webTarget.request().get(String.class);

        VerificadorEmail emailObjecto = new VerificadorEmail();
        Gson gson = new Gson();
        emailObjecto = gson.fromJson(emailJson, VerificadorEmail.class);

        return emailObjecto;

    }
}
