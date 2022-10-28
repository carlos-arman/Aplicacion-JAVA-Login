package com.itca.appmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText etcorreo;
    EditText etclave;
    EditText etTipo;
    Button btnlogin, registro;
    String correo, clave;
    String tipo, tipos;
    int numero = 2;

    String url = "https://melvinlopezsis21.000webhostapp.com/ws/validar_usuario.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        etcorreo = findViewById(R.id.etCorreo);
        etclave = findViewById(R.id.etclave);
        btnlogin = findViewById(R.id.btnLogin);
        registro = findViewById(R.id.btnRegistro);
        etTipo = findViewById(R.id.etTip);
        
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
                tipo = etTipo.getText().toString();
                if (tipo.length() == 2){

                }
            }
        });

    }


    public void Login() {

        if(etcorreo.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show();
        }
        else if(etclave.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese su clave", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espera...");

            progressDialog.show();

            correo = etcorreo.getText().toString().trim();
            clave = etclave.getText().toString().trim();
            tipo = etTipo.getText().toString().trim();
            tipos = etTipo.getText().toString().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("ingreso correctamente")){

                        etcorreo.setText("");
                        etclave.setText("");
                        etTipo.setText("");

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();


                    }

                    else{
                        Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",correo);
                    params.put("clave",String.valueOf(clave));
                    params.put("tipo", tipo);


                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
            requestQueue.add(request);




        }
    }

    public void Nueva(View view){
        Intent intent = new Intent(Login.this, RegistroUsu.class);
        startActivity(intent);
    }
    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }


}