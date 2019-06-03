package com.example.whychat;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.ObjectExclusionFilterable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class MensagensActivity extends AppCompatActivity {
    private Button mBtnComoFun;
    private Button mBtnPlay;
    private TextView mMeuNome;
    private TextView mMeuScore;
    private ImageView mMinhaFoto;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);

        verificarAutenticacao();
        inserirUser();

        mBtnComoFun = findViewById(R.id.HowTo);
        mBtnPlay = findViewById(R.id.bntPlay);



        mBtnComoFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MensagensActivity.this,ComoFuncionaActivity.class);
                startActivity(intent);
            }
        });
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MensagensActivity.this,EscolherPeriodo.class);
                startActivity(intent);
            }
        });
    }

    private void inserirUser() {
        mMeuScore = findViewById(R.id.MeuScore);
        mMeuNome = findViewById(R.id.MeuNome);
        mMinhaFoto = findViewById(R.id.MinhaFoto);//aqui sera inserido a foto e nome e score
        final String uuid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              Log.d("resultado", FirebaseAuth.getInstance().getUid() + " => " + document.get("uuid"));
                              try {
                                  if(uuid.equals(document.get("uuid"))) {
                                      mMeuNome.setText(document.get("username").toString());
                                      mMeuScore.setText("Score: "+document.get("score").toString());
                                      String url = document.get("profileUrl").toString();
                                      Picasso.get()
                                              .load(url)
                                              .into(mMinhaFoto);

                                  }

                              }catch (Exception e){
                                  Log.d("Vini",e.getMessage());
                              }
                            }
                        } else {
                            Log.w("teste", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void verificarAutenticacao() {
        if(FirebaseAuth.getInstance().getUid() == null){
            Intent intent = new Intent(MensagensActivity.this, MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rank:
                Intent intent = new Intent(MensagensActivity.this,ContatoActivity.class);
                startActivity(intent);
                break;
            case R.id.quizz:
                Intent intente = new Intent(MensagensActivity.this,HistoricoActicity.class);
                startActivity(intente);
                break;
            case  R.id.sair:
                FirebaseAuth.getInstance().signOut();
                verificarAutenticacao();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
