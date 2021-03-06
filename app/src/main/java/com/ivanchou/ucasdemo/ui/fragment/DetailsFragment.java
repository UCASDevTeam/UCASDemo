package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
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
    private PosterAlbumFragment mPosterAlbumFragment;
    private DetailsCallback mCallback;

    public static final int POSTER_ON_CLICK = 0;
    public static final int MAP_ON_CLICK = 1;

    /*  事件参数    */
    private EventModel mEvent;
    private int mEventID;
    private int mUserID;

    private TextView mAuthorNickView;
    private TextView mPlaceAtView;
    private TextView mStartAtView;
    private TextView mTitleView;
    private TextView mContentView;
    private TextView mEndAtView;
    private TextView mSupportView;
    private Button mMapButton;
    private ImageView mPosterView;

    private ImageView [] mImageViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  获取时间ID和用户ID */
        mEventID = savedInstanceState.getInt("EventID",0);
        mUserID = savedInstanceState.getInt("UserID",0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*  获取界面组件  */
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

        /*  设置监听器   */
        setListener();

        /*  初始化界面组件 */
        initData();
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
        releaseFragmentStack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseFragmentStack();
    }

    /*  绘制界面    */
    private void drawView(){
        mAuthorNickView.setText(mEvent.author.name);
        mPlaceAtView.setText(mEvent.placeAt);
        mStartAtView.setText(mEvent.startAt);
        mTitleView.setText(mEvent.title);
        mContentView.setText(mEvent.content);
        mEndAtView.setText(mEvent.endAt);
        //mSupportView.setText(mEvent.supporter);
    }

    /*  获取数据后刷新界面   */
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

    /*  设置事件    */
    public void setEvent(EventModel event){
        mEvent = event;
        getData();
        return;
    }

    /*  设置监听器   */
    private void setListener()
    {

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        mPosterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailsFragmentClick(POSTER_ON_CLICK);
            }
        });
    }

    /*  释放fragment队列*/
    private void releaseFragmentStack(){
        for (int i =0;i<getFragmentManager().getBackStackEntryCount();i++){
            getFragmentManager().popBackStack();
        }
    }
    /*
        create test event

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
        reSolvePosterImage();

    }*/

    /*   启动线程，获取活动  */
    private void initData(){

    }
    /*  解析海报图像  */
    private void reSolvePosterImage(){
        int [] mImages = new int[] {
                R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5,
                R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9, R.drawable.m10
        };

        mImageViews = new ImageView[mImages.length];
        for (int i=0;i<mImages.length;i++){
            ImageView imageView = new ImageView(this.getActivity());
            imageView.setImageResource(mImages[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            mImageViews[i] = imageView;
        }
    }
    /*  详情界面借口函数
    *   onDetailsFragmentClick
    *       收集来自详情界面的点击事件
    *   getEvent
    *       详情界面读取事件*/
    public void onDetailsFragmentClick(int viewID) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (viewID){
            case DetailsFragment.POSTER_ON_CLICK:
                if (mPosterAlbumFragment == null){
                    mPosterAlbumFragment = new PosterAlbumFragment();
                }
                mPosterAlbumFragment.setImageViews(mImageViews);
                fragmentTransaction.add(R.id.content_frame,mPosterAlbumFragment).addToBackStack("DetailsFragment");
                break;
            case DetailsFragment.MAP_ON_CLICK:
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    public EventModel getEvent() {
        return null;
    }

    /*End Test*/
    public interface DetailsCallback{

    }
}
