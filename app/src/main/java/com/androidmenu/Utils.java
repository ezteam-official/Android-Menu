package com.androidmenu;

import android.content.Context;

public class Utils {

    //-- Context Global --\\
    Context context;

    public Utils(Context globContext) {
        context = globContext;
    }

    //-- Function for syncronize DPI --\\
    public int syncDPI(float value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

}
