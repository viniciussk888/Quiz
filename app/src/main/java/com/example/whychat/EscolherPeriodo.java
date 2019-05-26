package com.example.whychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EscolherPeriodo extends AppCompatActivity {
    private Button mBtnPeriodo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_periodo);

        mBtnPeriodo1 = findViewById(R.id.btnPeriodo1);

        mBtnPeriodo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolherPeriodo.this, QuizzActivity.class);
                startActivity(intent);
            }
        });
    }
}
