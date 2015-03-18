package com.ivanchou.ucasdemo.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.UserModel;
import com.ivanchou.ucasdemo.ui.view.SimpleNameListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Origa on 2015/3/15.
 */
public class SimpleUserListAdapter extends BaseAdapter {

    Context mContext;
    List<UserModel> mUsers;
    ListView mListView;

    public SimpleUserListAdapter(Context context,ListView listView) {
        mContext = context;
        mUsers = new ArrayList<UserModel>();
        mListView = listView;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mUsers.get(position).userId;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RelativeLayout frameview = (RelativeLayout)view;
        if (frameview == null){
            frameview = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.simple_name_item,null);
        }
        SimpleNameListView listView = (SimpleNameListView)frameview.findViewById(R.id.snlv_simple_name_list_view);
        listView.id = position;
        listView.setOnSelectedListener(new SimpleNameListView.OnSelectedListener() {
            @Override
            public void onSelected(int id, boolean selected) {
                Log.v("SimpleNameListView","select "+selected+" at "+id);
            }
        });
        listView.setUser(mUsers.get(position));
        return frameview;
    }

    public void addData(List<UserModel> users){
        mUsers = users;
    }

    public int getTotalHeight(){
        if(mUsers.size()==0){
            return 0;
        }
        /*View view = getView(0,null,mListView);
        view.measure(0,0);*/
        int viewHeight = 80 * getCount() + mListView.getDividerHeight() * getCount() - 1;
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = viewHeight;
        mListView.setLayoutParams(params);
        return viewHeight;
    }

}
