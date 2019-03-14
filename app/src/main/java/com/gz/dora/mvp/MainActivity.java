package com.gz.dora.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mvp.gz.com.mvp.bean.WetherBean;
import mvp.gz.com.mvp.mvp.main.MainPresenter;
import mvp.gz.com.mvp.mvp.main.MainView;
import mvp.gz.com.mvp.ui.MvpActivity;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView<WetherBean> {

//    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        text = findViewById(R.id.text);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.queryWether("b9a05b741d04063963bd964e8d79d06c",
                        "301","2019-03-11");
            }
        });
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
    public void getDataSuccess(WetherBean model) {
      //  Log.i("MainActivity","MainModel="+model.getWeatherinfo().getCity());
    //    ToastUtils.show(this,model.getWeatherinfo().getCity());
        text.setText(model.getResult().getCity_name()+model.getResult().getDay_weather()+
                model.getResult().getDay_temp()+model.getResult().getDay_weather_id()+
                model.getResult().getNight_wind_comp()+model.getResult().getDay_weather());
    }

    @Override
    public void getDataFail(String msg) {

    }



}
