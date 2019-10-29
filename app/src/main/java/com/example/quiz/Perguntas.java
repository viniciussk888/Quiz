package com.example.quiz;



public class Perguntas {

    public Perguntas() {
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

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
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

    public Perguntas(String enunciado, String a, String b, String c, String d, String e, String altCerta, String nivel) {
        this.enunciado = enunciado;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.altCerta = altCerta;
        this.nivel = nivel;
    }

    private String enunciado;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String altCerta;
    private String nivel;






}
