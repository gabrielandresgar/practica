package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.AlolaPAdapter;
import com.zerodayschool.gocommunity.clases.Alola;

import java.util.ArrayList;

public class PokeAlola extends AppCompatActivity implements SearchView.OnQueryTextListener {


    ArrayList<Alola> listalola;
    AlolaPAdapter alolaAdapter;
    private SearchView svSearch; //buscador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_alola);

        listalola = new ArrayList<>();
        listalola.clear();
        listalola = MainActivity.listalola;
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeAlola);
        svSearch = findViewById(R.id.svSearch);//buscador
        alolaAdapter = new AlolaPAdapter(listalola) {
        };
        recyclerView.setAdapter(alolaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();
    }

    private void eventosClick() {
        alolaAdapter.setOnItemClickListener(new AlolaPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Alola alola =listalola.get(position);
                Bundle alolaEnviar =new Bundle();
                alolaEnviar.putSerializable("dato", alola);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(alolaEnviar);
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
        alolaAdapter.filter(newText);
        return false;
    }
}