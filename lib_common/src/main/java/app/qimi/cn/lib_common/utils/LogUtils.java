package app.qimi.cn.lib_common.utils;

import android.util.Log;

import app.qimi.cn.lib_common.myapplication.BaseApplication;


/**
 * @author GaoXP
 * @time 2018/2/11.
 */

public class LogUtils {

    private static String TAG = "MY_APP_DEMO";

    public static void i(String tag, String content) {
        if (BaseApplication.getInstance().isDebug) {
            Log.i(tag, content);
        }
    }

    public static void e(String tag, String content) {
        if (BaseApplication.getInstance().isDebug) {
            Log.e(tag, content);
        }
    }

    public static void e(String content) {
        if (BaseApplication.getInstance().isDebug) {
            Log.e(TAG, content);
        }
    }
}
