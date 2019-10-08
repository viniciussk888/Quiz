package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

public class PerguntasRepositorio {
    public List<Perguntas> getListaPerguntas(){
        return new ArrayList<Perguntas>(){{
           add(new Perguntas("Você gosta de resolver enigmas e charadas??",
                   "SIM",
                   "",
                   "",
                   "NAO",
                   "a"));
           add(new Perguntas("Você tem habilidade para resolver problemas sozinhos, buscando aprendizado em livros, artigos ou vídeos de tutorias?",
                   "SIM",
                   "",
                   "",
                   "NAO",
                   "a"));
           add(new Perguntas("As pessoas costumam te procurar para tirar alguma dúvida sobre celular ou computador?",
                   "SIM",
                   "",
                   "",
                   "NAO",
                   "a"));
           add(new Perguntas("Você gosta de jogos?",
                   "SIM",
                   "",
                   "",
                   "NAO",
                   "a"));
            add(new Perguntas("Você gostaria de conseguir desenvolver o seu próprio jogo?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
            add(new Perguntas("Você sabe formatar computador?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
            add(new Perguntas("Quando seu celular ou computador tem algum problema, você tenta resolver antes de mandar para um profissional?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
            add(new Perguntas("Você sabe a diferença entre software e hardware?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
            add(new Perguntas("Você se considera uma pessoa com um bom raciocínio lógico?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
            add(new Perguntas("Você se considera Criativo?",
                    "SIM",
                    "",
                    "",
                    "NAO",
                    "a"));
        }};
    }
}
