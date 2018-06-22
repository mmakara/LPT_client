package com.lpt.lpt_v4.fragmenty;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Uzytkownik;

public class EdycjaProfilu extends Fragment {
    public Uzytkownik active_uzytkownik;

    public static EdycjaProfilu newInstance(@Nullable Uzytkownik uzytkownik) {
        EdycjaProfilu pf = new EdycjaProfilu();
        if(uzytkownik != null) {
            pf.active_uzytkownik = uzytkownik;
        }

        return pf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.profile_container, FormularzUzytkownik.newInstance(active_uzytkownik));
        ft.commit();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        loadFragment(FormularzUzytkownik.newInstance(this.active_uzytkownik));

        return view;
    }

    protected void loadFragment(Fragment fragment) {

    }


}
