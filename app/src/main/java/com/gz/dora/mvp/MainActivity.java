package com.gz.dora.mvp;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.bean.LoginBean;
import com.gz.camming.mvp.mvp.MainPresenter;
import com.gz.camming.mvp.mvp.MainView;
import com.gz.camming.mvp.mvp.retrofit.MvpApi;
import com.gz.camming.mvp.ui.MvpActivity;
import com.gz.camming.mvp.ui.view.loadingview.XLoadingView;
import com.gz.camming.mvp.utils.XPermission;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView<String> {

//    @BindView(R.id.text)
    TextView text;

    EditText cityEt;

    XLoadingView xLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xLoadingView = XLoadingView.wrap(this);
//        xLoadingView.showLoading();
    }

    @Override
    protected void initView() {
        text = findViewById(R.id.text);
        cityEt = findViewById(R.id.et_city_id);

        XPermission.requestPermissions(this, 100,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, new XPermission.OnPermissionListener() {
            //权限申请成功时调用
            @Override
            public void onPermissionGranted() {
               showTips("授权了");
            }
            //权限被用户禁止时调用
            @Override
            public void onPermissionDenied() {
                showTips("没有权限");
            }
        });
        final String path = Environment.getExternalStorageDirectory()  +"/aideadora/";
        final String name  = "head.jpg";
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String cityId = cityEt.getText().toString();
//                if(TextUtils.isEmpty(cityId)){
//                    Toast.makeText(MainActivity.this,"请输入城市ID",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mvpPresenter.login("13560048370","123456");
//                mvpPresenter.createOrder("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjExLjE0XC9hcGlcL3VzZXJcL2xvZ2luIiwiaWF0IjoxNTU0MjU2NzYxLCJleHAiOjE1NTQzNDMxNjEsIm5iZiI6MTU1NDI1Njc2MSwianRpIjoiVEVoZGRWZ3BsV1dkYmVrayIsInN1YiI6MTEyLCJwcnYiOiJiOTEyNzk5NzhmMTFhYTdiYzU2NzA0ODdmZmYwMWUyMjgyNTNmZTQ4In0.4EhSn6m_Fw5tFYl64KYQRfTt-R9Jz7k0ZOh4_1b5h4k",
//                        "1","0","1");
//                mvpPresenter.uploadFile("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjExLjE0XC9hcGlcL3VzZXJcL2xvZ2luIiwiaWF0IjoxNTU0MjgxNTU3LCJleHAiOjE1NTQzNjc5NTcsIm5iZiI6MTU1NDI4MTU1NywianRpIjoidkVrWDBiRVRtakRMY296TCIsInN1YiI6MTEyLCJwcnYiOiJiOTEyNzk5NzhmMTFhYTdiYzU2NzA0ODdmZmYwMWUyMjgyNTNmZTQ4In0.VM9Nskb7lKoe1K-ROwFNJTpeZEHrWt6MWf2v8ziAyxA",
//                        path,name );
//                mvpPresenter.downLoadFile("https://download.tfwangs.com/apk/DoraAI_1.0.30.apk",
//                        path,"dora.apk");
//                mvpPresenter.getNews("");
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
    public void getDataSuccess(String model) {

        Log.i("MainActivity","model="+model);
        text.setText(model.toString());
    //    text.setText(model.toString()+model.getResult().toString());
    }

    @Override
    public void getDataFail(String msg) {
        text.setText("getDataFail ="+msg);
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
