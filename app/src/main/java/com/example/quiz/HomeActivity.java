package com.example.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {
    private Button mBtnComoFun;
    private Button mBtnPlay;
    private Button mbtnConfig;
    private Button mBtnPerso;
    private TextView mMeuNome;
    private TextView mTxtMeuCurso;
    private TextView mMeuScore;
    private ImageView mMinhaFoto;
    private DatabaseReference mDatabase;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        verificarAutenticacao();
        inserirUser();

        mBtnComoFun = findViewById(R.id.HowTo);
        mBtnPlay = findViewById(R.id.bntPlay);
        mbtnConfig = findViewById(R.id.btnConfig);
        mBtnPerso = findViewById(R.id.btnPersonalizados);



        mBtnPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"EM BREVE...",Toast.LENGTH_SHORT).show();

            }
        });
        mBtnComoFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ComoFuncionaActivity.class);
                startActivity(intent);
            }
        });
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,EscolherPeriodo.class);
                intent.putExtra("score",mMeuScore.getText());
                startActivity(intent);
            }
        });
        mbtnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,EditarUsuarioActivity.class);
                intent.putExtra("nome",mMeuNome.getText());
                intent.putExtra("linkFoto",url);
                startActivity(intent);
            }
        });
    }

    private void inserirUser() {
        mMeuScore = findViewById(R.id.MeuScore);
        mMeuNome = findViewById(R.id.MeuNome);
        mTxtMeuCurso = findViewById(R.id.MyCurso);
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
                              //Log.d("resultado", FirebaseAuth.getInstance().getUid() + " => " + document.get("uuid"));
                              try {
                                  if(uuid.equals(document.get("uuid"))) {
                                      mMeuNome.setText(document.get("username").toString());
                                      mTxtMeuCurso.setText(document.get("curso").toString());
                                      mMeuScore.setText(document.get("score").toString());
                                      url = document.get("profileUrl").toString();
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
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);

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
                Intent intent = new Intent(HomeActivity.this, RankActivity.class);
                startActivity(intent);
                break;
            case R.id.quizz:
                Intent intente = new Intent(HomeActivity.this,HistoricoActivity.class);
                startActivity(intente);
                break;
            case  R.id.sair:
                new AlertDialog.Builder(this)
                        .setTitle("Desconectar Usuario")
                        .setMessage("Tem certeza que deseja sair?")
                        .setPositiveButton("Sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseAuth.getInstance().signOut();
                                        verificarAutenticacao();
                                    }
                                })
                        .setNegativeButton("Não", null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
