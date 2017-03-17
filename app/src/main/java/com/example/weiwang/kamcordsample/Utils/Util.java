package com.example.weiwang.kamcordsample.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by weiwang on 3/16/17.
 */

public class Util {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static GridLayoutManager createLayoutManager(Context context) {

        GridLayoutManager mGridLayoutManager;

        int screenSize = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            mGridLayoutManager = new GridLayoutManager(context, 5);
        } else {
            mGridLayoutManager = new GridLayoutManager(context, 3);

        }
        return mGridLayoutManager;
    }
}
