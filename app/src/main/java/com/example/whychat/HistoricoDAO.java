package com.example.whychat;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;



@Dao
public interface HistoricoDAO {
    @Query("SELECT * FROM Historico")
    LiveData<List<Historico>> getAll();

    @Insert
    void insertAll(Historico... historico);

    @Delete
    void delete(Historico historico);

}