package com.itca.appmysql;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Consultarusu extends Fragment {
    TextView etidusu, etnombreusu,etapellido,etCorreousu, ettipousu, etestadousu;
    Button btnBuscarusu;
    RequestQueue requestQueue;



    public Consultarusu() {
        // Required empty public constructor
    }




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

        View root = inflater.inflate(R.layout.fragment_consultarusu, container, false);
        etidusu = root.findViewById(R.id.etidusu);
        etnombreusu = root.findViewById(R.id.etnombreusu);
        etapellido = root.findViewById(R.id.etapellido);
        etCorreousu = root.findViewById(R.id.etCorreousu);
        ettipousu = root.findViewById(R.id.tipo);
        etestadousu = root.findViewById(R.id.estadousu);

        btnBuscarusu = root.findViewById(R.id.btnBuscarUsu);

        btnBuscarusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsu("https://melvinlopezsis21.000webhostapp.com/ws/BuscarUsuario.php?id="+etidusu.getText()+"");
            }
        });
        return root;
    }

    private void buscarUsu(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(0);
                        etidusu.setText(jsonObject.getString("id"));
                        etnombreusu.setText(jsonObject.getString("nombre"));
                        etapellido.setText(jsonObject.getString("apellidos"));
                        etCorreousu.setText(jsonObject.getString("correo"));
                        ettipousu.setText(jsonObject.getString("tipo"));
                        etestadousu.setText(jsonObject.getString("estado"));

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
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }
}