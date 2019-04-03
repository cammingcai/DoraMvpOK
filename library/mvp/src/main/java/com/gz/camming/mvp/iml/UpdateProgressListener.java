package com.gz.camming.mvp.iml;

/**
 *
 *
 */
public interface UpdateProgressListener {
    /**
     * 开始下载
     */
     void start();

    /**
     * 更新下载进度
     * @param progress
     */
     void update(int progress);

    /**
     * 下载成功
     */
     void success();

    /**
     * 下载错误
     */
     void error();
}
