package com.ww6.mai.tplus.db;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ww6.mai.tplus.model.TplusEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "Tplus";

    public static final String DB_PATH = "sql";
    public static final String DB_NAME = "tplus.db";
    private Context mContext;


    public DBHelper(Context context, String databaseName,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, databaseName, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DBHelper--onCreate");
        executeAssetsSQL(db, "tplus.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion <= oldVersion) {
            return;
        }

        int changeCnt = newVersion - oldVersion;
        for (int i = 0; i < changeCnt; i++) {
            String schemaName = "update" + (oldVersion + i) + "_"
                    + (oldVersion + i + 1) + ".sql";
            executeAssetsSQL(db, schemaName);
        }
    }

    private void executeAssetsSQL(SQLiteDatabase db, String schemaName) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(mContext.getAssets()
                    .open(DB_PATH + "/" + schemaName)));
            Log.e(TAG, "path:"+DB_PATH + "/" + schemaName);
            String line;
            String buffer = "";
            while ((line = in.readLine()) != null) {
                buffer += line;
                if (line.trim().endsWith(";")) {
                    db.execSQL(buffer.replace(";", ""));
                    buffer = "";
                }
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    public ArrayList<TplusEntity> getTplusProject(){
        ArrayList<TplusEntity> mTplus = new ArrayList<TplusEntity>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TPLUS", null, null, null, "project", null, "id asc");//desc
        while (cursor.moveToNext()) {
            String tid = cursor.getString(cursor.getColumnIndex("id"));
            String tnum = cursor.getString(cursor.getColumnIndex("num"));
            String tproject = cursor.getString(cursor.getColumnIndex("project"));
            String ttype = cursor.getString(cursor.getColumnIndex("type"));
            String tprice = cursor.getString(cursor.getColumnIndex("price"));
            String tedit = cursor.getString(cursor.getColumnIndex("edit"));
            String tsms = cursor.getString(cursor.getColumnIndex("sms"));
            String tshortcode = cursor.getString(cursor.getColumnIndex("shortcode"));
           // Log.i(TAG,"getTplusProject-->"+" tnum:"+tnum+" tproject:"+tproject+" ttype:"+ttype+" tprice:"+tprice+" tedit:"+tedit+" tsms:"+tsms+" tshortcode:"+tshortcode);
            TplusEntity mTplusEntity = new TplusEntity(tid,tnum,tproject,ttype,tprice,tedit,tsms,tshortcode);
            mTplus.add(mTplusEntity);
        }
        return mTplus;
    }
    public ArrayList<TplusEntity> getTplusTypeByProject(String project){
        ArrayList<TplusEntity> mTplus = new ArrayList<TplusEntity>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TPLUS", null, "project = ?", new String[] {project}, "type", null, "id asc");
        while (cursor.moveToNext()) {
            String tid = cursor.getString(cursor.getColumnIndex("id"));
            String tnum = cursor.getString(cursor.getColumnIndex("num"));
            String tproject = cursor.getString(cursor.getColumnIndex("project"));
            String ttype = cursor.getString(cursor.getColumnIndex("type"));
            String tprice = cursor.getString(cursor.getColumnIndex("price"));
            String tedit = cursor.getString(cursor.getColumnIndex("edit"));
            String tsms = cursor.getString(cursor.getColumnIndex("sms"));
            String tshortcode = cursor.getString(cursor.getColumnIndex("shortcode"));
           // Log.i(TAG,"getTplusTypeByProject-->"+" tnum:"+tnum+" tproject:"+tproject+" ttype:"+ttype+" tprice:"+tprice+" tedit:"+tedit+" tsms:"+tsms+" tshortcode:"+tshortcode);
            TplusEntity mTplusEntity = new TplusEntity(tid,tnum,tproject,ttype,tprice,tedit,tsms,tshortcode);
            mTplus.add(mTplusEntity);
        }
        return mTplus;
    }
    public ArrayList<TplusEntity> getTplusPriceByProjectAndType(String project,String type){
        ArrayList<TplusEntity> mTplus = new ArrayList<TplusEntity>();
        SQLiteDatabase db = getReadableDatabase();
        //String[] columns = {"num", "project", "type","price","edit","sms","shortcode"};
        Cursor cursor = db.query("TPLUS", null, "project = ? and type = ?", new String[] {project,type}, null, null,"id asc");
        while (cursor.moveToNext()) {
            String tid = cursor.getString(cursor.getColumnIndex("id"));
            String tnum = cursor.getString(cursor.getColumnIndex("num"));
            String tproject = cursor.getString(cursor.getColumnIndex("project"));
            String ttype = cursor.getString(cursor.getColumnIndex("type"));
            String tprice = cursor.getString(cursor.getColumnIndex("price"));
            String tedit = cursor.getString(cursor.getColumnIndex("edit"));
            String tsms = cursor.getString(cursor.getColumnIndex("sms"));
            String tshortcode = cursor.getString(cursor.getColumnIndex("shortcode"));
           // Log.i(TAG,"getTplusPriceByProjectAndType-->"+" tnum:"+tnum+" tproject:"+tproject+" ttype:"+ttype+" tprice:"+tprice+" tedit:"+tedit+" tsms:"+tsms+" tshortcode:"+tshortcode);
            TplusEntity mTplusEntity = new TplusEntity(tid,tnum,tproject,ttype,tprice,tedit,tsms,tshortcode);
            if(!tprice.equals("")) mTplus.add(mTplusEntity);
        }
        return mTplus;
    }

}

