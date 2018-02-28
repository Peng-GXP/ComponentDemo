package app.qimi.cn.lib_common.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.vondear.rxtools.RxTool;

import app.qimi.cn.lib_common.retrofit.RequestUtils;
import app.qimi.cn.lib_common.retrofit.api.MyUrl;

/**
 * @author GaoXP
 * @time 2018/2/27.
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication;
    public static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RequestUtils.BASE_URL = MyUrl.BASE_URL;
        RxTool.init(this);

        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化

    }

    public synchronized static BaseApplication getInstance() {
        return mApplication;
    }

}
