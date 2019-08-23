package com.example.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static com.example.quiz.FimQuizzActivity.db;

public class HistoricoActicity extends AppCompatActivity {
    private Button btnLimpar;
    static AdapterHistorico adapter;
    List<Historico> historico;
    ListView listaDeHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_acticity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnLimpar = findViewById(R.id.bntLimpar);

        listaDeHistorico = (ListView) findViewById(R.id.ListViewHistorico);
        listaDeHistorico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearAllTables();
                Toast.makeText(getApplicationContext(),"Historico Limpo!", Toast.LENGTH_LONG).show();
            }
        });
        LiveData<List<Historico>> liveData = db.userDao().getAll();
        liveData.observe(this, new Observer<List<Historico>>() {
            @Override
            public void onChanged(@Nullable List<Historico> historic) {
                atualizarCursos(historic);
            }
        });


    }
    public void atualizarCursos(List<Historico> histo)
    {
        adapter = new AdapterHistorico(histo, this);
        listaDeHistorico.setAdapter(adapter);
    }
}
