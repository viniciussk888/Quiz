package com.example.whychat;

public class VerificarQuestao {
    public boolean isRespostacorreta(Perguntas pergunta, String resposta){
        return pergunta.getCerta() == resposta;
    }
}
