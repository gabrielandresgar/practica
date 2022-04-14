package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.JohtPAdapter;
import com.zerodayschool.gocommunity.clases.Joht;

import java.util.ArrayList;

public class PokeJoht extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ArrayList<Joht> listjoht;
    JohtPAdapter johtAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_joht);

        listjoht = new ArrayList<>();
        listjoht.clear();
        listjoht = MainActivity.listjoht;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeJoht);
        svSearch = findViewById(R.id.svSearch);//buscador
        johtAdapter = new JohtPAdapter(listjoht) {
        };
        recyclerView.setAdapter(johtAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador

    }
    private void eventosClick() {
        johtAdapter.setOnItemClickListener(new JohtPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Joht joht =listjoht.get(position);
                Bundle johtEnviar =new Bundle();
                johtEnviar.putSerializable("dato", joht);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(johtEnviar);
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
        johtAdapter.filter(newText);
        return false;
    }

}