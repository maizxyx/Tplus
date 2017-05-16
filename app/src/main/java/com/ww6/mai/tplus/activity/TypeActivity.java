package com.ww6.mai.tplus.activity;

import android.content.Context;
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
import com.ww6.mai.tplus.model.TplusEntity;
import com.ww6.mai.tplus.util.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class TypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ArrayList<TplusEntity> mTypeTplus = new ArrayList<TplusEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String project = getIntent().getStringExtra("project")+"";
        String type = Utils.getWeek(this);
        setActionBarTitle(project);
        if(project.equals("Xổ số")||project.equals("Thống kê xổ số")) {
            mTypeTplus = db.getTplusPriceByProjectAndType(project,type);
        }else{
            mTypeTplus = db.getTplusTypeByProject(project);
        }
        mListView.setAdapter(mBaseAdapter);
        mListView.setOnItemClickListener(this);
    }

    BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mTypeTplus.size();
        }

        @Override
        public Object getItem(int position) {
            return mTypeTplus.get(position);
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
            String project = mTypeTplus.get(position).getProject();
            if(project.equals("Xổ số")||project.equals("Thống kê xổ số")) {
                tvTitle.setText(mTypeTplus.get(position).getPrice());
            }else {
                tvTitle.setText(mTypeTplus.get(position).getType());
            }
            return convertView;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String project = mTypeTplus.get(position).getProject()+"";
        String price = mTypeTplus.get(position).getPrice();
        String edit = mTypeTplus.get(position).getEdit();
        String sms = mTypeTplus.get(position).getSms();
        String shortcode = mTypeTplus.get(position).getShortcode();
        String type = mTypeTplus.get(position).getType();
        if(price==null||price.equals("")||project.equals("Xổ số")||project.equals("Thống kê xổ số")){
            if(edit == null||edit.equals("")) {
                Utils.sendMessage(mContext, mTypeTplus.get(position).getSms(), mTypeTplus.get(position).getShortcode());
            }else{
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("project", project);
                intent.putExtra("edit",edit );
                intent.putExtra("sms",sms );
                intent.putExtra("shortcode",shortcode );
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(TypeActivity.this, PriceActivity.class);
            intent.putExtra("project", project);
            intent.putExtra("type", type);
            startActivity(intent);
        }

    }

}
