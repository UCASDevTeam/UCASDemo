package com.ivanchou.ucasdemo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanchou.ucasdemo.ui.base.BaseFragment;

/**
 * Created by ivanchou on 1/19/2015.
 */
public class PostNewFragment extends BaseFragment {

    private PostNewCallback mCallback;
    private Activity mActivity;
    private View mPostNewView;

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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface PostNewCallback {

    }
}
