package mvp.gz.com.mvp.mvp1;

import io.reactivex.Observable;
import mvp.gz.com.mvp.bean.WetherBean;
import mvp.gz.com.mvp.mvp1.retrofit.MvpRetrofit;

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
