package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.core.model.TagModel;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;
import com.ivanchou.ucasdemo.ui.view.FooterTagsView;

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

    private TagModel [] mTags;
    private TagModel [] mNameTags;

    private EventModel mEvent;

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
            mTags[i].tagId = i;
            mTags[i].tagName = names[i];
        }
    }

    private void initView(){
        if (mTags != null || mTags.length != 0) {
            mTagsViewTags.setCustomTags(mTags);
            mTagsViewTags.setOnTagClickListener(new FooterTagsView.OnTagClickListener() {
                @Override
                public void onTagClickRefresh(int tags) {

                }

                @Override
                public void onTagLongClickRefresh(int tags) {

                }
            });
        }

        if(mNameTags != null || mNameTags.length != 0) {
            mTagsViewNames.setCustomTags(mNameTags);
        }

        mTextPrivate.setText("私有");
        mTextTitle.setText("标题");
        mTextLocate.setText("地点");

        mStartAt.setText(mEvent.startAt);
        mEndAt.setText(mEvent.endAt);

        mInvite.setText("邀请");
        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    }

    private void initData(){
        mEvent = new EventModel();
    }
    public interface PostNewCallback {

    }
}
