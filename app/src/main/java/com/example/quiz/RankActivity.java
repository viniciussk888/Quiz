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

public class RankActivity extends AppCompatActivity {

    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView rv = findViewById(R.id.recycler);

        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        buscarUsuarios();
    }

    private void buscarUsuarios() {
        FirebaseFirestore.getInstance().collection("/users").orderBy("score", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e != null){
                            Log.e("teste",e.getMessage(),e);
                            return;
                        }

                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot doc: docs){
                            User user = doc.toObject(User.class);
                            Log.d("teste",user.getUsername());

                            adapter.add(new UserItem(user));
                        }

                    }
                });
    }

    private class UserItem extends Item<ViewHolder>{
        private final User user;

        private UserItem(User user) {
            this.user = user;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtUserName = viewHolder.itemView.findViewById(R.id.textView);
            ImageView imgFoto = viewHolder.itemView.findViewById(R.id.imageView);
            TextView txtScore = viewHolder.itemView.findViewById(R.id.textViewScore);
            TextView txtCurso = viewHolder.itemView.findViewById(R.id.textViewCurso);

            txtScore.setText("Score: "+user.getScore());
            txtCurso.setText(user.getCurso());
            txtUserName.setText(user.getUsername());

            Picasso.get()
                    .load(user.getProfileUrl())
                    .into(imgFoto);
        }

        @Override
        public int getLayout() {
            return R.layout.item_user;
        }
    }
}
