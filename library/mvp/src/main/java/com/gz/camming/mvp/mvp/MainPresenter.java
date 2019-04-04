package com.gz.camming.mvp.mvp;


import android.util.Log;

import com.gz.camming.mvp.bean.LoginBean;
import com.gz.camming.mvp.iml.DownloadListener;
import com.gz.camming.mvp.mvp.retrofit.MvpRxjavaCallback;
import com.gz.camming.mvp.mvp.retrofit.RxjavaCallback;
import com.gz.camming.mvp.utils.FileUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

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

//    public void login(String phone,String pas){
//        mvpView.showLoading("加载中");
//        requestDataSubscription(model.login(phone,pas),callback);
//    }
    public void login(String phone,String pas){
        mvpView.showLoading("加载中");
        requestRxjavaDataObservable(model.login(phone, pas), new RxjavaCallback<String>() {
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

    public void createOrder(String token,String id,String coin,String platform){
        mvpView.showLoading("加载中");
        requestDataSubscription(model.createAliOrder(token,id,coin,platform),callback);
    }

    /**
     * 上传文件
     * */
    public void uploadFile(String token,String path,String name) {
        if(!isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        File file = new File(path,name);
        if(!file.exists()){
            mvpView.showTips("文件不存在");
            return;
        }
        mvpView.showLoading("加载中");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
                requestDataSubscription(model.uploadFile(token,part),callback);

    }

    public void downLoadFile(String url,String path,String name){
        if(!isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        File dirFile =  new File(path);
        if(!dirFile.exists())
            dirFile.mkdir();

        final File file = new File(path,name);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mvpView.showLoading("下载中");

//        MvpRetrofit.getInstance().setDownloadState(true);

        requestDataSubscription(model.downloadFile(url),
                new MvpRxjavaCallback() {
                    @Override
                    public void onSuccess( String model) {


                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onDownloadFile(final ResponseBody body) {
                        //super.onDownloadFile(body);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                FileUtil.writeToFile(body,file,listener);
                            }
                        }).start();
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

    MvpRxjavaCallback callback = new MvpRxjavaCallback() {
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
    };
    DownloadListener listener = new DownloadListener() {
        @Override
        public void start() {
            Log.i("MainPresenter","start=");
        }

        @Override
        public void update(int progress) {
            Log.i("MainPresenter","progress="+progress);
        }

        @Override
        public void success() {
            Log.i("MainPresenter","success");
        }

        @Override
        public void error() {
            Log.i("MainPresenter","error");
        }
    };


}
