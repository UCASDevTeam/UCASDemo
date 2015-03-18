package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

/**
 * Created by Origa on 2015/3/18.
 */
public class PostNewSecondFragment extends BaseFragment {

    private OnEventChangeListener mCallback;

    private View mPostNewSecondView;
    private TextView mCostView;
    private TextView mCostUnit;
    private TextView mContentView;
    private EditText mCost;
    private EditText mContent;
    private TextView mPeopleNumView;
    private EditText mPeopleNum;

    private EventModel mEventModel;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPostNewSecondView = inflater.inflate(R.layout.fragment_post_new_second,container,false);

        mCostView = (TextView)mPostNewSecondView.findViewById(R.id.tv_post_new_second_cost);
        mCost = (EditText)mPostNewSecondView.findViewById(R.id.et_post_new_second_cost);
        mCostUnit = (TextView)mPostNewSecondView.findViewById(R.id.tv_post_new_second_unit);
        mContent = (EditText)mPostNewSecondView.findViewById(R.id.et_post_new_second_content);
        mContentView = (TextView)mPostNewSecondView.findViewById(R.id.tv_post_new_second_content);
        mPeopleNumView = (TextView)mPostNewSecondView.findViewById(R.id.tv_post_new_second_people_num);
        mPeopleNum = (EditText)mPostNewSecondView.findViewById(R.id.et_post_new_second_people_num);

        setListener();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setListener(){

    }

    private void sendEventModel(){
        mCallback.onEventChange(mEventModel);
    }
    public void setOnEventChangeListener(OnEventChangeListener listener){
        mCallback = listener;
        mCost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });
    }

    public interface OnEventChangeListener{
        public void onEventChange(EventModel eventModel);
    }
}
