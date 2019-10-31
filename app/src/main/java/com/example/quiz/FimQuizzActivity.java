package com.example.quiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FimQuizzActivity extends AppCompatActivity {
    private Button mVoltarMenu;
    private TextView mQtdAcertos;
    private TextView mPontosGanhos;
    private TextView mTxtFim;
    private ImageView image;
    String pontos;
    String acertos;
    String score;
    String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_quizz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //banco
        //salvar historico
        //buscando itens
        mVoltarMenu = findViewById(R.id.buttonVoltarFim);
        mQtdAcertos = findViewById(R.id.Acertos);
        mPontosGanhos = findViewById(R.id.PontosGanhos);
        mTxtFim = findViewById(R.id.txtFim);


        Intent it = getIntent();
        pontos = it.getStringExtra("pontos");
        acertos = it.getStringExtra("acertos");
        score = it.getStringExtra("score");
        curso = it.getStringExtra("curso");

        mPontosGanhos.setText("Pontos Ganhos: "+pontos);
        mQtdAcertos.setText("Acertos: "+acertos);

        //salvando score no FireBase
        salvarScoreFirebase();
        salvarHistoricoFirebase();

        //listerner
        mVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FimQuizzActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void salvarHistoricoFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> hist = new HashMap<>();
        hist.put("curso",curso);
        hist.put("acertos", acertos);
        hist.put("pontos", pontos);
        hist.put("data",new Timestamp(new Date()));

        db.collection("userHistorico").document(user.getUid()).collection("historico").document()
                .set(hist)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // Log.w(TAG, "Error writing document", e);
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
}
