package com.hd.test.livedata.permissionslibrary;

import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

public class LPFragment extends Fragment {

    public MutableLiveData<LPResult> liveData;
    private final static int REQUEST_CODE = 1024;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void requestPermissions(String[] p) {
        if (p != null && p.length > 0) {
            liveData = new MutableLiveData<>();
            requestPermissions(p, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            LPResult result = new LPResult();
            ArrayList<String> rationaleList = new ArrayList<>();
            ArrayList<String> denyList = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {
                int results = grantResults[i];
                if (results == PackageManager.PERMISSION_DENIED) {//如果是拒绝，再判断是不是勾选了不再显示
                    if (shouldShowRequestPermissionRationale(permissions[i])) {
                        rationaleList.add(permissions[i]);
                    } else {
                        denyList.add(permissions[i]);
                    }
                }
            }
            if (rationaleList.isEmpty() && denyList.isEmpty()) {
                result.grant = true;
            } else {
                if (!rationaleList.isEmpty()){
                    result.rationale = rationaleList.toArray(new String[0]);
                }else if (!denyList.isEmpty()){
                    result.deny = denyList.toArray(new String[0]);
                }
            }
            liveData.postValue(result);
        }
    }
}
