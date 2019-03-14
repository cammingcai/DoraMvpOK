package mvp.gz.com.mvp.mvp.other;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import mvp.gz.com.mvp.retrofit.MvpClient;
import mvp.gz.com.mvp.retrofit.MvpStores;


/**

 */
public class BasePresenter<V> {
    public V mvpView;
    protected MvpStores mvpStores;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        mvpStores = MvpClient.retrofit().create(MvpStores.class);
    }


    public void detachView() {
        this.mvpView = null;
        onUnSubscribe();
    }


    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observer);

        // Schedulers.io() I/O 操作（读写文件、数据库、网络请求等）
        // AndroidSchedulers.mainThread() RxJava 扩展的 Android 主线程
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}
