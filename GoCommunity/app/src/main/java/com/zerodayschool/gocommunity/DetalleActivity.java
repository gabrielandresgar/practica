package com.zerodayschool.gocommunity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.adapters.AlolaAdapter;
import com.zerodayschool.gocommunity.adapters.GalarAdapter;
import com.zerodayschool.gocommunity.adapters.GuiagoAdapter;
import com.zerodayschool.gocommunity.adapters.HoennAdapter;
import com.zerodayschool.gocommunity.adapters.JohtAdapter;
import com.zerodayschool.gocommunity.adapters.KalosAdapter;
import com.zerodayschool.gocommunity.adapters.KantoAdapter;
import com.zerodayschool.gocommunity.adapters.SinnohAdapter;
import com.zerodayschool.gocommunity.adapters.TeseliaAdapter;
import com.zerodayschool.gocommunity.adapters.TrucosgoAdapter;
import com.zerodayschool.gocommunity.clases.Alola;
import com.zerodayschool.gocommunity.clases.Galar;
import com.zerodayschool.gocommunity.clases.Guiago;
import com.zerodayschool.gocommunity.clases.Hoenn;
import com.zerodayschool.gocommunity.clases.Joht;
import com.zerodayschool.gocommunity.clases.Kalos;
import com.zerodayschool.gocommunity.clases.Kanto;
import com.zerodayschool.gocommunity.clases.Sinnoh;
import com.zerodayschool.gocommunity.clases.Teselia;
import com.zerodayschool.gocommunity.clases.Trucosgo;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {
//datopokemon
    ArrayList<Kanto> listkanto;
    ArrayList<Joht> listjoht;
    ArrayList<Hoenn> listhoenn;
    ArrayList<Sinnoh> listsinnoh;
    ArrayList<Teselia> listteselia;
    ArrayList<Kalos> listkalos;
    ArrayList<Alola> listalola;
    ArrayList<Galar> listgalar;
    KantoAdapter kantoAdapter;
    JohtAdapter johtAdapter;
    HoennAdapter hoennAdapter;
    SinnohAdapter sinnohAdapter;
    TeseliaAdapter teseliaAdapter;
    KalosAdapter kalosAdapter;
    AlolaAdapter alolaAdapter;
    GalarAdapter galarAdapter;
    RecyclerView rvDetalles;

    //activity de funcion
    ArrayList<Guiago> listguiago;
    ArrayList<Trucosgo> listtrucosgo;

    GuiagoAdapter guiagoAdapter;
    TrucosgoAdapter trucosgoAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        rvDetalles = (RecyclerView) findViewById(R.id.recyclerdetallado);
        cargarVista(savedInstanceState);
        rvDetalles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void cargarVista(Bundle bundle) {
       //datos pokemon
        listkanto = new ArrayList<>();
       listjoht = new ArrayList<>();
       listhoenn= new ArrayList<>();
       listsinnoh = new ArrayList<>();
       listteselia = new ArrayList<>();
       listkalos= new ArrayList<>();
       listalola = new ArrayList<>();
       listgalar= new ArrayList<>();
       Kanto kanto;
       Joht joht;
       Hoenn hoenn;
       Sinnoh sinnoh;
       Teselia teselia;
       Kalos kalos;
       Alola alola;
       Galar galar;

       //activity de funcion
        listguiago = new ArrayList<>();
        listtrucosgo = new ArrayList<>();

        Guiago guiago;
        Trucosgo trucosgo;

        //Bundle aMostrar = getIntent();
        String clase = getIntent().getSerializableExtra("dato").getClass().getSimpleName();

        if(clase.equalsIgnoreCase("Kanto")){
            kanto = (Kanto) getIntent().getSerializableExtra("dato");
            listkanto.add(kanto);
            kantoAdapter = new KantoAdapter(listkanto);
            rvDetalles.setAdapter(kantoAdapter);
        }
            else if(clase.equalsIgnoreCase("Joht")){
            joht = (Joht) getIntent().getSerializableExtra("dato");
            listjoht.add(joht);
            johtAdapter = new JohtAdapter(listjoht);
            rvDetalles.setAdapter(johtAdapter);
        }
        else if(clase.equalsIgnoreCase("Hoenn")){
            hoenn = (Hoenn) getIntent().getSerializableExtra("dato");
            listhoenn.add(hoenn);
            hoennAdapter = new HoennAdapter(listhoenn);
            rvDetalles.setAdapter(hoennAdapter);
        }
        else if(clase.equalsIgnoreCase("Sinnoh")){
            sinnoh = (Sinnoh) getIntent().getSerializableExtra("dato");
            listsinnoh.add(sinnoh);
            sinnohAdapter = new SinnohAdapter(listsinnoh);
            rvDetalles.setAdapter(sinnohAdapter);
        }
        else if(clase.equalsIgnoreCase("Teselia")){
            teselia = (Teselia) getIntent().getSerializableExtra("dato");
            listteselia.add(teselia);
            teseliaAdapter = new TeseliaAdapter(listteselia);
            rvDetalles.setAdapter(teseliaAdapter);
        }
        else if(clase.equalsIgnoreCase("Kalos")){
            kalos = (Kalos) getIntent().getSerializableExtra("dato");
            listkalos.add(kalos);
            kalosAdapter = new KalosAdapter(listkalos);
            rvDetalles.setAdapter(kalosAdapter);
        }
        else if(clase.equalsIgnoreCase("Alola")){
            alola = (Alola) getIntent().getSerializableExtra("dato");
            listalola.add(alola);
            alolaAdapter = new AlolaAdapter(listalola);
            rvDetalles.setAdapter(alolaAdapter);
        }
        else if(clase.equalsIgnoreCase("Galar")){
            galar = (Galar) getIntent().getSerializableExtra("dato");
            listgalar.add(galar);
            galarAdapter = new GalarAdapter(listgalar);
            rvDetalles.setAdapter(galarAdapter);
        }
        else if(clase.equalsIgnoreCase("Guiago")){
            guiago = (Guiago) getIntent().getSerializableExtra("dato");
            listguiago.add(guiago);
            guiagoAdapter = new GuiagoAdapter(listguiago);
            rvDetalles.setAdapter(guiagoAdapter);
        }
        else if(clase.equalsIgnoreCase("Trucosgo")){
            trucosgo = (Trucosgo) getIntent().getSerializableExtra("dato");
            listtrucosgo.add(trucosgo);
            trucosgoAdapter = new TrucosgoAdapter(listtrucosgo);
            rvDetalles.setAdapter(trucosgoAdapter);
        }
        else{

        }

    }

}
