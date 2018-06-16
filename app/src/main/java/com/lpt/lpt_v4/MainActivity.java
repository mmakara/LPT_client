package com.lpt.lpt_v4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.doFloatingButtonStuff();


        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSearchIntent = new Intent(
                        getApplicationContext(),
                        SearchActivity.class
                );

                startSearchIntent.putExtra("klucz", "wartosc");
                startActivity(startSearchIntent);
            }
        });

        Button newAccountBtn = (Button) findViewById(R.id.newAccountBtn);
        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSecondScreenIntent = new Intent(
                        getApplicationContext(),
                        RegisterActivity.class
                );

                startSecondScreenIntent.putExtra("klucz", "wartosc");
                startActivity(startSecondScreenIntent);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    login_user();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    private void doFloatingButtonStuff() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void login_user() throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://lpt2.mycibox.com/login";

        final TextView tvUsername = (TextView) findViewById(R.id.loginUser);
        final TextView tvPassword = (TextView) findViewById(R.id.loginPassword);

        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        JSONObject newUserData = new JSONObject();
        newUserData.put("username", username);
        newUserData.put("password", password);

//        final TextView mTextView = (TextView) findViewById(R.id.secondScreenResult);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, newUserData, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        onLoginResponse(response);
//                        String name = response.optString("username");
                        int ok = 0;

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

    public void onLoginResponse(JSONObject response) {
//        TextView tv3 = (TextView) findViewById(R.id.textView7);
//        tv3.setText("Response: " + );
//        int 4 =
        Intent startSecondScreenIntent = new Intent(
                getApplicationContext(),
                LoggedInActivity.class
        );
//
        String respx = response.toString();
        startSecondScreenIntent.putExtra("klucz", respx);
        startActivity(startSecondScreenIntent);
    }
}
