package com.lpt.lpt_v4;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoggedInActivity extends AppCompatActivity {

    private User active_user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            JSONObject userJsonObject = new JSONObject(getIntent().getStringExtra("user"));
            active_user = new User();
            active_user.id = userJsonObject.getString("id");
            active_user.first_name = userJsonObject.getString("first_name");
            active_user.username = userJsonObject.getString("username");
            active_user.email = userJsonObject.getString("email");
            active_user.account_type = userJsonObject.getString("account_type");

            TextView tvLoginResponse = (TextView) findViewById(R.id.loginResponse);
            tvLoginResponse.setText(getIntent().getStringExtra("user"));

            setupFragmentButtons();
            loadFragment(ProfileFragment.newInstance(active_user));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void setupFragmentButtons() {

        Button profile = (Button) findViewById(R.id.openProfileBtn);
        Button jobs = (Button) findViewById(R.id.openJobsBtn);
        Button messages = (Button) findViewById(R.id.openMessagesBtn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(ProfileFragment.newInstance(active_user));
            }
        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(JobsFragment.newInstance(active_user));
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(MessagesFragment.newInstance(active_user));
            }
        });

//
//        messages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(Fragment3.newInstance());
//            }
//        });

    }

}
