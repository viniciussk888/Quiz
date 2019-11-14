package com.example.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditarUsuarioActivity extends AppCompatActivity {
    private TextView mNomeUser;
    private ImageView mImagemUser;
    private Button mbtnSalvar;
    private Button mbtnAltSenha;
    private Button mBtnAltFoto;
    private EditText mEditSenha;
    private EditText mEditSenha2;
    private Button mbtnDeletarUser;
    private EditText mEditNome;
    private Uri mSelectedUri;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mbtnSalvar = findViewById(R.id.btnSalvar);
        mbtnAltSenha = findViewById(R.id.btnAltSenha);
        mbtnDeletarUser = findViewById(R.id.btnDeletarUser);
        mNomeUser = findViewById(R.id.txtNomeUser);
        mImagemUser = findViewById(R.id.imgUserEdit);
        mEditNome = findViewById(R.id.editNome);
        mEditSenha = findViewById(R.id.editAltSenha);
        mEditSenha2 = findViewById(R.id.editAltSenha2);
        mBtnAltFoto = findViewById(R.id.btnAltFoto);

        Intent it = getIntent();
        mNomeUser.setText(it.getStringExtra("nome"));
        Picasso.get()
                .load(it.getStringExtra("linkFoto"))
                .into(mImagemUser);



        mBtnAltFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecionarFoto();
            }
        });
        mbtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = mEditNome.getText().toString();
                if(nome==null||nome.isEmpty()){
                    Toast.makeText(EditarUsuarioActivity.this, "Digite o novo nome!", Toast.LENGTH_SHORT).show();
                    return;
                }
                confirmarSalvar();
            }
        });
        mbtnAltSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = mEditNome.getText().toString();
                if(mEditSenha.getText().toString()==null||mEditSenha.getText().toString().isEmpty() || mEditSenha2.getText().toString()==null||mEditSenha2.getText().toString().isEmpty()){
                    Toast.makeText(EditarUsuarioActivity.this, "Digite as Senhas!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mEditSenha.getText().toString().equals(mEditSenha2.getText().toString()) == false || mEditSenha.length()<6 || mEditSenha2.length()<6){
                    Toast.makeText(EditarUsuarioActivity.this, "As Senhas devem ser iguais e acima 6 de caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }
                alterarSenha();
            }
        });
        mbtnDeletarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarUser();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==0){
                //verificando a intencao de que pediu
                if(data != null) {
                    mSelectedUri = data.getData();

                    Bitmap bitmap = null;

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                    mImagemUser.setImageDrawable(new BitmapDrawable(bitmap));
                    enviarFoto();
                }else{
                    enviarFoto();
                }

            } }catch (IOException e){
            Intent intent = new Intent(EditarUsuarioActivity.this, EditarUsuarioActivity.class);

            startActivity(intent);

        }
    }

    private void enviarFoto() {
        String nomeDeArquivo = FirebaseAuth.getInstance().getUid();
        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+nomeDeArquivo);
        ref.putFile(mSelectedUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String uid = FirebaseAuth.getInstance().getUid().toString();
                                FirebaseFirestore.getInstance().collection("users").document(uid).update("profileUrl",uri.toString());

                                Toast.makeText(EditarUsuarioActivity.this, "Foto alterada com SUCESSO!", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditarUsuarioActivity.this, "Erro!\n"+e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarUsuarioActivity.this, "Erro!\n"+e, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void selecionarFoto() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,0);

        }catch (Exception e){
            Toast.makeText(this,"Erro ao selecionar a foto!"+e,Toast.LENGTH_LONG).show();
        }

    }

    private void alterarSenha() {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String newPassword = mEditSenha.getText().toString();

            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditarUsuarioActivity.this, "Senha alterada com SUCESSO!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditarUsuarioActivity.this, "Erro"+e, Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void deletarUser() {
        new AlertDialog.Builder(this)
                .setTitle("Deletar Usuario")
                .setMessage("Tem certeza que deseja DELETAR USUARIO?")
                .setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.delete();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("users").document(user.getUid()).delete();
                                    Intent intent = new Intent(EditarUsuarioActivity.this, MainActivity.class);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


                                    startActivity(intent);
                                }catch (Exception e){
                                    Toast.makeText(EditarUsuarioActivity.this, "Erro ao deletar Usuario!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                .setNegativeButton("Não", null)
                .show();
    }

    private void confirmarSalvar() {
        new AlertDialog.Builder(this)
                .setTitle("Alterar nome de Usuario")
                .setMessage("Tem certeza que deseja alterar?")
                .setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nome)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(EditarUsuarioActivity.this, "Nome Alterado", Toast.LENGTH_SHORT).show();
                                                    mNomeUser.setText(nome);
                                                }
                                            }
                                        });
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Map<String, Object> update = new HashMap<>();
                                update.put("username", nome);

                                db.collection("users").document(user.getUid()).set(update, SetOptions.merge());

                            }
                        })
                .setNegativeButton("Não", null)
                .show();
    }
}
