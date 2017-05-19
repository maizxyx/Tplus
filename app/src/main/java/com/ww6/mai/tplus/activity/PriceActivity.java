package com.ww6.mai.tplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ww6.mai.tplus.R;
import com.ww6.mai.tplus.SmsController;
import com.ww6.mai.tplus.model.TplusEntity;
import com.ww6.mai.tplus.util.Utils;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class PriceActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ArrayList<TplusEntity> mPriceTplus = new ArrayList<TplusEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String project = getIntent().getStringExtra("project");
        String type = getIntent().getStringExtra("type");
        Log.i(TAG,"PriceActivity----project:"+project+" type:"+type);
        setActionBarTitle(type);
        mPriceTplus = db.getTplusPriceByProjectAndType(project,type);
        mListView.setAdapter(mBaseAdapter);
        mListView.setOnItemClickListener(this);
    }

    BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mPriceTplus.size();
        }

        @Override
        public Object getItem(int position) {
            return mPriceTplus.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvNum.setText("" + (position + 1));
            tvTitle.setText(mPriceTplus.get(position).getPrice());
            return convertView;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(mContext,"send sms success",Toast.LENGTH_LONG).show();
        String edit = mPriceTplus.get(position).getEdit();
        String sms = mPriceTplus.get(position).getSms();
        String shortcode = mPriceTplus.get(position).getShortcode();
        if(edit == null||edit.equals("")) {
            SmsController.getInstance().sendMessage(mContext, mPriceTplus.get(position).getSms(), mPriceTplus.get(position).getShortcode());
        }else{
            Intent intent = new Intent(PriceActivity.this, EditActivity.class);
            intent.putExtra("edit",edit );
            intent.putExtra("sms",sms );
            intent.putExtra("shortcode",shortcode );
            startActivity(intent);
        }
    }
}

