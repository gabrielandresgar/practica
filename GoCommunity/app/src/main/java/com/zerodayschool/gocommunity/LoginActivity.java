package com.zerodayschool.gocommunity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    public static FirebaseAuth mAuth;
    public static FirebaseUser currentUser;

    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Instancias para trabajar con FireBase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //------------------------------------
        Button btnLoguin = (Button) findViewById(R.id.btnLogin);
        TextView btnRecuperarContrasenia = (TextView) findViewById(R.id.btnRecuperarPass);
        TextView btregistro =(TextView) findViewById(R.id.registro);
        TextInputEditText correo = (TextInputEditText) findViewById(R.id.correo); //Recoge el texto de las cajas (Correo)
        TextInputEditText contrasenia = (TextInputEditText) findViewById(R.id.pass);//Recoge el texto de las cajas(Contrasenia)

        //Permisos
        multiplePermisos();
        //---------------
        btregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        btnLoguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(correo.getText()) || TextUtils.isEmpty(contrasenia.getText()) || !esCorreo(correo.getText().toString())) { //Compara si alguna de los dos campos estan vacios
                    Toast.makeText(getApplicationContext(), "Ambos campos son obligatorios.", Toast.LENGTH_SHORT).show();
                } else { //Una vez digitado el correo y contraseña se pocede a validar el login
                    String correo_;
                    String contrasenia_;
                    correo_ = correo.getText().toString();
                    contrasenia_ = contrasenia.getText().toString();
                    login(correo_, contrasenia_, v); //A este metodo le envio el correo y contrasenia para luego verificar si las crdenciales son correctas
                }
            }
        });

        btnRecuperarContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecuperarActivity.class);
                intent.putExtra("correo_usuario", correo.getText().toString());//Envia lo que tengamos en el campo correo al nuevo activity recuper contrasenia
                startActivityForResult(intent, 0);
            }
        });

    }

    private void login(String correo, String pass, View v){
        mAuth.signInWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //En caso de que todo este bien procedo a enviar un mensaje
                            Toast.makeText(getApplicationContext(),"Inicio de sesión realizado con éxito.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //Se destruye el activity Login
                            finish();
                        } else {
                            //En caso de que se digite de manera erronea algun campo, se muestra un toast como advertencia.
                            Toast.makeText(getApplicationContext(),"Credenciales Incorrectas. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void multiplePermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE)
                + ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Do something, when permissions not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    LoginActivity.this, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    LoginActivity.this, Manifest.permission.CALL_PHONE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Camera, Call  Phone and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                LoginActivity.this,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.CALL_PHONE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        LoginActivity.this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        } else {
            // Do something, when permissions are already granted
            Toast.makeText(this, "Permissions already granted", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                        + grantResults[2]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                ) {
                    // Permissions are granted
                    Toast.makeText(this, "Permissions granted.", Toast.LENGTH_SHORT).show();
                } else {
                    // Permissions are denied
                    Toast.makeText(this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public boolean esCorreo(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    //guardar secion
    protected void onStart(){
        super.onStart();
        if (mAuth.getCurrentUser() !=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}