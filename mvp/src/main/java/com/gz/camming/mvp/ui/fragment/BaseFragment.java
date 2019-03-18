package com.gz.camming.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import com.gz.camming.mvp.utils.ProgressDialog;

/**
 * Created by camming on 2018\11\22 0022.
 * 继承  不是为了使用某个类的某个功能而继承
 * 而是类与类之间有所属关系
 *
 *
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =null ;
        if(getFragmentLayout()>0){
            view  = inflater.inflate(getFragmentLayout(), null);
            unbinder = ButterKnife.bind(this,view);

            initView(view);
            //unbinder = ButterKnife.bind(view);//这个方法在fragment 无效
        }else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return view;
    }

    public void showDialog(String msg){
        ProgressDialog.getInstance().show(getActivity(),msg);
    }
    public void dismissDialog(){
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract int getFragmentLayout();
    public abstract void initView(View view);
    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null)
            unbinder.unbind();
        onUnsubscribe();
    }


    private CompositeDisposable mCompositeDisposable;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(DisposableObserver observer) {
//        if (mCompositeDisposable == null) {
        mCompositeDisposable = new CompositeDisposable();
//        }
        mCompositeDisposable.add(observer);
    }
}
