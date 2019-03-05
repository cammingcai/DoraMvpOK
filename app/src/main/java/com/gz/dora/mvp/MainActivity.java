package com.gz.dora.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import mvp.gz.com.mvp.MvpTest;
import mvp.gz.com.mvp.mvp.main.MainModel;
import mvp.gz.com.mvp.mvp.main.MainPresenter;
import mvp.gz.com.mvp.mvp.main.MainView;
import mvp.gz.com.mvp.ui.MvpActivity;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView<MainModel> {

//    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        text = findViewById(R.id.text);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
        Log.i("MainActivity","MainModel="+model.getWeatherinfo().getCity());
    //    ToastUtils.show(this,model.getWeatherinfo().getCity());
        text.setText(model.getWeatherinfo().getCity());
    }

    @Override
    public void getDataFail(String msg) {

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
//                XLoadingView xLoadingView =   XLoadingView.wrap(this);
//                xLoadingView.showEmpty();
                MvpTest mvpTest = new MvpTest();
                mvpTest.startData();
                break;
            case R.id.button2:
                //请求接口
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }

}
