package com.lpt.lpt_v4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewJobActivity extends AppCompatActivity {

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        user_id = getIntent().getExtras().getString("user_id");

        Button addJobBtn = (Button) findViewById(R.id.addJobBtn);
        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    add_job();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void add_job() throws JSONException
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://lpt2.mycibox.com/job/api/new";

        final TextView tvNewJobTitleInput = (TextView) findViewById(R.id.newJobTitleInput);
        final TextView tvNewJobDescription = (TextView) findViewById(R.id.newJobDescription);

        String job_title = tvNewJobTitleInput.getText().toString();
        String job_description = tvNewJobDescription.getText().toString();

        JSONObject newJobData = new JSONObject();
        newJobData.put("user_id", user_id);
        newJobData.put("title", job_title);
        newJobData.put("description", job_description);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, newJobData, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        onNewJobResponse(response);
//                        String name = response.optString("username");
                        int ok = 0;
                        Utils.log(getApplicationContext(), "Job added!");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int xd = 0;
                        Utils.log(getApplicationContext(), "Error adding job!");
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void onNewJobResponse(JSONObject response) {
        logSuccess();
    }

    private void logSuccess() {
        Utils.log(getApplicationContext(), "some msg");

    }
}
