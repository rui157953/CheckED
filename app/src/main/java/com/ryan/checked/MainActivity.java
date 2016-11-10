package com.ryan.checked;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    PunchedInModel mPunchedInModel;
    private List<CheckedBean> mBeanList;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);

        mPunchedInModel = new PunchedInModel(this);

    }

    public void hei(View view) {
        mBeanList = mPunchedInModel.queryData();
        if (mBeanList.size() == 0){
            mTextView.setText("kong");
        }else{
        StringBuilder sb = new StringBuilder("");
        for (CheckedBean cb :
                mBeanList) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String data = format.format(cb.getData());
            sb.append(cb.getData()+"  ");
        }
        mTextView.setText(sb.toString());}
    }

    public void cha(View view) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(new Date());
        mPunchedInModel.insertData(new CheckedBean(data,1,"shui","12"));
    }
}
