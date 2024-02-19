package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

public class Mock {

    public static DesafioCreateDTO retornarDesafioCreateDTO(){
        DesafioCreateDTO desafioCreateDTO = new DesafioCreateDTO();
        desafioCreateDTO.setIdModulo(1);
        desafioCreateDTO.setTitulo("Escolha a letra inicial");
        desafioCreateDTO.setConteudo("Aprenda as consoantes");
        desafioCreateDTO.setTipo(TipoDesafio.valueOf("QUIZ"));
        desafioCreateDTO.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
        desafioCreateDTO.setA("D");
        desafioCreateDTO.setB("T");
        desafioCreateDTO.setC("B");
        desafioCreateDTO.setD("S");
        desafioCreateDTO.setE("R");
        desafioCreateDTO.setAlternativaCorreta("C");
        desafioCreateDTO.setPontos(10);
        desafioCreateDTO.setAtivo("S");

        return desafioCreateDTO;
    }

    public static Desafio retornarDesafio(){
        Desafio desafio = new Desafio();
        desafio.setId(new Random().nextInt());
        desafio.setTitulo("Escolha a letra inicial");
        desafio.setConteudo("Aprenda as consoantes");
        desafio.setTipo(TipoDesafio.valueOf("QUIZ"));
        desafio.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
        desafio.setA("D");
        desafio.setB("T");
        desafio.setC("B");
        desafio.setD("S");
        desafio.setE("R");
        desafio.setAlternativaCorreta("C");
        desafio.setPontos(10);

        Modulo modulo = new Modulo();
        modulo.setId(1);
        desafio.setModulo(modulo);

        desafio.setAtivo("S");

        return desafio;
    }

    public static DesafioDTO retornarDesafioDTO(){
        DesafioDTO desafioDTO = new DesafioDTO();
        desafioDTO.setId(72);
        desafioDTO.setTitulo("Escolha a letra inicial");
        desafioDTO.setConteudo("Aprenda as consoantes");
        desafioDTO.setTipo(TipoDesafio.valueOf("QUIZ"));
        desafioDTO.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
        desafioDTO.setA("D");
        desafioDTO.setB("T");
        desafioDTO.setC("B");
        desafioDTO.setD("S");
        desafioDTO.setE("R");
        desafioDTO.setAlternativaCorreta("C");
        desafioDTO.setPontos(10);
        desafioDTO.setAtivo("S");

        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setId(1);
        desafioDTO.setModulo(moduloDTO);

        return desafioDTO;
    }


    public static AlunoCreateDTO retornarAlunoCreateDTO(){
        AlunoCreateDTO alunoCreateDTO = new AlunoCreateDTO();
        alunoCreateDTO.setSexoAluno("F");
        alunoCreateDTO.setNomeAluno("Teste");
        alunoCreateDTO.setSobrenomeAluno("da silva");
        alunoCreateDTO.setDataNascimentoAluno(LocalDate.now());
        return alunoCreateDTO;
    }

    public static Aluno retornarAluno(){
        Aluno aluno = new Aluno();
        aluno.setIdAluno(new Random().nextInt());
        aluno.setSexoAluno("F");
        aluno.setNomeAluno("Teste");
        aluno.setSobrenomeAluno("da silva");
        aluno.setDataNascimentoAluno(LocalDate.now());
        return aluno;
    }

    public static AlunoDTO retornarAlunoDTO(){
        AlunoDTO aluno = new AlunoDTO();
        aluno.setIdAluno(new Random().nextInt());
        aluno.setSexoAluno("F");
        aluno.setNomeAluno("Teste");
        aluno.setSobrenomeAluno("da silva");
        aluno.setDataNascimentoAluno(LocalDate.now());
        return aluno;
    }

    public static Responsavel retornarResponsavel() {
        Responsavel responsavel = new Responsavel();
        responsavel.setSenha("2344");
        responsavel.setCpf("11111111111");
        responsavel.setSexo("F");
        responsavel.setTelefone("8776655753");
        responsavel.setNome("Teste");
        responsavel.setDataDeNascimento(LocalDate.now());
        responsavel.setSobrenome("da silva");
        responsavel.setEmail("teste@email.com");
        return responsavel;
    }

    public static Usuario retornarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setSenha("2344");
        usuario.setCpf("11111111111");
        usuario.setSexo("F");
        usuario.setTelefone("8776655753");
        usuario.setNome("Teste");
        usuario.setDataDeNascimento(LocalDate.now());
        usuario.setSobrenome("da silva");
        usuario.setEmail("teste@email.com");
        return usuario;
    }

    public static ResponsavelComAlunosDTO retornarResponsavelComALunosDTO(AlunoDTO aluno) {
        ResponsavelComAlunosDTO responsavel = new ResponsavelComAlunosDTO();
        responsavel.setCpf("11111111111");
        responsavel.setSexo("F");
        responsavel.setTelefone("8776655753");
        responsavel.setNome("Teste");
        responsavel.setDataDeNascimento(LocalDate.now());
        responsavel.setSobrenome("da silva");
        responsavel.setEmail("teste@email.com");
        responsavel.setAlunos(Set.of(aluno));
        return responsavel;
    }

    public static ResponsavelDTO retornarResponsavelDTO() {
        ResponsavelDTO responsavel = new ResponsavelDTO();
        responsavel.setCpf("11111111111");
        responsavel.setSexo("F");
        responsavel.setTelefone("8776655753");
        responsavel.setNome("Teste");
        responsavel.setDataDeNascimento(LocalDate.now());
        return responsavel;
    }

    public static ResponsavelCreateDTO retornarResponsavelCreateDTO() {
        ResponsavelCreateDTO responsavel = new ResponsavelCreateDTO();
        responsavel.setCpf("11111111111");
        responsavel.setSexo("F");
        responsavel.setTelefone("8776655753");
        responsavel.setNome("Teste");
        responsavel.setDataDeNascimento(LocalDate.now());
        return responsavel;
    }

    public static Modulo retornarModulo() {
        Modulo modulo = new Modulo();
        modulo.setTitulo("ABC");
        modulo.setId(new Random().nextInt());
        modulo.setConteudo("ABC");
        modulo.setClassificacao(ClassificacaoModulo.INICIANTE);
        return modulo;
    }

    public static ModuloDTO retornarModuloDTO() {
        ModuloDTO modulo = new ModuloDTO();
        modulo.setTitulo("ABC");
        modulo.setId(new Random().nextInt());
        modulo.setConteudo("ABC");
        modulo.setClassificacao(ClassificacaoModulo.INICIANTE);
        return modulo;
    }

    public static Professor retornarProfessor() {
        Professor professor = new Professor();
        professor.setIdUsuario(1);
        professor.setNome("Jake");
        professor.setSobrenome("Oliveira");
        professor.setTelefone("997239878");
        professor.setEmail("jake@email.com");
        professor.setDataDeNascimento(LocalDate.parse("2022-02-01"));
        professor.setAtivo("S");
        professor.setSexo("M");
        professor.setSenha("123");
        professor.setCpf("57665284000");
        professor.setDescricao("Licenciatura em Letras");

        return professor;

    }

    public static ProfessorDTO retornarProfessorDTO() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdUsuario(1);
        professorDTO.setNome("Jake");
        professorDTO.setSobrenome("Oliveira");
        professorDTO.setTelefone("997239878");
        professorDTO.setEmail("jake@email.com");
        professorDTO.setAtivo("S");
        professorDTO.setDescricao("Licenciatura em Letras");

        return professorDTO;

    }

    public static ProfessorCreateDTO retornarProfessorCreateDTO(){
        ProfessorCreateDTO professorCreateDTO = new ProfessorCreateDTO();
        professorCreateDTO.setNome("Jake");
        professorCreateDTO.setSobrenome("Oliveira");
        professorCreateDTO.setTelefone("997239878");
        professorCreateDTO.setEmail("jake@email.com");
        professorCreateDTO.setDataDeNascimento(LocalDate.parse("2022-02-01"));
        professorCreateDTO.setSexo("M");
        professorCreateDTO.setSenha("123");
        professorCreateDTO.setCpf("57665284000");
        professorCreateDTO.setDescricao("Licenciatura em Letras");

        return professorCreateDTO;
    }
}
