package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.KantoPAdapter;
import com.zerodayschool.gocommunity.clases.Kanto;

import java.util.ArrayList;

public class PokeKanto extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Kanto> listkanto;
    KantoPAdapter kantoAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_kanto);

        listkanto = new ArrayList<>();
        listkanto.clear();
        listkanto = MainActivity.listkanto;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokekanto);
        svSearch = findViewById(R.id.svSearch);//buscador
        kantoAdapter = new KantoPAdapter(listkanto) {
        };
        recyclerView.setAdapter(kantoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }

    private void eventosClick() {
        kantoAdapter.setOnItemClickListener(new KantoPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Kanto kanto =listkanto.get(position);
                Bundle kantoEnviar =new Bundle();
                kantoEnviar.putSerializable("dato", kanto);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(kantoEnviar);
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
        kantoAdapter.filter(newText);
        return false;
    }


}