package com.hd.test.livedata.permissionslibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

public class LP {

    private static final String TAG = "lp_fragment";

    private LPFragment mLPFragment;

    public LP(AppCompatActivity activity) {
        setFragment(activity.getSupportFragmentManager());
    }

    public LP(Fragment fragment) {
        setFragment(fragment.getChildFragmentManager());
    }

    private void setFragment(FragmentManager fragmentManager) {
        if (mLPFragment == null) {
            synchronized (LP.this) {
                if (mLPFragment == null) {
                    if (fragmentManager.findFragmentByTag(TAG) == null) {
                        mLPFragment = new LPFragment();
                        fragmentManager.beginTransaction().add(mLPFragment, TAG).commitNow();
                    } else {
                        mLPFragment = (LPFragment) fragmentManager.findFragmentByTag(TAG);
                    }
                }
            }
        }
    }

    public MutableLiveData<LPResult> request(String p) {
        return this.requestArray(p);
    }

    public MutableLiveData<LPResult> requestArray(String... p) {
        mLPFragment.requestPermissions(p);
        return mLPFragment.liveData;
    }
}
