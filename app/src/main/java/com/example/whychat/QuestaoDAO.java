package com.example.whychat;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;



@Dao
public interface QuestaoDAO {
    @Query("SELECT * FROM questao")
    List<Questao> getAll();

    @Query("SELECT * FROM questao WHERE id IN (:cursoIds)")
    List<Questao> loadAllByIds(int[] cursoIds);

    @Insert
    void insertAll(Questao... questao);

    @Delete
    void delete(Questao questao);
}