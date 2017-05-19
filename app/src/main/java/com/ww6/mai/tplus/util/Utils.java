package com.ww6.mai.tplus.util;


import android.content.Context;
import android.util.Log;

import com.ww6.mai.tplus.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class Utils {
    public static final String TAG = "Tplus";

    public static String getWeek(Context context){
        final Calendar c = Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_WEEK);
        String[] week = context.getResources().getStringArray(R.array.week);
        return week[day-1];
    }

}
