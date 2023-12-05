package com.example.magic.Activities;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://192.168.1.3:3306/magic/registro.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String correo, String pass, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params= new HashMap<>();
        params.put("nombre",nombre);
        params.put("correo",correo);
        params.put("pass",pass);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
