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
import com.zerodayschool.gocommunity.clases.Trucosgo;

import java.util.List;

public class TrucosgoAdapter extends RecyclerView.Adapter<TrucosgoAdapter.ViewHolder>{


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

    private List<Trucosgo> trucosgoList;

    public  TrucosgoAdapter(List<Trucosgo> trucosgos){this.trucosgoList = trucosgos;}
    public TrucosgoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View trucogoView = inflater.inflate(R.layout.card_trucosgo, parent, false);
     ViewHolder viewHolder = new ViewHolder(trucogoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrucosgoAdapter.ViewHolder holder, int position) {
        Trucosgo trucosgo = trucosgoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgguia = holder.imgguia;
        TextView titulo = holder.titulo;
        TextView uso = holder.uso;
        Uri urifotoguia = Uri.parse(trucosgo.getFoto());

        Glide.with(holder.context).load(urifotoguia).into(imgguia);
        titulo.setText(trucosgo.getNombre());
        uso.setText("Como hacerlo : "+trucosgo.getDescripcion());
    }

    @Override
    public int getItemCount() { return trucosgoList.size(); }
}
