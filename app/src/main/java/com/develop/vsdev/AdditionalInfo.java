package com.develop.vsdev;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.develop.vsdev.Utils.BlurBuilder;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdditionalInfo extends Fragment {
 ImageView bg;
 static  AdditionalInfo additionalInfo;
    public AdditionalInfo() {
        // Required empty public constructor
    }
    public static AdditionalInfo getInstance(){
        if(additionalInfo==null){
            additionalInfo=new AdditionalInfo();
        }
        return additionalInfo;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_additional_info, container, false);
        String imgpath=getArguments().getString("bitimg");
        Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(new File(imgpath)));
        Bitmap blurredBitmap = BlurBuilder.blur( getContext(), bitmap );

        BitmapDrawable ob=new BitmapDrawable(getResources(),blurredBitmap);
        v.setBackgroundDrawable(ob);

        return v;
    }

}
