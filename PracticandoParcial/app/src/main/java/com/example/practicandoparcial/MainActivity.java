package com.example.practicandoparcial;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
            }
        });
    }

    private void handleLogin(String username, String password) {
        SharedPreferences prefs = getSharedPreferences(ConfiguracionesFragment.PREF_NUEVO_FUTURO, MODE_PRIVATE);
        String storedUsername = prefs.getString(ConfiguracionesFragment.KEY_USERNAME, null);
        String storedPassword = prefs.getString(ConfiguracionesFragment.KEY_PASSWORD, null);

        if (storedUsername == null || storedPassword == null) {
            // Primera ejecución, guarda las credenciales
            prefs.edit().putString(ConfiguracionesFragment.KEY_USERNAME, "administrador").putString(ConfiguracionesFragment.KEY_PASSWORD, "nuevofuturo1").apply();
            storedUsername = "administrador";
            storedPassword = "nuevofuturo1";
        }

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            // Inicio de sesión exitoso, inicia StudentListActivity

            Intent intent = new Intent(this, HolaXD.class);
            startActivity(intent);

        } else {
            // Credenciales incorrectas
            Toast.makeText(this, "Datos de ingreso incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}

