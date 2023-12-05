package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.magic.R;

public class contra_recuperar extends AppCompatActivity {

    private EditText editTextCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contra_recuperar);

        Button btnEnviarSoli = findViewById(R.id.btnEnviarSoli);
        editTextCorreo = findViewById(R.id.editTextCorreo);

        btnEnviarSoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoElectronico = editTextCorreo.getText().toString();

                // Verifica que el correo electrónico no esté vacío antes de enviar la solicitud
                if (!TextUtils.isEmpty(correoElectronico)) {
                    // Aquí deberías implementar la lógica para enviar el correo electrónico
                    enviarCorreoRecuperacion(correoElectronico);
                } else {
                    // Manejar el caso en que el campo de correo electrónico esté vacío
                    Toast.makeText(contra_recuperar.this, "Ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para enviar el correo de recuperación
    private void enviarCorreoRecuperacion(String correoElectronico) {
        String asunto = "Recuperación de Cuenta";
        String mensaje = "Haga clic en el siguiente enlace para recuperar su cuenta.";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correoElectronico});
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo electrónico"));
        } catch (android.content.ActivityNotFoundException ex) {
            // Manejar el caso en que no haya aplicaciones de correo electrónico instaladas
            Toast.makeText(contra_recuperar.this, "No hay aplicaciones de correo electrónico instaladas", Toast.LENGTH_SHORT).show();
        }
    }
}
