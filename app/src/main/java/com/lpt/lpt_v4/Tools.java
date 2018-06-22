package com.lpt.lpt_v4;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Tools {
    public static void log(Context context_var, CharSequence msg_text){
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context_var, msg_text, duration).show();
    }

    public static String stringZTextView(View view) {
        TextView tv = (TextView) view;

        return tv.getText().toString();
    }
}
