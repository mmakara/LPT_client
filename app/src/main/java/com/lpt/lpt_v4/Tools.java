package com.lpt.lpt_v4;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.fabryka.FabrykaZlecen;

import org.json.JSONException;
import org.json.JSONObject;

public class Tools {
    public static void log(Context context_var, CharSequence msg_text){
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context_var, msg_text, duration).show();
    }

    public static String stringZTextView(View view) {
        TextView tv = (TextView) view;

        return tv.getText().toString();
    }

    public static void wczytajFragment(FragmentManager fragmentManager, Fragment fragment, int id_pojemnika) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.addToBackStack(null);
        ft.replace(id_pojemnika, fragment);
        ft.commit();
    }
}
