package com.zerodayschool.gocommunity.adapters;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.R;
import com.zerodayschool.gocommunity.clases.Perfil;

import java.util.List;

public class PerfilPAdapter extends AppCompatActivity {

    private PerfilPAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(PerfilPAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        ImageView fotoperfil;
        EditText nickname;
        EditText celular;

        public ViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            fotoperfil =(ImageView) itemView.findViewById(R.id.fotoPerfilImg);
            nickname = (EditText) itemView.findViewById(R.id.nicknameEET);
            celular = (EditText) itemView.findViewById(R.id.celularET);
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

            List<Perfil> perfilList;

        }
    }
}
