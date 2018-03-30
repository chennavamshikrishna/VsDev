package com.develop.vsdev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {


    private  int [] images={R.drawable.landsales,R.drawable.landlease,R.drawable.houserent,R.drawable.bachelor,R.drawable.office,R.drawable.commercial};
    private String [] names={"Land Sales","Land Lease","House Rent","Bachelor Rent","office space","Commercial space"};
    List<Infoclass>list;
    RecyclerView recyclerView;


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
