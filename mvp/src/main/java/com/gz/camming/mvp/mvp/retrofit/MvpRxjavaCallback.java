package com.gz.camming.mvp.mvp.retrofit;




import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**

 */
public abstract class MvpRxjavaCallback extends DisposableObserver<String> {


//    private Handler handler = new Handler();
    /**
     * 数据请求成功
     * @param model 请求到的数据
     */
    public abstract void onSuccess(String model);
    /**
     *  使用网络API接口请求方式时，虽然已经请求成功但是由
     *  于{@code msg}的原因无法正常返回数据。
     */
    public abstract void onFailure(String msg);
    /**
     * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
     * 请求时可以在此处隐藏“正在加载”的等待控件
     */
    public abstract void onFinish();

    @Override
    public void onNext(String model) {
        JSONObject json = null;
        try {
            json = new JSONObject(model);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if(json!=null)
                onSuccess(json.toString());
            else{
                onSuccess(model);
            }
        }
//        onSuccess(model);
//        Log.i("MvpRxjavaCallback",model.toString());
//        onSuccess(new Gson().<M>fromJson (model.toString(),getTClass(this.getClass() )));

    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
           // onFailure(msg);
        } else {
            msg = e.getMessage();
          //  onFailure(e.getMessage());
        }
        onFinish();
        onFailure(msg);

    }



    @Override
    public void onComplete() {
        onFinish();
    }


    public Class<?> getTClass(Object obj)
    {
        Class<?> tClass = (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
