package com.gz.camming.mvp.mvp.retrofit;

import io.reactivex.Observable;

import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.bean.LoginBean;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.MainModel;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
 *  @ Headers 请求头
 *  post提交数据方式
 *  application/x-www-form-urlencoded  表单数据
 *  multipart/form-data 文件上传
 *  application/json json数据格式
 *  text/xml  xml数据格式
 *
 */
public interface MvpApi {
    //baseUrl
   // String API_SERVER_URL = "http://127.0.0.1:8080/";
//    String API_SERVER_URL = "http://192.168.11.237:8181/";
    String API_SERVER_URL = "http://192.168.11.14/";//https://download.tfwangs.com/apk/DoraAI_1.0.30.apk
//    String API_SERVER_URL = "http://is.snssdk.com/";
//    String API_SERVER_URL = "https://download.tfwangs.com/";
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

    @POST("api/user/login")
    Observable<ResponseBody> login(@Query("phone") String phone,@Query("password") String password);

    @POST("api/alipay/createOrder")
    Observable<ResponseBody> createAliOrder(@Query("token") String token,@Query("id") String id,
                                      @Query("study_coin") String study_coin,@Query("platform") String platform);




    @Multipart
    @POST("api/user/avatar")
    Observable<ResponseBody> uploadFile(@Query("token") String token, @Part MultipartBody.Part file);


    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    /**
     * 获取新闻列表
     *
     * @param category 频道
     * @return
     */
    @GET("article/v2/tab_comments")
    Observable<ResponseBody> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);
}
