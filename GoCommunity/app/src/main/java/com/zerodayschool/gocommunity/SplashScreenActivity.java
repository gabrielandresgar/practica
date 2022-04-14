package com.zerodayschool.gocommunity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zerodayschool.gocommunity.clases.Slider;
import com.zerodayschool.gocommunity.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {
    private List<Slider> listSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView txtzeroday = findViewById(R.id.zeroday);
        ImageView logo = findViewById(R.id.imalogo);

        txtzeroday.setAnimation(animacion2);
        logo.setAnimation(animacion1);

        final AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        listSlider = new ArrayList<>();

        listSlider = db.sliderDAO().consultar();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (listSlider.isEmpty() || listSlider == null) {
                    intent = new Intent(getApplicationContext(), ManualUso.class);
                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                }

            Pair[] pairs =new Pair[1];
            pairs[0] =new Pair<View,String>(logo,"logoImageTrans");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }else {
                startActivity(intent);
                finish();
            }
            }
        }, 4000);
    }
}