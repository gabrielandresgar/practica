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
import com.zerodayschool.gocommunity.clases.Guiago;

import java.util.List;

public class GuiagoPAdapte extends   RecyclerView.Adapter<GuiagoPAdapte.ViewHolder>{


    // Define listener member variable
    private GuiagoPAdapte.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(GuiagoPAdapte.OnItemClickListener listener) {
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

    private List<Guiago> guiagoList;

    public  GuiagoPAdapte(List<Guiago> guiagos){this.guiagoList = guiagos;}

    public GuiagoPAdapte.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View guiagoView = inflater.inflate(R.layout.card_preview_guiago, parent, false);
        GuiagoPAdapte.ViewHolder viewHolder = new GuiagoPAdapte.ViewHolder(guiagoView);

        return viewHolder;
    }


    public void onBindViewHolder(GuiagoPAdapte.ViewHolder holder, int position) {
        Guiago guiago = guiagoList.get(position);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.animacioncardview));
        ImageView imgguia = holder.imgguia;
        TextView titulo = holder.titulo;
        Uri urifotoguia = Uri.parse(guiago.getFoto());

        Glide.with(holder.context).load(urifotoguia).into(imgguia);
        titulo.setText(guiago.getNombre());

    }

    @Override
    public int getItemCount() { return guiagoList.size(); }
}
