package com.lpt.lpt_v4.aktywnosci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lpt.lpt_v4.R;
import com.lpt.lpt_v4.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SzczegolyZlecenia extends AppCompatActivity implements View.OnClickListener {
    private JSONObject current_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        TextView job_title = findViewById(R.id.jobDetails_title);
        TextView job_description = findViewById(R.id.jobDetails_description);

        try {
            current_job = new JSONObject(getIntent().getStringExtra("job"));

            job_title.setText(current_job.getString("title"));
            job_description.setText(current_job.getString("description"));

            final JSONArray aplikacje = current_job.getJSONArray("applicants");
            int x = 0;

            TableLayout tabelaAplikacji = findViewById(R.id.jobDetails_applicants);

            for(int i = 0; i<aplikacje.length();i++) {
                JSONObject jsonJob = aplikacje.getJSONObject(i);

                TableRow tr = new TableRow(getApplicationContext());
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView title = new TextView(getApplicationContext());
                title.setText(jsonJob.getString("username"));
                title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                tr.addView(title);

                tabelaAplikacji.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } catch (JSONException e) {
            Tools.log(getApplicationContext(), "Can't set job details! :( "+e.getMessage()+" --- "+e.getCause());
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.noweKontoBtn:
                break;
            default:
                break;
        }
    }
}
