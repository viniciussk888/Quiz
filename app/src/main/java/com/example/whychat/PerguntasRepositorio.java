package com.example.whychat;

import java.util.ArrayList;
import java.util.List;

public class PerguntasRepositorio {
    public List<Perguntas> getListaPerguntas(){
        return new ArrayList<Perguntas>(){{
           add(new Perguntas("1","pedro","maria","josefa","joao","a","1"));
           add(new Perguntas("11","pedro","maria","josefa","joao","b","1"));
           add(new Perguntas("2","pedro","maria","josefa","joao","c","2"));
           add(new Perguntas("22","pedro","maria","josefa","joao","d","2"));
        }};
    }
}
