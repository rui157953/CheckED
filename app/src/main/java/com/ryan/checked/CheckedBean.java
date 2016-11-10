package com.ryan.checked;

import android.content.Context;

/**
 * Created by Ryan on 2016/11/10.
 */
public class CheckedBean  {
    private String mData;
    private int mType;
    private String mTag;
    private String mDescribe;
    private Context mContext;

/*
    public CheckedBean(Context context) {
//        mContext = context;
    }
    public CheckedBean() {
    }
*/

    public CheckedBean(String data, int type, String tag, String describe) {
        mData = data;
        mType = type;
        mTag = tag;
        mDescribe = describe;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String describe) {
        mDescribe = describe;
    }
}
