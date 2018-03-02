package app.qimi.cn.module_video.mvp.presenter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import app.qimi.cn.lib_common.myapplication.BaseApplication;
import app.qimi.cn.module_video.mvp.bean.VideoBean;
import app.qimi.cn.module_video.mvp.biz.IVideoBeanBiz;
import app.qimi.cn.module_video.mvp.biz.OnVideoBeanListener;
import app.qimi.cn.module_video.mvp.biz.VideoBeanBiz;
import app.qimi.cn.module_video.mvp.view.IVideoActivityView;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public class VideoPresenter {

    private Context mContext;
    private IVideoBeanBiz beanBiz;
    private IVideoActivityView rootView;
    private VideoBean data;
    private ArrayAdapter<String> adapter;

    public VideoPresenter(Context context, IVideoActivityView videoView) {
        this.mContext = context;
        this.beanBiz = new VideoBeanBiz();
        this.rootView = videoView;
    }

    public void getData() {
        beanBiz.getData(new OnVideoBeanListener() {
            @Override
            public void success(VideoBean bean) {
                data = bean;
                Toast.makeText(BaseApplication.getInstance(), "scuess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failed() {
                Toast.makeText(BaseApplication.getInstance(), "failed", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void initAdapter() {

        String []data ={"hi","nihao","yes","no"};
        TextView tv = new TextView(BaseApplication.getInstance());

        adapter = new  ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, data);
        rootView.setAdapter(adapter);


    }
}
