package ServiceTempo;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;

import javax.annotation.Generated;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by jonascosta on 12/06/16.
 */
@Generated("org.jsonschema2pojo")
public class ServiceTempoDAO {

    public Tempo temperatura(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://api.openweathermap.org/data/2.5/weather?q=Gravatai&lang=pt&type=like&units=metric&appid=9c4b5927647bc9947f6f4c2be948cb4e");
        String tempJson = webTarget.request().get(String.class);


        Tempo temp = new Tempo();
        Gson gson = new Gson();
        temp = gson.fromJson(tempJson, Tempo.class);

        Log.w("Tempo", temp.getMain().getTemp());

        return  temp;


    }
}
