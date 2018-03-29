package com.develop.vsdev;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.vsdev.Utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {


    private  int [] images={R.drawable.landsales,R.drawable.landlease,R.drawable.houserent,R.drawable.bachelor,R.drawable.office,R.drawable.commercial};
    private String [] names={"Land Sales","Land Lease","House Rent","Bachelor Rent","office space","Commercial space"};
    List<Infoclass>list;
    RecyclerView recyclerView;
    SharedPreferenceUtil sharedPreferenceUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        list=new ArrayList<>();
        for(int i=0;i<names.length;i++){
            Infoclass inf=new Infoclass();
            inf.setImages(images[i]);
            inf.setNames(names[i]);
            list.add(inf);


        }
        sharedPreferenceUtil=SharedPreferenceUtil.getInstance(getApplicationContext());
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.actionbar_layout, null);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);
        final TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_title);
        actionBarTitle.setText(sharedPreferenceUtil.getCity());
        ImageView img=findViewById(R.id.down_arrow);
        actionBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView =findViewById(R.id.recylerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);


        recyclerView.setLayoutManager(gridLayoutManager);
        InfoAdapter infoAdapter = new InfoAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(infoAdapter);
        infoAdapter.notifyDataSetChanged();




    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

        super.onBackPressed();
    }
}
