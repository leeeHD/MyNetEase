package com.it520.activity.yxxbase.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Yu xiangxin on 2016/6/23 - 3:15.
 *
 * @desc ${TODD}
 */
public class ClickImageView extends ImageView {
    public ClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setAlpha((isPressed() || isFocused() || isSelected()) ? 0.3f : 1.0f);
    }
}
