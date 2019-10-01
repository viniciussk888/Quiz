package com.example.quiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegistroActivity extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditnome;
    private EditText mEditSenha;
    private Button mBtnCadastrar;
    private Button mBtnSeletorFoto;
    private Uri mSelectedUri;
    private ImageView mImgFoto;
    private ProgressBar mProgressBarReg;
    private EditText mRepetSenha;
    private EditText mCodAluno;
    private Spinner mSpinCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        mEditnome = findViewById(R.id.editNome);
        mEditEmail = findViewById(R.id.editEmail);
        mEditSenha = findViewById(R.id.editSenha);
        mBtnCadastrar = findViewById(R.id.btnCadastrar);
        mBtnSeletorFoto = findViewById(R.id.btnSeletorFoto);
        mImgFoto = findViewById(R.id.imgFoto);
        mProgressBarReg = findViewById(R.id.progressBarReg);
        mRepetSenha = findViewById(R.id.editRepeatSenha);
        mCodAluno = findViewById(R.id.editCodAluno);
        mSpinCurso = (Spinner) findViewById(R.id.spinCurso);



        mBtnSeletorFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarFoto();
            }
        });

        mBtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBarReg.setVisibility(View.VISIBLE);
                createUser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            //verificando a intencao de que pediu
            mSelectedUri = data.getData();

            Bitmap bitmap =  null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mSelectedUri);
                mImgFoto.setImageDrawable(new BitmapDrawable(bitmap));
                mBtnSeletorFoto.setAlpha(0);
            }catch (IOException e){

            }
        }
    }

    private void selecionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    private void createUser() {
        String email = mEditEmail.getText().toString();
        String senha = mEditSenha.getText().toString();
        String nome = mEditnome.getText().toString();
        String RepetirSenha = mRepetSenha.getText().toString();
        String codAluno = mCodAluno.getText().toString();
        String curso = mSpinCurso.getSelectedItem().toString();

        if(codAluno == null || codAluno.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty() || nome == null || nome.isEmpty()){
            Toast.makeText(this,"Todos os campos devem ser preenchidos!",Toast.LENGTH_LONG).show();
            mProgressBarReg.setVisibility(View.INVISIBLE);
            return;
        }
        if(senha == RepetirSenha){
            Toast.makeText(this,"As senhas não conferem!",Toast.LENGTH_LONG).show();
            mProgressBarReg.setVisibility(View.INVISIBLE);
            return;
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("teste", task.getResult().getUser().getUid());
                            saveUserInFirebase();
                        }else{
                            String resposta = task.getException().toString();
                            tratarErros(resposta);
                           // Toast.makeText(RegistroActivity.this, resposta, Toast.LENGTH_SHORT).show();
                            mProgressBarReg.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("testeT", e.getMessage());
                        mProgressBarReg.setVisibility(View.INVISIBLE);
                        //Toast.makeText(RegistroActivity.this, "Erro ao criar Usuario, Verifique conexao ou seus dados!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void tratarErros(String resposta) {
        if (resposta.contains("least 6 characters")){
            Toast.makeText(RegistroActivity.this, "A senha deve ser maior que 6 caracteres!", Toast.LENGTH_SHORT).show();
        }else if(resposta.contains("address is badly")){
            Toast.makeText(RegistroActivity.this, "E-mail invalido!", Toast.LENGTH_SHORT).show();
        }else if(resposta.contains("address is already")){
            Toast.makeText(RegistroActivity.this, "E-mail já existe cadastrado!", Toast.LENGTH_SHORT).show();
        }else if(resposta.contains("interrupted connection")){
            Toast.makeText(RegistroActivity.this, "Sem conexão com a internet!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegistroActivity.this, resposta, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInFirebase() {
        String nomeDeArquivo = FirebaseAuth.getInstance().getUid();//UUID.randomUUID().toString();
        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+nomeDeArquivo);
        ref.putFile(mSelectedUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("teste",uri.toString());

                                String uid = FirebaseAuth.getInstance().getUid();
                                String username = mEditnome.getText().toString();
                                String fotoperfil = uri.toString();
                                int score = 0;
                                String CodAluno = mCodAluno.getText().toString();
                                String curso = mSpinCurso.getSelectedItem().toString();

                                User user =  new User(uid, username, fotoperfil, score, CodAluno, curso);//aqui foi mexido

                                FirebaseFirestore.getInstance().collection("users")
                                        .document(uid)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Intent intent = new Intent(RegistroActivity.this, HomeActivity.class);

                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                                startActivity(intent);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.i("teste",e.getMessage());

                                            }
                                        });

                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Teste",e.getMessage(),e);
                    }
                });
    }
}