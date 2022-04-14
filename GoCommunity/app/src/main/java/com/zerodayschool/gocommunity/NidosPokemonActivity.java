package com.zerodayschool.gocommunity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.NidosPokemonPAdapter;
import com.zerodayschool.gocommunity.clases.NidosPokemon;

import java.util.ArrayList;

public class NidosPokemonActivity extends AppCompatActivity {

    ArrayList<NidosPokemon> listNidospokemon;
    NidosPokemonPAdapter nidosPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nidos_pokemon);
        listNidospokemon = new ArrayList<>();
        listNidospokemon.clear();
        listNidospokemon = MainActivity.listNidospokemon;
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerNidosPokemon);
        nidosPokemonAdapter = new NidosPokemonPAdapter(listNidospokemon) {
        };
        recyclerView.setAdapter(nidosPokemonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
    }

    private void eventosClick() {
        nidosPokemonAdapter.setOnItemClickListener(new NidosPokemonPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                NidosPokemon nidosPokemon =listNidospokemon.get(position);
                Bundle nidoPokemonEnviar =new Bundle();
                nidoPokemonEnviar.putSerializable("dato", nidosPokemon);

            }
        });
    }
}