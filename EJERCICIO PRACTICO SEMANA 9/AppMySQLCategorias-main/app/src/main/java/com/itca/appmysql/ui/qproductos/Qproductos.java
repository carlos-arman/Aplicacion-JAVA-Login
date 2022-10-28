package com.itca.appmysql.ui.qproductos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itca.appmysql.MySingleton;
import com.itca.appmysql.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class Qproductos extends Fragment {


    private EditText etidproducto, fecha,etnombreproducto, etdesproducto, etstock, etprecioproducto, etunidad, estadoproducto, etcategoria;
    private Button btncpro, btncon, btnelimi, btnactu;
    private Spinner sp_fk_categoria;
    private String datoSelect;
    RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_qproductos, container, false);

        View root = inflater.inflate(R.layout.fragment_qproductos, container, false);
        etidproducto = root.findViewById(R.id.etidpro);
        etnombreproducto = root.findViewById(R.id.etnombrepro);
        etdesproducto = root.findViewById(R.id.etdespro);
        etstock = root.findViewById(R.id.ets);
        etprecioproducto = root.findViewById(R.id.etpreciopro);
        etunidad = root.findViewById(R.id.etunidadpro);
        fecha = root.findViewById(R.id.fecapro);
        estadoproducto = root.findViewById(R.id.estadopro);
        etcategoria = root.findViewById(R.id.sp_fk_categoria);
        btncon = root.findViewById(R.id.btnconp);
        btnelimi = root.findViewById(R.id.btnelimi);
        btnactu = root.findViewById(R.id.btnactualizar);

        btnelimi.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etidproducto.getText().toString();
                String dato = "";
                if(id.length() == 0){
                    etidproducto.setError("Campo obligatorio");
                    dato = "Debe seleccionar una ópcion";
                    Toast.makeText(getContext(), ""+dato, Toast.LENGTH_SHORT).show();
                }else{
                    EliminarPro(getContext(), Integer.parseInt(id));
                }
            }
        }));


        btncon.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String idcategoria = etid.getText().toString();
                buscarPro("https://melvinlopezsis21.000webhostapp.com/ws/buscarArticulos.php?id="+etidproducto.getText()+"");

            }
        }));
        btnactu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idproducto = etidproducto.getText().toString();
                String nombrepro = etnombreproducto.getText().toString();
                String despro = etdesproducto.getText().toString();
                String stock = etstock.getText().toString();
                String preciopro = etprecioproducto.getText().toString();
                String unidad = etunidad.toString();
                String estado = estadoproducto.getText().toString();
                String categoria = etcategoria.getText().toString();
                String dato = "";

                if(idproducto.length() == 0){
                    etidproducto.setError("Campo obligatorio");
                }else if(nombrepro.length()==0){
                    etnombreproducto.setError("Campo obligatorio");
                }else if(despro.length()==0){
                    etdesproducto.setError("Campo obligatorio");
                }else if(stock.length()==0){
                    etstock.setError("Campo obligatorio");
                }else if(preciopro.length()==0){
                    etprecioproducto.setError("Campo obligatorio");
                }else if(unidad.length()==0){
                    etunidad.setError("Campo obligatorio");
                }else if(estado.length()==0){
                    estadoproducto.setError("Campo obligatorio");
                }else if(categoria.length()==0){
                    etcategoria.setError("Campo obligatorio");
                    dato = "Debe seleccionar una ópcion";
                    Toast.makeText(getContext(), ""+dato, Toast.LENGTH_SHORT).show();
                }
                else{
                    ActualizarPro(getContext(), Integer.parseInt(idproducto), nombrepro, despro, Integer.parseInt(stock), Integer.parseInt(preciopro), unidad, Integer.parseInt(estado), Integer.parseInt(categoria));
                }
            }
        });

        return root;
    }



    private void buscarPro(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(0);
                        etnombreproducto.setText(jsonObject.getString("nom_producto"));
                        etdesproducto.setText(jsonObject.getString("des_producto"));
                        etstock.setText(jsonObject.getString("stock"));
                        etprecioproducto.setText(jsonObject.getString("precio"));
                        etunidad.setText(jsonObject.getString("unidad_medida"));
                        estadoproducto.setText(jsonObject.getString("estado_producto"));
                        fecha.setText(jsonObject.getString("fecha_entrada"));
                    } catch (JSONException exception) {
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        requestQueue =Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }



        private void buscarProducto(String url) {
            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            etnombreproducto.setText(jsonObject.getString("nom_producto"));
                            etdesproducto.setText(jsonObject.getString("des_producto"));
                            etstock.setText(Integer.parseInt(jsonObject.getString("stock")));
                            etprecioproducto.setText(Integer.parseInt(jsonObject.getString("precio")));
                            etunidad.setText(jsonObject.getString("unidad_medida"));
                            estadoproducto.setText(jsonObject.getString("estado_producto"));
                            etcategoria.setText(jsonObject.getString("categoria"));
                           // fecha.setText(jsonObject.getString("fecha_entrada"));
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    //En este método se colocan o se setean los valores a recibir por el fichero *.php
                    Map<String, String> map = new HashMap<>();
                    map.put("Content-Type", "application/json; charset=utf-8");
                    map.put("Accept", "application/json");

                    // map.put("id", String.valueOf(id_categoria));

                    return map;
                }

            };
            // MySingleton.getInstance(context).addToRequestQueue(request);
            requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(jsonArrayRequest);
        }

    private void EliminarPro(final Context context, final int id_producto) {

        String url = "https://melvinlopezsis21.000webhostapp.com/ws/eliminarProducto.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                JSONObject requestJSON = null;
                try {

                    JSONObject respuestaJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");






                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(context, ""+mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se puede guardar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id_producto));

                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public void ActualizarPro(final Context context, final int id_producto, final String nom_producto, final String des_producto, final double stock, final double precio, final String unidad_medida, final int estado_producto, final int categoria) {
        String url = "https://melvinlopezsis21.000webhostapp.com/ws/ActualizarProducto.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                JSONObject requestJSON = null;
                try {
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");
                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(context, ""+mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se puede guardar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", etidproducto.getText().toString());
                map.put("nombrepro", etnombreproducto.getText().toString());
                map.put("despro", etdesproducto.getText().toString());
                map.put("stock" , etstock.getText().toString());
                map.put("preciopro" , etprecioproducto.getText().toString());
                map.put("unidad" , etunidad.getText().toString());
                map.put("estadoproducto" , estadoproducto.getText().toString());
                map.put("categoria" , etcategoria.getText().toString());

                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    }

