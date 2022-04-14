package com.zerodayschool.gocommunity.adapters;

import android.content.ClipData;
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
import com.zerodayschool.gocommunity.clases.NidosPokemon;

import java.util.List;

public class NidosPokemonAdapter extends RecyclerView.Adapter<NidosPokemonAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgpokemon;
        ImageView imgpais;
        ImageView imgshini;
        TextView nombrepokemon;
        TextView coordenadas;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            imgpais = (ImageView) itemView.findViewById(R.id.imgpais);
            imgshini = (ImageView) itemView.findViewById(R.id.imgshini);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            cv=itemView.findViewById(R.id.card);
            coordenadas = (TextView) itemView.findViewById(R.id.coordenadas);
            coordenadas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Creates a new text clip to put on the clipboard
                    ClipData clip = ClipData.newPlainText("simple text", "Hello, World!");
                 }
            });
        }

    }

    private List<NidosPokemon> nidosPokemonList;

    public  NidosPokemonAdapter(List<NidosPokemon> nidosPokemons){this.nidosPokemonList = nidosPokemons;}

    public NidosPokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View nidospokemonView = inflater.inflate(R.layout.card_nidospokemon, parent, false);
        ViewHolder viewHolder = new ViewHolder(nidospokemonView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NidosPokemonAdapter.ViewHolder holder, int position) {
        NidosPokemon nidosPokemon = nidosPokemonList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgpais = holder.imgpais;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView coordenadas = holder.coordenadas;
        Uri urifotopokemon = Uri.parse(nidosPokemon.getFoto());
        Uri urifotopais = Uri.parse(nidosPokemon.getPais());
        Uri urifotoshini = Uri.parse(nidosPokemon.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifotopais).into(imgpais);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre :"+nidosPokemon.getNombre());
        coordenadas.setText("Coordenadas :"+nidosPokemon.getCoordenadas());

    }

    @Override
    public int getItemCount() { return nidosPokemonList.size(); }

}
