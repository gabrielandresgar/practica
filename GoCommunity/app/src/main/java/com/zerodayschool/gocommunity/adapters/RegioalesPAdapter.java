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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Regionales;

import java.util.List;

public class RegioalesPAdapter extends   RecyclerView.Adapter<RegioalesPAdapter.ViewHolder>{

    // Define listener member variable
    private RegioalesPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(RegioalesPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgregionales;
        TextView nombre;
        TextView coordenada;
        ImageButton imagcopy;
        TextView pais;
        CardView cv;


        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgregionales =(ImageView) itemView.findViewById(R.id.imgregionales);
            nombre = (TextView) itemView.findViewById(R.id.Nombreregionales);
            coordenada = (TextView) itemView.findViewById(R.id.coordenadasregionales);
            pais = (TextView) itemView.findViewById(R.id.pais);

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

    private List<Regionales> regionalesList;

    public  RegioalesPAdapter(List<Regionales> regionales){this.regionalesList = regionales;}


    public RegioalesPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View regionalesView = inflater.inflate(R.layout.card_preview_regionales, parent, false);
        RegioalesPAdapter.ViewHolder viewHolder = new RegioalesPAdapter.ViewHolder(regionalesView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegioalesPAdapter.ViewHolder holder, int position) {
        Regionales regionales = regionalesList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgregionales = holder.imgregionales;
        TextView pais = holder.pais;
        TextView nombre = holder.nombre;
        TextView coordenada = holder.coordenada;
        ImageButton imagcopy = holder.imagcopy;
        Uri urifotoregionale = Uri.parse(regionales.getFoto());


        Glide.with(holder.context).load(urifotoregionale).into(imgregionales);
        nombre.setText("Nombre : "+regionales.getNombre());
        coordenada.setText("coordenada : "+regionales.getCoordenada());
        pais.setText("Pais : " +regionales.getPais());

        imagcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Uri copia = Uri.parse(regionales.getCoordenada());

                Bundle dato = new Bundle();
                dato.putString("id", regionales.getId());
                dato.putSerializable("dato", regionales);

                String datocopi = copia.toString();
                ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("editext", datocopi);
                clipboardManager.setPrimaryClip(clipData);
                //clipData.getDescription();
                Toast.makeText(v.getContext(), "Coordenadas Copiadas", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return regionalesList.size();
    }
}
