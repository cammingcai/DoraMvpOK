package mvp.gz.com.mvp.mvp.main;


/**

 */
public interface MainView<T> extends BaseView {

    void getDataSuccess(T model);

    void getDataFail(String msg);

}
