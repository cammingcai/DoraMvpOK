package com.gz.dora.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.mvp.MainPresenter;
import com.gz.camming.mvp.mvp.MainView;
import com.gz.camming.mvp.ui.MvpActivity;
import com.gz.camming.mvp.ui.view.loadingview.XLoadingView;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView<AibBean> {

//    @BindView(R.id.text)
    TextView text;

    EditText cityEt;

    XLoadingView xLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xLoadingView = XLoadingView.wrap(this);
        xLoadingView.showLoading();
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
                mvpPresenter.queryAib("haaaaaaaaaaa",17);
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
    public void getDataSuccess(AibBean model) {

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
