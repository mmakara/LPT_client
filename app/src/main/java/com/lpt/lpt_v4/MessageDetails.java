package com.lpt.lpt_v4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageDetails extends AppCompatActivity {
    private JSONObject current_message;
    private JSONObject current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        setup_reply_button();

        TextView subject = findViewById(R.id.messageDetails_subject);
        TextView body = findViewById(R.id.messageDetails_body);

        try {
            current_message = new JSONObject(getIntent().getStringExtra("message"));
            subject.setText(current_message.getString("subject"));
            body.setText(current_message.getString("body"));


//            final JSONArray aplikacje = current_message.getJSONArray("applicants");
//            int x = 0;

//            TableLayout tabelaAplikacji = findViewById(R.id.jobDetails_applicants);
//
//            for(int i = 0; i<aplikacje.length();i++) {
//                JSONObject jsonJob = aplikacje.getJSONObject(i);
//
//                TableRow tr = new TableRow(getApplicationContext());
//                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//                TextView title = new TextView(getApplicationContext());
//                title.setText(jsonJob.getString("username"));
//                title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//                tr.addView(title);
//
//                tabelaAplikacji.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//            }
        } catch (JSONException e) {
            Utils.log(getApplicationContext(), "Can't set message details! :( "+e.getMessage()+" --- "+e.getCause());
        }
    }

    private void setup_reply_button() {
        Button replyBtn = findViewById(R.id.replyBtn);
        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    reply_to_message();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void reply_to_message() throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://lpt2.mycibox.com/message/new/api";

        final TextView tvReplyBody = (TextView) findViewById(R.id.replyBody);

        JSONObject newUserData = new JSONObject();
        newUserData.put("subject", "RE: " + current_message.getString("subject"));
        newUserData.put("to_user", current_message.getJSONObject("from_user").getString("id"));
        newUserData.put("from_user", current_message.getJSONObject("to_user").getString("id"));
        newUserData.put("body", tvReplyBody.getText());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, newUserData, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        onReplyToMessageResponse(response);

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

    public void onReplyToMessageResponse(JSONObject response) {
//        TextView tv3 = (TextView) findViewById(R.id.textView7);
//        tv3.setText("Response: " + );
////        int 4 =
//        Intent startSecondScreenIntent = new Intent(
//                getApplicationContext(),
//                LoggedInActivity.class
//        );
////
//        startSecondScreenIntent.putExtra("user", response.toString());
//        startActivity(startSecondScreenIntent);
    }

}
