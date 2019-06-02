package com.example.whychat;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Questao {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "periodo")
    private int periodo;
    @ColumnInfo(name = "enunciado")
    private String enunciado;
    @ColumnInfo(name = "a")
    private String a;
    @ColumnInfo(name = "b")
    private String b;
    @ColumnInfo(name = "c")
    private String c;
    @ColumnInfo(name = "d")
    private String d;
    @ColumnInfo(name = "resposta")
    private String resposta;

    public Questao() {
    }

    public Questao(int periodo, String enunciado, String a, String b, String c, String d, String resposta) {
        this.periodo = periodo;
        this.enunciado = enunciado;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.resposta = resposta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
