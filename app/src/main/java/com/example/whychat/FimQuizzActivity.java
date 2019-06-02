package com.example.whychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FimQuizzActivity extends AppCompatActivity {
    private Button mJogarNovamente;
    private Button mVoltarMenu;
    private TextView mQtdAcertos;
    private TextView mPontosGanhos;
    private TextView mPeriodo;
    String pontos;
    String acertos;
    String periodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_quizz);
        mJogarNovamente = findViewById(R.id.buttonJogaNovaFim);
        mVoltarMenu = findViewById(R.id.buttonVoltarFim);
        mQtdAcertos = findViewById(R.id.Acertos);
        mPontosGanhos = findViewById(R.id.PontosGanhos);
        mPeriodo = findViewById(R.id.txtPeriodoFim);
        Intent it = getIntent();
        pontos = it.getStringExtra("pontos");
        acertos = it.getStringExtra("acertos");
        periodo = it.getStringExtra("periodo");

        mPontosGanhos.setText("Pontos Ganhos: "+pontos);
        mQtdAcertos.setText("Acertos: "+acertos);
        mPeriodo.setText(periodo+" Periodo");

        //listerner
        mJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FimQuizzActivity.this, EscolherPeriodo.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FimQuizzActivity.this, MensagensActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
