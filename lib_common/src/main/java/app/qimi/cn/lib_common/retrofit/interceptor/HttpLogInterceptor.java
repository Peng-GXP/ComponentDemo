package app.qimi.cn.lib_common.retrofit.interceptor;


import java.io.IOException;

import app.qimi.cn.lib_common.utils.LogUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public class HttpLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        String requestMessage = "--> " + method + ' ' + request.url() + ' ';
//        if ("POST".equals(method)){
//            RequestBody body = request.body();
//            for (int i = 0; i < body.size(); i++) {
//                String paramName = body.name(i);
//                String paramValue = body.value(i);
//                requestMessage += '\n'+ paramName+"="+paramValue;
//            }
//            LogUtils.error(body.toString());
//        }
        LogUtils.e(requestMessage);

        Response response = chain.proceed(request);
        String responseMessage = "<-- "+response.code()+' '+response.message()+' '+response.request().url();
        LogUtils.e(responseMessage);
        return response;
    }
}