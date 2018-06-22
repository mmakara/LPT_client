package com.lpt.lpt_v4.fragmenty;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Uzytkownik;

public class FormularzUzytkownik extends Fragment {
    public Uzytkownik active_uzytkownik;

    public static FormularzUzytkownik newInstance(@Nullable Uzytkownik uzytkownik) {
        FormularzUzytkownik uff = new FormularzUzytkownik();
        if(uzytkownik != null) {
            uff.active_uzytkownik = uzytkownik;
        }

        return uff;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_user_fields, container, false);

        if(active_uzytkownik != null) {
            TextView tvUsername = (TextView) view.findViewById(R.id.userNameInput);
            TextView tvEmail = (TextView) view.findViewById(R.id.emailInput);
            TextView tvName = (TextView) view.findViewById(R.id.firstNameInput);
            TextView tvPassword = (TextView) view.findViewById(R.id.loginPassword);
            tvUsername.setText(active_uzytkownik.username);
            tvPassword.setText(active_uzytkownik.password);
            tvName.setText(active_uzytkownik.first_name);
            tvEmail.setText(active_uzytkownik.email);
        }

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(active_uzytkownik != null) {

        }
    }


}
