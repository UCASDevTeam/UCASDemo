package com.ivanchou.ucasdemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.UserModel;

/**
 * Created by Origa on 2015/3/17.
 */
public class SimpleNameListView extends LinearLayout {

    private LayoutInflater mInflater;

    private TextView mNameText;
    private CheckBox mSelected;
    private OnSelectedListener mCallback;

    private UserModel mUser;

    public int id;
    public boolean selected;

    public SimpleNameListView(Context context) {
        super(context);
        initView(context);
    }

    public SimpleNameListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SimpleNameListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int height = 0;
        if (mNameText != null && mSelected != null) {
            height = Math.max(mNameText.getHeight(), mSelected.getHeight());
        }
        height = Math.max(height, 80);
        setMeasuredDimension(widthSize,(heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {
        if(getChildAt(0)!=null) {
            getChildAt(0).layout(i, i2, i3, i4);
        }
    }

    private void initView(Context context){
        mInflater = LayoutInflater.from(context);
        FrameLayout layout = (FrameLayout)mInflater.inflate(R.layout.simple_name_lv_item,this,false);
        mNameText = (TextView)layout.findViewById(R.id.tv_simple_name_list_name);
        mSelected = (CheckBox)layout.findViewById(R.id.cb_simple_name_list_selected);
        selected = true;
        addView(layout);
        mCallback = null;
        mNameText.setText("Name");
        mSelected.setChecked(selected);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = !selected;
                mSelected.setChecked(selected);
                Log.v("SimpleNameList","Selected" + id);
                mCallback.onSelected(id,selected);
            }
        });
        mSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selected = b;
                mCallback.onSelected(id,selected);
            }
        });
    }

    public void setUser(UserModel user){
        mUser = user;
        mNameText.setText(mUser.name);
        mNameText.invalidate();
    }
    public void setOnSelectedListener(OnSelectedListener listener){
        mCallback = listener;
    }

    public interface OnSelectedListener{

        public void onSelected(int id,boolean selected);
    }
}
