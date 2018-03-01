package app.qimi.cn.module_video.api;

import app.qimi.cn.lib_common.retrofit.RequestUtils;
import app.qimi.cn.lib_common.retrofit.interceptor.PreHeaderInterceptor;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public class VideoApiManager {

    private static VideoApi api = (VideoApi) RequestUtils.getInstance().create(VideoApi.class);

    private VideoApiManager() {

    }

    /**
     * 默认请求是需要头部验证，需要添加拦截器
     *
     * @return
     */
    public static VideoApi getApi(String data) {
        //拦截器，用于拦截，添加Http头信息
        RequestUtils.initInterceptors(new PreHeaderInterceptor());
        return api;
    }

    /**
     * 请求不添加头部验证，不添加拦截器
     *
     * @return
     */
    public static VideoApi getApi() {
//        if (api == null) {
//            synchronized (ApiManager.class) {
//                if (api == null) {
//                    api = (Api) RequestUtils.getInstance().create(Api.class);
//                }
//            }
//        }
        return api;
    }
}
