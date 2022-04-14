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
import com.zerodayschool.gocommunity.clases.Teselia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeseliaPAdapter extends RecyclerView.Adapter<TeseliaPAdapter.ViewHolder>{

    // Define listener member variable
    private TeseliaPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(TeseliaPAdapter.OnItemClickListener listener) {
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


    private List<Teselia> teseliaList;
    private List<Teselia> originalItems;//buscador
    public  TeseliaPAdapter(List<Teselia> teselias){this.teseliaList = teselias;
        this.originalItems = new ArrayList<>();//buscador
        originalItems.addAll(teselias);//buscador
    }

    public TeseliaPAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View teseliaView = inflater.inflate(R.layout.card_preview_teselia, parent, false);
        TeseliaPAdapter.ViewHolder viewHolder = new TeseliaPAdapter.ViewHolder(teseliaView);

        return viewHolder;
    }


    public void onBindViewHolder(TeseliaPAdapter.ViewHolder holder, int position) {
        Teselia teselia = teseliaList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgtipo = holder.imgtipo;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView pcmaximo = holder.pcmaximo;
        Uri urifotopokemon = Uri.parse(teselia.getFoto());
        Uri urifototipo = Uri.parse(teselia.getTipo());
        Uri urifotoshini = Uri.parse(teselia.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifototipo).into(imgtipo);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+teselia.getNombre());
        pcmaximo.setText("PC Max : "+teselia.getPcmax());

    }

    @Override
    public int getItemCount() { return teseliaList.size(); }

    //buscador
    public void filter (String strSearch){

        int longuitud = strSearch.length();
        if (longuitud == 0){
            teseliaList.clear();
            teseliaList.addAll(originalItems);
        }else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Teselia> collecion = teseliaList.stream().filter(i -> i.getNombre().toLowerCase().contains(strSearch.toLowerCase()))
                        .collect(Collectors.toList());
                teseliaList.clear();
                teseliaList.addAll(collecion);
            }else {
                for (Teselia c: originalItems){
                    if (c.getNombre().toLowerCase().contains(strSearch.toLowerCase())){
                        teseliaList.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }
}
