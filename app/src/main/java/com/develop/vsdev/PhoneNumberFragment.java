package com.develop.vsdev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.develop.vsdev.Utils.ActivityUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneNumberFragment extends Fragment {

    static PhoneNumberFragment phoneNumberFragment;
    Button submit;
    MaterialEditText phonenumber;
    FirebaseAuth mAuth;

    public PhoneNumberFragment() {
        // Required empty public constructor
    }

    public static PhoneNumberFragment getInstance() {
        if (phoneNumberFragment == null) {
            phoneNumberFragment = new PhoneNumberFragment();
        }
        return phoneNumberFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phone_number, container, false);
        phonenumber = v.findViewById(R.id.phone_number_met);
        submit = v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePhonenumber();
            }
        });
        mAuth=FirebaseAuth.getInstance();

        return v;
    }

    private void validatePhonenumber() {
        if (getView() != null) {
            String phoneNumber = phonenumber.getText().toString();
            if (phoneNumber.length() == 10) {
                OTPFragment otpFragment = OTPFragment.getInstance();
                Bundle otpBundle = new Bundle();
                Log.i(TAG, "setUpOTPFragment: phone number " + phoneNumber);
                otpBundle.putString("phoneNumber", phoneNumber);
                otpFragment.setArguments(otpBundle);
                if (getFragmentManager() != null) {
                    ActivityUtils.replaceFragmentInActivity(getFragmentManager(), otpFragment, R.id.phone_number_fragment_holder);
                }
            } else {
                Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }




}