package mvp.gz.com.mvp.mvp.main;

/**
 *
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
