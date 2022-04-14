package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.HoennPAdapter;
import com.zerodayschool.gocommunity.clases.Hoenn;

import java.util.ArrayList;

public class PokeHoenn extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Hoenn> listhoenn;
    HoennPAdapter hoennAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_hoenn);

        listhoenn = new ArrayList<>();
        listhoenn.clear();
        listhoenn = MainActivity.listhoenn;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeHoenn);
        svSearch = findViewById(R.id.svSearch);//buscador
        hoennAdapter = new HoennPAdapter(listhoenn) {
        };
        recyclerView.setAdapter(hoennAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }

    private void eventosClick() {
        hoennAdapter.setOnItemClickListener(new HoennPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Hoenn hoenn =listhoenn.get(position);
                Bundle hoennEnviar =new Bundle();
                hoennEnviar.putSerializable("dato", hoenn);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(hoennEnviar);
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
        hoennAdapter.filter(newText);
        return false;
    }


}