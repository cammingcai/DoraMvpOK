package mvp.gz.com.mvp.ui;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;

/**

 */
public abstract class BaseActivity extends XActivity {
    public Activity mActivity;
//    private CompositeDisposable mCompositeDisposable;
//    private List<Call> calls;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mActivity = this;
    }


    @Override
    protected void onDestroy() {
       // callCancel();
//        onUnsubscribe();
        super.onDestroy();
    }
//
//    public MvpStores apiStores() {
//        return MvpRetrofit.retrofit().create(MvpStores.class);
//    }
//
//    public void addCalls(Call call) {
//        if (calls == null) {
//            calls = new ArrayList<>();
//        }
//        calls.add(call);
//    }
//
//    private void callCancel() {
//        if (calls != null && calls.size() > 0) {
//            for (Call call : calls) {
//                if (!call.isCanceled())
//                    call.cancel();
//            }
//            calls.clear();
//        }
//    }

//
//    public <T> void addSubscription(Observable<T> observable, DisposableObserver<T> observer) {
//        if (mCompositeDisposable == null) {
//            mCompositeDisposable = new CompositeDisposable();
//        }
//        mCompositeDisposable.add(observer);
//
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    public void addSubscription(Disposable disposable) {
//        if (mCompositeDisposable == null) {
//            mCompositeDisposable = new CompositeDisposable();
//        }
//        mCompositeDisposable.add(disposable);
//    }

//    public void onUnsubscribe() {
//        //取消注册，以避免内存泄露
//        if (mCompositeDisposable != null)
//            mCompositeDisposable.dispose();
//    }

//    public Toolbar initToolBar(String title) {
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            TextView toolbaTitle = toolbar.findViewById(R.id.toolbar_title);
//            toolbaTitle.setText(title);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return toolbar;
//    }
//
//    public Toolbar initToolBarAsHome(String title) {
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//            toolbaTitle.setText(title);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return toolbar;
//    }



}
