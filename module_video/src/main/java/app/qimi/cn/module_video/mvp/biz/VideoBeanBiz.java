package app.qimi.cn.module_video.mvp.biz;

import java.util.HashMap;
import java.util.Map;

import app.qimi.cn.lib_common.retrofit.NetCallBack;
import app.qimi.cn.module_video.api.VideoApiManager;
import app.qimi.cn.module_video.mvp.bean.VideoBean;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public class VideoBeanBiz implements IVideoBeanBiz {

    @Override
    public void getData(OnVideoBeanListener listener) {

        Map map = new HashMap();

        VideoApiManager.getApi().getVideoData(map).enqueue(new NetCallBack<VideoBean>() {
            @Override
            protected void onSuccess(Call<VideoBean> call, Response<VideoBean> response) {
                VideoBean bean = response.body();
                listener.success(bean);
            }

            @Override
            public void onFailure(Call<VideoBean> call, Throwable t) {
                super.onFailure(call, t);
                listener.failed();
            }
        });


    }
}
