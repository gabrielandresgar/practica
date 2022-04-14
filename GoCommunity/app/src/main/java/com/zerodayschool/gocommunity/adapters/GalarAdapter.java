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
import com.zerodayschool.gocommunity.clases.Galar;

import java.util.List;

public class GalarAdapter extends RecyclerView.Adapter<GalarAdapter.ViewHolder>{


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



    private List<Galar> galarList;
    public GalarAdapter(List<Galar> galars){this.galarList = galars;}

    public GalarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View galarView = inflater.inflate(R.layout.card_galar, parent, false);
       ViewHolder viewHolder = new ViewHolder(galarView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalarAdapter.ViewHolder holder, int position) {
        Galar galar = galarList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgtipo = holder.imgtipo;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView pcmaximo = holder.pcmaximo;
        TextView dato = holder.datos;
        Uri urifotopokemon = Uri.parse(galar.getFoto());
        Uri urifototipo = Uri.parse(galar.getTipo());
        Uri urifotoshini = Uri.parse(galar.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifototipo).into(imgtipo);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+galar.getNombre());
        pcmaximo.setText("PC Max : "+galar.getPcmax());
        dato.setText("Dato Pokemon : "+galar.getDatos());

    }

    @Override
    public int getItemCount() { return galarList.size(); }
}
