package com.lpt.lpt_v4;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MessagesFragment extends Fragment {
    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
//        if(listener != null)
//            listener.onTitleSet("Jestem we fragmencie 2");

        return view;
    }

    protected void fetchMessages() throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://lpt2.mycibox.com/messages";


        JSONObject newUserData = new JSONObject();
        newUserData.put("username", "username");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, newUserData, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        onMessagesResponse(response);
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

    protected void onMessagesResponse(JSONObject response) {

    }


}
