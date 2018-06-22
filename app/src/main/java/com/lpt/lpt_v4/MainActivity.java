package com.lpt.lpt_v4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.aktywnosci.EkranUzytkownika;
import com.lpt.lpt_v4.aktywnosci.NoweKonto;
import com.lpt.lpt_v4.fabryka.FabrykaUzytkownika;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button noweKontoBtn = (Button) findViewById(R.id.noweKontoBtn);
        noweKontoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uruchom_aktywnosc(NoweKonto.class, null, null);
            }
        });

        Button logowanieBtn = (Button) findViewById(R.id.logowanieBtn);
        logowanieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    zaloguj_uzytkownika();
                } catch (JSONException e) {
                    log("Blad logowania");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void zaloguj_uzytkownika() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String uzytkownik = Tools.stringZTextView(findViewById(R.id.loginUser));
        String haslo = Tools.stringZTextView(findViewById(R.id.loginPassword));

        JsonObjectRequest requestLogowanie = new JsonObjectRequest(
                Request.Method.POST,
                AdresyApi.logowanie,
                FabrykaUzytkownika.doLogowania(uzytkownik, haslo),
//                prawidlowa odpowiedz
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        uruchom_aktywnosc(EkranUzytkownika.class, "user", response.toString());
                    }
                },
//                blad
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        log("Blad po stronie serwera logowania");
                    }
                });

        queue.add(requestLogowanie);
    }

    private void log(String wiadomosc) {
        Tools.log(getApplicationContext(), wiadomosc);
    }

    private void uruchom_aktywnosc(
            Class klasa_aktywnosci,
            @Nullable String extra_klucz,
            @Nullable String extra_wartosc
    ) {
        Intent startSecondScreenIntent = new Intent(
                getApplicationContext(),
                klasa_aktywnosci
        );

        if(extra_klucz != null && extra_wartosc != null) {
            startSecondScreenIntent.putExtra(extra_klucz, extra_wartosc);
        }

        startActivity(startSecondScreenIntent);
    }
}
