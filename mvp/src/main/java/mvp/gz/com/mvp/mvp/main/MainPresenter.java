package mvp.gz.com.mvp.mvp.main;


import mvp.gz.com.mvp.bean.GradesModel;
import mvp.gz.com.mvp.bean.LoginModel;
import mvp.gz.com.mvp.bean.WetherBean;
import mvp.gz.com.mvp.mvp.other.BasePresenter;
import mvp.gz.com.mvp.retrofit.MvpCallback;

/**

 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading("加载中");
        addSubscription(mvpStores.loadDataByRetrofitRxJava(cityId),
                new MvpCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }
    public void login(String phone, String pass) {
        mvpView.showLoading("加载中");
        addSubscription(mvpStores.login(phone,pass),
                new MvpCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }
    public void queryGrades(String token) {
        mvpView.showLoading("加载中");
        addSubscription(mvpStores.queryGrades(token),
                new MvpCallback<GradesModel>() {
                    @Override
                    public void onSuccess(GradesModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

    public void queryWether(String key,String cityid,String date) {
        mvpView.showLoading("加载中");
        addSubscription(mvpStores.queryWether(key,cityid,date),
                new MvpCallback<WetherBean>() {
                    @Override
                    public void onSuccess(WetherBean model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }
}
