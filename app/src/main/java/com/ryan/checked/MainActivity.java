package com.ryan.checked;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IPunchedInView{

    private TextView mTextView;
    private CheckedPresenter mCheckedPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        mCheckedPresenter = new CheckedPresenter(this,this);
    }

    public void hei(View view) {
        mCheckedPresenter.startPunchedIn();
    }

    public void cha(View view) {
        mCheckedPresenter.showRecord();
    }

    @Override
    public CheckedBean punchedIn() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(new Date());
        return new CheckedBean(data,1,"shui","12");
    }

    @Override
    public void showRecord(List<CheckedBean> checkedBeans) {
        if (checkedBeans.size() == 0){
            mTextView.setText("kong");
        }else{
            StringBuilder sb = new StringBuilder("");
            for (CheckedBean cb : checkedBeans) {
                sb.append(cb.getData()).append("   ");
            }
            mTextView.setText(sb.toString());}
    }
}
