package com.example.matheus.gestaog2app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Login.this,
                        consultas.class);

                startActivity(intent);

                finish();
            }
        });

    }
}
