package com.ww6.mai.tplus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MaiY on 2017/5/19.
 */

public class SmsController {
    public static final String TAG = "Tplus";
    public static final String SMS_SEND_ACTION ="com.ww6.mai.tplus.sms_send_action";
    private static SmsController instance = null;

    public SmsController() {
    }
    public static SmsController getInstance() {
        if (instance == null) {
            instance = new SmsController();
        }
        return instance;
    }
    public  void sendMessage(final Context context, final String sms, final String shortcode){
        Log.i(TAG,"sms:"+sms+" shortcode:"+shortcode);
        Dialog sendDialog = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Send Sms")
                .setMessage("Send "+sms+" to "+shortcode)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                SmsManager smsManager = SmsManager.getDefault();
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
        sendDialog.show();

    }
    BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = getResultCode();
            String msg = "";
            if(resultCode== Activity.RESULT_OK){
                msg = "Send succeed";
                Log.i(TAG,"send sms success");
            }else{
                msg = "Send failed";
                Log.i(TAG,"send sms fail");
            }
            Dialog dialog = new AlertDialog.Builder(context)
                    .setIcon(0)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("Exit", null)
                    .create();

            dialog.show();
        }
    };
}
