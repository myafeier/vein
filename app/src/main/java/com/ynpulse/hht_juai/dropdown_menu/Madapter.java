package com.ynpulse.hht_juai.dropdown_menu;

import android.widget.BaseAdapter;

import java.util.List;

abstract class Madapter extends BaseAdapter {
    public abstract List<?> getItems();  //返回ListView的数据集
    public abstract void setSelectColor(int color);  //修改选中的item颜色
    public abstract void setSelectPosition(int selectedPosition); //设置选中项目
    public abstract String getShowKey(int position,String key);  //获取选中值，必须有这个方法
}
