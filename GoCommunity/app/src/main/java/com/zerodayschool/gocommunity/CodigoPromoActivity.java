package com.zerodayschool.gocommunity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.CodigoPromoPAdapte;
import com.zerodayschool.gocommunity.clases.CodigoPromo;

import java.util.ArrayList;

public class CodigoPromoActivity extends AppCompatActivity {

    ArrayList<CodigoPromo> listcodigopromo;
    CodigoPromoPAdapte codigoPromoAdapte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_promo);

        listcodigopromo = new ArrayList<>();
        listcodigopromo.clear();
        listcodigopromo = MainActivity.listcodigopromo;//Los anillos que se cargan en el MenuActivity paraque estes disponibles para la vista
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclercodigopromo);
        codigoPromoAdapte = new CodigoPromoPAdapte(listcodigopromo) {
        };
        recyclerView.setAdapter(codigoPromoAdapte);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventosClick();

    }
    private void eventosClick() {
        codigoPromoAdapte.setOnItemClickListener(new CodigoPromoPAdapte.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                CodigoPromo codigoPromo =listcodigopromo.get(position);
                Bundle codigopromoEnviar =new Bundle();
                codigopromoEnviar.putSerializable("dato", codigoPromo);

            }
        });
    }
}