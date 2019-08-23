package com.example.quiz;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Historico.class}, version = 1)
public abstract class BancoDados extends RoomDatabase {
    public abstract HistoricoDAO userDao();
}
