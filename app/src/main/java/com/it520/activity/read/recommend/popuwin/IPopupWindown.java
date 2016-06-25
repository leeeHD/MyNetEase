package com.it520.activity.read.recommend.popuwin;

import android.view.View;

/**
 * @author moy
 * @time 2016/6/6  18:54
 * @desc 对话框需要实现的方法
 *
 */

public interface IPopupWindown {
    void initView();//加载视图

    void show(Object bean, View anchor, int x, int y);//显示视图

    void dismiss();//隐藏视图
}
