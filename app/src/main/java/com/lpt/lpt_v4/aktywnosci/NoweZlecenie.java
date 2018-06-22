package com.lpt.lpt_v4.aktywnosci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class NoweZlecenie extends AppCompatActivity {
    private String user_id;
    private String job_lat;
    private String job_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        user_id = getIntent().getExtras().getString("user_id");
        job_lat = getIntent().getExtras().getString("job_lat");
        job_lng = getIntent().getExtras().getString("job_lng");

        Button addJobBtn = (Button) findViewById(R.id.addJobBtn);
        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 add_job();
            }
        });
    }

    public void add_job()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://lpt2.mycibox.com/job/api/new";
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    AdresyApi.nowe_zlecenie,
                    FabrykaZlecen.doNowegoZlecenia(
                            user_id,
                            Tools.stringZTextView(findViewById(R.id.newJobTitleInput)),
                            Tools.stringZTextView(findViewById(R.id.newJobDescription)),
                            job_lat,
                            job_lng
                    )
                    , new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Tools.log(getApplicationContext(), "Zlecenie dodane!");
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Tools.log(getApplicationContext(), "Blad w dodawaniu zlecenia!");
                }
            });

            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
