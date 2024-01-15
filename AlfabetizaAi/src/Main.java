import enums.ClassificacaoModulo;
import enums.TipoDesafio;
import exceptions.BancoDeDadosException;
import models.*;
import repository.AdminRepository;
import services.*;
import utils.MenuNumerico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws BancoDeDadosException {

        AdminService adminService = new AdminService();
        ModuloService moduloService = new ModuloService();
        DesafioService desafioService = new DesafioService();
        AlunoService alunoService = new AlunoService();
        ProfessorService professorService = new ProfessorService();

        Scanner sc = new Scanner(System.in);
        int escolha;

        String nome;
        String sobrenome;
        String telefone;
        String email;
        String dataNascimento;
        String sexo;
        String senha;
        String ativo;
        String cpf;
        String descricao;
        LocalDate data;
        LocalDate dataAluno;
        String tituloDesafio;
        Integer tipoNovoDesafio;
        TipoDesafio tipo;
        String conteudo;
        String aprovacaoModulo;
        int indexModulo;
        Professor professor;
        int idProfessor;
        boolean menuUsuarioLogado;
        String sexoAluno;
        String sobrenomeAluno;
        String nomeAluno;


        MenuNumerico.bemVindo();

            while (true) {
                try {
                MenuNumerico.exibirMenuInicial();
                System.out.print("Escolha uma opção do menu: ");

                    escolha = sc.nextInt();
                    sc.nextLine();

                    switch (escolha) {
                        case 1:
                            try {
                                // MENU ALUNO ---
                                System.out.println("Você escolheu a opção Aluno:");
                                System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                                int opcaoMenuAluno = sc.nextInt();
                                sc.nextLine();

                                switch (opcaoMenuAluno) {
                                    case 1:
                                        System.out.println("Informe seu email: ");
                                        String emailLogin = sc.nextLine();

                                        System.out.println("Informe sua senha: ");
                                        String senhaLogin = sc.nextLine();

                                        Aluno alunoLogado = alunoService.loginAluno(emailLogin, senhaLogin);

                                        menuUsuarioLogado = true;

                                        if (alunoLogado == null) {
                                            System.out.println("Falha no login. Verifique suas credenciais.");
                                            break;
                                        } else {
                                            System.out.println("Login bem-sucedido! Bem-vindo, " + alunoLogado.getNome() + ".");
                                            do {
                                                MenuNumerico.menuAluno();
                                                int escolhaInterna = sc.nextInt();
                                                sc.nextLine();

                                                switch (escolhaInterna) {
                                                    case 1:
                                                        System.out.println("Informe o novo nome do responsável: ");
                                                        nome = sc.nextLine();
                                                        System.out.println("Informe o novo sobrenome do responsável: ");
                                                        sobrenome = sc.nextLine();
                                                        System.out.println("Informe o novo cpf do responsável: ");
                                                        cpf = sc.nextLine();
                                                        System.out.println("Informe a nova data de nascimento do responsável (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o novo email do responsável: ");
                                                        email = sc.nextLine();
                                                        System.out.println("Informe o novo telefone do responsável: ");
                                                        telefone = sc.nextLine();
                                                        System.out.println("Informe o sexo do responsável: F - M ");
                                                        sexo = sc.nextLine();
                                                        System.out.println("Crie sua nova senha: ");
                                                        senha = sc.nextLine();

                                                        System.out.println("Informe o novo nome do aluno: ");
                                                        nomeAluno = sc.nextLine();
                                                        System.out.println("Informe o novo sobrenome do aluno: ");
                                                        sobrenomeAluno = sc.nextLine();
                                                        System.out.println("Informe a nova data de nascimento do aluno (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNovaAluno = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o sexo do aluno: F - M ");
                                                        sexoAluno = sc.nextLine();

                                                        Aluno alunoCadastrado = new Aluno();
                                                        alunoCadastrado.setNome(nome);
                                                        alunoCadastrado.setSobrenome(sobrenome);
                                                        alunoCadastrado.setTelefone(telefone);
                                                        alunoCadastrado.setEmail(email);
                                                        alunoCadastrado.setDataDeNascimento(dataNova);
                                                        alunoCadastrado.setSexo(sexo);
                                                        alunoCadastrado.setSenha(senha);
                                                        alunoCadastrado.setNomeAluno(nomeAluno);
                                                        alunoCadastrado.setSobrenomeAluno(sobrenomeAluno);
                                                        alunoCadastrado.setDataNascimentoAluno(dataNovaAluno);
                                                        alunoCadastrado.setSexoAluno(sexoAluno);
                                                        alunoCadastrado.setCpf(cpf);

                                                        alunoService.editar(alunoLogado, alunoCadastrado);
                                                        System.out.println("Aluno editado com sucesso");
                                                        break;

                                                    case 2:
                                                        moduloService.listar();
                                                        System.out.print("Informe o id do módulo que deseja acessar: ");
                                                        int idModuloEscolhido = sc.nextInt();
                                                        sc.nextLine();

                                                        Modulo moduloEscolhido = moduloService.BuscarModuloPorId(idModuloEscolhido);

                                                        if (moduloEscolhido != null) {
                                                            System.out.println(moduloEscolhido.getConteudo());
                                                            System.out.println("Pressione enter para ir para os desafios do módulo!");
                                                            sc.nextLine();
                                                            desafioService.listarDesafiosPorModulo(moduloEscolhido.getId());
                                                            System.out.println("Pressione enter para finalizar o módulo!");
                                                            sc.nextLine();

                                                            System.out.println("Parabéns por concluir o módulo!");
                                                        } else {
                                                            System.out.println("Módulo não encontrado.");
                                                        }
                                                        break;

                                                    case 0:
                                                        menuUsuarioLogado = false;
                                                        break;

                                                    default:
                                                        System.out.println("Opção inválida. Tente novamente.");
                                                        break;
                                                }
                                            } while (menuUsuarioLogado);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Informe o nome do responsável: ");
                                        nome = sc.nextLine();
                                        System.out.println("Informe o sobrenome do responsável: ");
                                        sobrenome = sc.nextLine();
                                        System.out.println("Informe o cpf do responsável: ");
                                        cpf = sc.nextLine();
                                        System.out.println("Informe o telefone do responsável: ");
                                        telefone = sc.nextLine();
                                        System.out.println("Informe a data de nascimento do responsável: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        data = LocalDate.parse(dataNascimento);
                                        System.out.println("Informe o email do responsável: ");
                                        email = sc.nextLine();
                                        System.out.println("Informe o sexo do responsável: F - M - O ");
                                        sexo = sc.nextLine();
                                        System.out.println("Crie sua senha: ");
                                        senha = sc.nextLine();

                                        System.out.println("Informe o nome do aluno: ");
                                        nomeAluno = sc.nextLine();
                                        System.out.println("Informe o sobrenome do aluno: ");
                                        sobrenomeAluno = sc.nextLine();
                                        System.out.println("Informe a data de nascimento do aluno: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        dataAluno = LocalDate.parse(dataNascimento);
                                        System.out.println("Informe o sexo do aluno: F - M - O ");
                                        sexoAluno = sc.nextLine();

                                        Aluno alunoCadastrado = new Aluno();
                                        alunoCadastrado.setNome(nome);
                                        alunoCadastrado.setSobrenome(sobrenome);
                                        alunoCadastrado.setTelefone(telefone);
                                        alunoCadastrado.setEmail(email);
                                        alunoCadastrado.setDataDeNascimento(data);
                                        alunoCadastrado.setSexo(sexo);
                                        alunoCadastrado.setSenha(senha);
                                        alunoCadastrado.setNomeAluno(nomeAluno);
                                        alunoCadastrado.setSobrenomeAluno(sobrenomeAluno);
                                        alunoCadastrado.setDataNascimentoAluno(dataAluno);
                                        alunoCadastrado.setSexoAluno(sexoAluno);
                                        alunoCadastrado.setCpf(cpf);

                                        alunoService.adicionar(alunoCadastrado);
                                        System.out.println("Aluno cadastrado com sucesso");
                                        break;
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            break;

                        case 2:
                            try {
                                //MENU PROFESSOR
                                System.out.println("Você escolheu a opção Professor:");
                                System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                                int opcaoMenuProfessor = sc.nextInt();
                                sc.nextLine();

                                switch (opcaoMenuProfessor) {

                                    case 1:
                                        System.out.println("Informe seu email: ");
                                        email = sc.nextLine();
                                        System.out.println("Informe a sua senha: ");
                                        senha = sc.nextLine();
                                        Professor professorLogado = professorService.loginProfessor(email, senha);
                                        menuUsuarioLogado = true;

                                        if (professorLogado == null) {
                                            System.out.println("Falha no login. Verifique suas credenciais.");
                                            break;
                                        } else {
                                            System.out.println("Login bem-sucedido! Bem-vindo, " + professorLogado.getNome() + ".");
                                            do {
                                                MenuNumerico.menuProfessor();
                                                int escolhaInterna = sc.nextInt();
                                                sc.nextLine();

                                                switch (escolhaInterna) {

                                                    case 1:
                                                        System.out.println("Informe o novo nome: ");
                                                        nome = sc.nextLine();
                                                        System.out.println("Informe o seu novo sobrenome: ");
                                                        sobrenome = sc.nextLine();
                                                        System.out.println("Informe o novo cpf: ");
                                                        cpf = sc.nextLine();
                                                        System.out.println("Informe sua especialidade: ");
                                                        descricao = sc.nextLine();
                                                        System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o novo email: ");
                                                        email = sc.nextLine();
                                                        System.out.println("Informe o seu novo telefone: ");
                                                        telefone = sc.nextLine();
                                                        System.out.println("Informe o seu sexo: F - M - O");
                                                        sexo = sc.nextLine();
                                                        System.out.println("Crie sua nova senha: ");
                                                        senha = sc.nextLine();

                                                        Professor professorCadastrado = new Professor();
                                                        professorCadastrado.setNome(nome);
                                                        professorCadastrado.setSobrenome(sobrenome);
                                                        professorCadastrado.setTelefone(telefone);
                                                        professorCadastrado.setEmail(email);
                                                        professorCadastrado.setDataDeNascimento(dataNova);
                                                        professorCadastrado.setSexo(sexo);
                                                        professorCadastrado.setSenha(senha);
                                                        professorCadastrado.setCpf(cpf);
                                                        professorCadastrado.setDescricao(descricao);

                                                        professorService.editar(professorLogado.getId(), professorCadastrado);
                                                        System.out.println("Professor editado com sucesso");
                                                        break;

                                                    case 2:
                                                        System.out.println("Informe o título do novo Modulo: ");
                                                        String tituloModulo = sc.nextLine();
                                                        professor = professorService.buscarProfessorPorId(professorLogado.getId());
                                                        System.out.println("Digite o Conteudo do novo Modulo: ");
                                                        conteudo = sc.nextLine();

                                                        System.out.println("Por fim, digite o número equivalente ao grau de dificuldade do Módulo: 1 - INICIANTE, 2 - INTERMEDIÁRIO, 3 - AVANÇADO");
                                                        int classificao = sc.nextInt();
                                                        if (classificao > 3 || classificao < 1) {
                                                            throw new IllegalArgumentException("Opção de módulo inexistente");
                                                        } else {
                                                            ClassificacaoModulo dificuldadeSelecionada = ClassificacaoModulo.trazEnumPeloOrdinal(classificao);
                                                            Modulo modulo = new Modulo();
                                                            modulo.setTitulo(tituloModulo);
                                                            modulo.setConteudo(conteudo);
                                                            modulo.setAutor(professor);
                                                            modulo.setClassificacao(dificuldadeSelecionada);
                                                            moduloService.adicionarModulo(modulo);
                                                            System.out.println("Novo módulo cadastrado com sucesso");
                                                        }

                                                        break;

                                                    case 3:
                                                        moduloService.listar();
                                                        System.out.println("Informe o id do módulo que deseja modificar: ");
                                                        Integer idModuloEdicao = sc.nextInt();

                                                        if (idModuloEdicao != null) {
                                                            System.out.println("Módulo encontrado:");
                                                            System.out.println("Informe o novo título do módulo: ");
                                                            String novoTitulo = sc.nextLine();
                                                            System.out.println("Digite o novo conteudo do novo Modulo: ");
                                                            conteudo = sc.nextLine();
                                                            System.out.println("Informe a nova classificação (1 - Iniciante, 2 - Intermediário, 3 - Avançado): ");
                                                            int novaClassificacao = sc.nextInt();
                                                            sc.nextLine();

                                                            ClassificacaoModulo classificacaoNova = ClassificacaoModulo.trazEnumPeloOrdinal(novaClassificacao);
                                                            moduloService.editar(idModuloEdicao, new Modulo(idModuloEdicao, novoTitulo, conteudo, professorLogado, classificacaoNova));
                                                            System.out.println("Módulo editado com sucesso.");
                                                        } else {
                                                            System.out.println("Módulo não encontrado.");
                                                        }
                                                        break;

                                                    case 4:
                                                        moduloService.listar();
                                                        System.out.println("Informe o índice do módulo ao qual o desafio pertence: ");
                                                        int idModuloDoDesafio = sc.nextInt();
                                                        sc.nextLine();
                                                        System.out.println("Informe o título do desafio: ");
                                                        tituloDesafio = sc.nextLine();
                                                        System.out.println("Informe o conteudo do desafio: ");
                                                        String conteudoDesafio = sc.nextLine();
                                                        System.out.println("Digite o número equivalente ao método do desafio: 1 - QUIZ , 2 - JOGO");
                                                        tipoNovoDesafio = sc.nextInt();
                                                        sc.nextLine();
                                                        tipo = TipoDesafio.ofTipo(tipoNovoDesafio);
                                                        if (tipo == null) {
                                                            throw new IllegalArgumentException("Opção de desafio inexistente");
                                                        } else {
                                                            desafioService.adicionar(new Desafio(idModuloDoDesafio, tituloDesafio, conteudoDesafio, tipo));
                                                            System.out.println("Novo desafio criado com sucesso");
                                                        }
                                                        break;
                                                    case 5:
                                                        desafioService.visualizarTodos();
                                                        System.out.println("Informe o id do desafio que deseja modificar: ");
                                                        int idDesafioVelho = sc.nextInt();
                                                        sc.nextLine();

                                                        Desafio desafioParaModificar = new Desafio();
                                                        desafioParaModificar.setId(idDesafioVelho);

                                                        if (desafioParaModificar != null) {
                                                            System.out.println("Desafio encontrado:");
                                                            System.out.println(desafioParaModificar);

                                                            moduloService.listar();
                                                            System.out.println("Informe o índice do novo módulo ao qual o desafio pertence: ");
                                                            int idModuloDoDesafioEditado = sc.nextInt();
                                                            sc.nextLine();
                                                            desafioParaModificar.setIdModulo(idModuloDoDesafioEditado);

                                                            System.out.println("Informe o novo título do desafio (ou pressione Enter para manter o atual):");
                                                            String novoTitulo = sc.nextLine();
                                                            if (!novoTitulo.isEmpty()) {
                                                                desafioParaModificar.setTitulo(novoTitulo);
                                                            }

                                                            System.out.println("Informe o novo conteudo do desafio: ");
                                                            String conteudoNovoDesafio = sc.nextLine();
                                                            desafioParaModificar.setConteudo(conteudoNovoDesafio);

                                                            System.out.println("Informe o novo tipo de desafio (1 - QUIZ, 2 - JOGO, ou 0 para manter o atual):");
                                                            int novoTipo = sc.nextInt();
                                                            sc.nextLine();
                                                            if (novoTipo == 1) {
                                                                desafioParaModificar.setTipoDesafio(TipoDesafio.QUIZ);
                                                            } else if (novoTipo == 2) {
                                                                desafioParaModificar.setTipoDesafio(TipoDesafio.JOGO);
                                                            }

                                                            desafioService.editar(idDesafioVelho, desafioParaModificar);

                                                            System.out.println("Desafio modificado com sucesso.");
                                                        } else {
                                                            System.out.println("Desafio não encontrado.");
                                                        }
                                                        break;
                                                    case 0:
                                                        menuUsuarioLogado = false;
                                                        break;
                                                    default:
                                                        System.out.println("Opção inválida.");
                                                        break;
                                                }

                                            } while (menuUsuarioLogado);

                                        }
                                    case 2:
                                        System.out.println("Informe o seu nome: ");
                                        nome = sc.nextLine();
                                        System.out.println("Informe o seu sobrenome: ");
                                        sobrenome = sc.nextLine();
                                        System.out.println("Informe o seu cpf: ");
                                        cpf = sc.nextLine();
                                        System.out.println("Informe sua especialidade: ");
                                        descricao = sc.nextLine();
                                        System.out.println("Informe o seu telefone: ");
                                        telefone = sc.nextLine();
                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        data = LocalDate.parse(dataNascimento);
                                        System.out.println("Informe o seu email: ");
                                        email = sc.nextLine();
                                        System.out.println("Informe o seu sexo: F - M ");
                                        sexo = sc.nextLine();
                                        System.out.println("Crie sua senha: ");
                                        senha = sc.nextLine();

                                        Professor professorCadastrado = new Professor();
                                        professorCadastrado.setNome(nome);
                                        professorCadastrado.setSobrenome(sobrenome);
                                        professorCadastrado.setTelefone(telefone);
                                        professorCadastrado.setEmail(email);
                                        professorCadastrado.setDataDeNascimento(data);
                                        professorCadastrado.setSexo(sexo);
                                        professorCadastrado.setSenha(senha);
                                        professorCadastrado.setCpf(cpf);
                                        professorCadastrado.setDescricao(descricao);


                                        professorService.adicionar(professorCadastrado);
                                        System.out.println("Professor cadastrado com sucesso");
                                        break;

                                    case 0:
                                        menuUsuarioLogado = false;
                                        break;

                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }


                        case 3:
                            try {
                                //MENU ADMIN
                                System.out.println("Você escolheu a opção Admin.");
                                System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                                int opcaoMenuAdmin = sc.nextInt();
                                sc.nextLine();
                                switch (opcaoMenuAdmin) {

                                    case 1:
                                        System.out.println("Informe seu email: ");
                                        email = sc.nextLine();
                                        System.out.println("Informe sua senha");
                                        senha = sc.nextLine();
                                        Admin adminLogado = adminService.loginAdmin(email, senha);
                                        menuUsuarioLogado = true;

                                        System.out.println(adminLogado);

                                        if (adminLogado == null) {
                                            System.out.println("Falha no login. Verifique suas credenciais.");
                                            break;
                                        } else {
                                            System.out.println("Login bem-sucedido! Bem-vindo, " + adminLogado.getNome() + ".");
                                            do {
                                                MenuNumerico.menuAdmin();
                                                int escolhaInterna = sc.nextInt();
                                                sc.nextLine();

                                                switch (escolhaInterna) {

                                                    case 1:
                                                        System.out.println("Informe o seu nome: ");
                                                        nome = sc.nextLine();
                                                        System.out.println("Informe o seu sobrenome: ");
                                                        sobrenome = sc.nextLine();
                                                        System.out.println("Informe seu cpf: ");
                                                        cpf = sc.nextLine();
                                                        System.out.println("Informe o seu telefone: ");
                                                        telefone = sc.nextLine();
                                                        System.out.println("Informe o seu email: ");
                                                        email = sc.nextLine();
                                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                                        dataNascimento = sc.nextLine();
                                                        data = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o seu sexo: 'F':Feminino 'M':Masculino 'O':Outros");
                                                        sexo = sc.nextLine();
                                                        System.out.println("Informe sua especialidade: ");
                                                        descricao = sc.nextLine();
                                                        System.out.println("Informe sua senha: ");
                                                        senha = sc.nextLine();
                                                        ativo = "S";

                                                        Admin adminCadastrado = new Admin(nome, sobrenome, telefone, email, data, ativo, sexo, senha, cpf, descricao);
                                                        adminService.editar(adminLogado.getId(), adminCadastrado);

                                                        break;
                                                    case 2:
                                                        moduloService.listarSemAprovacao();
                                                        System.out.println("Digite o número do módulo a ser aprovado: ");
                                                        indexModulo = sc.nextInt();
                                                        sc.nextLine();
                                                        System.out.println("S - Para aprovar módulo | R - Para reprovar módulo");
                                                        aprovacaoModulo = sc.nextLine();
                                                        moduloService.editarAprovacaoPorAdmin(adminLogado.getIdAdmin(), indexModulo, aprovacaoModulo);
                                                        break;
                                                    case 3:
                                                        moduloService.listarAprovados();
                                                        break;

                                                    case 4:
                                                        moduloService.listarReprovados();
                                                        break;

                                                    case 5:
                                                        alunoService.visualizarTodosAlunos();
                                                        break;
                                                    case 6:
                                                        System.out.println("Digite o ID do aluno:");
                                                        int idAluno = sc.nextInt();
                                                        sc.nextLine();
                                                        alunoService.BuscarAlunoPorId(idAluno);
                                                        break;
                                                    case 7:
                                                        professorService.visualizarTodos();
                                                        break;
                                                    case 8:
                                                        System.out.println("Digite o ID do professor:");
                                                        idProfessor = sc.nextInt();
                                                        sc.nextLine();
                                                        professorService.buscarProfessorPorId(idProfessor);
                                                        break;

                                                    case 0:
                                                        menuUsuarioLogado = false;
                                                        break;

                                                    default:
                                                        System.out.println("Opção inválida.");
                                                        break;
                                                }
                                            } while (menuUsuarioLogado);

                                        }

                                        break;

                                    case 2:
                                        System.out.println("Informe o seu nome: ");
                                        nome = sc.nextLine();
                                        System.out.println("Informe o seu sobrenome: ");
                                        sobrenome = sc.nextLine();
                                        System.out.println("Informe seu cpf: ");
                                        cpf = sc.nextLine();
                                        System.out.println("Informe o seu telefone: ");
                                        telefone = sc.nextLine();
                                        System.out.println("Informe o seu email: ");
                                        email = sc.nextLine();
                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        data = LocalDate.parse(dataNascimento);
                                        System.out.println("Informe o seu sexo: 'F':Feminino 'M':Masculino 'O':Outros");
                                        sexo = sc.nextLine();
                                        System.out.println("Informe sua especialidade: ");
                                        descricao = sc.nextLine();
                                        System.out.println("Informe sua senha: ");
                                        senha = sc.nextLine();
                                        ativo = "S";

                                        Admin adminCadastrado = new Admin(nome, sobrenome, telefone, email, data, ativo, sexo, senha, cpf, descricao);
                                        adminService.adicionar(adminCadastrado);
                                        break;

                                    case 0:
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println(" ");
                                System.out.println("Opcão inválida, digite somente números conforme opção do menu");
                            } finally {
                                escolha = 3;
                            }
                            break;
                        case 0:
                            System.out.println("Saindo do programa. Até logo!");
                            sc.close();
                            System.exit(0);
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(" ");
                    System.out.println("Opcão inválida, digite somente números conforme opção do menu");
                    sc.nextLine();
                }
            }

    }
}