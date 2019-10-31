package com.example.quiz;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class HistoricoActivity extends AppCompatActivity {
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView rv = findViewById(R.id.recyclerHist);

        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        buscarHistorico();
    }

    private void buscarHistorico() {
        FirebaseUser use = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("userHistorico").document(use.getUid()).collection("historico")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e != null){
                            Log.e("teste",e.getMessage(),e);
                            return;
                        }

                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot doc: docs){
                            Historico user = doc.toObject(Historico.class);

                            adapter.add(new UserItem(user));
                        }

                    }
                });
    }
    private class UserItem extends Item<ViewHolder>{
        private final Historico user;

        private UserItem(Historico user) {
            this.user = user;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtData = viewHolder.itemView.findViewById(R.id.itemData);
            TextView txtCurso = viewHolder.itemView.findViewById(R.id.itemCurso);
            TextView txtPontos = viewHolder.itemView.findViewById(R.id.itemPontos);
            TextView txtAcertos = viewHolder.itemView.findViewById(R.id.itemAcertos);


            txtData.setText(user.getData().toString());
            txtCurso.setText("Curso: "+user.getCurso());
            txtPontos.setText("Pontuação ganha: "+user.getPontos());
            txtAcertos.setText("Qtd Acertos: "+user.getAcertos());
        }

        @Override
        public int getLayout() {
            return R.layout.item_lista;
        }
    }
}
