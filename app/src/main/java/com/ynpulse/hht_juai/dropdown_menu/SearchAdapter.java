package com.ynpulse.hht_juai.dropdown_menu;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ynpulse.hht_juai.dropdown_menu.Madapter;
import com.ynpulse.hht_juai.R;
import com.ynpulse.hht_juai.entities.User;

import java.util.List;

public class SearchAdapter extends Madapter {
    private Context context;
    private int selectColor= Color.GRAY;
    private LayoutInflater inflater;
    private List<User> items;
    private int selectedPosition=-1;

    public SearchAdapter(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    public void setItems(List<User> items){
        this.items=items;
    }

    @Override
    public List<User> getItems() {
        return items;
    }

    @Override
    public String getShowKey(int position, String key) {
        if (key.equals("name")){
            return items.get(position).getRealname();
        }else{
            return  items.get(position).getUuid();
        }
    }

    @Override
    public void setSelectColor(int color) {
        this.selectColor=color;
    }

    @Override
    public void setSelectPosition(int selectedPosition) {
        this.selectedPosition=selectedPosition;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 选中条目的处理
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        SearchAdapter.ViewHolder holder;
        if (convertView==null){
            holder=new SearchAdapter.ViewHolder();
            convertView=inflater.inflate(R.layout.item_listview,parent,false);
            holder.name=convertView.findViewById(R.id.name);
            holder.hasVein=convertView.findViewById(R.id.vein);
            holder.listLayout=convertView.findViewById(R.id.list_layout);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //该项被选中时改变背景颜色
        if (selectedPosition==position){
            holder.listLayout.setBackgroundColor(selectColor);
        }else{
            holder.listLayout.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.name.setText(items.get(position).getRealname());
        holder.name.setText(items.get(position).getUuid().isEmpty()?"无":"有");

        return convertView;
    }

    /**
     * 列表条目
     */
    class ViewHolder{
        TextView name;
        TextView hasVein;
        LinearLayout listLayout;
    }
}