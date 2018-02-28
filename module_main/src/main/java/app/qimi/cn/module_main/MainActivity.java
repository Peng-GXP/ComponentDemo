package app.qimi.cn.module_main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends Activity {
    private Button mBt1;
    private Button mBt2;
    private Button mBt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBt1 = (Button) findViewById(R.id.bt1);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt3 = (Button) findViewById(R.id.bt3);

        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/test/videoactivity").navigation();
            }
        });

        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/login").navigation();
            }
        });

        mBt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/test/videodetailactivity").navigation();
            }
        });

    }
}
