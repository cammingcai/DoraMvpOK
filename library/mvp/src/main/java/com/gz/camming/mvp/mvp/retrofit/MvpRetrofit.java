package com.gz.camming.mvp.mvp.retrofit;


import com.gz.camming.mvp.iml.DownloadListener;

import okhttp3.OkHttpClient;
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

          //  if (isDownload) {
                // Log信息拦截器
               // HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                if(mUpdateProgressListener!=null){
//                    JsDownloadInterceptor mInterceptor = new JsDownloadInterceptor(mUpdateProgressListener);
//                    builder.addInterceptor(mInterceptor);
//                }
                //isDownload = false;
//            }
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
