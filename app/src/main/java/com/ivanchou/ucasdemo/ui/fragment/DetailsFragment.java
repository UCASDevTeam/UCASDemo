package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.core.model.UserModel;

/**
 * Created by ivanchou on 1/18/2015.
 */
public class DetailsFragment extends BaseFragment {

    private Activity mActivity;
    private View mDetailsView;
    private View mFragmentContainerView;
    private DetailsCallback mCallback;

    public static final int POSTER_ON_CLICK = 0;
    public static final int MAP_ON_CLICK = 1;

    private EventModel mEvent;
    private TextView mAuthorNickView;
    private TextView mPlaceAtView;
    private TextView mStartAtView;
    private TextView mTitleView;
    private TextView mContentView;
    private TextView mEndAtView;
    private TextView mSupportView;
    private Button mMapButton;
    private ImageView mPosterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);
        mDetailsView = view;

        mAuthorNickView = (TextView) mDetailsView.findViewById(R.id.tv_details_author_nick);
        mPlaceAtView = (TextView) mDetailsView.findViewById(R.id.tv_details_place_at);
        mStartAtView = (TextView) mDetailsView.findViewById(R.id.tv_details_start_at);
        mTitleView = (TextView) mDetailsView.findViewById(R.id.tv_details_title);
        mMapButton = (Button) mDetailsView.findViewById(R.id.bt_details_map);
        mPosterView = (ImageView) mDetailsView.findViewById(R.id.iv_details_poster_view);
        mEndAtView = (TextView) mDetailsView.findViewById(R.id.tv_details_end_at);
        mContentView = (TextView) mDetailsView.findViewById(R.id.tv_details_content);
        mSupportView = (TextView) mDetailsView.findViewById(R.id.tv_details_supporter);

        setListener();

        mEvent =  mCallback.getEvent();
        createTestEvent();
        drawView();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        try {
            mCallback = (DetailsCallback)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("The Activity must implement DetailsCallback!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    private void drawView(){
        mAuthorNickView.setText(mEvent.author.name);
        mPlaceAtView.setText(mEvent.placeAt);
        mStartAtView.setText(mEvent.startAt);
        mTitleView.setText(mEvent.title);
        mContentView.setText(mEvent.content);
        mEndAtView.setText(mEvent.endAt);
        //mSupportView.setText(mEvent.supporter);
    }

    public void getData(){
        drawView();

        mAuthorNickView.invalidate();
        mPlaceAtView.invalidate();
        mStartAtView.invalidate();
        mTitleView.invalidate();
        mContentView.invalidate();
        mEndAtView.invalidate();
        mSupportView.invalidate();
    }

    public void setEvent(EventModel event){
        mEvent = event;
        getData();
        return;
    }

    private void setListener()
    {

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTestEvent();
                getData();
            }
        });

        mPosterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onDetailsFragmentClick(POSTER_ON_CLICK);
            }
        });
    }
    /*
        create test event
     */
    private void createTestEvent(){
        if(mEvent == null) {
            mEvent = new EventModel();
        }
        mEvent.author = new UserModel();
        mEvent.author.name = "应用天团第" + (int) (Math.random() * 100) + "代宗师";
        mEvent.author.userId = (int) (Math.random() * 100);
        mEvent.startAt = "2015/2/" + (int) (Math.random() * 100) + "\n 18:00";
        mEvent.endAt = "2015/2/" + (int) (Math.random() * 100) + "\n 20:00";
        mEvent.placeAt = "第一教学楼" + (int) (Math.random() * 100) + "教室";
        mEvent.title = "应用天团第" + (int) (Math.random() * 100) + "场演习报告大会";
        mEvent.content = "天气晴朗，阳光明媚，在这风和日丽的春天，我们迎来了应用天团第" +
                (int) (Math.random() * 100) + "场演习报告大会。本次演习在党的关怀领导下，取得了" +
                "巨大的成功。在经历了第" + (int) (Math.random() * 100) + "次成功之后，我们总结" +
                "出了一套强军建国的方法。相信，在未来的" + (int) (Math.random() * 100) + "年之" +
                "内，我们将处于世界领先的水平，继往开来，勇创新高！我们坚信，经过我们不懈的努" +
                "力，应用天团必将屹立于世界之巅，为中华之崛起奉献出自己的力量！最后，热烈感谢" +
                "前来聆听报告的各位领导以及老同志们，向各位领导以及老同志致以节日的祝福！";
        //mEvent.supporter = "中共中央国务院，中共中央巡视组，中共中央统战部";

    }
    /*End Test*/
    public interface DetailsCallback{
        public void onDetailsFragmentClick(int viewID);
        public EventModel getEvent();
    }
}
