package com.it520.activity.news.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 广告栏（banner）gallery
 * 
 * @author jackytan
 * 
 */
@SuppressWarnings("deprecation")
public class BannerGallery extends Gallery {
	private static final int SWIPE_IMAGE_FLAG = 0x256;

	private boolean mFling = true;

	public void setFling(boolean fling) {
		mFling = fling;
	}

	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SWIPE_IMAGE_FLAG:
				int position = getSelectedItemPosition();
				try {
					if(isMoveRight(position)) {
		                onScroll(null, null, 1, 0);
		                onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
		            }
		            else {
		                onScroll(null, null, -1, 0);
		                onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};

	public BannerGallery(Context paramContext) {
		super(paramContext);
		//startSchedule();
	}

	public BannerGallery(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		//startSchedule();
	}

	public BannerGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		//startSchedule();
	}
	
	private boolean isMoveRight(int position) {
		return !(position >= (getCount() - 1));
	}

	private boolean isScrollingLeft(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2) {
		float f2 = paramMotionEvent2.getX();
		float f1 = paramMotionEvent1.getX();
		if (f2 > f1)
			return true;
		return false;
	}
	
	@Override
	public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		if (!mFling)
			return true;

		int keyCode;
		if (isScrollingLeft(paramMotionEvent1, paramMotionEvent2)) {
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		try {
			onKeyDown(keyCode, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
		
	private float startX = 0F;
	private float startY = 0F;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mFling) {
			return super.onTouchEvent(event);
		}
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				startY = event.getY();
				//mHandler.removeCallbacks(runnable);
				break;

			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_MOVE:
				if (Math.abs(event.getX() - startX) > 5 ||
						Math.abs(event.getY() - startY) > 5) {
					return false;
				}
				break;


			case MotionEvent.ACTION_UP:
				//startSchedule();
				break;

		}
		return super.onTouchEvent(event);
	}
	private Runnable runnable = new Runnable() {
		public void run() {
			mHandler.removeMessages(SWIPE_IMAGE_FLAG);
			mHandler.sendEmptyMessage(SWIPE_IMAGE_FLAG);
			startSchedule();
		}
	};
	
	private static final int TIME_INTERVL = 5000;

	public void startSchedule() {
		mHandler.removeCallbacks(runnable);
		mHandler.postDelayed(runnable, TIME_INTERVL);
	}

	public void destroy() {
		mHandler.removeCallbacks(runnable);
	}
}
