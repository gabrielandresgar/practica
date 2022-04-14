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
import com.zerodayschool.gocommunity.clases.Guiago;

import java.util.List;

public class GuiagoAdapter extends RecyclerView.Adapter<GuiagoAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgguia;
        TextView titulo;
        TextView uso;
        CardView cv;

        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgguia =(ImageView) itemView.findViewById(R.id.imgguia);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            uso = (TextView) itemView.findViewById(R.id.uso);
            cv=itemView.findViewById(R.id.card);
        }
    }
    private List<Guiago> guiagoList;

    public  GuiagoAdapter(List<Guiago> guiagos){this.guiagoList = guiagos;}

    public GuiagoAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View guiagoView = inflater.inflate(R.layout.card_guiago, parent, false);
    ViewHolder viewHolder = new ViewHolder(guiagoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuiagoAdapter.ViewHolder holder, int position) {
        Guiago guiago = guiagoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgguia = holder.imgguia;
        TextView titulo = holder.titulo;
        TextView uso = holder.uso;
        Uri urifotoguia = Uri.parse(guiago.getFoto());

        Glide.with(holder.context).load(urifotoguia).into(imgguia);
        titulo.setText(guiago.getNombre());
        uso.setText("Como hacerlo :  "+guiago.getUso());
    }

    @Override
    public int getItemCount() { return guiagoList.size(); }
}
