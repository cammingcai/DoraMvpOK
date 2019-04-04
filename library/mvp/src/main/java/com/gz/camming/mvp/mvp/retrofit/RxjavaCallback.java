package com.gz.camming.mvp.mvp.retrofit;

/**
 * Created by camming on 2019/4/4.
 *
 */

public abstract class RxjavaCallback<T> {

    /**
     * 数据请求成功
     * @param model 请求到的数据
     */
    public abstract void onSuccess(T model);
    /**
     *  使用网络API接口请求方式时，虽然已经请求成功但是由
     *  于{@code msg}的原因无法正常返回数据。
     */
    public abstract void onFailure(String msg);
    /**
     * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
     * 请求时可以在此处隐藏“正在加载”的等待控件
     */
    public abstract void onFinish();

}
