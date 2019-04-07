package com.gz.camming.mvp.mvp;

import com.gz.camming.mvp.mvp.retrofit.MvpApi;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by camming on 2019/4/7.
 * MVP模式 Model View Presenter
 */

public interface BaseMvp {
    /**
     * MVP View负责展示数据
     * */
    interface BaseMvpView {
        /**
         * 显示正在加载进度框
         */
        void showLoading(String msg);
        /**
         * 隐藏正在加载进度框
         */
        void hideLoading();

        /**
         * 显示吐司
         **/
        void showTips(String msg);

    }

    /**
     * MVP Model负责请求数据
     * */
    interface BaseMvpModel{
        /**
         * 接口请求类
         *
         * */
        MvpApi getMvpApi();
    }

    /**
     * MVP Presenter负责处理数据
     * */
    interface BaseMvpPresenter<V>{

        /**
         * 绑定view，一般在初始化中调用该方法
         * */
        void attachView(V mvpView);

        /**
         * 断开view，一般在onDestroy中调用
         */
        void detachView();

        /**
         * RXjava 开始注册
         *
         */
        void onSubscribe(DisposableObserver observer);

        /**
         * RxJava取消注册，以避免内存泄露
         * 再网络取消请求时可条用
         */
        void onUnSubscribe();
    }
}
