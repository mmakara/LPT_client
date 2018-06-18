package com.lpt.lpt_v4;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JobsFragment extends Fragment {
    private User active_user;
    private JSONArray user_jobs;

    public static JobsFragment newInstance(User user) {
        JobsFragment jf = new JobsFragment();
        jf.active_user = user;

        return jf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_fragment, container, false);
//        if(listener != null)
//            listener.onTitleSet("Jestem we fragmencie 2");

        Button newJobBtn = (Button) view.findViewById(R.id.newJobBtn2);
        newJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newJobIntent = new Intent(
                        getContext(),
                        NewJobActivity.class
                );

                newJobIntent.putExtra("user_id", active_user.id);

                startActivity(newJobIntent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            fetch_jobs(active_user.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void fetch_jobs(String user_id) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://lpt2.mycibox.com/job/get_for_user/"+user_id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            onJobsResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int xd = 0;

                    }
                });


        queue.add(jsonArrayRequest);
    }


    private void onJobsResponse(JSONArray response) throws JSONException
    {
        user_jobs = response;
        int x = 0;

        TableLayout jobsTable = (TableLayout) getView().findViewById(R.id.jobsTable);

        for(int i = 0; i<user_jobs.length();i++) {
            JSONObject jsonJob = user_jobs.getJSONObject(i);

            TableRow tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            Button b = new Button(getContext());
            b.setText(jsonJob.getString("title"));
            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(b);

            Button c = new Button(getContext());
            c.setText(jsonJob.getString("title"));
            c.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(c);


            jobsTable.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

}
