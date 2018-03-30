package com.develop.vsdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.vsdev.Utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    RecyclerView rv;
    String [] location={"Nizampet","Miyapur"};
    int images[]={R.drawable.tenant,R.drawable.land};
    List<Detailclass> avail;
    DetailAdapter detailAdapter;
    SharedPreferenceUtil sharedPreferenceUtil;
    TextView actionBarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
         avail=new ArrayList<>();
         rv=findViewById(R.id.rev);
         for(int i=0;i<images.length;i++){
             Detailclass d=new Detailclass();
             d.setSaleimage(images[i]);
             d.setSalename(location[i]);
             avail.add(d);


         }
         sharedPreferenceUtil=SharedPreferenceUtil.getInstance(getApplicationContext());
        detailAdapter=new DetailAdapter(getApplicationContext(),avail);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        detailAdapter.notifyDataSetChanged();
        rv.setAdapter(detailAdapter);
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.actionbar_layout, null);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);
          actionBarTitle = (TextView) findViewById(R.id.action_bar_title);
        actionBarTitle.setText(sharedPreferenceUtil.getCity());
        ImageView img=findViewById(R.id.down_arrow);
        actionBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,LocationEdit.class));
            }

        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,LocationEdit.class));

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        actionBarTitle.setText(sharedPreferenceUtil.getCity());

    }
}
