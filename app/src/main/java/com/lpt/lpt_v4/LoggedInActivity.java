package com.lpt.lpt_v4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoggedInActivity extends AppCompatActivity {

    private String user_type = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String text = getIntent().getExtras().getString("klucz");

        TextView tvLoginResponse = (TextView) findViewById(R.id.loginResponse);
        tvLoginResponse.setText(text);

        Button openProfileBtn = (Button) findViewById(R.id.openProfileBtn);
        openProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityToOpen = new Intent(
                        getApplicationContext(),
                        ProfileUser.class
                );
                startActivity(activityToOpen);
            }
        });

        Button openJobsBtn = (Button) findViewById(R.id.openJobsBtn);
        openJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityToOpen = new Intent(
                        getApplicationContext(),
                        JobsUserActivity.class
                );
                startActivity(activityToOpen);
            }
        });

        Button openReviewsBtn = (Button) findViewById(R.id.openReviewsBtn);
        openReviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityToOpen = new Intent(
                        getApplicationContext(),
                        ReviewsActivity.class
                );
                startActivity(activityToOpen);
            }
        });
    }



}
