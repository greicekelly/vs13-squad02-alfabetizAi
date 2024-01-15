package utils;

import enums.ClassificacaoModulo;
import enums.TipoDesafio;
import models.*;
import services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuNumerico {

    public static void exibirMenuInicial() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 se você é um Aluno");
        System.out.println("2. Pressione 2 se você é um Professor");
        System.out.println("3. Pressione 3 se você é um Admin");
        System.out.println("0. Sair");
    }

    public static void menuAluno() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais"); //UPDATE
        System.out.println("2. Pressione 2 para acessar os módulos"); // READ
        System.out.println("0. Logoff");
    }

    public static void menuProfessor() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais"); //UPDATE
        System.out.println("2. Pressione 2 para criar um novo módulo"); // CREATE //passar por aprovação novamente
        System.out.println("3. Pressione 3 para modificar um módulo"); // UPDATE //passar por aprovação novamente
        System.out.println("4. Pressione 4 para criar um novo desafio"); // CREATE //passar por aprovação novamente
        System.out.println("5. Pressione 5 para modificar um desafio"); // UPDATE //passar por aprovação novamente
        System.out.println("0. Logoff");
    }

    public static void menuAdmin() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais");
        System.out.println("2. Pressione 2 para aprovar/reprovar um módulo");
        System.out.println("3. Pressione 3 para listar módulos aprovados");
        System.out.println("4. Pressione 4 para listar módulos reprovados");
        System.out.println("5. Pressione 5 para listar todos os alunos");
        System.out.println("6. Pressione 6 para detalhar o aluno por ID");
        System.out.println("7. Pressione 7 para listar todos os professores");
        System.out.println("8. Pressione 8 para detalhar o professor por ID");
        System.out.println("0. Logoff");
    }

    public static void bemVindo(){
        System.out.println(" ");
        System.out.println("para efeito de teste, o sistema possui alunos, professores e administradores já cadastrados");
        System.out.println(" ");
        System.out.println("*************************************************************************");
        System.out.println("****************Seja bem vindo(a) ao sistema alfabetizaAi****************");
        System.out.println("*************************************************************************");
    }
}
