package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private CheckBox terminos_condiciones;

    private EditText bnombre;
    private EditText bapellido;
    private EditText bnickname;
    private ImageView bfoto;
    private EditText bpais;
    private EditText bcelular;
    private EditText bcorreo;
    private EditText bcontraseña;
    private Button breqistro;
    // private CountryCodePicker ccp;


    //variable de reguistro
    private  String nombre = "";
    private  String apellido = "";
    private  String nickname = "";
    private String foto = "";
    private  String pais = "";
    private  String celular = "";
    private  String correo = "";
    private  String contraseña = "";


    CheckBox terminos;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //conexion a la base de dato
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //bariables

        terminos = (CheckBox) findViewById(R.id.terminos);
        bnombre = (EditText) findViewById(R.id.nombreET);
        bapellido = (EditText) findViewById(R.id.apellidoET);
        bnickname = (EditText) findViewById(R.id.nicknameET);
        bfoto = (ImageView) findViewById(R.id.fotoPerfilImg);
        bpais = (EditText) findViewById(R.id.paisET);
        bcorreo = (EditText) findViewById(R.id.correoET);
        bcontraseña = (EditText) findViewById(R.id.contraseñaET);
        bcelular = (EditText) findViewById(R.id.celularET);
        breqistro = (Button)  findViewById(R.id.registroBT);
        terminos_condiciones = (CheckBox) findViewById(R.id.terminos);
        breqistro.setEnabled(false);

        //valida los campos
        breqistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = bnombre.getText().toString();
                apellido = bapellido.getText().toString();
                nickname = bnickname.getText().toString();
                foto = bfoto.getImageMatrix().toString();
                pais = bpais.getText().toString();
                celular = bcelular.getText().toString();
                correo = bcorreo.getText().toString();
                contraseña = bcontraseña.getText().toString();



                if(!nombre.isEmpty() && !apellido.isEmpty() && !nickname.isEmpty() && !pais.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty() && !celular.isEmpty()){
                    if (contraseña.length() >=10){
                        registerUser();
                    }else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 10 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistroActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }

            //envia los datos a firebase

            private void registerUser(){
                mAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Map<String, Object> map = new HashMap<>();
                            map.put("nombre", nombre);
                            map.put("apellido", apellido);
                            map.put("nickname", nickname);
                            map.put ("foto", foto);
                            map.put("pais", pais);
                            map.put("celular", celular);
                            map.put("correo", correo);
                            map.put("contraseña", contraseña);


                            String id= mAuth.getCurrentUser().getUid();

                            mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if (task2.isSuccessful()){
                                        startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(RegistroActivity.this, "No se pudo crear el registro", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegistroActivity.this, "No se pudo registar este usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //manda a terminos y condiciones
        terminos_condiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroActivity.this, TerminoCondiciones.class));
            }
        });

        //activacion del boton con el chebox
        terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (terminos.isChecked()==false){
                    breqistro.setEnabled(false);
                }else{
                    breqistro.setEnabled(true);
                }
            }
        });

    }
}