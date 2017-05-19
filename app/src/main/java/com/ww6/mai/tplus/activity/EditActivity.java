package com.ww6.mai.tplus.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ww6.mai.tplus.R;
import com.ww6.mai.tplus.SmsController;
import com.ww6.mai.tplus.util.Utils;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class EditActivity extends AppCompatActivity {
    public static final String TAG = "Tplus";
    TextView tvLable;
    EditText etMsg;
    Button btSend;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String project = getIntent().getStringExtra("project");
        String edit = getIntent().getStringExtra("edit");
        Log.i(TAG,"EditActivity----project:"+project+" edit:"+edit);
        final String sms = getIntent().getStringExtra("sms");
        final String shortcode = getIntent().getStringExtra("shortcode");
        getSupportActionBar().setTitle(project);
        tvLable = (TextView)findViewById(R.id.tv_lable);
        tvLable.setText(edit);
        etMsg = (EditText)findViewById(R.id.et_msg);
        btSend = (Button)findViewById(R.id.bt_send);
        btSend.setEnabled(false);
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btSend.setEnabled(count!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsController.getInstance().sendMessage(EditActivity.this, sms+"<"+etMsg.getText()+">",shortcode);
            }
        });
    }
}
