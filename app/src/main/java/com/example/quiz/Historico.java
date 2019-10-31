package com.example.quiz;

import java.util.Date;

public class Historico {




    public Historico(String curso, Date data, String pontos, String acertos) {
        this.curso = curso;
        this.data = data;
        this.pontos = pontos;
        this.acertos = acertos;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getAcertos() {
        return acertos;
    }

    public void setAcertos(String acertos) {
        this.acertos = acertos;
    }

    public Historico() {
    }

    String curso;
    Date data;
    String pontos;
    String acertos;
}
