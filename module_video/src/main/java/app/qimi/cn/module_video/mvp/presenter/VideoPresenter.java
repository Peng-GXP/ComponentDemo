package app.qimi.cn.module_video.mvp.presenter;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    private IVideoBeanBiz beanBiz;
    private IVideoActivityView rootView;
    private VideoBean data;
    private ArrayAdapter<String> adapter;

    public VideoPresenter(IVideoActivityView videoView) {
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

        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        adapter = new ArrayAdapter<>(BaseApplication.getInstance(),android.R.layout.simple_expandable_list_item_1,list);
        rootView.setAdapter(adapter);


    }
}
