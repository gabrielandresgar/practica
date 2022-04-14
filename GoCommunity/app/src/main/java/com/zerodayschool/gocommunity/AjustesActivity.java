package com.zerodayschool.gocommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AjustesActivity extends AppCompatActivity {

    ImageView cardvalor,cardsabiduria,cardinstinto;
    public static final String colorTema="colorTema";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        cardvalor = (ImageView) findViewById(R.id.valor);
        cardsabiduria = (ImageView) findViewById(R.id.instinto);
        cardinstinto = (ImageView) findViewById(R.id.instinto);
        setTheme(R.style.Theme_AppCompat_Equipovalor);

        cardvalor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}