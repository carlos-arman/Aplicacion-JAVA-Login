package com.itca.appmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroUsu extends AppCompatActivity {
    EditText etidus, etnomusu, etapeusu, etcorreo, etusuaio, etclave, ettpipo, etestado, etpregunta, etrespueta;
    Button btnregistro, btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usu);

        etidus = findViewById(R.id.etidusu);
        etnomusu = findViewById(R.id.etnombreusu);
        etapeusu = findViewById(R.id.etapellido);
        etcorreo = findViewById(R.id.etCorreousu);
        etusuaio = findViewById(R.id.etusuario);
        etclave = findViewById(R.id.etClaveusu);
        ettpipo = findViewById(R.id.tipo);
        etestado = findViewById(R.id.estadousu);
        etpregunta = findViewById(R.id.pregunta);
        etrespueta = findViewById(R.id.respuesta);
        btnregistro = findViewById(R.id.btnRegistro);
        btnVolver = findViewById(R.id.btnVolver);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resgistrar();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });

    }

    public void Resgistrar(){
        final String idusu = etidus.getText().toString().trim();
        final String nombreusu = etnomusu.getText().toString().trim();
        final String apellido = etapeusu.getText().toString().trim();
        final String correo = etcorreo.getText().toString().trim();
        final String usuario = etusuaio.getText().toString().trim();
        final String clave = etclave.getText().toString().trim();
        final String tipo = ettpipo.getText().toString().trim();
        final String estado = etestado.getText().toString().trim();
        final String pregunta = etpregunta.getText().toString().trim();
        final String respuesta = etrespueta.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");

        if(idusu.isEmpty()){
            etidus.setError("complete los campos");
            return;
        }

        else if (nombreusu.isEmpty()){
            etnomusu.setError("complete los campos");
            return;
        }
        else if (apellido.isEmpty()){
            etapeusu.setError("complete los campos");
            return;
        }
        else if (correo.isEmpty()){
            etcorreo.setError("complete los campos");
            return;
        }
        else if (usuario.isEmpty()){
            etusuaio.setError("complete los campos");
            return;
        }
        else if (clave.isEmpty()){
            etclave.setError("complete los campos");
            return;
        }
        else if (tipo.isEmpty()){
            ettpipo.setError("complete los campos");
            return;
        }
        else if (estado.isEmpty()){
            etestado.setError("complete los campos");
            return;
        }
        else if (pregunta.isEmpty()){
            etpregunta.setError("complete los campos");
            return;
        }
        else if (respuesta.isEmpty()){
            etrespueta.setError("complete los campos");
            return;
        }

        else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://melvinlopezsis21.000webhostapp.com/ws/guardarUsu.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            if(response.equalsIgnoreCase("Datos insertados")){

                                Toast.makeText(RegistroUsu.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();

                                Intent intent=new Intent(RegistroUsu.this,Login.class);
                                startActivity(intent);
                            }
                            else {
                                /*Toast.makeText(RegistroUsu.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Toast.makeText(RegistroUsu.this, "No se puede insertar", Toast.LENGTH_SHORT).show();*/
                                Toast.makeText(RegistroUsu.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();

                                Intent intent=new Intent(RegistroUsu.this,Login.class);
                                startActivity(intent);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistroUsu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();
                    params.put("Content-Type", "application/json; charset=utf-8");
                    params.put("Accept", "application/json");
                    params.put("id",String.valueOf(idusu));
                    params.put("nombre",nombreusu);
                    params.put("apellidos",apellido);
                    params.put("correo",correo);
                    params.put("usuario",usuario);
                    params.put("clave",clave);
                    params.put("tipo",tipo);
                    params.put("estado",estado);
                    params.put("pregunta",pregunta);
                    params.put("respuesta",respuesta);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(RegistroUsu.this);
            requestQueue.add(request);



        }



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public  void  login(View v){
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
    public void volver(){
        Intent intent = new Intent(RegistroUsu.this, Login.class);
        startActivity(intent);
    }


}