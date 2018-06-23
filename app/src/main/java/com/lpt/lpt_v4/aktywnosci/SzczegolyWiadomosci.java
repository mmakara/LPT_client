package com.lpt.lpt_v4.aktywnosci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Tools;

import org.json.JSONException;
import org.json.JSONObject;

public class SzczegolyWiadomosci extends AppCompatActivity implements View.OnClickListener {
    private JSONObject current_message;
    private JSONObject current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        TextView subject = findViewById(R.id.messageDetails_subject);
        TextView body = findViewById(R.id.messageDetails_body);

        try {
            current_message = new JSONObject(getIntent().getStringExtra("message"));
            subject.setText(current_message.getString("subject"));
            body.setText(current_message.getString("body"));
        } catch (JSONException e) {
            Tools.log(getApplicationContext(), "Can't set message details! :( "+e.getMessage()+" --- "+e.getCause());
        }
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
//                EkranUzytkownika.class
//        );
////
//        startSecondScreenIntent.putExtra("user", response.toString());
//        startActivity(startSecondScreenIntent);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.replyBtn:
                try {
                    reply_to_message();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
