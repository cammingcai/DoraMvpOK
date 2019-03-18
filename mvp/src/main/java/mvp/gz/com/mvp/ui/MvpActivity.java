package mvp.gz.com.mvp.ui;

import android.os.Bundle;

import mvp.gz.com.mvp.mvp1.BasePresenter;
import mvp.gz.com.mvp.utils.ProgressDialog;


/**

 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void showLoading(String msg) {
        ProgressDialog.getInstance().show(this,msg);
    }
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

}


