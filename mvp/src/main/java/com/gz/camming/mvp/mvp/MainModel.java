package com.gz.camming.mvp.mvp;

import io.reactivex.Observable;
import com.gz.camming.mvp.bean.WetherBean;
import com.gz.camming.mvp.mvp.retrofit.MvpRetrofit;

/**
 *  Model接口 创建对应的联网请求的方法
 *  将Presenter提交的字段放到联网请求中，发送给服务器
 */
public class MainModel implements BaseModel {

    //查询天气 测试
    public Observable<WetherBean> queryWether(String key, String cityid,String date){
        return MvpRetrofit.getInstance().getMvpApi().queryWether(key,cityid,date);
    }

}
