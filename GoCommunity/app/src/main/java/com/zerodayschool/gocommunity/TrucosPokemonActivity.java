package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.TrucosgoPAdapter;
import com.zerodayschool.gocommunity.clases.Trucosgo;

import java.util.ArrayList;

public class TrucosPokemonActivity extends AppCompatActivity {

    ArrayList<Trucosgo> listtrucosgo;
    TrucosgoPAdapter trucosgoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucos_pokemon);

        listtrucosgo = new ArrayList<>();
        listtrucosgo.clear();
        listtrucosgo = MainActivity.listtrucosgo;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerTrucosgo);
        trucosgoAdapter = new TrucosgoPAdapter(listtrucosgo) {
        };
        recyclerView.setAdapter(trucosgoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
    }

    private void eventosClick() {
        trucosgoAdapter.setOnItemClickListener(new TrucosgoPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Trucosgo trucosgo =listtrucosgo.get(position);
                Bundle trucosgoEnviar =new Bundle();
                trucosgoEnviar.putSerializable("dato", trucosgo);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(trucosgoEnviar);
                startActivity(intent);
            }
        });
    }
}