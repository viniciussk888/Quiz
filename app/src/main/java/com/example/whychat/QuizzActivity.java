package com.example.whychat;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuizzActivity extends AppCompatActivity {
    private TextView times;
    private TextView mEnunciado;
    private TextView mA;
    private TextView mB;
    private TextView mC;
    private TextView mD;
    private TextView mMsgResposta;
    private Button mButonA;
    private Button mButonB;
    private Button mButonC;
    private Button mButonD;
    private Button mFinalizaQuizz;
    private TextView mtxtPontos;
    private PerguntasRepositorio repositorio = new PerguntasRepositorio();
    private String respostaCerta;
    public String periodo;
    public int pontuacao;
    public int acertos;
    private int indice_questao=0;
    CounterClass timer = new CounterClass(30000, 1000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        mtxtPontos = findViewById(R.id.txtPontos);
        mEnunciado = findViewById(R.id.txtEnunciado);
        mMsgResposta = findViewById(R.id.msgAcerto);
        mA = findViewById(R.id.txtA);
        mB = findViewById(R.id.txtB);
        mC = findViewById(R.id.txtC);
        mD = findViewById(R.id.txtD);
        mFinalizaQuizz = findViewById(R.id.btnFinalizarQuizz);
        mButonA = findViewById(R.id.buttonA);
        mButonB = findViewById(R.id.buttonB);
        mButonC = findViewById(R.id.buttonC);
        mButonD = findViewById(R.id.buttonD);
        //Capturando Periodo da intent
        Intent it = getIntent();
        periodo = it.getStringExtra("periodo");
        mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
        //logica
        Perguntas pergunta = repositorio.getListaPerguntas().get(indice_questao);
        respostaCerta = pergunta.getCerta();
        mEnunciado.setText(pergunta.getEnunciado());
        mA.setText(pergunta.getB());
        mB.setText(pergunta.getB());
        mC.setText(pergunta.getC());
        mD.setText(pergunta.getD());

        //LISTERNER
        mButonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostaCerta.equals("a")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU! +10");
                    novaPergunta();
                }else{
                    pontuacao = pontuacao - 12;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU! -12");
                    novaPergunta();
                }
            }
        });
        mButonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostaCerta.equals("b")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU! +10");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 12;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU! -12");
                    novaPergunta();

                }

            }
        });
        mButonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostaCerta.equals("c")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU! +10");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 12;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU! -12");
                    novaPergunta();

                }
            }
        });
        mButonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostaCerta.equals("d")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU! +10");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 12;
                    mtxtPontos.setText(periodo+"P Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU! -12");
                    novaPergunta();

                }
            }
        });
        mFinalizaQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizzActivity.this, FimQuizzActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                String pon = Integer.toString(pontuacao);
                String acer = Integer.toString(acertos);
                intent.putExtra("pontos",pon);
                intent.putExtra("acertos",acer);
                intent.putExtra("periodo",periodo);
                startActivity(intent);
            }
        });
        //Timer
        times = findViewById(R.id.times);
        timer.start();
    }

    private void novaPergunta() {
        indice_questao++;
        //Verificando se ainda tem alguma questao!!
        if (indice_questao>=repositorio.getListaPerguntas().size()){
            //chamo tela de fim
            indice_questao=0;
            Intent intent = new Intent(QuizzActivity.this, FimQuizzActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            String pon = Integer.toString(pontuacao);
            String acer = Integer.toString(acertos);
            intent.putExtra("pontos",pon);
            intent.putExtra("acertos",acer);
            intent.putExtra("periodo",periodo);
            startActivity(intent);
        }
        Perguntas pergunta = repositorio.getListaPerguntas().get(indice_questao);
        respostaCerta = pergunta.getCerta();
        mEnunciado.setText(pergunta.getEnunciado());
        mA.setText(pergunta.getB());
        mB.setText(pergunta.getB());
        mC.setText(pergunta.getC());
        mD.setText(pergunta.getD());
        timer.start();
    }

    public class CounterClass extends CountDownTimer {


        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }


        @Override
        public void onFinish() {
            times.setText("Tempo Esgotado!");
            // Intent intent = new Intent(QuizzActivity.this,EscolherPeriodo.class);
            //startActivity(intent);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }


    }
}