package com.zerodayschool.gocommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AmigosGoActivity extends AppCompatActivity {

    private EditText bnickname;
    private Button breqistro;
    private  String nickname = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_go);

        //conexion a la base de dato
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        bnickname = (EditText) findViewById(R.id.nicknameET);
        breqistro = (Button)  findViewById(R.id.registroBT);


        //valida los campos
        breqistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nickname = bnickname.getText().toString();

                if(!nickname.isEmpty()){
                    registerUser();
                }else{
                    Toast.makeText(AmigosGoActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }

            //envia los datos a firebase

            private void registerUser(){
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

             Map<String, Object> map = new HashMap<>();

              map.put("nickname", nickname);
              String id= mAuth.getCurrentUser().getUid();
              mDatabase.child("Usuario").child(id).child("amigos").child("1").setValue(map);

                }

        });

    }
}