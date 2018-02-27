package com.develop.vsdev;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.vsdev.Utils.OtpView;
import com.develop.vsdev.Utils.SharedPreferenceUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class OTPFragment extends Fragment {

    static OTPFragment otpfragment;
    OtpView otp;
    TextView resendcounter,timer;
    Button resend;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static final String TAG = "Otp";
    SharedPreferenceUtil sharedPreferenceUtil;
    String Mobilenumber;
    CountDownTimer countDownTimer;


    public OTPFragment() {
        // Required empty public constructor
    }
    public static OTPFragment getInstance(){

        if (otpfragment == null) {
            otpfragment = new OTPFragment();
        }
        return otpfragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Mobilenumber = "+91" + getArguments().getString("phoneNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_ot, container, false);
        otp=v.findViewById(R.id.otp_view);
        resendcounter=v.findViewById(R.id.Resend);
        resend=v.findViewById(R.id.ResendButton);
        timer=v.findViewById(R.id.timer);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferenceUtil =SharedPreferenceUtil.getInstance(getContext());

        Log.d("mobile",Mobilenumber);
        countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000));
                resend.setVisibility(View.GONE);


            }

            @Override
            public void onFinish() {
                resend.setVisibility(View.VISIBLE);

            }
        } ;
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                otp.setOTP(credential.getSmsCode());
                signInWithPhoneAuthCredential(credential);
                sharedPreferenceUtil.setPhoneNumber(Mobilenumber);
                countDownTimer.cancel();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                } else if (e instanceof FirebaseTooManyRequestsException) {

                    countDownTimer.cancel();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mResendToken = token;
                countDownTimer.start();
            }
        };
        startPhoneNumberVerification(Mobilenumber);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhoneNumberVerification(Mobilenumber);
            }
        });


        return v;

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        Log.i(TAG, "signInWithPhoneAuthCredential: ");
        if (getActivity() != null) {
            mAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.i(TAG, "onComplete: ");
                    if (task.isSuccessful()) {
                        Intent otpintent=new Intent(getActivity(), InfoActivity.class);
                        otpintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(otpintent);
                    } else {
                        Log.i(TAG, "onComplete: ");
                    }
                }
            });
        }
    }
    public void startPhoneNumberVerification(String s) {
        Log.i(TAG, "requestOTP: " + s);
        if (getActivity() != null && s != null) {
            Mobilenumber = s;
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    s,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    getActivity(),               // Activity (for callback binding)
                    mCallbacks);
        } else {
            Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
        }
    }


}
