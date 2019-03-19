package com.gz.camming.mvp.mvp.retrofit;



import com.gz.camming.mvp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * 网络加载框架 Retrofit
 */
public class MvpRetrofit {
    private static MvpRetrofit instance;
    private Retrofit  mRetrofit;

    private MvpApi mvpStores;
    public static MvpRetrofit getInstance(){
        if(instance==null){
            synchronized (MvpRetrofit.class){
                if(instance==null){
                    synchronized (MvpRetrofit.class){
                        instance = new MvpRetrofit();
                    }
                }
            }
        }
        return instance;
    }
    public MvpRetrofit(){
        retrofit();
    }

    private  Retrofit retrofit() {
        if (mRetrofit == null) {
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//            if (BuildConfig.DEBUG) {
//                // Log信息拦截器
//                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                //设置 Debug Log 模式
//                builder.addInterceptor(loggingInterceptor);
//            }
//
//            OkHttpClient okHttpClient = builder.build();
            OkHttpClient okHttpClient = new OkHttpClient();
            mRetrofit = new Retrofit.Builder()
                    //设置网络请求的Url地址
                    .baseUrl(MvpApi.API_SERVER_URL)
                    //设置数据解析器
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();


        }

        return mRetrofit;
    }

    /**
     * 获取网络请求接口实例
     * */
    public MvpApi getMvpApi(){

        if(mvpStores==null)
            // 创建网络请求接口的实例
            mvpStores = mRetrofit.create(MvpApi.class);
        return  mvpStores;
    }

}
