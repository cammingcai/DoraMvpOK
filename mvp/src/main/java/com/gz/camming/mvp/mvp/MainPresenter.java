package com.gz.camming.mvp.mvp;


import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.retrofit.MvpCallback;

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
        return mvpView==null;
    }

    /**
     * 查询天气
     * */
    public void queryWether(String key,String cityid,String date) {
        if(isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        mvpView.showLoading("加载中");
        requestData(model.queryWether(key,cityid,date),
                new MvpCallback<WetherBean>() {
                    @Override
                    public void onSuccess(WetherBean model) {

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
