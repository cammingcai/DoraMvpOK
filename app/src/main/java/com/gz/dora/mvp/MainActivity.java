package com.gz.dora.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mvp.gz.com.mvp.bean.WetherBean;
import mvp.gz.com.mvp.mvp.main.MainPresenter;
import mvp.gz.com.mvp.mvp.main.MainView;
import mvp.gz.com.mvp.ui.MvpActivity;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView<WetherBean> {

//    @BindView(R.id.text)
    TextView text;

    EditText cityEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        text = findViewById(R.id.text);
        cityEt = findViewById(R.id.et_city_id);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityId = cityEt.getText().toString();
                if(TextUtils.isEmpty(cityId)){
                    Toast.makeText(MainActivity.this,"请输入城市ID",Toast.LENGTH_SHORT).show();
                    return;
                }
                mvpPresenter.queryWether("b9a05b741d04063963bd964e8d79d06c",
                        cityId,"2019-03-11");
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

        text.setText(model.toString());
    //    text.setText(model.toString()+model.getResult().toString());
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.detachView();
    }
}
