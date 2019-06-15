package com.example.whychat;

import java.util.ArrayList;
import java.util.List;

public class PerguntasRepositorio {
    public List<Perguntas> getListaPerguntas(){
        return new ArrayList<Perguntas>(){{
           add(new Perguntas("Existem vários tipos de Sistemas de Informações para serem aplicados em uma organização. A afirmação Sistema de computação que tenta imitar o conhecimento humano aplicando metodologias de raciocínio ou conhecimento sobre uma área específica define um sistema de que tipo?",
                   "SIG-Sistema de Informação Gerencial",
                   "SE-Sistema Especialista",
                   "SPT-Sistema de Processamento de Transações",
                   "SAD-Sistema de Apoio à Decisão",
                   "b","1"));
           add(new Perguntas("Sobre sistemas operacionais é INCORRETO afirmar:",
                   "O sistema operacional é uma camada de hardware que separa as aplicações do software que elas acessam.",
                   "Na maioria dos sistemas operacionais um usuário requisita ao computador que execute uma ação e o sistema operacional gerencia o software e o hardware para produzir o resultado esperado.",
                   "Um usuário interage com o sistema operacional via uma ou mais aplicações de usuário e, muitas vezes, por meio de uma aplicação especial denominada shell ou interpretador de comandos.",
                   "O software que contém os componentes centrais do sistema operacional chama-se núcleo (kernel).",
                   "a","2"));
           add(new Perguntas("As funções de um sistema de gerenciamento de banco de dados (SGBD) incluem",
                   "gerenciar a integridade de dados, o dicionário e o armazenamento de dados, bem como a memória do computador enquanto o SGBD estiver em execução.",
                   "transformar e apresentar dados, controlar o acesso de multiusuário e prover interfaces de comunicação do banco de dados.",
                   "gerenciar o sistema de arquivos e a segurança do banco de dados.",
                   "gerenciar a entrada e saída de dispositivos, linguagens de acesso ao banco de dados e interfaces de programação de aplicações.",
                   "b","2"));
           add(new Perguntas("Para ter acesso e manipular mensagens de correio eletrônico, um software cliente pode utilizar o protocolo",
                   "IMAP.",
                   "LDAP.",
                   "IP.",
                   "NTP.",
                   "a","2"));
        }};
    }
}
