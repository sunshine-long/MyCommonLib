package com.unht.myutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author KangLong
 *         date 2017/5/26
 *         description 此类是用于快速创建SharedPreferences和存储数据
 */
public class LitePrefUtils {

    /**
     * 保存在手机里面的名字
     */
    private static final String FILE_NAME = "shared_data";
    private static LitePrefUtils liteSPUtils;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Context mContext;

    /**
     * 重载构造方法
     *
     * @param context
     */
    private LitePrefUtils(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public LitePrefUtils setFileName(String fileName) {
        preferences = mContext.getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
        return liteSPUtils;
    }

    /**
     * 创建对象单例
     *
     * @param context
     * @return
     */
    public static LitePrefUtils getInstance(Context context) {
        if (liteSPUtils == null) {
            synchronized (LitePrefUtils.class) {
                if (liteSPUtils == null) {
                    liteSPUtils = new LitePrefUtils(context);
                }
            }
        }
        mContext = context;
        return liteSPUtils;
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key    键
     * @param object 值
     */
    public void putValue(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键
     * @param defaultObject 默认值
     * @return 键的值
     */

    public Object getValue(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return preferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return preferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return preferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return preferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return preferences.getLong(key, (Long) defaultObject);
        } else {
            return preferences.getString(key, null);
        }

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key 键
     */
    public void remove(String key) {
        editor.remove(key);
        SharedPreferencesCompat.apply();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        editor.clear();
        SharedPreferencesCompat.apply();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key 键
     * @return
     */
    public boolean contains(String key) {
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
