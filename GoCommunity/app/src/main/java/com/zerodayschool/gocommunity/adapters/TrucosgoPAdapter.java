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

public class TrucosgoPAdapter extends RecyclerView.Adapter<TrucosgoPAdapter.ViewHolder>{


    // Define listener member variable
    private TrucosgoPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(TrucosgoPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgguia;
        TextView titulo;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgguia =(ImageView) itemView.findViewById(R.id.imgguia);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
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

    private List<Trucosgo> trucosgoList;

    public  TrucosgoPAdapter(List<Trucosgo> trucosgos){this.trucosgoList = trucosgos;}

    public TrucosgoPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View trucogoView = inflater.inflate(R.layout.card_preview_trucosgo, parent, false);
        TrucosgoPAdapter.ViewHolder viewHolder = new TrucosgoPAdapter.ViewHolder(trucogoView);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull TrucosgoPAdapter.ViewHolder holder, int position) {
        Trucosgo trucosgo = trucosgoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgguia = holder.imgguia;
        TextView titulo = holder.titulo;
        Uri urifotoguia = Uri.parse(trucosgo.getFoto());

        Glide.with(holder.context).load(urifotoguia).into(imgguia);
        titulo.setText(trucosgo.getNombre());

    }

    @Override
    public int getItemCount() { return trucosgoList.size(); }
}
