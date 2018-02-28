package app.qimi.cn.lib_common.retrofit.api;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author GaoXP
 * @time 2017/7/31.
 */

public class ApiPostJson {

    private HashMap<String, String> map = new HashMap<>();

    public ApiPostJson setParams(String key, String value) {
        map.put(key, value);
        return this;
    }

    public String getMapJson() {
        return JSON.toJSONString(map);
    }

    public RequestBody getRequestBody() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getMapJson());
        return requestBody;
    }

}
