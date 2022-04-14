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
import com.zerodayschool.gocommunity.clases.CodigoPromo;

import java.util.List;

public class CodigoPromoPAdapte extends   RecyclerView.Adapter<CodigoPromoPAdapte.ViewHolder>{

    // Define listener member variable
    private CodigoPromoPAdapte.OnItemClickListener listener;

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(CodigoPromoPAdapte.OnItemClickListener listener) {
        this.listener = listener;
    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgcodigopromo;
        TextView nombre;
        TextView codigo;
        ImageButton imagcopy;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgcodigopromo =(ImageView) itemView.findViewById(R.id.imgcodigopromo);
            nombre = (TextView) itemView.findViewById(R.id.nombrecodigo);
            codigo = (TextView) itemView.findViewById(R.id.codigopromotxt);
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

    private List<CodigoPromo> codigoPromoList;

    public  CodigoPromoPAdapte(List<CodigoPromo> codigopromogo){this.codigoPromoList = codigopromogo;}



    public CodigoPromoPAdapte.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View codigopromoView = inflater.inflate(R.layout.card_preview_codigo_promo, parent, false);
        CodigoPromoPAdapte.ViewHolder viewHolder = new CodigoPromoPAdapte.ViewHolder(codigopromoView);
        return viewHolder;
    }



    public void onBindViewHolder(ViewHolder holder, int position) {
        CodigoPromo codigopromo = codigoPromoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgcodigopromo = holder.imgcodigopromo;
        TextView nombre = holder.nombre;
        TextView codigo = holder.codigo;
        ImageButton imagcopy = holder.imagcopy;
        Uri urifotoguia = Uri.parse(codigopromo.getFoto());

        Glide.with(holder.context).load(urifotoguia).into(imgcodigopromo);
        nombre.setText("Nombre : "+codigopromo.getNombre());
        codigo.setText("Codigo : "+codigopromo.getCodigo());

        imagcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri copia = Uri.parse(codigopromo.getCodigo());

                Bundle dato = new Bundle();
                dato.putString("id", codigopromo.getId());
                dato.putSerializable("dato", codigopromo);

                String datocopi = copia.toString();
                ClipboardManager clipboardManager = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("editext", datocopi);
                clipboardManager.setPrimaryClip(clipData);
                //clipData.getDescription();
                Toast.makeText(v.getContext(), "Codigo Copiado", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public int getItemCount() {
        return codigoPromoList.size();
    }




}
