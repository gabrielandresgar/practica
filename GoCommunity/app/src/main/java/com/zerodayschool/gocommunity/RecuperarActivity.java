package com.zerodayschool.gocommunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;



public class RecuperarActivity extends AppCompatActivity {

    MaterialButton recuperarcontraseña;
    TextInputEditText correoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        recuperarcontraseña=findViewById(R.id.btnrecuperar);
        correoEditText=findViewById(R.id.correoET);

        recuperarcontraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {
        String correo = correoEditText.getText().toString().trim();
        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            correoEditText.setError("Correo Invalido");
        }else{
            sendEmail(correo);
        }
    }

    private void sendEmail(String correo) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAdress = correo;
        auth.sendPasswordResetEmail(emailAdress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RecuperarActivity.this, "Correo Enviado",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RecuperarActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(RecuperarActivity.this, "Correo Invalido",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}