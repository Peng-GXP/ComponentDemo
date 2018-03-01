package app.qimi.cn.module_video.api;

import java.util.Map;

import app.qimi.cn.module_video.mvp.bean.VideoBean;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public interface VideoApi  {

    @FormUrlEncoded
    @POST(VideoUrl.VIDEO_URL)
    Call<VideoBean> getVideoData(@FieldMap Map<String, String> map);

}
