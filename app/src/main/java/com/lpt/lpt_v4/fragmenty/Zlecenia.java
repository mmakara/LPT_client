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
import com.lpt.lpt_v4.Uzytkownik;
import com.lpt.lpt_v4.aktywnosci.NoweZlecenie;
import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Tools;
import com.lpt.lpt_v4.aktywnosci.SzczegolyZlecenia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Zlecenia extends Fragment {
    private Uzytkownik active_uzytkownik;
    private JSONArray jobs;

    public static Zlecenia newInstance(Uzytkownik uzytkownik) {
        Zlecenia job_fragment = new Zlecenia();
        job_fragment.active_uzytkownik = uzytkownik;

        return job_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_fragment, container, false);
        setup_new_job_button(view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fetch_jobs(active_uzytkownik.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setup_new_job_button(View view) {
        Button newJobBtn = (Button) view.findViewById(R.id.getReceivedBtn);
        newJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newJobIntent = new Intent(
                        getContext(),
                        NoweZlecenie.class
                );

                newJobIntent.putExtra("user_id", active_uzytkownik.id);
                newJobIntent.putExtra("job_lat", active_uzytkownik.lat);
                newJobIntent.putExtra("job_lng", active_uzytkownik.lng);
                startActivity(newJobIntent);
            }
        });
    }

    private void fetch_jobs(String user_id) throws JSONException {
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

    private void onJobsResponse(JSONArray response) throws JSONException
    {
        jobs = response;

        TableLayout jobsTable = (TableLayout) getView().findViewById(R.id.jobsTable);
        jobsTable.removeAllViews();

        for(int i = 0; i<jobs.length();i++) {
            TableRow job_table_row = do_job_row(jobs.getJSONObject(i)) ;
            jobsTable.addView(job_table_row, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private TableRow do_job_row(final JSONObject jsonJob) throws JSONException {

        TableRow table_row = new TableRow(getContext());

        TextView title = new TextView(getContext());
        title.setText(jsonJob.getString("title"));
        title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JobDetails = new Intent(getContext(), SzczegolyZlecenia.class);
                JobDetails.putExtra("job", jsonJob.toString());
                startActivity(JobDetails);
            }
        });

        TextView created_at = new TextView(getContext());
        created_at.setText(jsonJob.getString("created_at"));
        created_at.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        table_row.addView(created_at);
        table_row.addView(title);

        return table_row;
    }

}
