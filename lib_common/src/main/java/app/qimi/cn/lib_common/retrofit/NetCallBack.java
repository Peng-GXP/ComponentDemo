package app.qimi.cn.lib_common.retrofit;

import app.qimi.cn.lib_common.utils.LogUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public abstract class NetCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        //hiddenProgress();
        T t = response.body();
        if (response.code() == 200) {
            if (isNullNeedCallback()) {
                onSuccess(call, response);
            } else {
                if (t != null) {
                    onSuccess(call, response);
                } else {
                    onErrorCodeCallback();
                }
            }
        } else {
            onFailure(call, new Exception("response code is " + response.code()));
        }
    }

    protected void onErrorCodeCallback() {

    }

    protected boolean isNullNeedCallback() {
        return false;
    }

    protected void hiddenProgress() {
        System.out.println("hiddenProgress111");
    }

    protected abstract void onSuccess(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
//        hiddenProgress();
        LogUtils.e("==========失败", t.getMessage() + "");
//        Toast.makeText(BaseApplication.getInstance(), "网络连接异常!!", Toast.LENGTH_SHORT).show();

//        if ("200".equals(error)) {//token失效
//            //处理相关逻辑,需要统一处理的
//            if (null != listener) {
//                listener.errorCode(error);
//            }
//        }

    }

}
