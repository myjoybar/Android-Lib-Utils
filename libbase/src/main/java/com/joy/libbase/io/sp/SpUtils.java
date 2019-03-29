package com.joy.libbase.io.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created by joybar on 23/12/2017.
 */

public class SpUtils {

    /**
     * @param context the context
     * @param fileName the file name
     * @param key  the key
     * @param object the object you will save
     */
    public static void put(Context context, String fileName, String key, Object object) {
        if(null != object && context != null) {
            SharedPreferences sp = context.getSharedPreferences(fileName, 0);
            SharedPreferences.Editor editor = sp.edit();
            if(object instanceof String) {
                editor.putString(key, (String)object);
            } else if(object instanceof Integer) {
                editor.putInt(key, ((Integer)object).intValue());
            } else if(object instanceof Boolean) {
                editor.putBoolean(key, ((Boolean)object).booleanValue());
            } else if(object instanceof Float) {
                editor.putFloat(key, ((Float)object).floatValue());
            } else if(object instanceof Long) {
                editor.putLong(key, ((Long)object).longValue());
            } else {
                editor.putString(key, object.toString());
            }

            editor.apply();
        }
    }

    /**
     *
     * @param context
     * @param fileName
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String fileName, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public static Map<String, ?> getAll(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public static boolean contains(Context context, String fileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }


    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void remove(Context context,String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }



    /**
     * 清除指定数据
     *
     * @param context
     */
    public static void removeAll(Context context,String fileName,String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     *
     * @param context the context
     * @param fileName the file name
     * @param key the key
     * @param object the object you will be save
     */
    public static void saveObject(Context context, String fileName, String key,  Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try {
            if(object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
                String newsListBase64 = new String(Base64.encode(baos.toByteArray(), 1));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, newsListBase64);
                editor.apply();
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                    oos = null;
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            if(baos != null) {
                try {
                    baos.close();
                    baos = null;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param context the context
     * @param fileName the file name
     * @param key the key
     * @return the object you will get
     */
    public static Object getObject(Context context, String fileName, String key ) {
        Object object = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object obj;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
            String newsListBase64 = sharedPreferences.getString(key, (String)null);
            if(newsListBase64 != null) {
                byte[] base64Bytes = Base64.decode(newsListBase64.getBytes(), 1);
                bais = new ByteArrayInputStream(base64Bytes);
                ois = new ObjectInputStream(bais);
                object = ois.readObject();
                return object;
            }
            obj = null;
        } catch (Exception ioe) {
            return object;
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                    ois = null;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if(bais != null) {
                try {
                    bais.close();
                    bais = null;
                } catch (IOException var22) {
                    var22.printStackTrace();
                }
            }

        }

        return obj;
    }
}
