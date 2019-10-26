package com.example.quiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditSenha;
    private Button mBtnLogin;
    private TextView mTxtcriarConta;
    private ImageView mLogo;
    private ProgressBar mProgressBarMain;
    private TextView mResetarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mEditEmail = findViewById(R.id.editEmail);
        mEditSenha = findViewById(R.id.editSenha);
        mBtnLogin = findViewById(R.id.btnCadastrar);
        mTxtcriarConta = findViewById(R.id.txtCriarConta);
        mLogo = findViewById(R.id.logo);
        mProgressBarMain = findViewById(R.id.progressBarMain);
        mResetarSenha = findViewById(R.id.txtRecuperarSenha);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = mEditEmail.getText().toString();
               String senha = mEditSenha.getText().toString();
                Log.i("teste",email);
                Log.i("teste",senha);
                mProgressBarMain.setVisibility(View.VISIBLE);

                if(email == null || email.isEmpty() || senha == null || senha.isEmpty()){
                    Toast.makeText(MainActivity.this,"Email e Senha devem ser preenchidos!",Toast.LENGTH_LONG).show();
                    mProgressBarMain.setVisibility(View.INVISIBLE);
                    return;
                }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                }else{
                                    String resposta = task.getException().toString();
                                    mProgressBarMain.setVisibility(View.INVISIBLE);
                                    tratarErros(resposta);
                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("teste", e.getMessage());
                                //Toast.makeText(MainActivity.this, "Erro ao se Conectar, Verifique internet ou seus dados!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        mTxtcriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        mResetarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecuperaSenhaActivity.class);
                startActivity(intent);
            }
        });

    }

    private void tratarErros(String resposta) {
        if (resposta.contains("The password is invalid")){
            Toast.makeText(MainActivity.this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
        }else if(resposta.contains("is no user record corresponding")){
            Toast.makeText(MainActivity.this, "E-mail não cadastrado!", Toast.LENGTH_SHORT).show();
        }else if(resposta.contains("interrupted connection")){
            Toast.makeText(MainActivity.this, "Sem conexão com a internet!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, resposta, Toast.LENGTH_SHORT).show();
        }
    }
}
