package app.qimi.cn.module_video;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;

import app.qimi.cn.module_video.mvp.presenter.VideoPresenter;
import app.qimi.cn.module_video.mvp.view.IVideoActivityView;


@Route(path = "/test/videoactivity")
public class VideoActivity extends Activity implements IVideoActivityView {

    private ListView listview;

    private VideoPresenter presenter = new VideoPresenter(VideoActivity.this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        presenter.getData();
        initView();
        initData();
    }

    private void initData() {
        presenter.initAdapter();

    }

    private void initView() {
        listview = findViewById(R.id.lv);

    }


    @Override
    public void setAdapter(ArrayAdapter<String> adapter) {

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
