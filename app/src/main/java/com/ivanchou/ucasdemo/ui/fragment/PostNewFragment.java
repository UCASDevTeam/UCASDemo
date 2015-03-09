package com.ivanchou.ucasdemo.ui.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.core.model.TagModel;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;
import com.ivanchou.ucasdemo.ui.view.FooterTagsView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class PostNewFragment extends BaseFragment {

    private PostNewCallback mCallback;
    private Activity mActivity;
    private View mPostNewView;

    private FooterTagsView mTagsViewTags;
    private FooterTagsView mTagsViewNames;
    private EditText mTitle;
    private EditText mLocate;
    private TextView mTextTitle;
    private TextView mTextLocate;
    private TextView mStartAt;
    private TextView mEndAt;
    private TextView mTextPrivate;
    private Button mInvite;
    private Button mAdvanced;
    private Button mPost;
    private Button mMap;
    private Switch mPrivate;
    private PopupWindow mTimePicker;

    private TagModel [] mTags;
    private TagModel [] mNameTags;
    private List<TagModel> mNameList;

    private EventModel mEvent;

    private boolean mTitleLock;
    private int mNameLocalID;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        try {
            mCallback = (PostNewCallback)activity;
        } catch (ClassCastException e){
            throw new ClassCastException("Activity must implements PostNewCallback;");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNameLocalID = 0;
        mNameList = new ArrayList<TagModel>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPostNewView = inflater.inflate(R.layout.fragment_post_new,container,false);

        mTagsViewTags = (FooterTagsView)mPostNewView.findViewById(R.id.ftv_post_new_tags);
        mTagsViewNames = (FooterTagsView)mPostNewView.findViewById(R.id.ftv_post_new_names);
        mTitle = (EditText)mPostNewView.findViewById(R.id.et_post_new_title);
        mLocate = (EditText)mPostNewView.findViewById(R.id.et_post_new_locate_at);
        mTextTitle = (TextView)mPostNewView.findViewById(R.id.tv_post_new_title);
        mTextLocate = (TextView)mPostNewView.findViewById(R.id.tv_post_new_locate_at);
        mStartAt = (TextView)mPostNewView.findViewById(R.id.tv_post_new_start_at);
        mEndAt = (TextView)mPostNewView.findViewById(R.id.tv_post_new_end_at);
        mTextPrivate = (TextView)mPostNewView.findViewById(R.id.tv_post_new_private);
        mInvite = (Button)mPostNewView.findViewById(R.id.bt_post_new_invite);
        mAdvanced = (Button)mPostNewView.findViewById(R.id.bt_post_new_advanced);
        mPost = (Button)mPostNewView.findViewById(R.id.bt_post_new_post);
        mMap = (Button)mPostNewView.findViewById(R.id.bt_post_new_map);

        createTags();
        initData();
        initView();

        return mPostNewView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void createTags(){
        String [] names = new String[]{"运动","娱乐","游戏","体育","竞技","讲座","农场","地主","训练"};
        mTags = new TagModel[9];
        for (int i=0;i<9;i++){
            mTags[i] = new TagModel();
            mTags[i].tagId = i;
            mTags[i].tagName = names[i];
        }

        mNameTags = null;
    }

    private void initView(){
        if (mTags != null && mTags.length != 0) {
            mTagsViewTags.setCustomTags(mTags);
            mTagsViewTags.setOnTagClickListener(new FooterTagsView.OnTagClickListener() {
                @Override
                public void onTagClickRefresh(int tags) {
                    tagsChange(tags);
                }

                @Override
                public void onTagLongClickRefresh(int tags) {
                    tagsChange(tags);
                }
            });
        }

        if(mNameTags != null && mNameTags.length != 0) {
            mTagsViewNames.setCustomTags(mNameTags);
            mTagsViewNames.setOnTagClickListener(new FooterTagsView.OnTagClickListener() {
                @Override
                public void onTagClickRefresh(int tags) {

                }

                @Override
                public void onTagLongClickRefresh(int tags) {

                }
            });
        }

        mTextPrivate.setText("私有");
        mTextTitle.setText("标题");
        mTextLocate.setText("地点");

        mStartAt.setText(mEvent.startAt);
        mStartAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime(0);
            }
        });
        mEndAt.setText(mEvent.endAt);
        mEndAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime(1);
            }
        });

        mInvite.setText("邀请");
        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invitePeople();
            }
        });

        mAdvanced.setText("高级选项");
        mAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mPost.setText("发布活动");
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mMap.setText("Map");
        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initData(){
        mEvent = new EventModel();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd\nhh:mm:ss");
        String Data = format.format(new Date(System.currentTimeMillis()));
        mEvent.startAt = Data;
        mEvent.endAt = Data;
    }

    private void tagsChange(int tags){
        mTitle.setText(Integer.toBinaryString(tags));
        mTitle.invalidate();
    }

    private void invitePeople(){
        TagModel tagModel = new TagModel();
        tagModel.tagId = mNameLocalID;
        tagModel.tagName = "张氏" + (int)(Math.random()*100);
        mNameList.add(tagModel);

        mNameTags = new TagModel[mNameList.size()];
        for (int i=0;i<mNameList.size();i++){
            mNameTags[i] = mNameList.get(i);
        }
        mTagsViewNames.setCustomTags(mNameTags);
        mTagsViewNames.invalidate();
    }

    private void pickTime(int textViewID)
    {
        LinearLayout layout = (LinearLayout)this.getActivity().getLayoutInflater().inflate(R.layout.popup_time_picker,null);
        mTimePicker = new PopupWindow(layout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTimePicker.setFocusable(true);
        mTimePicker.setOutsideTouchable(true);
        mTimePicker.update();
        mTimePicker.setBackgroundDrawable(new BitmapDrawable());
        mTimePicker.showAtLocation(mPostNewView, Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);

    }
    public interface PostNewCallback {
        public void onPostNewFragmentClick(int viewID);
    }
}
