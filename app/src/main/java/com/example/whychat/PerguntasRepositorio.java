package com.example.whychat;

import java.util.ArrayList;
import java.util.List;

public class PerguntasRepositorio {
    public List<Perguntas> getListaPerguntas(){
        return new ArrayList<Perguntas>(){{
           add(new Perguntas("1+1","2","3","4","5","a","1"));
           add(new Perguntas("5+5","7","10","8","89","b","1"));
           add(new Perguntas("20+5","23","30","25","65","c","2"));
           add(new Perguntas("7+7","2","85","65","14","d","2"));
        }};
    }
}
