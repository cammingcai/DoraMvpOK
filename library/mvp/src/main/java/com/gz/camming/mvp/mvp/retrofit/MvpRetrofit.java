package com.gz.camming.mvp.mvp.retrofit;


import com.gz.camming.mvp.iml.DownloadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 * 网络加载框架 Retrofit
 */
public class MvpRetrofit {
    private static MvpRetrofit instance;
    private Retrofit  mRetrofit;

    private MvpApi mMvpApi;

//    private boolean isDownload;
//    private UpdateProgressListener mUpdateProgressListener;
    public static MvpRetrofit getInstance(){
        if(instance==null){
            synchronized (MvpRetrofit.class){
                if(instance==null){
                    instance = new MvpRetrofit();
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
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /**
             * 设置头信息
             */
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .addHeader("Accept-Encoding", "gzip")
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .method(originalRequest.method(), originalRequest.body());
//                    requestBuilder.addHeader("Authorization", "Bearer " + BaseConstant.TOKEN);//添加请求头信息，服务器进行token有效性验证
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(headerInterceptor);
            OkHttpClient okHttpClient = builder.build();

//            OkHttpClient okHttpClient = new OkHttpClient();
            mRetrofit = new Retrofit.Builder()
                    //设置网络请求的Url地址
                    .baseUrl(MvpApi.API_SERVER_URL)
                    //设置数据解析器
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addConverterFactory(ScalarsConverterFactory.create())
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


        if(mMvpApi==null)
            // 创建网络请求接口的实例
            mMvpApi = mRetrofit.create(MvpApi.class);
        return  mMvpApi;
    }


//    public void setDownloadState(boolean downloadState){
//        this.isDownload = downloadState;
//    }
//
//    public void setDownloadListener(UpdateProgressListener listener){
//        this.mUpdateProgressListener = listener;
//    }
}
