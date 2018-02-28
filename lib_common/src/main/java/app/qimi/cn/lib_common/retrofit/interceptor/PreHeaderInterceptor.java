package app.qimi.cn.lib_common.retrofit.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public class PreHeaderInterceptor implements Interceptor {
    private Map<String, String> params = new HashMap<String, String>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        params.clear();
        if (oldRequest.method().equals("POST")) {
            oldRequest = addPostParams(oldRequest, params);
        } else {
            oldRequest = addGetParams(oldRequest, params);
        }
        // 新的请求
        Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body())
//                .url(authorizedUrlBuilder.build())
                .addHeader("X-Query-Sign", "")
                .addHeader("Authorizationtoken", "").build();

//        LogUtils.i("PreHeaderInterceptor", "sort() X-Query-Sign -> " + SecretUtils.getSecretKey(sort()));

        return chain.proceed(newRequest);

    }

    private String sort() {
//        StringBuffer stringBuffer = new StringBuffer();
//
//        Set<String> keySet = params.keySet();
//        if (keySet.size() == 0)
//            return "";
//        List<String> paramKeyList = new ArrayList<String>();
//        for (String key : keySet) {
//            paramKeyList.add(key);
//
//        }
//        List<String> newKeyList = StringUtill.sortStr(paramKeyList);
//        for (String key : newKeyList) {
//            if (null != params.get(key) && !"".equals(params.get(key))) {
//                stringBuffer.append(key).append("=").append(params.get(key)).append("&");
//            }
//        }
//        if (stringBuffer.length() > 1) {
//            LogUtils.i("PreHeaderInterceptor", "stringBuffer -> " + stringBuffer.substring(0, stringBuffer.length() - 1));
//            return stringBuffer.substring(0, stringBuffer.length() - 1);
//        }else {
//            return "";
//        }
        return "";
    }

    //get请求 参数
    private static Request addGetParams(Request request, Map<String, String> params) {
        //添加公共参数
        HttpUrl httpUrl = request.url().newBuilder().build();

        //添加签名
        Set<String> nameSet = httpUrl.queryParameterNames();

        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(nameSet);

        for (int i = 0; i < nameList.size(); i++) {
            if (httpUrl.queryParameterValues(nameList.get(i)) != null && httpUrl.queryParameterValues(nameList.get(i)).size() > 0) {
                params.put(nameList.get(i), httpUrl.queryParameterValues(nameList.get(i)).get(0));
            }
        }
        httpUrl = httpUrl.newBuilder().build();
        request = request.newBuilder().url(httpUrl).build();
        return request;
    }

    //post 添加签名和公共参数
    private Request addPostParams(Request request, Map<String, String> params) throws UnsupportedEncodingException {
        if (request.body() instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            FormBody formBody = (FormBody) request.body();

            //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
            for (int i = 0; i < formBody.size(); i++) {
                String key = formBody.encodedName(i);
                String value = URLDecoder.decode(formBody.encodedValue(i), "UTF-8");
                params.put(key, value);
                bodyBuilder.addEncoded(key, value);
            }

            request = request.newBuilder().post(formBody).build();
        }
        return request;
    }

}