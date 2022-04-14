package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
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

import java.util.List;

public class SinnohAdapter extends RecyclerView.Adapter<SinnohAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgpokemon;
        ImageView imgtipo;
        ImageView imgshini;
        TextView nombrepokemon;
        TextView pcmaximo;
        TextView datos;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            imgtipo = (ImageView) itemView.findViewById(R.id.imgtipo);
            imgshini = (ImageView) itemView.findViewById(R.id.imgshini);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            pcmaximo = (TextView) itemView.findViewById(R.id.pcmaximo);
            datos = (TextView) itemView.findViewById(R.id.dato);
            cv=itemView.findViewById(R.id.card);
        }
    }

    private List<Sinnoh> sinnohList;
    public  SinnohAdapter(List<Sinnoh> sinnohs){this.sinnohList = sinnohs;}

    public SinnohAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View sinnohView = inflater.inflate(R.layout.card_sinnoh, parent, false);
        ViewHolder viewHolder = new ViewHolder(sinnohView);

        return viewHolder;
    }


    public void onBindViewHolder(SinnohAdapter.ViewHolder holder, int position) {
        Sinnoh sinnoh = sinnohList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgtipo = holder.imgtipo;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView pcmaximo = holder.pcmaximo;
        TextView dato = holder.datos;
        Uri urifotopokemon = Uri.parse(sinnoh.getFoto());
        Uri urifototipo = Uri.parse(sinnoh.getTipo());
        Uri urifotoshini = Uri.parse(sinnoh.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifototipo).into(imgtipo);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+sinnoh.getNombre());
        pcmaximo.setText("PC Max : "+sinnoh.getPcmax());
        dato.setText("Dato Pokemon : "+sinnoh.getDatos());
    }

    @Override
    public int getItemCount()  { return sinnohList.size(); }
}
