package com.example.whychat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditEmail = findViewById(R.id.editEmail);
        mEditSenha = findViewById(R.id.editSenha);
        mBtnLogin = findViewById(R.id.btnCadastrar);
        mTxtcriarConta = findViewById(R.id.txtCriarConta);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = mEditEmail.getText().toString();
               String senha = mEditSenha.getText().toString();
                Log.i("teste",email);
                Log.i("teste",senha);

                if(email == null || email.isEmpty() || senha == null || senha.isEmpty()){
                    Toast.makeText(MainActivity.this,"Email e Senha devem ser preenchidos!",Toast.LENGTH_LONG).show();
                    return;
                }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.i("teste", task.getResult().getUser().getUid());
                                Intent intent = new Intent(MainActivity.this, MensagensActivity.class);

                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("teste", e.getMessage());
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

    }
}
