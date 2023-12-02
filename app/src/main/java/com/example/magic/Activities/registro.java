package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.magic.R;

    public class registro extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro);

            // Obtener el contenedor LinearLayout
            LinearLayout container = findViewById(R.id.container);

            // Crear elementos de la interfaz de usuario dinámicamente
            EditText editTextUsername = new EditText(this);
            editTextUsername.setHint("Nombre de usuario");

            EditText editTextEmail = new EditText(this);
            editTextEmail.setHint("Email");
            editTextEmail.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            EditText editTextPassword = new EditText(this);
            editTextPassword.setHint("Contraseña");
            editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

            Button buttonRegister = new Button(this);
            buttonRegister.setText("Registrarse");
            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtener los valores de los EditText (nombre de usuario y contraseña)
                    String username = editTextUsername.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    // Crear un mensaje con los datos
                    String message = "Usuario: " + username + "\nContraseña: " + password + "\nEmail: " + email;

                    // Mostrar un cuadro de diálogo con el mensaje
                    showMessageDialog("Registro exitoso", message);
                }
            });

            // Agregar elementos al contenedor
            container.addView(editTextUsername);
            container.addView(editTextEmail);
            container.addView(editTextPassword);
            container.addView(buttonRegister);

        }

        // Método para mostrar un cuadro de diálogo
        private void showMessageDialog(String title, String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Puedes agregar lógica adicional si es necesario al hacer clic en OK
                        }
                    });
            builder.create().show();
        }
    }