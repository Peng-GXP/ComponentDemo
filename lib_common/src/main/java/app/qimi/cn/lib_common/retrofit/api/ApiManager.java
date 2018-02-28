package app.qimi.cn.lib_common.retrofit.api;


import app.qimi.cn.lib_common.retrofit.RequestUtils;
import app.qimi.cn.lib_common.retrofit.interceptor.PreHeaderInterceptor;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public class ApiManager {

    private static Api api  = (Api) RequestUtils.getInstance().create(Api.class);

    private ApiManager() {

    }

    /**
     * 默认请求是需要头部验证，需要添加拦截器
     *
     * @return
     */
    public static Api getApi(String data) {
        //拦截器，用于拦截，添加Http头信息
        RequestUtils.initInterceptors(new PreHeaderInterceptor());
        return api;
    }

    /**
     * 请求不添加头部验证，不添加拦截器
     * @return
     */
    public static Api getApi() {
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