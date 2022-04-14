package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Regionales;

import java.util.List;

public class RegionalesAdapter extends RecyclerView.Adapter<RegionalesAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgregionales;
        ImageView imgpais;
        TextView nombre;
        TextView coordenada;

        CardView cv;

        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgregionales =(ImageView) itemView.findViewById(R.id.imgregionales);
            imgpais =(ImageView) itemView.findViewById(R.id.imgpaisregionales);
            nombre = (TextView) itemView.findViewById(R.id.Nombreregionales);
            cv=itemView.findViewById(R.id.card);


        }
    }


    private List<Regionales> regionalesList;

    public  RegionalesAdapter(List<Regionales> regionales){this.regionalesList = regionales;}


    public RegionalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View regionalesView = inflater.inflate(R.layout.card_regionales, parent, false);
        RegionalesAdapter.ViewHolder viewHolder = new RegionalesAdapter.ViewHolder(regionalesView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegionalesAdapter.ViewHolder holder, int position) {

        Regionales regionales = regionalesList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgregionales = holder.imgregionales;
        ImageView imgpais = holder.imgpais;
        TextView nombre = holder.nombre;
        TextView coordenada = holder.coordenada;
        Uri urifotoregionales = Uri.parse(regionales.getFoto());
        Uri urifotopais = Uri.parse(regionales.getFoto());

        Glide.with(holder.context).load(urifotoregionales).into(imgregionales);
        Glide.with(holder.context).load(urifotopais).into(imgpais);
        nombre.setText("Nombre :"+regionales.getNombre());
        coordenada.setText("Coordenada :"+regionales.getCoordenada());




    }

    @Override
    public int getItemCount() { return regionalesList.size(); }
}
