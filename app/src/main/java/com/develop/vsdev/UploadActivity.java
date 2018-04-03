package com.develop.vsdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.develop.vsdev.Utils.ActivityUtils;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        setUpFragmentView();
    }
    private void setUpFragmentView() {
        CameraFragment cameraFragment = (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.uploadframe);
        if (cameraFragment == null) {
            cameraFragment = CameraFragment.getInstance();
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),cameraFragment, R.id.uploadframe);
        }
    }
}
