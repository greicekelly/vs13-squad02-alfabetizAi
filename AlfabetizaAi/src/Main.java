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
        String tituloNovoDesafio;
        Integer tipoNovoDesafio;
        TipoDesafio tipo;
        String conteudo;
        String aprovacaoModulo;
        int indexModulo;
        Professor professor;
        int idProfessor;
        boolean menuUsuarioLogado;


        MenuNumerico.bemVindo();

        try {
            while (true) {
                MenuNumerico.exibirMenuInicial();
                System.out.print("Escolha uma opção do menu: ");

                if (sc.hasNextInt()) {
                    escolha = sc.nextInt();
                    sc.nextLine();

                    switch (escolha) {
                        case 1:
//                            try {
//                                // MENU ALUNO ---
//                                System.out.println("Você escolheu a opção Aluno:");
//                                System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
//                                int opcaoMenuAluno = sc.nextInt();
//                                sc.nextLine();
//
//                                switch (opcaoMenuAluno) {
//                                    case 1:
//                                        System.out.println("Informe seu email: ");
//                                        String emailLogin = sc.nextLine();
//
//                                        System.out.println("Informe sua senha: ");
//                                        String senhaLogin = sc.nextLine();
//
//                                        Aluno alunoLogado = alunoService.loginAluno(emailLogin, senhaLogin);
//
//                                        boolean menuUsuarioLogado = true;
//
//                                        if (alunoLogado == null) {
//                                            System.out.println("Falha no login. Verifique suas credenciais.");
//                                            break;
//                                        } else {
//                                            System.out.println("Login bem-sucedido! Bem-vindo, " + alunoLogado.getNome() + ".");
//                                            do {
//                                                MenuNumerico.menuAluno();
//                                                int escolhaInterna = sc.nextInt();
//                                                sc.nextLine();
//
//                                                switch (escolhaInterna) {
//                                                    case 1:
//                                                        System.out.println("Informe o novo nome: ");
//                                                        String novoNome = sc.nextLine();
//                                                        System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
//                                                        String novaDataNascimento = sc.nextLine();
//                                                        LocalDate dataNova = LocalDate.parse(novaDataNascimento);
//                                                        System.out.println("Informe o novo email: ");
//                                                        String novoEmail = sc.nextLine();
//                                                        Aluno alunoEditado = new Aluno();
//                                                        alunoEditado.setNome(novoNome);
//                                                        alunoEditado.setDataDeNascimento(dataNova);
//                                                        alunoEditado.setEmail(novoEmail);
//                                                        alunoService.editarAluno(alunoLogado, alunoEditado);
//                                                        System.out.println("Aluno editado com sucesso");
//                                                        break;
//
//                                                    case 2:
//                                                        moduloService.listar();
//                                                        System.out.print("Informe o titulo do módulo que deseja acessar os desafios: ");
//                                                        String tituloModuloDesafios = sc.nextLine();
//
//                                                        //TODO: metodo buscar modulo por ID
//                                                        Modulo moduloEscolhidoParaDesafios = moduloService.listar();
//
//                                                        if (moduloEscolhidoParaDesafios != null) {
//                                                            DesafioService desafiosDoModulo = new DesafioService();
//
//                                                            desafiosDoModulo.visualizarTodos(moduloEscolhidoParaDesafios.);
//
//                                                            System.out.print("Informe o índice do desafio que deseja acessar: ");
//                                                            int indiceDesafio = sc.nextInt();
//
//                                                            desafiosDoModulo.visualizarTodos();
//                                                            sc.nextLine();
//
//                                                            System.out.println("Parabéns por concluir o desafio!");
//                                                            moduloService.adicionarModulo(moduloEscolhidoParaDesafios);
//                                                        } else {
//                                                            System.out.println("Módulo não encontrado.");
//                                                        }
//                                                        break;
//
//                                                    case 3:
//                                                        moduloService.listar();
//                                                        break;
//
//                                                    case 0:
//                                                        menuUsuarioLogado = false;
//                                                        break;
//
//                                                    default:
//                                                        System.out.println("Opção inválida. Tente novamente.");
//                                                        break;
//                                                }
//                                            } while (menuUsuarioLogado);
//                                        }
//                                        break;
//
//                                    case 2:
//                                        System.out.println("Informe o seu nome: ");
//                                        String novoNome = sc.nextLine();
//
//                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
//                                        String novaDataNascimento = sc.nextLine();
//                                        LocalDate novaDataNascimentoLocal = LocalDate.parse(novaDataNascimento);
//
//                                        System.out.println("Informe o seu email: ");
//                                        String novoEmail = sc.nextLine();
//
//                                        Aluno alunoCadastrado = new Aluno(novoNome, novaDataNascimentoLocal, novoEmail);
//
//                                        alunoService.adicionarAluno(alunoCadastrado);
//                                        System.out.println("Aluno cadastrado com sucesso");
//                                        break;

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
                                                        System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o novo email: ");
                                                        email = sc.nextLine();
                                                        System.out.println("Informe o seu novo telefone: ");
                                                        telefone = sc.nextLine();
                                                        System.out.println("Informe o seu sexo: F - M ");
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

                                                        professorService.editar(professorLogado.getId(), professorCadastrado);
                                                        System.out.println("Professor editado com sucesso");
                                                        break;

                                                    case 2:
                                                        System.out.println("Informe o título do novo Modulo: ");
                                                        String tituloModulo = sc.nextLine();
                                                        professor = professorService.buscarProfessorPorId(professorLogado.getId());
                                                        System.out.println("Digite o Conteudo do novo Modulo: ");
                                                        conteudo = sc.nextLine();
                                                        System.out.println("Adicione o primeiro desafio deste módulo, insira o título do desafio: ");
                                                        tituloNovoDesafio = sc.nextLine();
                                                        System.out.println("Digite o número equivalente ao método do desafio: 1 - QUIZ , 2 - JOGO");
                                                        tipoNovoDesafio = sc.nextInt();
                                                        tipo = TipoDesafio.ofTipo(tipoNovoDesafio);
                                                        if (tipo == null) {
                                                            throw new IllegalArgumentException("Opção de desafio inexistente");
                                                        } else {
                                                            Desafio desafioCriado = new Desafio();
                                                            desafioCriado.setTitulo(tituloNovoDesafio);
                                                            desafioCriado.setTipoDesafio(tipo);
                                                            desafioService.adicionar(desafioCriado);
                                                            System.out.println("Por fim, digite o número equivalente ao grau de dificuldade do Módulo: 1 - INICIANTE, 2 - INTERMEDIÁRIO, 3 - AVANÇADO");
                                                            int classificao = sc.nextInt();
                                                            if (classificao > 3 || classificao < 1) {
                                                                throw new IllegalArgumentException("Opção de módulo inexistente");
                                                            } else {
                                                                ClassificacaoModulo dificuldadeSelecionada = ClassificacaoModulo.trazEnumPeloOrdinal(classificao);
                                                                DesafioService novaListaDesafio = new DesafioService();
                                                                novaListaDesafio.adicionar(desafioCriado);
                                                                Modulo modulo = new Modulo();
                                                                modulo.setTitulo(tituloModulo);
                                                                modulo.setConteudo(conteudo);
                                                                modulo.setAutor(professor);
                                                                modulo.setClassificacao(dificuldadeSelecionada);
                                                                moduloService.adicionarModulo(modulo);
                                                                desafioService.adicionar(desafioCriado);
                                                                System.out.println("Novo módulo cadastrado com sucesso");
                                                            }
                                                            break;
                                                        }

                                                    case 3:
                                                        moduloService.listar();
                                                        System.out.println("Informe o id do módulo que deseja modificar: ");
                                                        Integer idModuloEdicao = sc.nextInt();
//
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
                                                        System.out.println("Informe o título do novo desafio: ");
                                                        tituloNovoDesafio = sc.nextLine();
                                                        System.out.println("Digite o número equivalente ao método do desafio: 1 - QUIZ , 2 - JOGO");
                                                        tipoNovoDesafio = sc.nextInt();
                                                        sc.nextLine();
                                                        tipo = TipoDesafio.ofTipo(tipoNovoDesafio);
                                                        if (tipo == null) {
                                                            throw new IllegalArgumentException("Opção de desafio inexistente");
                                                        } else {
                                                            System.out.println("Informe o ID do professor autor deste desafio: ");
                                                            idProfessor = sc.nextInt();
                                                            professor = professorService.buscarProfessorPorId(idProfessor);
                                                            desafioService.adicionar(new Desafio(tituloNovoDesafio, tipo));
                                                            System.out.println("Novo desafio criado com sucesso");
                                                        }
                                                        break;
                                                    case 5:
                                                        System.out.println("Informe o título do desafio que deseja modificar: ");
                                                        String tituloDesafioModificar = sc.nextLine();

                                                        Desafio desafioParaModificar = null;
                                                        for (Desafio desafio : listaDesafios.getListaDesafios()) {
                                                            if (desafio.getTitulo().equalsIgnoreCase(tituloDesafioModificar)) {
                                                                desafioParaModificar = desafio;
                                                                break;
                                                            }
                                                        }

                                                        if (desafioParaModificar != null) {
                                                            System.out.println("Desafio encontrado:");
                                                            System.out.println(desafioParaModificar);

                                                            System.out.println("Informe o novo título do desafio (ou pressione Enter para manter o atual):");
                                                            String novoTitulo = sc.nextLine();
                                                            if (!novoTitulo.isEmpty()) {
                                                                desafioParaModificar.setTitulo(novoTitulo);
                                                            }

                                                            System.out.println("Informe o novo tipo de desafio (1 - QUIZ, 2 - JOGO, ou 0 para manter o atual):");
                                                            int novoTipo = sc.nextInt();
                                                            sc.nextLine();
                                                            if (novoTipo == 1) {
                                                                desafioParaModificar.setTipoDesafio(TipoDesafio.QUIZ);
                                                            } else if (novoTipo == 2) {
                                                                desafioParaModificar.setTipoDesafio(TipoDesafio.JOGO);
                                                            }

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
                                }
                            } catch (Exception e){
                                    e.printStackTrace();
                                    System.out.println(" ");
                                    System.out.println("Opcão inválida, digite somente números conforme opção do menu");
                                }
                                break;

                                    case 2:
                                        System.out.println("Informe o seu nome: ");
                                        nome = sc.nextLine();
                                        System.out.println("Informe o seu sobrenome: ");
                                        sobrenome = sc.nextLine();
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


                                        professorService.adicionar(professorCadastrado);
                                        System.out.println("Professor cadastrado com sucesso");
                                        break;

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
                                                        System.out.println("S - Para apovar módulo | N - Para reprovar módulo");
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
                                                        professorService.visualizarTodos();
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
                            } catch (Exception e){
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
                } else {
                    System.out.println("Por favor, insira um número válido.");
                    sc.next();
                }
            }
        } catch (Exception e){
            System.out.println(" ");
            System.out.println("Opcão inválida, digite somente números conforme opção do menu");
        }
                }

    }
}