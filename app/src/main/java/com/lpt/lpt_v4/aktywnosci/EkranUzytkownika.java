package com.lpt.lpt_v4.aktywnosci;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.lpt.lpt_v4.Aktywnosc;
import com.lpt.lpt_v4.Tools;
import com.lpt.lpt_v4.Uzytkownik;
import com.lpt.lpt_v4.fragmenty.Wiadomosci;
import com.lpt.lpt_v4.fragmenty.EdycjaProfilu;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.fabryka.FabrykaUzytkownikow;
import com.lpt.lpt_v4.fragmenty.Zlecenia;

import org.json.JSONException;
import org.json.JSONObject;

public class EkranUzytkownika extends Aktywnosc implements View.OnClickListener {

    private Uzytkownik uzytkownik = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_uzytkownika);

        try {
            JSONObject uzytkownikJson = new JSONObject(getIntent().getStringExtra("uzytkownik"));
            uzytkownik = FabrykaUzytkownikow.zApi(uzytkownikJson);

            ustawPrzycisk(R.id.openProfileBtn);
            ustawPrzycisk(R.id.openJobsBtn);
            ustawPrzycisk(R.id.openMessagesBtn);

            wczytajFragment(EdycjaProfilu.newInstance(uzytkownik));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void wczytajFragment(Fragment fragment) {
        Tools.wczytajFragment(
                getSupportFragmentManager(),
                fragment,
                R.id.content_frame
        );
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.openProfileBtn:
                wczytajFragment(EdycjaProfilu.newInstance(uzytkownik));
                break;
            case R.id.openJobsBtn:
                wczytajFragment(Zlecenia.newInstance(uzytkownik));
                break;
            case R.id.openMessagesBtn:
                wczytajFragment(Wiadomosci.newInstance(uzytkownik));
                break;
            default:
                break;
        }
    }

}
