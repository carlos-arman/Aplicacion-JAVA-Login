package com.itca.appmysql.ui.categorias;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itca.appmysql.Login;
import com.itca.appmysql.MainActivity;
import com.itca.appmysql.MySingleton;
import com.itca.appmysql.R;
import com.itca.appmysql.RegistroUsu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Categorias extends Fragment {
    private EditText etid, etnombre, etestado;
    private Spinner sp1;
    private Button btncat;
    private TextView tvrespuesta;

    String datoSelect = "";

    public Categorias() {
    }


    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_categorias, container, false);

        etid = root.findViewById(R.id.etid);
        etnombre = root.findViewById(R.id.etnombre);
        sp1 = root.findViewById(R.id.sp1);
        btncat = root.findViewById(R.id.btncat);

        tvrespuesta = root.findViewById(R.id.tvrespuesta);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp1.getSelectedItemPosition()>0) {
                    datoSelect = sp1.getSelectedItem().toString();
                }else{
                    datoSelect = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btncat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Clic en botón Guardar", Toast.LENGTH_SHORT).show();
                //recibirJson(getContext());

                guardarcategoria();


            }
        });


        return root;
    }






    public void recibirJson(final Context context, int idcategoria){

        String url = "https://carlosminerosis11a.000webhostapp.com/ws/buscarArticulosCodigo.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{


                    JSONObject respuestaJSON = new JSONObject(response.toString());
                    String dato1 = respuestaJSON.getString("id_categoria");
                    String dato2 = respuestaJSON.getString("nom_categoria");
                    String dato3 = respuestaJSON.getString("estado_categoria");
                    //Toast.makeText(context, "Datos recibidos: \n" +"Id: " + dato1 + ".\n" + "nombre:"+dato2, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "Respuesta: " + response.toString(), Toast.LENGTH_SHORT).show();

                    etid.setText("Id categoria: " + dato1);
                    etnombre.setText(dato2);
                    etestado.setText(dato3.toString());

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ERROR. Verifque su conexión.", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


    private void guardarcategoria() {

        String url = "https://melvinlopezsis21.000webhostapp.com/ws/guardarCat.php";
        String idcategoria = etid.getText().toString().trim();
        String nombrecat = etnombre.getText().toString().trim();
        String estado = datoSelect;

        String dato = "";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                JSONObject requestJSON = null;

                    if(response.equalsIgnoreCase("Registro de categoria fue Exitoso")){
                        //Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), Categorias.class));



                        Toast.makeText(getContext(), "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                        }else if(estado.equals("2")){
                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }
                }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "No se puede guardar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
            Map<String, String> map = new HashMap<>();
            map.put("Content-Type", "application/json; charset=utf-8");
            map.put("Accept", "application/json");
            map.put("id", String.valueOf(idcategoria));
            map.put("nombre", nombrecat);
            map.put("estado", String.valueOf(estado));
            return map;
        }
    };
        MySingleton.getInstance(this.getContext()).addToRequestQueue(request);

    }







}