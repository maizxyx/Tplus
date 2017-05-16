package com.ww6.mai.tplus.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ww6.mai.tplus.R;
import com.ww6.mai.tplus.db.DBHelper;
import com.ww6.mai.tplus.model.TplusEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "Tplus";
    public Context mContext;
    private ActionBar actionBar;
    public DBHelper db;
    ListView mListView;
    TextView tvNum,tvTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView =(ListView) findViewById(R.id.t_listview);
        mContext = this;
        db= new DBHelper(this,DBHelper.DB_NAME,null,1);
    }
    public void setActionBarTitle(String title) {
        actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }

}
