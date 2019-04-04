package com.gz.camming.mvp.mvp;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.gz.camming.mvp.http.XHttp;
import com.gz.camming.mvp.mvp.retrofit.MvpRetrofitCallback;
import com.gz.camming.mvp.mvp.retrofit.RxjavaCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;


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

    private RxjavaCallback mRxjavaCallback;
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


    /**
     * RxJava取消注册，以避免内存泄露
     *
     */
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

    /**
     *
     * 解决实体类和字符串共存问题
     * */
    public void requestRxjavaDataObservable(Observable observable, RxjavaCallback callback) {
        this.mRxjavaCallback = callback;
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


    /**
     * Rxjava 回调
     *
     * */
    DisposableObserver<ResponseBody> observer = new DisposableObserver<ResponseBody>() {
        @Override
        public void onNext(ResponseBody body) {
            String result = null;
            JSONObject json = null;
            try {
                json = new JSONObject(body.string());
                if(json!=null){
                    result = json.toString();
                }else{
                    result = body.string();
                }
                //根据泛型类型转换为对应的类型实体类
                if(mRxjavaCallback!=null)//gson  转为字符串有问题，这里用fastjson
                    mRxjavaCallback.onSuccess(JSON.parseObject(result, XHttp.analysisClassInfo(mRxjavaCallback)));
                  //  mRxjavaCallback.onSuccess(new Gson().fromJson(result, XHttp.analysisClassInfo(mRxjavaCallback)));
            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            if(mRxjavaCallback!=null)
                mRxjavaCallback.onFailure(e.getMessage());
        }

        @Override
        public void onComplete() {
            if(mRxjavaCallback!=null)
                mRxjavaCallback.onFinish();
        }
    };


    /**
     * retrofit 回调
     *
     * */
    public void requestRetrofitData(Call call, Callback callback){
//        call.enqueue(callback);
//        call.enqueue(new MvpRetrofitCallback<>());
    }



}
