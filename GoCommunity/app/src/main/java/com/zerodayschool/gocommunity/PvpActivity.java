package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PvpActivity extends AppCompatActivity {


    private ImageView gym;
    private ImageView gobatelite;
    private ImageView estrategia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvp);

        gym = (ImageView) findViewById(R.id.imgdefendergym);
        gobatelite = (ImageView) findViewById(R.id.imggobatelite);
        estrategia = (ImageView) findViewById(R.id.imgestrategiatipo);


        //embio a dependencias
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PvpActivity.this, DefenderGymActivity.class);
                startActivity(intent);
            }
        });

        gobatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PvpActivity.this, GobateliteActivity.class);
                startActivity(intent);
            }
        });

        estrategia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PvpActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}