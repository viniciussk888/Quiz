package com.example.whychat;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FimQuizzActivity extends AppCompatActivity {
    static BancoDados db;
    private Button mJogarNovamente;
    private Button mVoltarMenu;
    private TextView mQtdAcertos;
    private TextView mPontosGanhos;
    private TextView mPeriodo;
    String pontos;
    String acertos;
    String periodo;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_quizz);
        //banco
        db = Room.databaseBuilder(getApplicationContext(),
                BancoDados.class, "historico").allowMainThreadQueries().build();
        //buscando itens
        mJogarNovamente = findViewById(R.id.buttonJogaNovaFim);
        mVoltarMenu = findViewById(R.id.buttonVoltarFim);
        mQtdAcertos = findViewById(R.id.Acertos);
        mPontosGanhos = findViewById(R.id.PontosGanhos);
        mPeriodo = findViewById(R.id.txtPeriodoFim);


        Intent it = getIntent();
        pontos = it.getStringExtra("pontos");
        acertos = it.getStringExtra("acertos");
        periodo = it.getStringExtra("periodo");
        score = it.getStringExtra("score");

        mPontosGanhos.setText("Pontos Ganhos: "+pontos);
        mQtdAcertos.setText("Acertos: "+acertos);
        mPeriodo.setText(periodo+" Periodo");
        
        //salvando ho Sqllite
        salvar();
        //salvando score no FireBase
        salvarScoreFirebase();

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

    private void salvarScoreFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        int NovoScore = Integer.parseInt(score) + Integer.parseInt(pontos);
        //enviando
        Map<String, Object> update = new HashMap<>();
        update.put("score", NovoScore);

        db.collection("users").document(user.getUid()).set(update, SetOptions.merge());
    }

    private void salvar() {
        Historico c = new Historico(pontos,periodo,acertos);
        db.userDao().insertAll(c);
    }
}
