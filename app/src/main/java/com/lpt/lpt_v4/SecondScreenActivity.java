package com.lpt.lpt_v4;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SecondScreenActivity extends AppCompatActivity {

    private void get_request() {
        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://lpt.mycibox.com/user/2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void post_request() {
        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://lpt.mycibox.com/user/new";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    protected void register_user() throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://lpt.mycibox.com/user/new";

        final TextView textViewX = (TextView) findViewById(R.id.firstNameInput);
        String fname = textViewX.getText().toString();

        JSONObject newUserData = new JSONObject();
        newUserData.put("first_name", fname);

        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, newUserData, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView.setText("Response: " + response.toString());
                        String name = response.optString("username");
                        int stop = 0;

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int xd = 0;

                    }
                });

        queue.add(jsonObjectRequest);

    }

    private void json_get_request() {
        String url = "http://lpt.mycibox.com/user/2";

        RequestQueue queue = Volley.newRequestQueue(this);


        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView.setText("Response: " + response.toString());
                        String name = response.optString("username");
                        int stop = 0;

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);

// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        TextView tvSecondScreenResult = (TextView) findViewById(R.id.secondScreenResult);
        String text = getIntent().getExtras().getString("klucz");

        tvSecondScreenResult.setText(text);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    register_user();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);


        json_get_request();

    }
}
