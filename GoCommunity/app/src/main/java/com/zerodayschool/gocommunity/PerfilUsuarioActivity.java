package com.zerodayschool.gocommunity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PerfilUsuarioActivity extends AppCompatActivity {

    int requestUsarCamara =102, requestSeleccionarImagen = 104;
    private ImageView imagenPerfil, imagenQr;
    private Button bcerrarsesion, bguardar;
    private TextInputEditText nickname, celular;


    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    StorageReference mStorage;
    String urlFotoPerfil, idUsuario;
    Uri data_;
    ProgressDialog mProgressDialog;
    String currentPhotoPath;
    String datosUsuario = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);


        //ConstraintLayout perfil = (ConstraintLayout) findViewById(R.id.perfil); //Obtengo el layoout de fondo que aparece en perfil
        SharedPreferences sp = getSharedPreferences("SP_V_ZD", MODE_PRIVATE); //Llamo al sharedPreferences para obtener el valor del tema asignado
        int tema = sp.getInt("tema", (int) R.drawable.fondo_azul);//Obtengo el numero del tema, en caso de que no se haya seleccionado ninguno entonces coloco el fondo 8n por defecto
       // perfil.setBackground(getResources().getDrawable(tema));//Agrego el color de fondo
        mProgressDialog = new ProgressDialog(this);//Es para obtener el dialogo de "Subiendo foto de perfil..."
        inicializar();//Inicializo todos los elementos de la vista.
        //Al tocar la foto se puede acceder a la camara y tomar una foto.
        imagenPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PerfilUsuarioActivity.this).setMessage("Seleccione su aplicación")
                        .setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (ActivityCompat.checkSelfPermission(PerfilUsuarioActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                        tomarFoto();
                                    } else {
                                        Toast.makeText(PerfilUsuarioActivity.this, "Se necesitan los permisos para acceder a la cámara.", Toast.LENGTH_SHORT).show();
                                        //ActivityCompat.requestPermissions(PerfilUsuarioActivity.this, new String[]{Manifest.permission.CAMERA},requestPermisoCam);
                                    }
                                } else {
                                    tomarFoto();
                                }
                            }
                        })
                        .setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (ActivityCompat.checkSelfPermission(PerfilUsuarioActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                        seleccionarImagen();
                                    } else {
                                        Toast.makeText(PerfilUsuarioActivity.this, "Se necesitan los permisos para acceder a la galeria.", Toast.LENGTH_SHORT).show();
                                        //ActivityCompat.requestPermissions(PerfilUsuarioActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestPermisoGal);
                                    }
                                } else {
                                    seleccionarImagen();
                                }
                            }
                        }).show();
            }
        });
        //-------------------------------
        //Generar Qr
        bguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data_ == null || imagenPerfil == null || nickname.getText().toString().isEmpty() || celular.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Todos los campos deben llenarse correctamente y haber seleccionado alguna imagen.", Toast.LENGTH_SHORT).show();
                } else {
                    guardarDatosUsuario(data_); //Envio este data_ porque es de donde se va a extraer la foto que se tomo o la que se escogio de la galeria.
                    generarQR();
                }
            }
        });


        bcerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PerfilUsuarioActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode!= RESULT_CANCELED) {
            if (requestCode == requestUsarCamara) {
                if (resultCode == Activity.RESULT_OK) {//Si se tomo la foto
                    //Bitmap bitmap = (Bitmap) data.getExtras().get("data");//Se crea un bitmap porque se utiliza la camara para la foto.
                    //imagenPerfil.setImageBitmap(bitmap);
                    imagenPerfil.setImageURI(Uri.parse(currentPhotoPath));//
                    //data_ = (Uri) Uri.parse("android.resource://"+getPackageName()+currentPhotoPath);
                    //Glide.with(getApplicationContext()).load(Uri.parse(currentPhotoPath)).into(imagenPerfil);
                } else {//ResultCancel, si no se tomo la foto.

                }
            } else if (requestCode == requestSeleccionarImagen) {
                if (resultCode == Activity.RESULT_OK) {//Si se selecciono la foto
                    Uri path = (Uri) data.getData();//Se crea un Uri porque se utiliza la galaeria para la foto.
                    Glide.with(getApplicationContext()).load(path).into(imagenPerfil);

                    //imagenPerfil.setImageURI(path);
                    data_ = path;
                } else {//ResultCancel, si no se selecciono la foto.

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        //guardarImagenPerfil(data.getData());
        //if(data!=null) {
        //    data_ = (data.getData());
        //}
    }

    public void inicializar(){
        mAuth =FirebaseAuth.getInstance(); //Instancia del usuario
        mDatabase = FirebaseDatabase.getInstance().getReference(); //Instancia de la base de datos
        mStorage = FirebaseStorage.getInstance().getReference(); //Instancia del storage de Firebase
        //Instancia de los elementos de la vista
        imagenPerfil = (ImageView) findViewById(R.id.fotoPerfilImg);
        imagenQr = (ImageView)findViewById(R.id.codigoqr);
        bguardar = (Button) findViewById(R.id.registroBT);
        bcerrarsesion =(Button)findViewById(R.id.cerrarsesionbt);
        nickname = (TextInputEditText) findViewById(R.id.nicknameEET);
        celular = (TextInputEditText) findViewById(R.id.celularET);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        cargarValoresUsuario(); //Carga los valores de nombre de usuario,telefono, cant de ventas y la foto.
        if(!nickname.getText().toString().isEmpty() && !celular.getText().toString().isEmpty()) { //Si los campos no estan vacios entonces se genera el QR
            generarQR();
        }
    }

    public void generarQR(){
        String datosQr = "- Aplicativo GoCommunity"+"\n- Vendedor (ra)"+
                "\n- Nombre y Apellidos: "+nickname.getText().toString()+
                "\n- Teléfono/Celular: "+celular.getText().toString();
        MultiFormatWriter write = new MultiFormatWriter(); //Se crea un objeto de la clsee MultiFormatWriter para escribir en el los datos que se veran en el codigo QR
        try {
            BitMatrix matrix = write.encode(datosQr, BarcodeFormat.QR_CODE, 350,350); //Se envian los datos a la matriz y sus dimensiones.
            BarcodeEncoder encoder = new BarcodeEncoder(); //Se crea este tipo de objeto
            Bitmap bitmap = encoder.createBitmap(matrix); //Para poder mostrar en el imageview lo que se creo dentro de el.
            imagenQr.setImageBitmap(bitmap); //Finalmente se muestra el QR.
        } catch (WriterException e) {//Se captura cualquier excepcion.
            e.printStackTrace();
        }
    }

    public void seleccionarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //Se crea el intent que muestra todas las galerias y app relacionadas por el SO
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"), requestSeleccionarImagen);
    }

    public void tomarFoto(){
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamara.resolveActivity(getPackageManager())!=null){
            File archivoFoto = null;
            try {
                archivoFoto = crearArchivoFoto(); //Se crea el archivo de foto dentro del dispositivo para luego enviarlo a la firebase storage
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(archivoFoto!=null){
                Uri photoUri = FileProvider.getUriForFile( //Esto crea el Uri del archivo
                        this,
                        "com.zerodayschool.ventasZD",
                        archivoFoto
                );
                intentCamara.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intentCamara,requestUsarCamara);
                data_ = photoUri; //Igualo ese data_ que es un uri al uri del del file para poderlo subir a la firebase storage
            }
        }
    }

    public File crearArchivoFoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss", Locale.getDefault()).format(new Date());
        String imgNombre = "IMG_"+timeStamp+"_";
        File direccionAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(
                imgNombre,
                ".jpg",
                direccionAlmacenamiento
        );
        currentPhotoPath = imagen.getAbsolutePath();
        return imagen;
    }

    public void guardarDatosUsuario(Uri data){
        Uri uri = data;
        idUsuario = mAuth.getCurrentUser().getUid();
        mProgressDialog.setTitle("Subiendo...");
        mProgressDialog.setMessage("Subiendo foto");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        StorageReference filePath = mStorage.child("fotoPerfilUsuarios").child(idUsuario); //Se hace referencia a la foto direccion de la foto que guardara el usuario con el id correspondiente
        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { //Agrega la imagen y la envia a la Base FB pero creamos un listener para saber si se agregao correctamente y tambien obtener el url de la foto
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl(); //Es la manera como se obtiene el url de la foto que se subio de perfil
                firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        urlFotoPerfil = uri.toString();//Aqui finalmente se obtiene el URL de la foto.
                        String nombUsu = nickname.getText().toString();
                        String telf = celular.getText().toString();
                        String url = urlFotoPerfil;
                        Map<String,Object> campos_TBL_Usuaios = new HashMap<>(); //Creo un map similar a la tabla de firebase
                        campos_TBL_Usuaios.put("nombreUsuario", nombUsu);
                        campos_TBL_Usuaios.put("telefono_celular", telf);
                        campos_TBL_Usuaios.put("imagenPerfil", url);
                        mDatabase.child("users").child(idUsuario).setValue(campos_TBL_Usuaios).addOnCompleteListener(new OnCompleteListener<Void>() { //Aqui lo envio o lo almaceno
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Los datos del perfil se agregaron satisfactoriamente.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "La imagen de perfil se agrego de manera correcta.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cargarValoresUsuario (){
        String a = MainActivity.listDatosUsuario.get("nomb");
        String b = MainActivity.listDatosUsuario.get("cel");
        String d = MainActivity.listDatosUsuario.get("foto");
        datosUsuario = "- Nombre Usuario: "+a +"\n"+"- Número de Contacto: " + b +"\n";
        nickname.setText(a);
        celular.setText(b);

        if(d.isEmpty() || d == null){
            imagenPerfil.setImageResource(R.drawable.no_image);
        }
        else {
            try {
                Glide.with(getApplicationContext()).load(Uri.parse(d)).apply(RequestOptions.circleCropTransform()).into(imagenPerfil);
                //Glide.with(getApplicationContext()).load(Uri.parse(d)).into(imagenPerfil);
            }
            catch (Exception e){
                Glide.with(getApplicationContext()).load(R.drawable.no_image).into(imagenPerfil);
            }
        }
    }


}