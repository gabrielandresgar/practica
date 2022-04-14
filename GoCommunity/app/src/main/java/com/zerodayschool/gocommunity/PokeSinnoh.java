package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.SinnohPAdapter;
import com.zerodayschool.gocommunity.clases.Sinnoh;

import java.util.ArrayList;

public class PokeSinnoh extends AppCompatActivity implements SearchView.OnQueryTextListener {


    ArrayList<Sinnoh> listsinnoh;
    SinnohPAdapter sinnohAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_sinnoh);

        listsinnoh = new ArrayList<>();
        listsinnoh.clear();
        listsinnoh = MainActivity.listsinnoh;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeSinnoh);
        svSearch = findViewById(R.id.svSearch);//buscador
        sinnohAdapter = new SinnohPAdapter(listsinnoh) {
        };
        recyclerView.setAdapter(sinnohAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }
    private void eventosClick() {
        sinnohAdapter.setOnItemClickListener(new SinnohPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Sinnoh sinnoh =listsinnoh.get(position);
                Bundle sinnohEnviar =new Bundle();
                sinnohEnviar.putSerializable("dato", sinnoh);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(sinnohEnviar);
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
        sinnohAdapter.filter(newText);
        return false;
    }


}