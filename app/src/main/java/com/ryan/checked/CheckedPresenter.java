package com.ryan.checked;

import android.content.Context;

/**
 * Created by Ryan on 2016/11/11.
 */
public class CheckedPresenter {

    private IPunchedInModel mPunchedInModel;
    private IPunchedInView mPunchedInView;


    public CheckedPresenter(Context context,IPunchedInView iPunchedInView) {
        mPunchedInModel = new PunchedInModel(context);
        mPunchedInView = iPunchedInView;
    }

    public void startPunchedIn (){
        CheckedBean checkedBean = mPunchedInView.punchedIn();
        mPunchedInModel.insertData(checkedBean);
    }

    public void showRecord (){
        mPunchedInView.showRecord(mPunchedInModel.queryData());
    }

}
