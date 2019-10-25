package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class QuizzActivity extends AppCompatActivity {
    private TextView times;
    private TextView mEnunciado;
    private TextView mA;
    private TextView mB;
    private TextView mC;
    private TextView mD;
    private TextView mMsgResposta;
    private TextView mTxtNquestao;
    private Button mButonA;
    private Button mButonB;
    private Button mButonC;
    private Button mButonD;
    private Button mFinalizaQuizz;
    private TextView mtxtPontos;
    private PerguntasRepositorio repositorio = new PerguntasRepositorio();
    private String respostaCerta;
    public int pontuacao;
    public int acertos;
    String score;
    String curso;
    private int indice_questao=0;
    private int questaoAtual=1,questaoTotal;
    CounterClass timer = new CounterClass(30000, 1000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mtxtPontos = findViewById(R.id.txtPontos);
        mEnunciado = findViewById(R.id.txtEnunciado);
        mMsgResposta = findViewById(R.id.msgAcerto);
        mTxtNquestao = findViewById(R.id.txtNquestao);
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
        score = it.getStringExtra("score");
        curso = it.getStringExtra("curso");
        mtxtPontos.setText(" Pontos: "+pontuacao);
        //logica
        questaoTotal = repositorio.getListaPerguntas().size();
        mTxtNquestao.setText(questaoAtual+"/"+questaoTotal);
        Perguntas pergunta = repositorio.getListaPerguntas().get(indice_questao);
        respostaCerta = pergunta.getCerta();
        mEnunciado.setText(pergunta.getEnunciado());
        mA.setText("A. "+pergunta.getA());
        mB.setText("B. "+pergunta.getB());
        mC.setText("C. "+pergunta.getC());
        mD.setText("D. "+pergunta.getD());

        //LISTERNER
        mButonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrar();
                if (respostaCerta.equals("a")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    acertos++;
                   mMsgResposta.setText("ACERTOU!");
                    novaPergunta();
                }else{
                    pontuacao = pontuacao - 7;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU!");
                    novaPergunta();
                }
            }
        });
        mButonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrar();
                if (respostaCerta.equals("b")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU!");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 7;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU!");
                    novaPergunta();

                }

            }
        });
        mButonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrar();
                if (respostaCerta.equals("c")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU!");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 7;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU!");
                    novaPergunta();

                }
            }
        });
        mButonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrar();
                if (respostaCerta.equals("d")){
                    pontuacao = pontuacao +10;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    acertos++;
                    mMsgResposta.setText("ACERTOU!");
                    novaPergunta();

                }else{
                    pontuacao = pontuacao - 7;
                    mtxtPontos.setText(" Pontos: "+pontuacao);
                    mMsgResposta.setText("ERROU!");
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
                intent.putExtra("score",score);
                startActivity(intent);
            }
        });
        //Timer
        times = findViewById(R.id.times);
        timer.start();
    }

    private void vibrar() {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 50;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds);
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
            intent.putExtra("score",score);
            startActivity(intent);
        }
        Perguntas pergunta = repositorio.getListaPerguntas().get(indice_questao);
        respostaCerta = pergunta.getCerta();
        mEnunciado.setText(pergunta.getEnunciado());
        mA.setText("A. "+pergunta.getA());
        mB.setText("B. "+curso);
        mC.setText("C. "+pergunta.getC());
        mD.setText("D. "+pergunta.getD());
        questaoAtual++;
        mTxtNquestao.setText(questaoAtual+"/"+questaoTotal);
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
            mButonA.setVisibility(View.INVISIBLE);
            mButonB.setVisibility(View.INVISIBLE);
            mButonC.setVisibility(View.INVISIBLE);
            mButonD.setVisibility(View.INVISIBLE);

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