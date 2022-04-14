package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Joht;

import java.util.List;

public class JohtAdapter extends RecyclerView.Adapter<JohtAdapter.ViewHolder>{


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

    private List<Joht> johtList;

    public  JohtAdapter(List<Joht> johts){this.johtList = johts;}

    public JohtAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View johtView = inflater.inflate(R.layout.card_joht, parent, false);
        ViewHolder viewHolder = new ViewHolder(johtView);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull JohtAdapter.ViewHolder holder, int position) {
        Joht joht = johtList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgtipo = holder.imgtipo;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView pcmaximo = holder.pcmaximo;
        TextView dato = holder.datos;
        Uri urifotopokemon = Uri.parse(joht.getFoto());
        Uri urifototipo = Uri.parse(joht.getTipo());
        Uri urifotoshini = Uri.parse(joht.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifototipo).into(imgtipo);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+joht.getNombre());
        pcmaximo.setText("PC Max : "+joht.getPcmax());
        dato.setText("Dato Pokemon : "+joht.getDatos());
    }

    @Override
    public int getItemCount() { return johtList.size(); }
}
