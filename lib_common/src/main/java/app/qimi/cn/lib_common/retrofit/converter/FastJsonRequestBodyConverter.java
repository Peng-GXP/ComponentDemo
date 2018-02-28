package app.qimi.cn.lib_common.retrofit.converter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import app.qimi.cn.lib_common.utils.LogUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        LogUtils.e(value.toString());
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
