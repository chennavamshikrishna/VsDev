package com.develop.vsdev;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.develop.vsdev.Datamodels.Infoclass;
import com.develop.vsdev.Utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

public class MaintabActivity extends AppCompatActivity {

    private  int [] images={R.drawable.landsales,R.drawable.landlease,R.drawable.houserent,R.drawable.bachelor,R.drawable.office,R.drawable.commercial};
    private String [] names={"Land Sales","Land Lease","House Rent","Bachelor Rent","office space","Commercial space"};
    List<Infoclass> list;
    RecyclerView recyclerView;

    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintabactivity);
        list=new ArrayList<>();
        for(int i=0;i<names.length;i++){
            Infoclass inf=new Infoclass();
            inf.setImages(images[i]);
            inf.setNames(names[i]);
            list.add(inf);


        }
        recyclerView =findViewById(R.id.recylerview);
        fab=findViewById(R.id.floatingActionButton);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);


        recyclerView.setLayoutManager(gridLayoutManager);
        InfoAdapter infoAdapter = new InfoAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(infoAdapter);
        infoAdapter.notifyDataSetChanged();
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MaintabActivity.this,UploadActivity.class));
           }
       });








    }


    /** Create a File for saving an image or video */




    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

        super.onBackPressed();
    }

}
