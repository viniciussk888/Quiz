package com.example.whychat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

public class EditarUsuarioActivity extends AppCompatActivity {
    private TextView mNomeUser;
    private ImageView mImagemUser;
    private Button mbtnSalvar;
    private Button mbtnDeletarUser;
    private EditText mEditNome;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        mbtnSalvar = findViewById(R.id.btnSalvar);
        mbtnDeletarUser = findViewById(R.id.btnDeletarUser);
        mNomeUser = findViewById(R.id.txtNomeUser);
        mImagemUser = findViewById(R.id.imgUserEdit);
        mEditNome = findViewById(R.id.editNome);

        Intent it = getIntent();
        mNomeUser.setText(it.getStringExtra("nome"));
        Picasso.get()
                .load(it.getStringExtra("linkFoto"))
                .into(mImagemUser);
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
        mbtnDeletarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarUser();
            }
        });


    }

    private void deletarUser() {
        new AlertDialog.Builder(this)
                .setTitle("Deletar Usuario")
                .setMessage("Tem certeza que DELETAR USUARIO?")
                .setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.delete();
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
                            }
                        })
                .setNegativeButton("Não", null)
                .show();
    }
}
