package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;

import java.time.LocalDate;
import java.util.Random;

public class Mock {

    public static Desafio retornarDesafio(){
        Desafio desafio = new Desafio();
        desafio.setId(72);
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


}
