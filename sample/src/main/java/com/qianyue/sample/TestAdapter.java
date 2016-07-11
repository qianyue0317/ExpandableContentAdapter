package com.qianyue.sample;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zcsj.expandabledetail.adapter.ExpandDetailAdapter;

public class TestAdapter extends ExpandDetailAdapter<Data>{


	public TestAdapter(Context mContext, List<Data> data, int titleViewId,
			int contentViewId) {
		super(mContext, data, titleViewId, contentViewId);
	}

	@Override
	public void bindData(View a, View b, Data c) {
		TextView tvTitle = (TextView) a.findViewById(R.id.tv_title);
		TextView tvContent = (TextView) b.findViewById(R.id.tv_content);
		
		tvTitle.setText(c.getTitle());
		tvContent.setText(c.getContent());
	}

}
