package com.gz.camming.mvp.mvp;


import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.retrofit.MvpRxjavaCallback;

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

    /**
     * 查询天气
     * */
    public void queryWether(String key,String cityid,String date) {
        if(!isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        mvpView.showLoading("加载中");
        requestDataSubscription(model.queryWether(key,cityid,date),
                new MvpRxjavaCallback<WetherBean>() {
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


    /**
     * 查询天气
     * */
    public void queryAib(String name,int age) {
        if(!isMvpView()){
            throw new RuntimeException("not  mvp view");
        }
        mvpView.showLoading("加载中");
        requestDataSubscription(model.queryAib(name,age,"aaaa","bbbbb"),
                new MvpRxjavaCallback<AibBean>() {
                    @Override
                    public void onSuccess(AibBean model) {

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
