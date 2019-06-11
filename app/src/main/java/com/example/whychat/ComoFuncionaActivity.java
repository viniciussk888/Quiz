package com.example.whychat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComoFuncionaActivity extends AppCompatActivity {

    private Button mJogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_funciona);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mJogar = findViewById(R.id.JogarComoJoga);

        mJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComoFuncionaActivity.this,EscolherPeriodo.class);
                startActivity(intent);
            }
        });
    }
}
