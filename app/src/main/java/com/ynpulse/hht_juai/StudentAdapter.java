package com.ynpulse.hht_juai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynpulse.hht_juai.entities.User;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<User> mList;
    private LayoutInflater layoutInflater;
    private Context context;

    public StudentAdapter(Context context,List<User> mList) {
        this.mList = mList;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);  //使用context初始化布局填充器
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 逗比式，没有应用listView的缓存机制
        View view=layoutInflater.inflate(R.layout.list_item_students,null);
        ImageView avatar=view.findViewById(R.id.avatar);
        TextView name=view.findViewById(R.id.name);
        TextView hasVein=view.findViewById(R.id.vein);
        avatar.setImageBitmap(mList.get(position).getAvatarBitMap());

        name.setText(mList.get(position).getRealname());
        if(!mList.get(position).getVein().isEmpty()){
            hasVein.setText("有");
        }else {
            hasVein.setText("无");
        }
        return view;
    }
}
