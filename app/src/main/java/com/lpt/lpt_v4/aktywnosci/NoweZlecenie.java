package com.lpt.lpt_v4.aktywnosci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lpt.lpt_v4.Aktywnosc;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.fasada.Zlecenia;

public class NoweZlecenie extends Aktywnosc {

    private Zlecenia zlecenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktywnosc_nowe_zlecenie);

        zlecenia = new Zlecenia(getApplicationContext(), getIntent(), getCurrentFocus());
        ustawPrzycisk(R.id.addJobBtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addJobBtn:
                zlecenia.nowe();
                break;
            default:
                break;
        }
    }

}
