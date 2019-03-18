package com.gz.camming.mvp.ui;


import android.os.Bundle;
import android.view.View;

import com.gz.camming.mvp.mvp.BasePresenter;
import com.gz.camming.mvp.ui.fragment.BaseFragment;


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
