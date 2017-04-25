package com.example.lenovo.crud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_nombre,et_apellidoP, et_apellidoM;
    Button bt_guardar,bt_mostrar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidoP = (EditText) findViewById(R.id.et_apellidoP);
        et_apellidoM = (EditText) findViewById(R.id.et_apellidoM);

        bt_guardar = (Button) findViewById(R.id.bt_Guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(et_nombre.getText().toString(),et_apellidoP.getText().toString(),et_apellidoM.getText().toString());
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });

    }

    private void guardar(String Nombre, String ApellidoP, String ApellidoM){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("Nombre",Nombre);
            c.put("ApellidoP", ApellidoP);
            c.put("ApellidoM", ApellidoM);
            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
