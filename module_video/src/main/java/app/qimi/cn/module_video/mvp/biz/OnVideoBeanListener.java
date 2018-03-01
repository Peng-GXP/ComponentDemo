package app.qimi.cn.module_video.mvp.biz;

import app.qimi.cn.module_video.mvp.bean.VideoBean;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public interface OnVideoBeanListener {

    void success(VideoBean body);

    void failed();

}
