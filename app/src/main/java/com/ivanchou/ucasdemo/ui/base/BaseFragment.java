package com.ivanchou.ucasdemo.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanchou.ucasdemo.app.Config;

/**
 * Created by ivanchou on 1/15/2015.
 */
public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private final boolean ISCYCLE = Config.MODE.ISCYCLE;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        logLifeCycle();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logLifeCycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        logLifeCycle();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logLifeCycle();
    }

    @Override
    public void onStart() {
        super.onStart();
        logLifeCycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        logLifeCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        logLifeCycle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logLifeCycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logLifeCycle();
    }

    private void logLifeCycle() {
        if (ISCYCLE) {
            String methodName = Thread.currentThread().getStackTrace()[3]
                    .getMethodName();
            Log.d(TAG, "[" + TAG + "]→" + methodName + "!!!");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSerializableExtra(final String name) {
        return (T) getActivity().getIntent().getSerializableExtra(name);
    }
}
