package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.KalosPAdapter;
import com.zerodayschool.gocommunity.clases.Kalos;

import java.util.ArrayList;

public class PokeKalos extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Kalos> listkalos;
    KalosPAdapter kalosAdapter;
    private SearchView svSearch; //buscador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_kalos);

        listkalos = new ArrayList<>();
        listkalos.clear();
        listkalos = MainActivity.listkalos;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeKalos);
        svSearch = findViewById(R.id.svSearch);//buscador
        kalosAdapter = new KalosPAdapter(listkalos) {
        };
        recyclerView.setAdapter(kalosAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }

    private void eventosClick() {
        kalosAdapter.setOnItemClickListener(new KalosPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Kalos kalos =listkalos.get(position);
                Bundle kalosEnviar =new Bundle();
                kalosEnviar.putSerializable("dato", kalos);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(kalosEnviar);
                startActivity(intent);
            }
        });
    }
    //buscador
    private void initListener(){
        svSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        kalosAdapter.filter(newText);
        return false;
    }


}