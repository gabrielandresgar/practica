package com.zerodayschool.gocommunity.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.NidosPokemon;

import java.util.List;

public class NidosPokemonPAdapter  extends   RecyclerView.Adapter<NidosPokemonPAdapter.ViewHolder>{
    // Define listener member variable
    private NidosPokemonPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(NidosPokemonPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView imgpokemon;
        ImageView imgpais;
        ImageView imgshini;
        TextView nombrepokemon;
        TextView coordenadas;
        ImageButton imagcopy;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            imgpais = (ImageView) itemView.findViewById(R.id.imgpais);
            imgshini = (ImageView) itemView.findViewById(R.id.imgshini);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            coordenadas = (TextView) itemView.findViewById(R.id.coordenadas);
            imagcopy = (ImageButton) itemView.findViewById(R.id.imacopy);
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

    private List<NidosPokemon> nidosPokemonList;

    public  NidosPokemonPAdapter(List<NidosPokemon> nidosPokemons){this.nidosPokemonList = nidosPokemons;}

    public NidosPokemonPAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View nidospokemonView = inflater.inflate(R.layout.card_preview_nidospokemon, parent, false);
        NidosPokemonPAdapter.ViewHolder viewHolder = new NidosPokemonPAdapter.ViewHolder(nidospokemonView);

        return viewHolder;
    }

    public void onBindViewHolder(NidosPokemonPAdapter.ViewHolder holder, int position) {
        NidosPokemon nidosPokemon = nidosPokemonList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgpokemon = holder.imgpokemon;
        ImageView imgpais = holder.imgpais;
        ImageView imgshini = holder.imgshini;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView coordenadas = holder.coordenadas;
        ImageButton imagcopy = holder.imagcopy;
        Uri urifotopokemon = Uri.parse(nidosPokemon.getFoto());
        Uri urifotopais = Uri.parse(nidosPokemon.getPais());
        Uri urifotoshini = Uri.parse(nidosPokemon.getVariocolor());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        Glide.with(holder.context).load(urifotopais).into(imgpais);
        Glide.with(holder.context).load(urifotoshini).into(imgshini);
        nombrepokemon.setText("Nombre : "+nidosPokemon.getNombre());
        coordenadas.setText("Coordenadas : "+nidosPokemon.getCoordenadas());

        imagcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri copia = Uri.parse(nidosPokemon.getCoordenadas());

                Bundle dato = new Bundle();
                dato.putString("id", nidosPokemon.getId());
                dato.putSerializable("dato", nidosPokemon);

                String datocopi = copia.toString();
                ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("editext", datocopi);
                clipboardManager.setPrimaryClip(clipData);
                //clipData.getDescription();
                Toast.makeText(v.getContext(), "Coordenadas Copiadas", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public int getItemCount() { return nidosPokemonList.size(); }

}
