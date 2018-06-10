package com.lpt.lpt_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

//    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TableLayout tl = (TableLayout) findViewById(R.id.searchTable);

//        TableLayout tl = new TableLayout(this);

//        Items items_data[] = new Items[] {
//                new Items(R.drawable.ic_launcher, "Samsung", "400", "Hot Item"),
//                new Items(R.drawable.ic_launcher, "Samsung1", "4001", "Hot Item1"),
//        };

//        for (Items items : items_data) {
            TableRow tr = new TableRow(this);

//            ImageView iv = new ImageView(this);
//            iv.setBackgroundResource(items.icon);
//            tr.addView(iv);

            TextView label = new TextView(this);
            label.setText("dssd");
            tr.addView(label);

//            TextView price = new TextView(this);
//            price.setText(items.price);
//            tr.addView(price);
//
//            TextView offer = new TextView(this);
//            offer.setText(items.offer);
//            tr.addView(offer);
//
            tl.addView(tr);
//        }

    }
}
