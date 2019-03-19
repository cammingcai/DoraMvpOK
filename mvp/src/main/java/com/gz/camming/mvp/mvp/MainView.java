package com.gz.camming.mvp.mvp;


import com.gz.camming.mvp.bean.Bean;

/**
 * View接口是Activity与Presenter层的中间层，它
 * 的作用是根据具体业务的需要，
 * 为Presenter提供调用Activity中具体UI逻辑操作的方法。
 */
public interface MainView<T extends Bean> extends BaseView {
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param model 数据源
     */
    void getDataSuccess(T model);
    /**
     * 当数据请求失败后，调用此接口提示
     * @param msg 失败原因
     */
    void getDataFail(String msg);
    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage();
}
