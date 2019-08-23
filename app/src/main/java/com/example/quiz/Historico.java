package com.example.quiz;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Historico {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "pontuacao")
    private String pontuacao;
    @ColumnInfo(name = "periodo")
    private String periodo;
    @ColumnInfo(name = "acertos")
    private String acertos;

    public Historico(String pontuacao, String periodo, String acertos) {
        this.pontuacao = pontuacao;
        this.periodo = periodo;
        this.acertos = acertos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAcertos() {
        return acertos;
    }

    public void setAcertos(String acertos) {
        this.acertos = acertos;
    }
}
