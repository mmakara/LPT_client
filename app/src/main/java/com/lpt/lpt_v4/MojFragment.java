package com.lpt.lpt_v4;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MojFragment extends Fragment implements View.OnClickListener {

    protected void ustawPrzycisk(int view_id) {
        getView().findViewById(view_id)
                .setOnClickListener(this);
    }

    public void onClick(View v){

    }
}
