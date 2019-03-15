package mvp.gz.com.mvp.retrofit;



import mvp.gz.com.mvp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**

 */
public class MvpRetrofit {
    private static MvpRetrofit instance;
    private static Retrofit  mRetrofit;

    private MvpStores mvpStores;
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

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(MvpStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();


        }

        return mRetrofit;
    }

    public MvpStores getMvpApi(){

        if(mvpStores==null)
            mvpStores = mRetrofit.create(MvpStores.class);
        return  mvpStores;
    }

}
