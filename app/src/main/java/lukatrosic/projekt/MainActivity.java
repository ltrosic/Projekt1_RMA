package lukatrosic.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickInterface{

    //Promjeniti imena izgleda u red_liste i activity_detalji, promjena API URL-a u strings i imena stringova

    private RecyclerView recyclerView;
    private AdapterListe adapterListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapterListe = new AdapterListe(this);
        adapterListe.setItemClickInterface((AdapterListe.ItemClickInterface) this);

        recyclerView.setAdapter(adapterListe);

        String url =getString(R.string.REST_API);
        Log.d("URL", url);
        RESTTask task = new RESTTask();
        task.execute(url);

    }

    private class RESTTask extends AsyncTask<String,Void, List<Crypto>> {

        @Override
        protected List<Crypto> doInBackground(String... strings) {
            String adresa = strings[0];
            try {
                URL url = new URL(adresa);

                HttpURLConnection httpURLConnection =
                        (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();
                InputStreamReader inputStreamReader =
                        new InputStreamReader((httpURLConnection.getInputStream()));

                BufferedReader bufferedReader = new BufferedReader((inputStreamReader));


                //Dodane ove dvije linije za sacuvanje podataka iz API-ja direkt u array
                Type listType = new TypeToken<ArrayList<Crypto>>(){}.getType();
                List<Crypto> yourClassList = new Gson().fromJson(bufferedReader, listType);

                bufferedReader.close();
                inputStreamReader.close();

                return yourClassList;


            } catch (MalformedURLException e) {
                Log.e("Problem adresa", e.getMessage());
            } catch (IOException e) {
                Log.e("Problem pristupa", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Crypto> cryptos) {
            adapterListe.setCrypto(cryptos);
            adapterListe.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Crypto crypto) {
        Intent intent = new Intent(this,DetaljiActivity.class);
        intent.putExtra("Crypto", crypto);
        startActivity(intent);
    }

}