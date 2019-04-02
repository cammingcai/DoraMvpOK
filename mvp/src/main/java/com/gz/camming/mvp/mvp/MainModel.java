package com.gz.camming.mvp.mvp;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Part;

import com.gz.camming.mvp.bean.AibBean;
import com.gz.camming.mvp.bean.LoginBean;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.retrofit.MvpRetrofit;

/**
 *  Model接口 创建对应的联网请求的方法
 *  将Presenter提交的字段放到联网请求中，发送给服务器
 */
public class MainModel implements BaseModel  {

    //查询天气 测试
    public Observable<WetherBean> queryWether(String key, String cityid,String date){
        return MvpRetrofit.getInstance().getMvpApi().queryWether(key,cityid,date);
    }

    //上传文件
    public Observable<String> uploadFile(RequestBody requestBody,MultipartBody.Part file){
        return MvpRetrofit.getInstance().getMvpApi().retrofitUploadFile(requestBody,file);
    }

    public Observable<LoginBean> login(String phone, String pass){

        return MvpRetrofit.getInstance().getMvpApi().login(phone,pass);
    }
}
