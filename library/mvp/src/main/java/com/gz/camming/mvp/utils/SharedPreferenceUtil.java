package com.gz.camming.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Map;

/**
 * SharedPreferece 工具类
 */
public class SharedPreferenceUtil {
	/**
	 * SharedPreference名称
	 */
	private static final String PREFERENCE_FILE_NAME = "AIDEA_SharedPreference";

	/**
	 * 保存string
	 * 
	 * @param context Context
	 * @param key 键
	 * @param value 值
	 **/
	public static void saveString(final Context context, final String key,
                                  final String value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putString(key, value);
		editor.commit();
	}
	/**
	 * 保存long
	 *
	 * @param context Context
	 * @param key 键
	 * @param value 值
	 **/
	public static void saveLong(final Context context, final String key,
								  final long value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	/**
	 * 保存int
	 * @param context Context
	 * @param key
	 * @param value
	 */
	public static void saveInt(final Context context, String key, int value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 保存boolean
	 * 
	 * @param context Context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(final Context context, String key,
                                   Boolean value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 获取string
	 * @param context
	 * @param key
	 * @param deau 默认值
	 * @return
	 */
	public static String getStringValue(final Context context, final String key, final String deau) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getString(key, deau);
	}
	/**
	 * 获取long
	 * @param context
	 * @param key
	 * @param deau 默认值
	 * @return
	 */
	public static long getLongValue(final Context context, final String key, final long deau) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getLong(key, deau);
	}
	/**
	 * 获取Map
	 * @param context
	 * @param key
	 * @return
	 */
	public static Map<String, String> getStringValues(final Context context,
                                                      final String key) {
		Map<String, String> map = new HashMap<String, String>();
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		map.put("key", key);
		map.put("value", preference.getString(key, ""));
		return map;
	}

	/**
	 * 获取Boolean（默认为false）
	 * @param context
	 * @param key
	 * @param istrue 默认值
	 * @return
	 */
	public static boolean getBooleanValue(final Context context,
                                          final String key, final boolean istrue) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getBoolean(key, istrue);
	}

	/**
	 * 获取int
	 * @param context
	 * @param key
	 * @param value 默认值
	 * @return
	 */
	public static int getIntValue(final Context context, final String key, final int value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getInt(key, value);
	}

	/**
	 * 保存object类型(String,Boolean,Float,Long)
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveOBj(final Context context, final String key,
                               final Object value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		if (value instanceof String) {
			editor.putString(key, value.toString());
		} else if (value instanceof Integer) {
			editor.putInt(key, ((Integer) value).intValue());
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, ((Boolean) value).booleanValue());
		} else if (value instanceof Float) {
			editor.putFloat(key, ((Float) value).floatValue());
		} else if (value instanceof Long) {
			editor.putLong(key, ((Long) value).longValue());
		}
		editor.commit();
	}

	class Type<T> {
		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		/*
		 * public static void main(String[] args) { Test<String> stringTest =
		 * new Test<String>(); stringTest.setName("aaa");
		 * System.out.println(stringTest.getName()); Test<Integer> integerTest =
		 * new Test<Integer>(); integerTest.setName(1111);
		 * System.out.println(integerTest.getName()); }
		 */
	}

	/**
	 * 删除指定键对应的值
	 * @param context
	 * @param key
	 */
	public static void remove(final Context context, String key) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.remove(key);
		editor.commit();
	}
}
