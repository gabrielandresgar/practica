package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Defendergym;

import java.util.List;

public class DefenergymAdapter extends RecyclerView.Adapter<DefenergymAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgpokemon;
        TextView nombrepokemon;
        TextView top;


        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            top = (TextView) itemView.findViewById(R.id.top);

        }
    }

    private List<Defendergym> defendergymList;
    public  DefenergymAdapter(List<Defendergym> defendergyms){this.defendergymList = defendergyms;}

    public DefenergymAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View defendergymView = inflater.inflate(R.layout.card_defendergym, parent, false);
        DefenergymAdapter.ViewHolder viewHolder = new DefenergymAdapter.ViewHolder(defendergymView);

        return viewHolder;
    }


    public void onBindViewHolder(DefenergymAdapter.ViewHolder holder, int position) {
        Defendergym defendergym = defendergymList.get(position);

        ImageView imgpokemon = holder.imgpokemon;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView top = holder.top;
        Uri urifotopokemon = Uri.parse(defendergym.getFoto());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        nombrepokemon.setText("Nombre :"+defendergym.getNombre());
        top.setText("TOP :"+defendergym.getTop());

    }

    @Override
    public int getItemCount()  { return defendergymList.size(); }
}
