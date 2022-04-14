package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zerodayschool.gocommunity.clases.Alola;
import com.zerodayschool.gocommunity.clases.CodigoPromo;
import com.zerodayschool.gocommunity.clases.Defendergym;
import com.zerodayschool.gocommunity.clases.Galar;
import com.zerodayschool.gocommunity.clases.Guiago;
import com.zerodayschool.gocommunity.clases.Hoenn;
import com.zerodayschool.gocommunity.clases.Joht;
import com.zerodayschool.gocommunity.clases.Kalos;
import com.zerodayschool.gocommunity.clases.Kanto;
import com.zerodayschool.gocommunity.clases.NidosPokemon;
import com.zerodayschool.gocommunity.clases.Regionales;
import com.zerodayschool.gocommunity.clases.Sinnoh;
import com.zerodayschool.gocommunity.clases.Teselia;
import com.zerodayschool.gocommunity.clases.Trucosgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    //activity de funcion
    public static ArrayList<NidosPokemon> listNidospokemon;
    public static ArrayList<Guiago> listguiago;
    public static ArrayList<Trucosgo> listtrucosgo;
    public static ArrayList<CodigoPromo> listcodigopromo;
    public static ArrayList<Defendergym> listdefendergym;
    public static ArrayList<Regionales> listregionales;
    public static Map<String, String> listDatosUsuario;
    //pokedex datos pokemon
    public static ArrayList<Kanto> listkanto;
    public static ArrayList<Joht> listjoht;
    public static ArrayList<Hoenn> listhoenn;
    public static ArrayList<Sinnoh> listsinnoh;
    public static ArrayList<Teselia> listteselia;
    public static ArrayList<Kalos> listkalos;
    public static ArrayList<Alola> listalola;
    public static ArrayList<Galar> listgalar;

    private TextView perfil;
    private  TextView mnombre;
    private ImageView fotoPerfil;
    private ImageView datospokemon;
    private ImageView guiaentrenador;
    private ImageView pvp;
    private ImageView amigosgo;
    private ImageView nidopokemon;
    private ImageView trucosgo;
    private ImageView acercadenosotros;
    private ImageView ajustes;
    private ImageView codigopromo;
    private ImageView reionales;
    private FirebaseUser usuario;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //llamado a base de dato
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mnombre = (TextView) findViewById(R.id.nombrePerfilTxt);
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        //menu
        perfil = (TextView) findViewById(R.id.Perfil);
        fotoPerfil = (ImageView) findViewById(R.id.fotoPerfilImg);
        datospokemon = (ImageView) findViewById(R.id.datospokemon);
        guiaentrenador = (ImageView) findViewById(R.id.guiaentrenador);
        pvp = (ImageView) findViewById(R.id.pvp);
        amigosgo = (ImageView) findViewById(R.id.amigosgo);
        nidopokemon = (ImageView) findViewById(R.id.nidospokemon);
        trucosgo = (ImageView) findViewById(R.id.trucosgo);
        acercadenosotros = (ImageView) findViewById(R.id.acercadenosotros);
        ajustes = (ImageView) findViewById(R.id.ajustes);
        codigopromo = (ImageView) findViewById(R.id.codigopromo);
        reionales = (ImageView) findViewById(R.id.reionales);

        //llamados de carga
         cargarnidospokemon();
         cargarGuiago();
         cargarTrucosgo();
         //cargarDefendergym();
         cargarKanto();
         cargarJoht();
         cargarHoenn();
         cargarSinnoh();
         cargarTeselia();
         cargarKalos();
         cargarAlola();
         cargarGalar();
         cargarPromo();
         cargarRegionales();
        //presentacion
       // getUserInfor();
        cargarDatosUsuario();
        inicializarUsuario();




        //embio a dependencias
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);

            }
        });

        datospokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatoPokemonActivity.class);
                startActivity(intent);
            }
        });

        guiaentrenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GuiaEntrenadorActivity.class);
                startActivity(intent);
            }
        });

        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PvpActivity.class);
                startActivity(intent);
            }
        });

        amigosgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmigosGoActivity.class);
                startActivity(intent);
            }
        });

        nidopokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NidosPokemonActivity.class);
                startActivity(intent);
            }
        });

        trucosgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrucosPokemonActivity.class);
                startActivity(intent);
            }
        });

        acercadenosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AcercadeNosotrosActivity.class);
                startActivity(intent);
            }
        });

        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        codigopromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CodigoPromoActivity.class);
                startActivity(intent);
            }
        });

        reionales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReionalesActivity.class);
                startActivity(intent);
            }
        });

    }

    private void cargarRegionales() {
        listregionales = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("Regionales").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String id = dataSnapshot.getKey();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String coordenada = dataSnapshot.child("coordenada").getValue().toString();
                        String pais = dataSnapshot.child("pais").getValue().toString();
                        listregionales.add(new Regionales(id,foto,nombre,coordenada,pais));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void cargarPromo() {
        listcodigopromo = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("Codigopromo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String id = dataSnapshot.getKey();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String codigo = dataSnapshot.child("codigo").getValue().toString();
                        listcodigopromo.add(new CodigoPromo(id,foto,nombre,codigo));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void cargarDatosUsuario(){
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        String id = mUser.getUid();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        listDatosUsuario = new HashMap<>();

        mData.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nickname = "";
                String celular ="";
                String foto = "";
                if(snapshot.exists()){
                    if(snapshot.child("nickname").exists()) {
                        nickname = snapshot.child("nickname").getValue().toString();
                    }
                    else{
                        nickname = "NO SE HA REGISTRADO NINGUN NOMBRE PARA ESTE USUARIO";
                    }
                    listDatosUsuario.put("nomb", nickname);

                    if(snapshot.child("celular").exists()) {
                        celular = snapshot.child("celular").getValue().toString();
                    }
                    else{
                        celular = "NO SE HA REGISTRADO NINGUN NUMERO PARA ESTE USAURIO";
                    }
                    listDatosUsuario.put("cel",celular);

                    if(snapshot.child("imagenPerfil").exists()){
                        foto = snapshot.child("imagenPerfil").getValue().toString();
                    }
                    else{
                        foto = "R.drawable.no_image";
                    }
                    listDatosUsuario.put("foto", foto);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void inicializarUsuario(){
        String id = usuario.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombreUsuario = snapshot.child("nickname").getValue().toString();
                    mnombre.setText(nombreUsuario);
                    if(nombreUsuario.isEmpty()){
                        mnombre.setText("Aún no se ha agregado un nombre de perfil para este usuario.");
                    }
                    String urlFoto = snapshot.child("imagenPerfil").getValue().toString();
                    Uri uri = Uri.parse(urlFoto);
                    if(uri==null){
                        fotoPerfil.setImageResource(R.drawable.no_image);
                    }
                    else {
                        Glide.with(getApplicationContext()).load(uri).apply(RequestOptions.circleCropTransform()).into(fotoPerfil);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void cargarDefendergym() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listdefendergym = new ArrayList<>();

        mData.child("defendergym").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String top = dataSnapshot.child("top").getValue().toString();
                        listdefendergym.add(new Defendergym(id,foto,nombre,top));

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarGalar() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listgalar = new ArrayList<>();

        mData.child("Galar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listgalar.add(new Galar(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarAlola() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listalola = new ArrayList<>();

        mData.child("Alola").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listalola.add(new Alola(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarKalos() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listkalos = new ArrayList<>();

        mData.child("Kalos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listkalos.add(new Kalos(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarTeselia() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listteselia = new ArrayList<>();

        mData.child("Teselia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listteselia.add(new Teselia(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarSinnoh() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listsinnoh = new ArrayList<>();

        mData.child("Sinnoh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listsinnoh.add(new Sinnoh(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarHoenn() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listhoenn = new ArrayList<>();

        mData.child("Hoenn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listhoenn.add(new Hoenn(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarJoht() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listjoht = new ArrayList<>();

        mData.child("Johto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listjoht.add(new Joht(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarTrucosgo() {
        listtrucosgo = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("trucosgo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String id = dataSnapshot.getKey();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String descripcion = dataSnapshot.child("descripcion").getValue().toString();
                        listtrucosgo.add(new Trucosgo(id,foto,nombre,descripcion));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarGuiago() {
         listguiago = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("guiago").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String id = dataSnapshot.getKey();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String uso = dataSnapshot.child("uso").getValue().toString();
                        listguiago.add(new Guiago(id,foto,nombre,uso));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void cargarKanto() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        listkanto = new ArrayList<>();

        mData.child("kanto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.getKey();
                        String dato = dataSnapshot.child("datos").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String pcmax = dataSnapshot.child("pcmax").getValue().toString();
                        String tipo = dataSnapshot.child("tipo").getValue().toString();
                        String variocolor  = dataSnapshot.child("variocolor").getValue().toString();
                        listkanto.add(new Kanto(id,dato,foto,nombre,pcmax,tipo,variocolor));


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void cargarnidospokemon() {
        listNidospokemon = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("nidopoke").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String idNidosPokemon = dataSnapshot.getKey();
                        String nombre = dataSnapshot.child("pais").getValue().toString();
                        String coordenadas = dataSnapshot.child("coordenadas").getValue().toString();
                        String foto = dataSnapshot.child("foto").getValue().toString();
                        String pais = dataSnapshot.child("nombre").getValue().toString();
                        String variocolor = dataSnapshot.child("variocolor").getValue().toString();
                        listNidospokemon.add(new NidosPokemon(idNidosPokemon,coordenadas,foto,pais,nombre,variocolor));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //informacion de usuario

    private void getUserInfor(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nickname").getValue().toString();
                    mnombre.setText(nombre);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}