package mvp.gz.com.mvp.ui;


import android.os.Bundle;
import android.view.View;

import mvp.gz.com.mvp.mvp.other.BasePresenter;
import mvp.gz.com.mvp.ui.fragment.BaseFragment;


/**

 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
