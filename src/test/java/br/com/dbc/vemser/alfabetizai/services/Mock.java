package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloAdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
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

    public static Set<Desafio> retornarDesafios() {
        Set<Desafio> desafios = new HashSet<>();

        Desafio desafio = new Desafio();
        desafio.setId(new Random().nextInt());
        desafio.setTitulo("Escolha a letra inicial");
        desafio.setConteudo("Aprenda as consoantes");
        desafio.setTipo(TipoDesafio.QUIZ);
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

        desafios.add(desafio);

        return desafios;
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
        modulo.setDesafios(new HashSet<>());
        modulo.setAlunos(new HashSet<>());
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

    public static ModuloCreateDTO retornarModuloCreateDTO(){
        ModuloCreateDTO modulo = new ModuloCreateDTO();
        modulo.setTitulo("ABC");
        modulo.setConteudo("ABC");
        modulo.setClassificacao(ClassificacaoModulo.INICIANTE);
        modulo.setIdProfessor(1);
        return modulo;
    }

    public static ModuloProfessorDTO retornarModuloProfessorDTO() {
        ModuloProfessorDTO modulo = new ModuloProfessorDTO();
        modulo.setTitulo("ABC");
        modulo.setId(new Random().nextInt());
        modulo.setConteudo("ABC");
        modulo.setFoiAprovado("S");
        modulo.setClassificacao(ClassificacaoModulo.INICIANTE);
        return modulo;
    }

    public static ModuloAdminDTO retornarModuloAdminDTO() {
        ModuloAdminDTO modulo = new ModuloAdminDTO();
        modulo.setTitulo("ABC");
        modulo.setId(new Random().nextInt());
        modulo.setConteudo("ABC");
        modulo.setFoiAprovado("S");
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

    public static Admin retornarAdmin(){
        Admin admin = new Admin();
        admin.setIdUsuario(1);
        admin.setNome("Admin");
        admin.setSobrenome("Um");
        admin.setTelefone("48912345678");
        admin.setEmail("adminUm@email.com");
        admin.setDataDeNascimento(LocalDate.parse("1990-10-10"));
        admin.setSexo("M");
        admin.setSenha("1234");
        admin.setCpf("12345678911");
        admin.setDescricao("Administrador de Sistema");
        admin.setAtivo("S");

        return admin;
    }

    public static Admin retornarAdminSegundo(){
        Admin admin = new Admin();
        admin.setIdUsuario(2);
        admin.setNome("Admin");
        admin.setSobrenome("Dois");
        admin.setTelefone("51987654321");
        admin.setEmail("AdminDois@email.com");
        admin.setDataDeNascimento(LocalDate.parse("1990-10-10"));
        admin.setSexo("F");
        admin.setSenha("1234");
        admin.setCpf("12345678922");
        admin.setDescricao("Administrador de Sistema");
        admin.setAtivo("S");

        return admin;
    }

    public static AdminCreateDTO retornarAdminCreateDTO(){
        AdminCreateDTO adminCreateDTO = new AdminCreateDTO("Tiago", "Raupp", "48912345678", "tiago@email.com", LocalDate.parse("1990-10-10"), "M","1234", "12345678911", "Admistrador de Sistema");

        return adminCreateDTO;
    }

    public static AdminDTO retornarAdminDTO(){
        AdminDTO adminDTO = new AdminDTO(1, "Tiago", "Raupp", "48912345678", "tiago@email.com", LocalDate.parse("1990-10-10"), "S", "M", "12345678911", "Admintrador de Sistema");

        return adminDTO;
    }

    public static AdminModuloDTO retornarAdminModuloDTO(){
        AdminModuloDTO adminModuloDTO = new AdminModuloDTO(1,"teste", "admin", "48911223344", "teste@email.com", "S", "Admin");

        return adminModuloDTO;
    }

}
