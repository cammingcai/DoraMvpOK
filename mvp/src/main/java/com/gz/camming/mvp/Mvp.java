package com.gz.camming.mvp;


import com.gz.camming.mvp.mvp.BasePresenter;

/**

 */
public abstract class Mvp<P extends BasePresenter> {
    protected P mvpPresenter;


    protected abstract P createPresenter();


    public void onDestroyPresenter(){
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void showLoading(String msg) {
      //  ProgressDialog.getInstance().show(context,msg);
    }
    public void hideLoading() {
      //  ProgressDialog.getInstance().dismiss();
    }
}


