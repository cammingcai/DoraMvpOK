package com.gz.camming.mvp.mvp;


import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.bean.LoginBean;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.retrofit.MvpRxjavaCallback;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Presenter类是具体的逻辑业务处理类，该
 * 类为纯Java类，不包含任何Android API，
 * 负责请求数据，并对数据请求的反馈进行处理。
 */
public class MainPresenter extends BasePresenter<MainView> {
    private MainModel model;
    public MainPresenter(MainView view) {
        attachView(view);
        model = new MainModel();
    }

    private boolean isMvpView(){
        return mvpView!=null;
    }

    public void login(String phone,String pas){
        mvpView.showLoading("加载中");
        requestDataSubscription(model.login(phone,pas),
                new MvpRxjavaCallback<String>() {
                    @Override
                    public void onSuccess(String model) {

                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }





    /**
     * 上传头像
     *
     * */
    public void uploadFile(String path) {
        if(!isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        mvpView.showLoading("加载中");

        File file = new File(path);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        requestDataSubscription(model.uploadFile(requestBody,part),
                new MvpRxjavaCallback<String>() {
                    @Override
                    public void onSuccess(String model) {

                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });

    }
}
