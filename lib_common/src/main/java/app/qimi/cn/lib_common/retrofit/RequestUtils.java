package app.qimi.cn.lib_common.retrofit;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.qimi.cn.lib_common.retrofit.bean.RequestParam;
import app.qimi.cn.lib_common.retrofit.converter.FastJsonConverterFactory;
import app.qimi.cn.lib_common.retrofit.interceptor.HttpLogInterceptor;
import app.qimi.cn.lib_common.retrofit.interceptor.PreHeaderInterceptor;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class RequestUtils {
    private static RequestUtils instance;
    public static String BASE_URL;
    private static Interceptor[] interceptors;
    private final Retrofit mRetrofit;

    private RequestUtils(){

        //支持 Https
//        OkHttpClient.Builder okClientBuild = new OkHttpClient.Builder()
//                .sslSocketFactory(HttpsSSLSocketFactory.getSSLContext(BaseApplication.getInstance()).getSocketFactory());

        //不实用 Http
        OkHttpClient.Builder okClientBuild = new OkHttpClient.Builder();


        if (interceptors != null && interceptors.length > 0){
            for (int i = 0; i < interceptors.length; i++) {
                okClientBuild.addInterceptor(interceptors[i]);
            }
        }

        if (true){
            okClientBuild.addInterceptor(new HttpLogInterceptor());
        }
        okClientBuild.addInterceptor(new PreHeaderInterceptor());
        okClientBuild.cookieJar(new SJCookieJar());

        OkHttpClient okHttpClient = okClientBuild.build();
        mRetrofit = new Retrofit.Builder()//
                .client(okHttpClient)//
                .baseUrl(BASE_URL)//
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

    }

    public Object create(Class clazz){
        return mRetrofit.create(clazz);
    }

    public static RequestUtils getInstance(){
        if (instance == null){
            synchronized (RequestUtils.class){
                if (instance == null){
                    instance = new RequestUtils();
                }
            }
        }
        return instance;
    }

    public static void initInterceptors(Interceptor ... interceptor){
        interceptors = interceptor;
    }


    private static class SJCookieJar implements CookieJar {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            app.qimi.cn.lib_common.retrofit.UrlRecorder.getInstance().setCookies(cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = app.qimi.cn.lib_common.retrofit.UrlRecorder.getInstance().getCookies();
            if (cookies != null) {
                return cookies;
            }
            return Collections.emptyList();
        }
    }

    public static Map<String, RequestBody> createPartMap(RequestParam... requestParams){
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < requestParams.length; i++) {
            RequestParam requestParam = requestParams[i];
            if (requestParam.value instanceof String){
                params.put(requestParam.key, RequestBody.create(MediaType.parse("multipart/form-data"), (String) requestParam.value));
            } else if (requestParam.value instanceof File){
                File file = (File) requestParam.value;
                params.put(""+requestParam.key+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }
        }
        return params;
    }


}