package com.ynpulse.hht_juai.dropdown_menu;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ynpulse.hht_juai.R;

public class DropDownMenu {
    private final OnListClickListener mListener;  //item单击事件接口
    private static DropDownMenu instance;
    private static Context context;
    private PopupWindow popupWindow;
    private View dismiss;  //点击后展开的半透明挡板
    private Boolean showShadow=true;  //是否需要半透明挡板
    private String showName;   //适配器中所需要显示内容的名字，仅在modifyText中有值时使用
    private String selectName;  //搜索时用的key
    private int itemNum=6;
    private int indexColor;   //设置外部所点击的View颜色
    private static int viewColor;  //外部所点击的View本来的颜色

    public static DropDownMenu getInstance(Context context,OnListClickListener mListener){
        instance=new DropDownMenu(context,mListener);
        return instance;
    }
    public DropDownMenu(Context context,OnListClickListener mListener){
        this.context=context;
        this.mListener=mListener;
    }

    /**
     *
     * @param screenWidth  屏幕宽度
     * @param screenHeight  屏幕高度
     * @param searchAdapter 设配器
     * @param item            listView的item
     * @param layout   含有ListView的布局文件
     * @param dropDownByView  菜单弹出后在哪个View下
     * @param modifyText    点击后需要修改的TextView
     * @param type          点击了哪一个标签
     * @param menuSize           是否需要限制弹出菜单大小,若需要,则传true，默认为6个item高,通过setItemNum方法进行设定
     */
    public void showSelectList(final int screenWidth, final int screenHeight, final Madapter searchAdapter, View layout, View item, final View dropDownByView, final TextView modifyText,final String type,final boolean menuSize){
        ListView listView=layout.findViewById(R.id.listview);  //显示列表
        viewColor=dropDownByView.getDrawingCacheBackgroundColor();
        if (menuSize&&searchAdapter!=null&&searchAdapter.getCount()!=0){
            ViewGroup.LayoutParams params=listView.getLayoutParams();
            int width=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            int height=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            item.measure(width,height);
            item.getMeasuredWidth(); //获取宽度
            item.getMeasuredHeight(); //获取高度
            params.height=itemNum*(item.getMeasuredHeight())+14;
            listView.setLayoutParams(params);

        }else{
            listView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
        }
        if (showShadow){
            dismiss=layout.findViewById(R.id.dismiss);

            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    dropDownByView.setBackgroundColor(viewColor);
                }
            });

        }

        if (indexColor!=0){
            dropDownByView.setBackgroundColor(indexColor);
        }else{
            dropDownByView.setBackgroundColor(viewColor);
        }


        listView.setAdapter(searchAdapter);
        popupWindow=new PopupWindow(layout,screenWidth,FrameLayout.LayoutParams.WRAP_CONTENT,true);

        //使点击popupWindow之外的区域时，popupWindow自动消失，这句必须放在showDropDown 之前
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置popWindow的位置，第一个参数表示位于哪个控件之下，第二个参数表示x轴的偏移量，第三个参数表示y轴的偏移量
        popupWindow.showAsDropDown(dropDownByView,-5,3);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dropDownByView.setBackgroundColor(viewColor);   //设置弹出窗消失后触发按钮回复到初始颜色。
            }
        });
        popupWindow.update();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchAdapter.setSelectPosition(position);
                searchAdapter.notifyDataSetInvalidated();
                if (modifyText!=null){
                    modifyText.setText(searchAdapter.getShowKey(position,showName));
                }
                mListener.search(searchAdapter.getShowKey(position,selectName));
                popupWindow.dismiss();
                dropDownByView.setBackgroundColor(viewColor);
            }
        });
    }


    public void setShowShadow(Boolean showShadow){
        this.showShadow=showShadow;
    }
    public void setItemNum(int itemNum){
        this.itemNum=itemNum;
    }
    public void setShowName(String showName){
        this.showName=showName;
    }
    public void setSelectName(String selectName){
        this.selectName=selectName;
    }
    public void setIndexColor(int color){
        this.indexColor=context.getResources().getColor(color);
    }







    public interface  OnListClickListener{
        void search(String name); //查询学生姓名
        void changeSelectPanel(Madapter madapter, View view);  //修改选中后的item颜色，以及点击后对View进行一些修改
    }

}
