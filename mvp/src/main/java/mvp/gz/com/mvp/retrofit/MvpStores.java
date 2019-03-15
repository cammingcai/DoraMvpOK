package mvp.gz.com.mvp.retrofit;

import io.reactivex.Observable;
import mvp.gz.com.mvp.bean.GradesModel;
import mvp.gz.com.mvp.bean.LoginModel;
import mvp.gz.com.mvp.bean.WetherBean;
import mvp.gz.com.mvp.mvp.main.MainModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ Query (GET请求):用于在url后拼接上参数
 *
 * @ QueryMap(GET请求):如果入参比较多，就可以把它们都放在Map中
 * @ Body(POST请求):可以指定一个对象作为HTTP请求体
 * @ Field(POST请求):用于传送表单数据：开头必须多加上@FormUrlEncoded这句注释
 * @ Header/@Headers(POST请求):用于添加请求头部
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
public interface MvpStores {
    //baseUrl
    String API_SERVER_URL = "http://v.juhe.cn/";
//    String API_SERVER_URL = "http://120.78.121.247:8090/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Call<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);
//
//    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxJava(@Path("cityId") String cityId);

    @POST("api/side/queryGrades")
    Observable<GradesModel> queryGrades(@Header("Authorization") String token);

    @POST("api/user/shiroLogin")
    Observable<LoginModel> login(@Query("phone") String phone, @Query("wordpass") String wordpass);
//    @POST("api/user/shiroLogin")
//    Observable<LoginModel> login(@Field("phone") String phone, @Field("wordpass") String wordpass);


    @GET("historyWeather/weather")
    Observable<WetherBean> queryWether(@Query("key") String key, @Query("city_id") String city_id
    , @Query("weather_date") String weather_date);

}
