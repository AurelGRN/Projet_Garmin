package com.example.pagedebase;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;
import java.util.Locale;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
public class MainActivityTEST extends AppCompatActivity {



    private TextView errorConnectAccountTextView, createAccountTextView;
    private EditText userEditText, passwordEditText;
    private Button connectButton;

    private String username, password;
    private DatabaseManager DatabaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        errorConnectAccountTextView = findViewById(R.id.textViewErrorConnect);
        userEditText = findViewById(R.id.editTextUser);
        passwordEditText = findViewById(R.id.editTextPassword);
        connectButton = findViewById(R.id.btnConnect);
        createAccountTextView = findViewById(R.id.btnCreate);
        DatabaseManager = new DatabaseManager(getApplicationContext());

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userEditText.getText().toString();
                password = passwordEditText.getText().toString();

                connectUser();

                // Lancer la requête
            }
        });

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountActivity = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(createAccountActivity);
            }
        });


    }



    public void connectUser() {
        String url = "https://projet-android-garmin.iut-orsay.fr/API/actions/Reward/connectUser.php";//A CHANGER AVEC LE /Reward pour etre dans notre dossier
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("error")) {
                                String debugInfo = response.getString("error");
                                System.out.println("DebugInfo -> ' " + debugInfo + " '");
                            }
                            else
                            {
                                System.out.println("Erreur sur le PHP");
                            }
                            System.out.println("DebugInfo -> ' " + response.getString("pass1") + " '");
                            System.out.println("DebugInfo -> ' " + response.getString("pass2") + " '");
                            System.out.println("Status connection -> ' " + response.getString("succes") + " '");


                            Toast.makeText(getApplicationContext(), "Opération réussite", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Une erreur s'est produite. 321", Toast.LENGTH_LONG).show();
                }
            }
        });

        DatabaseManager.requestQueue.add(jsonObjectRequest);
    }
}
