package com.example.weiwang.kamcordsample.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by william on 1/11/16.
 * Show Toast message only once
 */
public class SingleToast {

    private static Toast mToast;

    public static void show(Context context, String text, int duration) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }
}