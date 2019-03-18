package com.gz.camming.mvp.utils;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	public static final int TOAST_LONG = 1;
	public static final int TOAST_SHORT = 0;
	/**
	 * 显示土司的工具类,安全的工具类可以在任意线程里面显示
	 * 
	 * @param activity
	 * @param text
	 */
	public static void show(final Activity activity, final String text) {
		if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
			Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
			   toast.show();
		} else {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
					toast.show();
				}
			});
		}
	}
	public static void show(final Context context, final String text) {
		if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
			Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			   toast.show();
		} else {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
					toast.show();
				}
			});
		}
	}
	/**
	 * 显示土司的工具类,安全的工具类可以在任意线程里面显示
	 * 
	 * @param activity
	 * @param text
	 * @param length
	 *            显示的时常
	 */
	public static void show(final Activity activity, final String text,
                            final int length) {
		if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
			Toast toast = Toast.makeText(activity, text, length);
			toast.show();
		} else {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast toast = Toast.makeText(activity, text, length);
					toast.show();
				}
			});
		}
	}
	

}
