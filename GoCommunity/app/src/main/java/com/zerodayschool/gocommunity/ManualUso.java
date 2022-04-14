package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.zerodayschool.gocommunity.adapters.OnboardingAdapter;
import com.zerodayschool.gocommunity.adapters.OnboardingItem;
import com.zerodayschool.gocommunity.clases.Slider;
import com.zerodayschool.gocommunity.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class ManualUso extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutonboarningIndicator;
    private MaterialButton buttonOnboardingInaction;
    private Slider slider = new Slider();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_uso);

        layoutonboarningIndicator=findViewById(R.id.layoutonboardingIndicators);
        buttonOnboardingInaction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItens();

        ViewPager2 onboarningViewPager = findViewById(R.id.onboardingViewPager);
        onboarningViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();
        setCurrentOnboardindIndicator(0);

        onboarningViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardindIndicator(position);
            }
        });
        buttonOnboardingInaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboarningViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboarningViewPager.setCurrentItem(onboarningViewPager.getCurrentItem());
                } else {
                    Intent intent = new Intent(ManualUso.this, LoginActivity.class);
                    startActivity(intent);

                    savePrefsData();

                    final AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                    slider.setEstado(true);
                    db.sliderDAO().registrar(slider);

                    finish();
                }
            }
        });
    }
    private void setupOnboardingItens(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemdatopokemon = new OnboardingItem();
        itemdatopokemon.setTitulo("Datos Pokemon");
        itemdatopokemon.setDescripsion("En la aplicación encontraras datos de los diferentes tipos de pokemon con su Pc maximo filtrados por regiones");
        itemdatopokemon.setImage(R.drawable.datopoke);


        OnboardingItem itemamigos = new OnboardingItem();
        itemamigos.setTitulo("Amigos");
        itemamigos.setDescripsion("Podras vincular tu NickName para ver a tus amigos del juego podras buscar amistad con suerte");
        itemamigos.setImage(R.drawable.amigopoke);

        OnboardingItem itempvp = new OnboardingItem();
        itempvp.setTitulo("PvP");
        itempvp.setDescripsion("Tendras una lista de los pokemones que causan mas daño una lista para las tres ligas y una con los mejores pokemones para defender gym");
        itempvp.setImage(R.drawable.pvp);

        OnboardingItem itemnidopoke = new OnboardingItem();
        itemnidopoke.setTitulo("Nidos Pokemon");
        itemnidopoke.setDescripsion("Tendras una lista de coordenadas de los pokemones con aparición mayor dentro del juego");
        itemnidopoke.setImage(R.drawable.mapanido);

        OnboardingItem itemtrucogo = new OnboardingItem();
        itemtrucogo.setTitulo("Trucos GO");
        itemtrucogo.setDescripsion("Tendras una lista de todos los trucos que existen para mejorar tu pokemon, para hacer la evolucion mas rapido");
        itemtrucogo.setImage(R.drawable.trucosgo);

        OnboardingItem itemguia = new OnboardingItem();
        itemguia.setTitulo("Guia Entrenador");
        itemguia.setDescripsion("Tendras una lista de los pasos a seguir para tener una mejor experiencia dentro del juego");
        itemguia.setImage(R.drawable.guia_entrenador);



        onboardingItems.add(itemdatopokemon);
        onboardingItems.add(itemamigos);
        onboardingItems.add(itempvp);
        onboardingItems.add(itemnidopoke);
        onboardingItems.add(itemtrucogo);
        onboardingItems.add(itemguia);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicator(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onbording_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutonboarningIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardindIndicator(int index){
        int childcount = layoutonboarningIndicator.getChildCount();
        for (int i = 0; i < childcount; i++){
            ImageView imageView = (ImageView) layoutonboarningIndicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onbording_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() -1){
            buttonOnboardingInaction.setText("Comenzar");
            buttonOnboardingInaction.setVisibility(View.VISIBLE);
        }else{
            buttonOnboardingInaction.setText("");
            buttonOnboardingInaction.setVisibility(View.INVISIBLE);
        }
    }
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    }
