package com.lpt.lpt_v4.aktywnosci;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lpt.lpt_v4.Uzytkownik;
import com.lpt.lpt_v4.fragmenty.Wiadomosci;
import com.lpt.lpt_v4.fragmenty.EdycjaProfilu;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.fabryka.FabrykaUzytkownika;
import com.lpt.lpt_v4.fragmenty.Zlecenia;

import org.json.JSONException;
import org.json.JSONObject;

public class EkranUzytkownika extends AppCompatActivity {

    private Uzytkownik active_uzytkownik = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            JSONObject userJsonObject = new JSONObject(getIntent().getStringExtra("user"));
            active_uzytkownik = FabrykaUzytkownika.zApi(userJsonObject);

            TextView tvLoginResponse = (TextView) findViewById(R.id.loginResponse);
            tvLoginResponse.setText(getIntent().getStringExtra("user"));

            setupFragmentButtons();
            loadFragment(EdycjaProfilu.newInstance(active_uzytkownik));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void setupFragmentButtons() {

        Button profile = (Button) findViewById(R.id.openProfileBtn);
        Button jobs = (Button) findViewById(R.id.openJobsBtn);
        Button messages = (Button) findViewById(R.id.openMessagesBtn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(EdycjaProfilu.newInstance(active_uzytkownik));
            }
        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(Zlecenia.newInstance(active_uzytkownik));
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(Wiadomosci.newInstance(active_uzytkownik));
            }
        });

//
//        messages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(Fragment3.newInstance());
//            }
//        });

    }

}
