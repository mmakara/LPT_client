package com.lpt.lpt_v4.fasada;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.AdresyApi;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Tools;
import com.lpt.lpt_v4.fabryka.FabrykaZlecen;

import org.json.JSONException;
import org.json.JSONObject;

public class Zlecenia {
    private Context context;
    private Intent intent;
    private View view;

    public Zlecenia(final Context context, Intent intent, View view) {
        this.context = context;
        this.intent = intent;
        this.view = view;
    }

    public void nowe(String nazwa_zlecenia, String opis_zlecenia)
    {

        String lat = intent.getStringExtra("job_lat");
        String lng = intent.getStringExtra("job_lng");

        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    AdresyApi.nowe_zlecenie,
                    FabrykaZlecen.doNowegoZlecenia(
                            intent.getStringExtra("user_id"),
                            nazwa_zlecenia,
                            opis_zlecenia,
                            lat,
                            lng

                    )
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Tools.log(context, "Zlecenia dodane!");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Tools.log(context, "Blad w dodawaniu zlecenia!");
                }
            });

            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
