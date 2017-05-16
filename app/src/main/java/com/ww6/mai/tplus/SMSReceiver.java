package com.ww6.mai.tplus;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ww6.mai.tplus.util.Utils;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int resultCode = getResultCode();
        if(resultCode== Activity.RESULT_OK){
            //Toast.makeText(context,"send sms success",Toast.LENGTH_SHORT).show();
            Log.i(Utils.TAG,"send sms success");
        }else{
            //Toast.makeText(context,"send sms fail",Toast.LENGTH_SHORT).show();
            Log.i(Utils.TAG,"send sms fail");
        }
    }
}
