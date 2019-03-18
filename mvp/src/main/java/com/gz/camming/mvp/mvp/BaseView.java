package com.gz.camming.mvp.mvp;


/**
 * View接口是Activity与Presenter层的中间层，它
 * 的作用是根据具体业务的需要，
 * 为Presenter提供调用Activity中具体UI逻辑操作的方法。
 */
public interface BaseView {
    /**
     * 显示正在加载进度框
     */
    void showLoading(String msg);
    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();
}
