package com.hd.test.livedata.permissions;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hd.test.livedata.permissionslibrary.LP;
import com.hd.test.livedata.permissionslibrary.LPResult;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_p).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissions();
            }
        });

    }

    private void permissions() {
        new LP(this).requestArray(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE).observe(this, new Observer<LPResult>() {
            @Override
            public void onChanged(LPResult lpResult) {
                Log.d("lp", "onChanged: grant = " + lpResult.grant);
                Log.d("lp", "onChanged: rationale = " + Arrays.toString(lpResult.rationale));
                Log.d("lp", "onChanged: deny = " + Arrays.toString(lpResult.deny));
            }
        });
    }
}
