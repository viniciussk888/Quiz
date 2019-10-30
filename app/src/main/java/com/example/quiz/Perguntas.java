package com.example.quiz;


import java.util.Date;

public class Perguntas {

    public Perguntas() {

    }






    public Perguntas(String enunciado, String a, String b, String c, String d, String e, String altCerta, String nivel, Date postada) {
        this.enunciado = enunciado;
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        this.altCerta = altCerta;
        this.nivel = nivel;
        this.postada = postada;
    }
    private String enunciado;
    private String A;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getAltCerta() {
        return altCerta;
    }

    public void setAltCerta(String altCerta) {
        this.altCerta = altCerta;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Date getPostada() {
        return postada;
    }

    public void setPostada(Date postada) {
        this.postada = postada;
    }

    private String B;
    private String C;
    private String D;
    private String E;
    private String altCerta;
    private String nivel;
    private Date postada;

}
