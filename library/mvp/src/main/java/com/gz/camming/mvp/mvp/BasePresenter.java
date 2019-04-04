package com.gz.camming.mvp.mvp;


import com.gz.camming.mvp.mvp.retrofit.MvpRetrofitCallback;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;


/**
 * Presenter类是具体的逻辑业务处理类，该
 * 类为纯Java类，不包含任何Android API，
 * 负责请求数据，并对数据请求的反馈进行处理。
 */
public class BasePresenter<V> {
    // View接口
    public V mvpView;
    //protected MvpStores mvpStores;
    private CompositeDisposable mCompositeDisposable;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;

    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
        onUnSubscribe();
    }


    //RxJava取消注册，以避免内存泄露
    //取消请求
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }



    //RxJava 开始注册
    //public void addSubscription(Observable observable, DisposableObserver observer) {
    public void requestDataSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
        // Schedulers.io() I/O 操作（读写文件、数据库、网络请求等）  请求数据在IO线程
        // AndroidSchedulers.mainThread() RxJava 扩展的 Android 主线程
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//请求完成后再主线程更新UI
               // .retry(1)//请求失败重连次数
                .subscribeWith(observer);
    }

    public void requestRetrofitData(Call call, Callback callback){
//        call.enqueue(callback);
//        call.enqueue(new MvpRetrofitCallback<>());
    }



}
