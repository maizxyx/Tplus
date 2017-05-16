package com.ww6.mai.tplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ww6.mai.tplus.R;
import com.ww6.mai.tplus.model.TplusEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class ProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ArrayList<TplusEntity> mProjectTplus = new ArrayList<TplusEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("Tplus");
        mProjectTplus = db.getTplusProject();
        mListView.setAdapter(mBaseAdapter);
        mListView.setOnItemClickListener(this);
    }

    BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mProjectTplus.size();
        }

        @Override
        public Object getItem(int position) {
            return mProjectTplus.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            tvNum = (TextView)convertView.findViewById(R.id.tv_num);
            tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            tvNum.setText(""+(position+1));
            tvTitle.setText(mProjectTplus.get(position).getProject());
            return convertView;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String project = mProjectTplus.get(position).getProject();
        Intent intent = new Intent(ProjectActivity.this, TypeActivity.class);
        intent.putExtra("project",project );
        startActivity(intent);
    }
}
