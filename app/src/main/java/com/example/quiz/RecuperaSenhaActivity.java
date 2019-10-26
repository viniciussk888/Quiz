package com.example.quiz;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperaSenhaActivity extends AppCompatActivity {

    private EditText mEditRSE;
    private Button mBtnRSE;
    private TextView mMsgRSE;
    private ProgressBar mCarregando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mEditRSE = findViewById(R.id.editRSE);
        mBtnRSE = findViewById(R.id.btnRSE);
        mMsgRSE = findViewById(R.id.txtMsgRSE);
        mCarregando = findViewById(R.id.pReset);
        mCarregando.setVisibility(View.INVISIBLE);

        mBtnRSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCarregando.setVisibility(View.VISIBLE);
                redefinirSenha(mEditRSE.getText().toString());
            }
        });



    }

    private void redefinirSenha(String email) {
        if(email.isEmpty() || email == null){
            mMsgRSE.setText("Insira seu E-mail de acesso!");
            mCarregando.setVisibility(View.INVISIBLE);
            return;
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if( task.isSuccessful() ){
                    mMsgRSE.setText("Foi enviado um link de Redefinição para seu E-mail!");
                    mCarregando.setVisibility(View.INVISIBLE);
                }
                else{
                    mCarregando.setVisibility(View.INVISIBLE);
                    mMsgRSE.setText("E-mail invalido!");
                }
            }
        });
    }
}
