package com.lpt.lpt_v4.fragmenty;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Uzytkownik;
import com.lpt.lpt_v4.Tools;
import com.lpt.lpt_v4.aktywnosci.SzczegolyWiadomosci;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Wiadomosci extends Fragment {
    private Uzytkownik active_uzytkownik;
    private JSONArray messages;
    protected View view;

    public static Wiadomosci newInstance(Uzytkownik uzytkownik) {
        Wiadomosci msgFragment = new Wiadomosci();
        msgFragment.active_uzytkownik = uzytkownik;

        return msgFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.messages_fragment, container, false);
        setup_messages_buttons();

        return this.view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fetchMessages(active_uzytkownik.id, "received");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void fetchMessages(String user_id, String message_type) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://lpt2.mycibox.com/message/get_" + message_type + "/" + user_id;
        Tools.log(getContext(), "Fetching "+message_type);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            onMessagesResponse(response);
                            Tools.log(getContext(), "Messages fetched!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Tools.log(getContext(), "JSON error loading jobs!");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Tools.log(getContext(), "Server error loading jobs!");
                    }
                });

        queue.add(jsonArrayRequest);
    }

    private void setup_messages_buttons() {
        setup_received_button();
        setup_sent_button();
        setup_archived_button();
    }

    private void setup_received_button() {
        setup_message_button(
                (Button) view.findViewById(R.id.getReceivedBtn),
                "received"
        );
    }

    private void setup_sent_button() {
        setup_message_button(
                (Button) view.findViewById(R.id.getSentBtn),
                "sent"
        );
    }

    private void setup_archived_button() {
        setup_message_button(
                (Button) view.findViewById(R.id.getArchivedBtn),
                "archived"
        );
    }

    private void setup_message_button(Button btn, final String message_type) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fetchMessages(active_uzytkownik.id, message_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Tools.log(getContext(), "JSON error loading "+ message_type +" messages!");
                }
            }
        });
    }

    private void onMessagesResponse(JSONArray response) throws JSONException
    {
        messages = response;

        TableLayout messagesTable = (TableLayout) getView().findViewById(R.id.messagesTable);
        messagesTable.removeAllViews();

        for(int i = 0; i<messages.length();i++) {
            TableRow job_table_row = build_message_row(messages.getJSONObject(i)) ;
            messagesTable.addView(job_table_row, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private TableRow build_message_row(final JSONObject jsonMessage) throws JSONException {

        TableRow table_row = new TableRow(getContext());

        TextView title = new TextView(getContext());
        title.setText(jsonMessage.getString("subject"));
        title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MessageDetails = new Intent(getContext(), SzczegolyWiadomosci.class);
                MessageDetails.putExtra("message", jsonMessage.toString());
                startActivity(MessageDetails);
            }
        });

        table_row.addView(title);

        return table_row;
    }

}
