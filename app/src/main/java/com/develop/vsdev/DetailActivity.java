package com.develop.vsdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    RecyclerView rv;
    String [] location={"Nizampet","Miyapur"};
    int images[]={R.drawable.tenant,R.drawable.land};
    List<Detailclass> avail;
    DetailAdapter detailAdapter;
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
             Log.d("length is","ram"+avail.size());


         }
        detailAdapter=new DetailAdapter(getApplicationContext(),avail);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        detailAdapter.notifyDataSetChanged();
        rv.setAdapter(detailAdapter);


    }
}
