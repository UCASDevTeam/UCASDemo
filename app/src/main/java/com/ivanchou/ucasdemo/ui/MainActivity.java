package com.ivanchou.ucasdemo.ui;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.ui.base.BaseActivity;
import com.ivanchou.ucasdemo.ui.fragment.DetailsFragment;
import com.ivanchou.ucasdemo.ui.fragment.JointedFragment;
import com.ivanchou.ucasdemo.ui.fragment.NavigationDrawerFragment;
import com.ivanchou.ucasdemo.ui.fragment.NavigationDrawerFragment.NavigationDrawerCallback;
import com.ivanchou.ucasdemo.ui.fragment.PosterAlbumFragment;
import com.ivanchou.ucasdemo.ui.fragment.TimeLineFragment;
import com.ivanchou.ucasdemo.ui.fragment.DetailsFragment.DetailsCallback;
import com.ivanchou.ucasdemo.ui.fragment.PosterAlbumFragment.PosterAlbumCallback;

/**
 * Created by ivanchou on 1/15/2015.
 */
public class MainActivity extends BaseActivity implements NavigationDrawerCallback, DetailsCallback, PosterAlbumCallback {
    private CharSequence mTitle;

    private static final int TIME_LINE_FRAGMENT = 0;
    private static final int JOINTED_FRAGMENT = 1;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private TimeLineFragment mTimeLineFragment;
    private JointedFragment mJointedFragment;
    private DetailsFragment mDetailsFragment;
    private PosterAlbumFragment mPosterAlbumFragment;

    private ImageView [] mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setIcon(R.drawable.ic_launcher);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Log.e(TAG, "-------" + position + "------------");

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        switch (position) {
            case TIME_LINE_FRAGMENT:
                if (mTimeLineFragment == null) {
                    mTimeLineFragment = new TimeLineFragment();
                }
                mTitle = getString(R.string.drawer_item_timeline);
                fragmentTransaction.replace(R.id.content_frame, mTimeLineFragment);
                break;
            case JOINTED_FRAGMENT:
                if (mJointedFragment == null) {
                    mJointedFragment = new JointedFragment();
                }
                mTitle = getString(R.string.drawer_item_jointed);
                fragmentTransaction.replace(R.id.content_frame, mJointedFragment);
                break;
        }
        setTitle(mTitle);
        fragmentTransaction.commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // 打开 关闭 drawer 的两种不同的 menu 状态
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /*  解析海报图像  */
    private void reSolvePosterImage(){
        int [] mImages = new int[] {
                R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5,
                R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9, R.drawable.m10
        };

        mImageViews = new ImageView[mImages.length];
        for (int i=0;i<mImages.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImages[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            mImageViews[i] = imageView;
        }
    }

    /*  构建详情界面  */
    private void createDetailsFragment(){
        if (mDetailsFragment == null){
            mDetailsFragment = new DetailsFragment();
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,mDetailsFragment);
        fragmentTransaction.commit();
    }

    /*  详情界面借口函数
    *   onDetailsFragmentClick
    *       收集来自详情界面的点击事件
    *   getEvent
    *       详情界面读取事件*/
    @Override
    public void onDetailsFragmentClick(int viewID) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (viewID){
            case DetailsFragment.POSTER_ON_CLICK:
                if (mPosterAlbumFragment == null){
                    mPosterAlbumFragment = new PosterAlbumFragment();
                }
                fragmentTransaction.replace(R.id.content_frame,mPosterAlbumFragment);
                break;
            case DetailsFragment.MAP_ON_CLICK:
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public EventModel getEvent() {
        return null;
    }

    /*  相册界面接口
    *   onPosterAlbumQuit
    *       相册界面的退出接口
    *   getImageViews
    *       获取图像数据*/
    @Override
    public void onPosterAlbumQuit() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,mDetailsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public ImageView[] getImageViews() {
        return mImageViews;
    }



}
