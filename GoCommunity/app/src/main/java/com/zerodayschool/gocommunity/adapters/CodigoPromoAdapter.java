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
import com.zerodayschool.gocommunity.clases.CodigoPromo;

import java.util.List;

public class CodigoPromoAdapter extends RecyclerView.Adapter<CodigoPromoAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgcodigopromo;
        TextView nombre;
        TextView codigo;
        CardView cv;

        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgcodigopromo =(ImageView) itemView.findViewById(R.id.imgcodigopromo);
            nombre = (TextView) itemView.findViewById(R.id.nombrecodigo);
            codigo = (TextView) itemView.findViewById(R.id.codigopromotxt);
            cv=itemView.findViewById(R.id.card);
        }
    }

    private List<CodigoPromo> codigoPromoList;

    public  CodigoPromoAdapter(List<CodigoPromo> codigoPromos){this.codigoPromoList = codigoPromos;}


    public CodigoPromoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View codigopromoView = inflater.inflate(R.layout.card_codigo_promo, parent, false);
        CodigoPromoAdapter.ViewHolder viewHolder = new CodigoPromoAdapter.ViewHolder(codigopromoView);

        return viewHolder;
    }


    public void onBindViewHolder(@NonNull CodigoPromoAdapter.ViewHolder holder, int position) {
        CodigoPromo codigoPromo = codigoPromoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgcodigopromo = holder.imgcodigopromo;
        TextView nombre = holder.nombre;
        TextView codigo = holder.codigo;
        Uri urifotocodigo = Uri.parse(codigoPromo.getFoto());

        Glide.with(holder.context).load(urifotocodigo).into(imgcodigopromo);
        nombre.setText("Nombre :"+codigoPromo.getNombre());
        codigo.setText("Codigo :"+codigoPromo.getCodigo());
    }


    public int getItemCount() {
         return codigoPromoList.size();
    }
}
