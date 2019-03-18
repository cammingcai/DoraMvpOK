package com.gz.camming.mvp.mvp.retrofit;

import io.reactivex.Observable;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.MainModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 *  @ Headers 添加请求头
 *  @ Path 替换路径
 *  @ Query 替代参数值，通常是结合get请求的 (GET请求):用于在url后拼接上参数
 *  @ QueryMap(GET请求):如果入参比较多，就可以把它们都放在Map中
 *  @ Body(POST请求):可以指定一个对象作为HTTP请求体
 *  @ Field(POST请求):用于传送表单数据：开头必须多加上@FormUrlEncoded这句注释  替换参数值，是结合post请求的
 *
 *
 //    @Headers(MvpStores.API_SERVER_URL)
 //    @GET("user")
 //    Call<WetherBean> getUser();
 //@GET("user")
 //Call<User> getUser(@Header("Authorization") String authorization)
 //@FormUrlEncoded
 //@POST("user/edit")
 //Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);
 //@POST("users/new")
 //Call<User> createUser(@Body User user);

 @GET("group/{id}/users")
 Call<Book> groupList(@Path("id") int groupId);
 @GET("book/search")
 Call<Book> getSearchBook(@QueryMap Map<String, String> options);
 *
 */
public interface MvpApi {
    //baseUrl
    String API_SERVER_URL = "http://v.juhe.cn/";
//    String API_SERVER_URL = "http://120.78.121.247:8090/";

    //加载天气
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("adat/sk/{cityId}.html")
    Call<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);
//
//    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxJava(@Path("cityId") String cityId);



    @GET("historyWeather/weather")
    Observable<WetherBean> queryWether(@Query("key") String key, @Query("city_id") String city_id
    , @Query("weather_date") String weather_date);

    //PDF文件Retrofit下载
    @Streaming
    @GET
    Observable<ResponseBody> retrofitDownloadFile(@Url String fileUrl);


}
