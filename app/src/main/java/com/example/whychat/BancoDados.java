package com.example.whychat;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Questao.class}, version = 1)
public abstract class BancoDados extends RoomDatabase {
    public abstract QuestaoDAO userDao();
}
