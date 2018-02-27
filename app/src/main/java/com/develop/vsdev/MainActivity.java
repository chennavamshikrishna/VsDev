package com.develop.vsdev;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.develop.vsdev.Utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.status));
        }
        setUpFragmentView();
    }

    private void setUpFragmentView() {
        PhoneNumberFragment phoneNumberFragment = (PhoneNumberFragment) getSupportFragmentManager().findFragmentById(R.id.phone_number_fragment_holder);
        if (phoneNumberFragment == null) {
            phoneNumberFragment = PhoneNumberFragment.getInstance();
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), phoneNumberFragment, R.id.phone_number_fragment_holder);
        }
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

        super.onBackPressed();
    }
}
