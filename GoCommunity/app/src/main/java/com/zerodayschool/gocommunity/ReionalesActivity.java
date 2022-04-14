package com.zerodayschool.gocommunity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.RegioalesPAdapter;
import com.zerodayschool.gocommunity.clases.Regionales;

import java.util.ArrayList;

public class ReionalesActivity extends AppCompatActivity {

    ArrayList<Regionales> regionaleslist;
    RegioalesPAdapter regioalesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reionales);

        regionaleslist = new ArrayList<>();
        regionaleslist.clear();
        regionaleslist = MainActivity.listregionales;
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerRegionales);
        regioalesAdapter = new RegioalesPAdapter(regionaleslist) {
        };
        recyclerView.setAdapter(regioalesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();


    }

    private void eventosClick() {
        regioalesAdapter.setOnItemClickListener(new RegioalesPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Regionales regionales =regionaleslist.get(position);
                Bundle regionalesEnviar =new Bundle();
                regionalesEnviar.putSerializable("dato", regionales);

            }
        });
    }
}