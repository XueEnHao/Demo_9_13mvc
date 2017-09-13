package com.jiyun.defaultuser0.demo_9_13mvc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {

    private ListView lv_listview;
    private SwipeRefreshLayout srl;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        lv_listview = (ListView) findViewById(R.id.lv_listview);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setColorSchemeColors(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
//                        android.R.color.holo_green_light,
//                        android.R.color.holo_orange_light,
//                        android.R.color.holo_red_light);
            }
        });

        OkhttpUtils.getInstance().sendGet("http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=1", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, ""+string, Toast.LENGTH_SHORT).show();

                        Gson gson = new Gson();
                        bean bean = gson.fromJson(string, bean.class);
                        List<com.jiyun.defaultuser0.demo_9_13mvc.bean.ResultBean.ListBean> list = bean.getResult().getList();
                        listviewAdapter adapter = new listviewAdapter(MainActivity.this,list);
                        lv_listview.setAdapter(adapter);

                    }
                });
            }
        });


    }

    private void setListener() {
        srl.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }
}
