package com.example.lenovo.crud;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    EditText et_nombre,et_apellidoP,et_apellidoM;
    Button bt_modificar,bt_borrar;
    int id;
    String nombre;
    String apellidop;
    String apellidom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b= getIntent().getExtras();
        if (b!=null){
            id = b.getInt("id");
            nombre = b.getString("Nombre");
            apellidop = b.getString("ApellidoP");
            apellidom = b.getString("ApellidoM");
        }

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidoP = (EditText) findViewById(R.id.et_apellidoP);
        et_apellidoM = (EditText) findViewById(R.id.et_apellidoM);

        et_nombre.setText(nombre);
        et_apellidoP.setText(apellidop);
        et_apellidoM.setText(apellidom);

        bt_modificar  = (Button) findViewById(R.id.bt_salvar);

        bt_borrar = (Button) findViewById(R.id.bt_borrar);

        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });

        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, et_nombre.getText().toString(),et_apellidoP.getText().toString(),et_apellidoM.getText().toString());
                onBackPressed();
            }
        });


    }

    private void Modificar(int Id, String Nombre, String ApellidoP, String ApellidoM){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="update Persona set Nombre='"+Nombre+"',apellidoP='"+ApellidoP+"', apellidoM='"+ApellidoM+"' where Id="+Id;
        db.execSQL(sql);
        db.close();
    }

    private void Eliminar(int Id){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="delete from personas where Id="+Id;
        db.execSQL(sql);
        db.close();
    }
}
