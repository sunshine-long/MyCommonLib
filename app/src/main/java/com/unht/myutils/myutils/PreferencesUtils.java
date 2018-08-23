package com.unht.myutils.myutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.unht.myutils.app.App;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author KangLong
 * date 2017/5/26
 * description 此类是用于快速创建SharedPreferences和存储数据
 */
public class PreferencesUtils {

    /**
     * 保存在手机里面的名字
     */
    private static final String FILE_NAME = "preferences_data";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    /**
     * 重载构造方法
     */
    private PreferencesUtils() {
        this(FILE_NAME);
    }

    private PreferencesUtils(String fileName) {
        preferences = App.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static PreferencesUtils setFileName(String fileName) {
        return new PreferencesUtils(fileName);
    }

    /**
     * 创建对象单例
     *
     * @return
     */
    public static PreferencesUtils init() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static PreferencesUtils INSTANCE = new PreferencesUtils();
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key   键
     * @param value 值
     */
    public static void putString(String key, String value) {
        editor.putString(key, value);
        SharedPreferencesCompat.apply();
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        SharedPreferencesCompat.apply();
    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply();
    }

    public static void putFloat(String key, float value) {
        editor.putFloat(key, value);
    }

    public static void putLong(String key, long value) {
        editor.putLong(key, value);
        SharedPreferencesCompat.apply();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key 键
     * @return 键的值
     */

    public static String getString(String key) {
        return preferences.getString(key, null);
    }

    public static int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static float getFloat(String key) {
        return preferences.getFloat(key, 0f);
    }

    public static long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    /**
     * 通过类名字去获取一个对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        String clazzName = clazz.getSimpleName();
        String json = getString(key + clazzName);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * // 通过Type去获取一个泛型对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getObject(Type type) {
        String key = type.toString();
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 保存一个对象，object必须是普通类，而不是泛型，如果是泛型,请使用{}
     *
     * @param object 对象的类名
     */
    public static void putObject(String key, Object object) {
        String clazzName = object.getClass().getSimpleName();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(key + clazzName, json);
    }

    /**
     * 保存一个泛型对象
     *
     * @param object
     * @param type   如果你要保存 List<Person> 这个类, type应该 传入 new TypeToken<List<Person>>() {}.getType()
     */
    public static void putObject(Object object, Type type) {
        String key = type.toString();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(key, json);
    }

    /**
     * 移除一个对象
     *
     * @param clazz
     */
    public static void removeObject(Class<?> clazz) {
        remove(clazz.getName());
    }

    /**
     * 移除一个泛型对象
     *
     * @param type
     */
    public static void removeObject(Type type) {
        remove(type.toString());
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key 键
     */
    public static void remove(String key) {
        editor.remove(key);
        SharedPreferencesCompat.apply();
    }

    /**
     * 清除所有的数据
     */
    public static void clear() {
        editor.clear();
        SharedPreferencesCompat.apply();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key 键
     * @return
     */
    public static boolean contains(String key) {
        return preferences.contains(key);
    }

    /**
     * 返回
     *
     * @return 所有的键值对
     */
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method S_APPLY_METHOD = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return Method
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        static void apply() {
            try {
                if (S_APPLY_METHOD != null) {
                    S_APPLY_METHOD.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
