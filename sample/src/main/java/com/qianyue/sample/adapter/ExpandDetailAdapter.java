package com.qianyue.sample.adapter;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

public abstract class ExpandDetailAdapter<T> extends BaseAdapter {

	private int mHeight;
	private int duration = 200;
	private Context mContext;
	private LinearLayout ll;
	private int contentViewId;
	private int titleViewId;
	private LayoutInflater inflater;
	protected List<T> data;
	private List<Boolean> isExpandedList = new ArrayList<Boolean>();

	public ExpandDetailAdapter(Context mContext, List<T> data, int titleViewId,
			int contentViewId) {
		if (data == null) {
			this.data = new ArrayList<T>();
		} else {
			this.data = data;
		}
		this.mContext = mContext;
		for (int i = 0; i < data.size(); i++) {
			isExpandedList.add(false);
		}
		inflater = LayoutInflater.from(mContext);
		this.titleViewId = titleViewId;
		this.contentViewId= contentViewId;
	}

	
	/**
	 * 设置展开或者闭合动画时长 默认是200毫秒
	 * @param duration
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			ll = new LinearLayout(mContext);
			ll.setOrientation(LinearLayout.VERTICAL);

			View titleView = inflater.inflate(titleViewId, ll, false);
			ll.addView(titleView);
			View contentView = inflater.inflate(contentViewId, ll, false);
			ll.addView(contentView);
			convertView = ll;
			viewHolder = new ViewHolder();
			viewHolder.titleView = titleView;
			viewHolder.contentView = contentView;
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		bindData(viewHolder.titleView, viewHolder.contentView,
				data.get(position));
		if (mHeight==0) {
			viewHolder.contentView.measure(0, 0);
			mHeight = viewHolder.contentView.getMeasuredHeight();
		}

		LayoutParams lp;
		lp = viewHolder.contentView.getLayoutParams();

		if (isExpandedList.get(position)) {
			lp.height = mHeight;
		} else {
			lp.height = 0;
		}
		viewHolder.contentView.setLayoutParams(lp);
		viewHolder.titleView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (viewHolder.contentView.getHeight() == 0) {
					showAnimation(viewHolder, true);
					isExpandedList.set(position, true);
				} else {
					showAnimation(viewHolder, false);
					isExpandedList.set(position, false);
				}
			}

		});

		return convertView;
	}

	private void showAnimation(final ViewHolder viewHolder, boolean toExpand) {
		ValueAnimator animator;
		if (toExpand) {// 如果是要展开
			animator = ValueAnimator.ofFloat(0, mHeight); // 定义动画
		} else {
			animator = ValueAnimator.ofFloat(mHeight, 0); // 定义动画
		}
		animator.setTarget(viewHolder.contentView); // 设置作用目标
		animator.setDuration(duration).start();
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();
				LayoutParams lp;
				lp = viewHolder.contentView.getLayoutParams();
				lp.height = (int) value; // 必须通过这里设置属性值才有效
				viewHolder.contentView.setLayoutParams(lp);
				// tv.setWidth().mXXX = value; //不需要setXXX属性方法
			}
		});
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	class ViewHolder {
		View contentView;
		View titleView;
	}

	public abstract void bindData(View a, View b, T c);
}
