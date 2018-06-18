package com.lpt.lpt_v4;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void log(Context context_var, CharSequence msg_text){
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context_var, msg_text, duration).show();
    }
}
