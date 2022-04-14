package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.TeseliaPAdapter;
import com.zerodayschool.gocommunity.clases.Teselia;

import java.util.ArrayList;

public class PokeTeselia extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Teselia> listteselia;
    TeseliaPAdapter teseliaAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_teselia);

        listteselia = new ArrayList<>();
        listteselia.clear();
        listteselia = MainActivity.listteselia;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeTeselia);
        svSearch = findViewById(R.id.svSearch);//buscador
        teseliaAdapter = new TeseliaPAdapter(listteselia) {
        };
        recyclerView.setAdapter(teseliaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }

    private void eventosClick() {
        teseliaAdapter.setOnItemClickListener(new TeseliaPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Teselia teselia =listteselia.get(position);
                Bundle teseliaEnviar =new Bundle();
                teseliaEnviar.putSerializable("dato", teselia);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(teseliaEnviar);
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
        teseliaAdapter.filter(newText);
        return false;
    }


}