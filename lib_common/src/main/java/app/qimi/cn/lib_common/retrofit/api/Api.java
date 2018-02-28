package app.qimi.cn.lib_common.retrofit.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author GaoXP
 */

public interface Api {

    @FormUrlEncoded
    @POST(MyUrl.TEST)
    Call<String> testHttp(@FieldMap Map<String, String> map);


}

