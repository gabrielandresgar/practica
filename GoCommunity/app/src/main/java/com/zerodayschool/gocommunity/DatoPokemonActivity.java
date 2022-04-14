package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DatoPokemonActivity extends AppCompatActivity {


    private ImageView pokekanto;
    private ImageView pokejohto;
    private ImageView pokehoenn;
    private ImageView pokesinnoh;
    private ImageView poketeselia;
    private ImageView pokekalos;
    private ImageView pokealola;
    private ImageView pokegalar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_pokemon);

        pokekanto = (ImageView) findViewById(R.id.pokekanto);
        pokejohto = (ImageView) findViewById(R.id.pokejohto);
        pokehoenn = (ImageView) findViewById(R.id.pokehoenn);
        pokesinnoh = (ImageView) findViewById(R.id.pokesinnoh);
        poketeselia = (ImageView) findViewById(R.id.poketeselia);
        pokekalos = (ImageView) findViewById(R.id.pokekalos);
        pokealola = (ImageView) findViewById(R.id.pokealola);
        pokegalar = (ImageView) findViewById(R.id.pokegalar);


        //animacion
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView textView2 = findViewById(R.id.textView2);


        textView2.setAnimation(animacion2);

        //embio a dependencias
        pokekanto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeKanto.class);
                startActivity(intent);
            }
        });

        pokejohto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeJoht.class);
                startActivity(intent);
            }
        });

        pokehoenn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeHoenn.class);
                startActivity(intent);
            }
        });

        pokesinnoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeSinnoh.class);
                startActivity(intent);
            }
        });

        poketeselia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeTeselia.class);
                startActivity(intent);
            }
        });

        pokekalos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeKalos.class);
                startActivity(intent);
            }
        });

        pokealola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeAlola.class);
                startActivity(intent);
            }
        });

        pokegalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatoPokemonActivity.this, PokeGalar.class);
                startActivity(intent);
            }
        });
    }

}