package com.develop.vsdev;

/**
 * Created by vamshi on 27/2/18.
 */

import android.content.Context;
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

class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    public Context context;
    private List<Detailclass> workshopclassList;
    int itemcount;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        SquareImageView rent;

        MyViewHolder(View view){
            super(view);
            name= (TextView) view.findViewById(R.id.salesplace);
            rent=view.findViewById(R.id.salesimage);





        }
    }
    public DetailAdapter(Context context, List<Detailclass> homeclassList){
        this.context=context;
        this.workshopclassList=homeclassList;
        Log.d("length is","krishna"+homeclassList.size());

    }



    @Override
    public DetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailactivity,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final DetailAdapter.MyViewHolder holder, int position) {
        final Detailclass workshopclass=workshopclassList.get(position);


        holder.name.setText(workshopclass.getSalename());
        holder.rent.setImageResource(workshopclass.getSaleimage());






    }

    @Override
    public int getItemCount() {
        itemcount= workshopclassList.size();
        Log.i("logman","ldkdkdkfkd"+itemcount);

        return itemcount;

    }


}

