package com.develop.vsdev;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vamshi on 26/2/18.
 */

class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {
public Context context;
private List<Infoclass> workshopclassList;
        int itemcount;

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    SquareImageView rent;

    MyViewHolder(View view){
        super(view);
        name= (TextView) view.findViewById(R.id.typelabel);
        rent=view.findViewById(R.id.typeimage);





    }
}
    public InfoAdapter(Context context, List<Infoclass> homeclassList){
        this.context=context;
        this.workshopclassList=homeclassList;

    }

    public InfoAdapter() {
    }



    @Override
    public InfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.infolayout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final InfoAdapter.MyViewHolder holder, int position) {
        final Infoclass workshopclass=workshopclassList.get(position);


        holder.name.setText(workshopclass.getNames());
        holder.rent.setImageResource(workshopclass.getImages());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c=v.getContext();
                Intent i =new Intent(c,DetailActivity.class);
                c.startActivity(i);

            }
        });






    }

    @Override
    public int getItemCount() {
        itemcount= workshopclassList.size();
        Log.i("logman","ldkdkdkfkd"+itemcount);

        return itemcount;

    }


}
