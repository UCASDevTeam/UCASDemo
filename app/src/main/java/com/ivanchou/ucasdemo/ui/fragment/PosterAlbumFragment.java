package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ivanchou.ucasdemo.R;
import com.ivanchou.ucasdemo.ui.adapter.PosterPagerAdapter;
import com.ivanchou.ucasdemo.ui.base.BaseFragment;

/**
 * Created by Origa on 2015/3/1.
 */
public class PosterAlbumFragment extends BaseFragment {

    private View mPosterAlbumView;
    private ViewPager mViewPager;
    private PosterPagerAdapter mPagerAdapter;

    private ImageView mBackView;
    private ImageView [] mImageViews;

    private Activity mActivity;
    private PosterAlbumCallback mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster_album,container,false);
        mPosterAlbumView = view;

        mViewPager = (ViewPager) mPosterAlbumView.findViewById(R.id.vp_poster_album_poster);
        mBackView = (ImageView) mPosterAlbumView.findViewById(R.id.iv_poster_album_back);

        setListener();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        try {
            mCallback = (PosterAlbumCallback) activity;
        } catch (ClassCastException e){
            throw new ClassCastException("Activity must implement PosterAlbumCallback");
        }
    }

    public void setImageViews(ImageView [] imageViews){
        mImageViews = imageViews;
        mPagerAdapter = new PosterPagerAdapter(this.getActivity(),mImageViews);
        mViewPager.setAdapter(mPagerAdapter);
        if(mImageViews.length > 1){
            mViewPager.setCurrentItem(mImageViews.length*50);
        }
        mViewPager.invalidate();
    }

    public void updateImage(int imageID, int position){
        mImageViews[position%mImageViews.length].setImageResource(imageID);
        mImageViews[position%mImageViews.length].invalidate();
    }

    private void setListener(){
        for (int i=0;i<mImageViews.length;i++) {
            mImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPosterAlbumQuit();
                }
            });
        }
    }

    /*  相册界面接口
*   onPosterAlbumQuit
*       相册界面的退出接口
*   getImageViews
*       获取图像数据*/
    public void onPosterAlbumQuit() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    public interface PosterAlbumCallback{
    }


}
