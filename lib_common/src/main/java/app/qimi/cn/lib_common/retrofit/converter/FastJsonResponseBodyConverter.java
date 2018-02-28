package app.qimi.cn.lib_common.retrofit.converter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import app.qimi.cn.lib_common.utils.LogUtils;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private String responseStr;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            responseStr = responseBody.string();
            JSONObject jsonObject = JSON.parseObject(responseStr);
            String error = jsonObject.getString("error");
            String msg = jsonObject.getString("msg");
            if (jsonObject.containsKey("data")) {
                JSONObject data = jsonObject.getJSONObject("data");
                data.put("error", error);
                data.put("msg", msg);
                responseStr = data.toString().trim();
            }
            LogUtils.i("==========ResponseData", responseStr);
            if (false) {//token失效
                //处理相关逻辑,退出登录
                ARouter.getInstance().build("/test/activity").navigation();
            }

            JSONReader reader = new JSONReader(new StringReader(responseStr));
            return reader.readObject(type);

        } finally {
            responseBody.close();
        }
    }

}