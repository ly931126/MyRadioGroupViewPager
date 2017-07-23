package com.example.test.myradiogroupviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	private RadioGroup	mRadioGroup	= null;
	private ViewPager	mViewPager	= null;
	
	private List<View>	mAllView	= null;
	private View		mViewOne	= null;
	private View		mViewTwo	= null;
	private View		mViewThree	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		mAllView = new ArrayList<>();
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		LayoutInflater inflater = LayoutInflater.from(this);
		mViewOne = inflater.inflate(R.layout.item_one_layout, null);
		mViewTwo = inflater.inflate(R.layout.item_two_layout, null);
		mViewThree = inflater.inflate(R.layout.item_three_layout, null);
		mAllView.add(mViewOne);
		mAllView.add(mViewTwo);
		mAllView.add(mViewThree);
		mRadioGroup.setOnCheckedChangeListener(this);
		mRadioGroup.check(R.id.menu_one_button);
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setOnPageChangeListener(new PageChangeListener());
		mViewPager.setOffscreenPageLimit(mAllView.size());
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.menu_one_button :
				mViewPager.setCurrentItem(0);
				break;
			
			case R.id.menu_two_button :
				mViewPager.setCurrentItem(1);
				break;
			
			case R.id.menu_three_button :
				mViewPager.setCurrentItem(2);
				break;
			
		}
	}
	
	private class PageChangeListener implements ViewPager.OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			switch (position) {
				case 0 :
					mRadioGroup.check(R.id.menu_one_button);
					break;
				
				case 1 :
					mRadioGroup.check(R.id.menu_two_button);
					break;
				
				case 2 :
					mRadioGroup.check(R.id.menu_three_button);
					break;
				
			}
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	}
	
	private class MyPagerAdapter extends PagerAdapter {
		
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		public Object instantiateItem(android.view.ViewGroup container, int position) {
			container.addView(mAllView.get(position));
			return mAllView.get(position);
			
		}
		
		public void destroyItem(android.view.ViewGroup container, int position, Object object) {
			container.removeView(mAllView.get(position));
		}
		
		public int getCount() {
			return mAllView.size();
		}
		
	}
}
