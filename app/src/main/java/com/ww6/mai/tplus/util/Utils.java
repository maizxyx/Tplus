package com.ww6.mai.tplus.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.app.Dialog;

import android.util.Log;

import com.ww6.mai.tplus.R;
import com.ww6.mai.tplus.SMSReceiver;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class Utils {
    public static final String TAG = "Tplus";
    public static final String SMS_SEND_ACTION ="com.ww6.mai.tplus.sms_send_action";

    public static String getWeek(Context context){
        final Calendar c = Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_WEEK);
        String[] week = context.getResources().getStringArray(R.array.week);
        return week[day-1];
    }

    public static void sendMessage(final Context context, final String sms, final String shortcode){
        Log.i(TAG,"sms:"+sms+" shortcode:"+shortcode);
        SmsManager smsManager = SmsManager.getDefault();
        SMSReceiver smsReceiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SMS_SEND_ACTION);
        context.registerReceiver(smsReceiver,filter);
        Intent intent = new Intent(SMS_SEND_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        smsManager.sendTextMessage(shortcode,null,sms,pi,null);
        context.sendBroadcast(new Intent(""));
        /*Dialog dialog = new AlertDialog.Builder(context)
                .setIcon(0)
                .setTitle("Send Sms")
                .setMessage("Message:"+sms)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                SmsManager smsManager = SmsManager.getDefault();
                                SMSReceiver smsReceiver = new SMSReceiver();
                                IntentFilter filter = new IntentFilter();
                                filter.addAction(SMS_SEND_ACTION);
                                context.registerReceiver(smsReceiver,filter);
                                Intent intent = new Intent(SMS_SEND_ACTION);
                                PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
                                smsManager.sendTextMessage(shortcode,null,sms,pi,null);
                                context.sendBroadcast(new Intent(""));
                            }
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        //dialog.setCanceledOnTouchOutside(false);
        dialog.show();*/

    }
}
