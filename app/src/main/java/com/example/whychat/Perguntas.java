package com.example.whychat;



public class Perguntas {

    public Perguntas(String enunciado, String a, String b, String c, String d, String certa, String periodo) {
        this.enunciado = enunciado;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.certa = certa;
        this.periodo = periodo;
    }

    private String enunciado;
    private String a;
    private String b;
    private String c;
    private String d;
    private String certa;
    private String periodo;



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

    public String getCerta() {
        return certa;
    }

    public void setCerta(String certa) {
        this.certa = certa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


}
