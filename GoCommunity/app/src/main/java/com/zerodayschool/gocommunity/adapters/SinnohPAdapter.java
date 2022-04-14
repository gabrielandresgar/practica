package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Sinnoh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SinnohPAdapter extends RecyclerView.Adapter<SinnohPAdapter.ViewHolder> {


    // Define listener member variable
    private SinnohPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(SinnohPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgpokemon;
        ImageView imgtipo;
        ImageView imgshini;
        TextView nombrepokemon;
        TextView pcmaximo;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            imgtipo = (ImageView) itemView.findViewById(R.id.imgtipo);
            imgshini = (ImageView) itemView.findViewById(R.id.imgshini);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            pcmaximo = (TextView) itemView.findViewById(R.id.pcmaximo);
            cv=itemView.findViewById(R.id.card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    private List<Sinnoh> sinnohList;
    private List<Sinnoh> originalItems;//buscador
    public  SinnohPAdapter(List<Sinnoh> sinnohs){this.sinnohList = sinnohs;
        this.originalItems = new ArrayList<>();//buscador
        originalItems.addAll(sinnohs);//buscador
    }

    public SinnohPAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View sinnohView = inflater.inflate(R.layout.card_preview_sinnoh, parent, false);
        SinnohPAdapter.ViewHolder viewHolder = new SinnohPAdapter.ViewHolder(sinnohView);

        return viewHolder;
    }


    public void onBindViewHolder(SinnohPAdapter.ViewHolder holder, int position) {
        Sinnoh sinnoh = sinnohList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgtipo = holder.imgtipo;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView pcmaximo = holder.pcmaximo;
        Uri urifotopokemon = Uri.parse(sinnoh.getFoto());
        Uri urifototipo = Uri.parse(sinnoh.getTipo());
        Uri urifotoshini = Uri.parse(sinnoh.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifototipo).into(imgtipo);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+sinnoh.getNombre());
        pcmaximo.setText("PC Max : "+sinnoh.getPcmax());

    }

    @Override
    public int getItemCount() { return sinnohList.size(); }

    //buscador
    public void filter (String strSearch){

        int longuitud = strSearch.length();
        if (longuitud == 0){
            sinnohList.clear();
            sinnohList.addAll(originalItems);
        }else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Sinnoh> collecion = sinnohList.stream().filter(i -> i.getNombre().toLowerCase().contains(strSearch.toLowerCase()))
                        .collect(Collectors.toList());
                sinnohList.clear();
                sinnohList.addAll(collecion);
            }else {
                for (Sinnoh c: originalItems){
                    if (c.getNombre().toLowerCase().contains(strSearch.toLowerCase())){
                        sinnohList.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

}
