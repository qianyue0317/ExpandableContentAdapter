package com.qianyue.sample;

import java.util.ArrayList;
import java.util.List;


import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

    private ListView listView;
    private List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        data = new ArrayList<Data>();
        for (int i = 0; i < 20; i++) {
            Data d = new Data();
            d.setTitle("标题" + (i + 1));
            d.setContent("内容" + (i + 1));
            data.add(d);
        }
    }

    private void initView() {
        m2Adapter = new TestAdapter(this, data, R.layout.item_title, R.layout.item_content_content);
        listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(m2Adapter);
        listView.setOnItemClickListener(this);
    }

    private TestAdapter m2Adapter;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast.makeText(this, "点击了" + (position+1), Toast.LENGTH_SHORT).show();
    }

}
