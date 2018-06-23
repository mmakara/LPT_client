package com.lpt.lpt_v4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Aktywnosc extends AppCompatActivity implements View.OnClickListener {

    protected void ustawPrzycisk(int view_id) {
        findViewById(view_id)
                .setOnClickListener(this);
    }

    public void onClick(View v){

    }

    protected void uruchom_aktywnosc(
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
