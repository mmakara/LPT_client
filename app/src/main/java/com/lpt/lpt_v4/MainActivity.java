package com.lpt.lpt_v4;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.aktywnosci.EkranUzytkownika;
import com.lpt.lpt_v4.aktywnosci.NoweKonto;
import com.lpt.lpt_v4.fabryka.FabrykaUzytkownikow;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Aktywnosc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ustawPrzycisk(R.id.logowanieBtn);
        ustawPrzycisk(R.id.noweKontoBtn);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.noweKontoBtn:
                uruchom_aktywnosc(NoweKonto.class, null, null);
                break;
            case R.id.logowanieBtn:
                try {
                    zaloguj_uzytkownika();
                } catch (JSONException wyjatek_logowania) {
                    Tools.log(this, wyjatek_logowania.toString());
                }
                break;
            default:
                break;
        }
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
                FabrykaUzytkownikow.doLogowania(uzytkownik, haslo),
//                prawidlowa odpowiedz
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        uruchom_aktywnosc(EkranUzytkownika.class, "uzytkownik", response.toString());
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

}
