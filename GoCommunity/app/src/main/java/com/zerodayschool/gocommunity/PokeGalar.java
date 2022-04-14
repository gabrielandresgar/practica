package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.GalarPAdapter;
import com.zerodayschool.gocommunity.clases.Galar;

import java.util.ArrayList;

public class PokeGalar extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<Galar> listgalar;
    GalarPAdapter galarAdapter;
    private SearchView svSearch; //buscador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_galar);

        listgalar = new ArrayList<>();
        listgalar.clear();
        listgalar = MainActivity.listgalar;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerpokeGalar);
        svSearch = findViewById(R.id.svSearch);//buscador
        galarAdapter = new GalarPAdapter(listgalar) {
        };
        recyclerView.setAdapter(galarAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
        initListener();//buscador
    }

    private void eventosClick() {
        galarAdapter.setOnItemClickListener(new GalarPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Galar galar =listgalar.get(position);
                Bundle galarEnviar =new Bundle();
                galarEnviar.putSerializable("dato", galar);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(galarEnviar);
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
        galarAdapter.filter(newText);
        return false;
    }


}