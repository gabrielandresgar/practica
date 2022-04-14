package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.GuiagoPAdapte;
import com.zerodayschool.gocommunity.clases.Guiago;

import java.util.ArrayList;

public class GuiaEntrenadorActivity extends AppCompatActivity {

    ArrayList<Guiago> listguiago;
    GuiagoPAdapte guiagoAdapte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_entrenador);
        listguiago = new ArrayList<>();
        listguiago.clear();
        listguiago = MainActivity.listguiago;
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerGuiaGo);
        guiagoAdapte = new GuiagoPAdapte(listguiago) {
        };
        recyclerView.setAdapter(guiagoAdapte);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();
    }

    private void eventosClick() {
        guiagoAdapte.setOnItemClickListener(new GuiagoPAdapte.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Guiago guiago =listguiago.get(position);
                Bundle guiagoEnviar =new Bundle();
                guiagoEnviar.putSerializable("dato", guiago);
                Intent intent = new Intent(getApplicationContext(),DetalleActivity.class);
                intent.putExtras(guiagoEnviar);
                startActivity(intent);
            }
        });
    }
}