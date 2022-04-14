package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Defendergym;

import java.util.List;

public class DefendergymPAdapter  extends   RecyclerView.Adapter<DefendergymPAdapter.ViewHolder> {

    // Define listener member variable
    private DefendergymPAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(DefendergymPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView imgpokemon;
        TextView nombrepokemon;
        TextView top;


        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            imgpokemon =(ImageView) itemView.findViewById(R.id.imgpokemon);
            nombrepokemon = (TextView) itemView.findViewById(R.id.nombrepokemon);
            top = (TextView) itemView.findViewById(R.id.top);

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

    private List<Defendergym> defendergymList;
    public  DefendergymPAdapter(List<Defendergym> defendergyms){this.defendergymList = defendergyms;}

    public DefendergymPAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View defendergymView = inflater.inflate(R.layout.card_preview_defendergym, parent, false);
        DefendergymPAdapter.ViewHolder viewHolder = new DefendergymPAdapter.ViewHolder(defendergymView);

        return viewHolder;
    }


    public void onBindViewHolder( DefendergymPAdapter.ViewHolder holder, int position) {
        Defendergym defendergym = defendergymList.get(position);

        ImageView imgpokemon = holder.imgpokemon;
        TextView nombrepokemon = holder.nombrepokemon;
        TextView top = holder.top;
        Uri urifotopokemon = Uri.parse(defendergym.getFoto());

        Glide.with(holder.context).load(urifotopokemon).into(imgpokemon);
        nombrepokemon.setText("Nombre :"+defendergym.getNombre());
        top.setText("TOP :"+defendergym.getTop());
    }

    @Override
    public int getItemCount() { return defendergymList.size(); }
}
