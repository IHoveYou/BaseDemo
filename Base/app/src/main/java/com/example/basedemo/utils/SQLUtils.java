package com.example.basedemo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.basedemo.BaseApplication;
import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * Created by Administrator on 2019/8/14.
 * SQLITE 数据库
 */

public class SQLUtils extends SQLiteOpenHelper {
    static SQLUtils sqlUtils;
    final static String TABLE_NAME = "user";
    String createSql = "create table user(_id integer primary key autoincrement, key1, ver)";

    public synchronized static SQLUtils newInstance() {
        if (sqlUtils == null) {
            sqlUtils = new SQLUtils(BaseApplication.getInstance(), TABLE_NAME, null,
                    (int) BaseApplication.getInstance().getAppVersionCode());
        }
        return sqlUtils;
    }

    private SQLUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }


    public synchronized void set(String key, Object ver) {
        if (StringUtil.isEmpty(get(key))) {
            add(key, ver);
        } else {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put("key", key);
            value.put("ver", encode(new Gson().toJson(ver)));
            db.update(TABLE_NAME, value, "key=?", new String[]{key});
            db.close();
        }
    }


    public  void put(String key, Object ver) {
        delect(key);
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("key", key);
        cv.put("ver", encode(new Gson().toJson(ver)));
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public  void add(String key, Object ver) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("key", key);
        cv.put("ver", encode(new Gson().toJson(ver)));
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }


    public void delect(String key) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, "key=?", new String[]{key});
        db.close();
    }


    public String get(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"key", "ver"}, "key=?", new String[]{key}, null, null, null);
        String ver = null;
        db.close();
        return decode(ver);
    }


    /**
     * 功能：解码字符串
     *
     * @param data 源字符串
     * @return String
     * @author jiangshuai
     * @date 2016年10月03日
     */
    private static String decode(String data) {

        try {
            byte byteArr[] = android.util.Base64.decode(data.getBytes("utf-8"), android.util.Base64.NO_WRAP);
            data = new String(byteArr);
        } catch (Exception e) {

        }
        return data;
//		return new String(decode(data.toCharArray()));
    }

    /**
     * 功能：编码byte[]
     * @param str 源
     * @return char[]
     * @author jiangshuai
     * @date 2016年10月03日
     */
    private static String encode(String str) {
        if (StringUtil.isEmpty(str.trim())) {
            return "";
        }
        String dataMessage;
        try {
//            dataMessage = encode1(data);
            dataMessage = android.util.Base64.encodeToString(str.getBytes("utf-8"), android.util.Base64.NO_WRAP);
        } catch (Exception e) {
            dataMessage = new String(str);
        }
        return new String(dataMessage);
    }

    private static Gson GsonUtilsGson = new Gson();

    private static <T> T fromJson(String json, Type typeOfT) {
        T bean = null;
        try {
            bean = (T) GsonUtilsGson.fromJson(json, typeOfT);
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        return bean;
    }
}
