package com.ryan.checked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

import com.ryan.checked.db.Constants;
import com.ryan.checked.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 打卡控制器
 * <p/>
 * 根据传入的日期进行存入数据库.
 * <p/>
 * Created by Ryan on 2016/11/10.
 */
class PunchedInModel {
    public PunchedInModel(Context context) {
        mOpenHelper =  new DatabaseHelper(context);
    }

    private DatabaseHelper mOpenHelper;

    private static final HandlerThread sWorkerThread = new HandlerThread("checked-loader");

    static {
        sWorkerThread.start();
    }

    private static final Handler sWorker = new Handler(sWorkerThread.getLooper());

    private CheckedBean mCheckedBean;

    public void insertData(final CheckedBean mCheckedBean) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        final String data = format.format(mCheckedBean.getData());

        Runnable r = new Runnable() {

            @Override
            public void run() {
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constants.DATE,mCheckedBean.getData());
                values.put(Constants.DESCRIBE,mCheckedBean.getDescribe());
                values.put(Constants.TAG,mCheckedBean.getTag());
                values.put(Constants.TYPE,mCheckedBean.getType());
//                values.put(Constants.TIMESTAMP,mCheckedBean.getData());
                db.insert(Constants.TABLE_NAME,null,values);
            }
        };
        runOnWorkerThread(r);
    }


    public List<CheckedBean> queryData() {
        List<CheckedBean> beanList = new ArrayList<>();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(Constants.DATE));
            String describe = cursor.getString(cursor.getColumnIndex(Constants.DESCRIBE));
            String tag = cursor.getString(cursor.getColumnIndex(Constants.TAG));
            String timestamp = cursor.getString(cursor.getColumnIndex(Constants.TIMESTAMP));
            int type = cursor.getInt(cursor.getColumnIndex(Constants.TYPE));

            beanList.add(new CheckedBean(date,type,tag,describe));
        }
        return beanList ;
    }

    public interface Callbacks {
        void startPunchedIn(CheckedBean CheckedBean);

    }


    private DeferredHandler mHandler = new DeferredHandler();

    /**
     * Runs the specified runnable immediately if called from the main thread, otherwise it is
     * posted on the main thread handler.
     */
    private void runOnMainThread(Runnable r) {
        runOnMainThread(r, 0);
    }

    private void runOnMainThread(Runnable r, int type) {
        if (sWorkerThread.getThreadId() == Process.myTid()) {
            // If we are on the worker thread, post onto the main handler
            mHandler.post(r);
        } else {
            r.run();
        }
    }

    /**
     * Runs the specified runnable immediately if called from the worker thread, otherwise it is
     * posted on the worker thread handler.
     */
    private static void runOnWorkerThread(Runnable r) {
        if (sWorkerThread.getThreadId() == Process.myTid()) {
            r.run();
        } else {
            // If we are not on the worker thread, then post to the worker handler
            sWorker.post(r);
        }
    }
}
