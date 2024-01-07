import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuNumerico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha;
        ModuloService listaModulos = new ModuloService();
        DesafioService listaDesafios = new DesafioService();
        AlunoService listaAluno = new AlunoService();
        AdminService listaAdmin = new AdminService();
        ProfessorService listaProfessor = new ProfessorService();
        ModuloService modulosConcluidos = new ModuloService();

        listaAluno.adicionarAluno(new Aluno("Lucas", LocalDate.parse("2018-07-01"), "lucas@email.com", new ArrayList<>()));

        String nome;
        String dataNascimento;
        String email;
        LocalDate data;
        int opcaoMenu;
        int indexModulo;

        while (true) {
            exibirMenuInicial();
            System.out.print("Escolha uma opção (0 para sair): ");

            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                sc.nextLine();

                switch (escolha) {
                    case 1:
                        //MENU ALUNO
                        System.out.println("Você escolheu a opção Aluno:");
                        System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoMenu) {
                            case 1:
                                System.out.println("Informe seu email: ");
                                email = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                Aluno alunoLogado = listaAluno.loginAluno(email, data);
                                if (alunoLogado == null) {
                                    System.out.println("Falha no login. Verifique suas credenciais.");
                                    break;
                                } else {
                                    System.out.println("Login bem-sucedido! Bem-vindo, " + alunoLogado.getNome() + ".");
                                    menuAluno();
                                    int escolhaInterna = sc.nextInt();
                                    sc.nextLine();

                                    switch (escolhaInterna) {
                                        case 1:
                                            System.out.println("Informe o novo email: ");
                                            String alunoASerEditado = sc.nextLine();
                                            Aluno aluno = listaAluno.consultarAlunoEmail(alunoASerEditado);
                                            System.out.println("Informe o novo nome: ");
                                            nome = sc.nextLine();
                                            System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                            dataNascimento = sc.nextLine();
                                            LocalDate dataNova = LocalDate.parse(dataNascimento);
                                            System.out.println("Informe o novo email: ");
                                            email = sc.nextLine();
                                            listaAluno.editarAluno(aluno, new Aluno(nome, dataNova, email));
                                            System.out.println("Aluno editado com sucesso");
                                            break;
                                        case 2:
                                            System.out.print("Informe o titulo do módulo que deseja estudar: ");
                                            listaModulos.visualizarTodos();
                                            Modulo moduloEscolhido = listaModulos.consultarModuloTitulo(sc.nextLine());
                                            System.out.println(moduloEscolhido.getConteudo());
                                            System.out.println("Parabéns pelo estudo! Agora já está pronto para os desafios!");

                                            break;
                                        case 3:
                                            System.out.print("Informe o titulo do módulo que deseja acessar os desafios: ");
                                            listaModulos.visualizarTodos();
                                            Modulo moduloEscolhidoParaDesafios = listaModulos.consultarModuloTitulo(sc.nextLine());
                                            DesafioService DesafiosDoModulo = new DesafioService(moduloEscolhidoParaDesafios.getDesafios());
                                            System.out.print("Informe o índice do desafio que deseja acessar: ");
                                            DesafiosDoModulo.visualizarDesafios();
                                            DesafiosDoModulo.consultarDesafio(sc.nextInt());
                                            System.out.println("Parabéns por concluir o desafio!");
                                            modulosConcluidos.adicionar(moduloEscolhidoParaDesafios);

                                            break;
                                        case 4:
                                            modulosConcluidos.visualizarTodos();
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                }

                            break;

                            case 2:
                                System.out.println("Informe o seu nome: ");
                                nome = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                System.out.println("Informe o seu email: ");
                                email = sc.nextLine();
                                Aluno alunoCadastrado = new Aluno(nome, data, email);
                                listaAluno.adicionarAluno(alunoCadastrado);
                                System.out.println("Aluno cadastrado com sucesso");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }

                    break;

                    case 2:
                        //MENU PROFESSOR
                        System.out.println("Você escolheu a opção Professor:");
                        System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoMenu) {

                            case 1:
                                System.out.println("Informe seu email: ");
                                email = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                Professor professorLogado = listaProfessor.loginProfessor(email, data);
                                if (professorLogado == null) {
                                    System.out.println("Falha no login. Verifique suas credenciais.");
                                    break;
                                } else {
                                    System.out.println("Login bem-sucedido! Bem-vindo, " + professorLogado.getNome() + ".");
                                    menuProfessor();
                                    int escolhaInterna = sc.nextInt();
                                    sc.nextLine();

                                    switch (escolhaInterna) {

                                        case 1:
                                            System.out.println("Informe o novo email: ");
                                            String professorASerEditado = sc.nextLine();
                                            Professor professor = listaProfessor.consultarProfessorEmail(professorASerEditado);
                                            System.out.println("Informe o novo nome: ");
                                            nome = sc.nextLine();
                                            System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                            dataNascimento = sc.nextLine();
                                            LocalDate dataNova = LocalDate.parse(dataNascimento);
                                            System.out.println("Informe o novo email: ");
                                            email = sc.nextLine();
                                            listaProfessor.editar(professor, new Professor(nome, dataNova, email));
                                            System.out.println("Professor editado com sucesso");
                                            break;

                                        case 2:
                                            System.out.println("Informe o título do novo Modulo: ");
                                            String tituloModulo = sc.nextLine();
                                            System.out.println("Informe o email do autor do novo Modulo: ");
                                            email = sc.nextLine();
                                            professor = listaProfessor.consultarProfessorEmail(email);
                                            System.out.println("Adicione o primeiro desafio deste módulo, insira o título do desafio: ");
                                            String tituloNovoDesafio = sc.nextLine();
                                            System.out.println("Digite o número equivalente ao método do desafio: 1 - QUIZ , 2 - JOGO");
                                            Integer tipoNovoDesafio = sc.nextInt();
                                            TipoDesafio tipo = TipoDesafio.ofTipo(tipoNovoDesafio);
                                            if (tipo == null) {
                                                throw new IllegalArgumentException("Opção de desafio inexistente");
                                            } else {
                                                Desafio desafioCriado = new Desafio(tituloNovoDesafio, tipo, professor);
                                                listaDesafios.adicionarDesafio(desafioCriado);
                                                System.out.println("Por fim, digite o número equivalente ao grau de dificuldade do Módulo: 1 - INICIANTE, 2 - INTERMEDIÁRIO, 3 - AVANÇADO");
                                                int classificao = sc.nextInt();
                                                if (classificao > 3 || classificao < 1) {
                                                    throw new IllegalArgumentException("Opção de módulo inexistente");
                                                } else {
                                                    ClassificacaoModulo dificuldadeSelecionada = ClassificacaoModulo.trazEnumPeloOrdinal(classificao);
                                                    DesafioService novaListaDesafio = new DesafioService();
                                                    novaListaDesafio.adicionarDesafio(desafioCriado);
                                                    listaModulos.adicionar(new Modulo(tituloModulo, professor, novaListaDesafio.getListaDesafios(), dificuldadeSelecionada));
                                                    listaDesafios.adicionarDesafio(desafioCriado);
                                                    System.out.println("Novo módulo cadastrado com sucesso");
                                                }
                                                break;
                                            }

                                        case 3:
                                            System.out.println("Informe o título do módulo que deseja modificar: ");
                                            String tituloModuloEdicao = sc.nextLine();
                                            Modulo moduloParaEditar = listaModulos.consultarModuloTitulo(tituloModuloEdicao);

                                            if (moduloParaEditar != null) {
                                                System.out.println("Módulo encontrado:");
                                                listaModulos.visualizarModulo(moduloParaEditar, 0);
                                                System.out.println("Informe o novo título do módulo: ");
                                                String novoTitulo = sc.nextLine();
                                                System.out.println("Informe a nova classificação (1 - Iniciante, 2 - Intermediário, 3 - Avançado): ");
                                                int novaClassificacao = sc.nextInt();
                                                sc.nextLine();

                                                ClassificacaoModulo classificacaoNova = ClassificacaoModulo.trazEnumPeloOrdinal(novaClassificacao);
                                                listaModulos.editar(0, new Modulo(novoTitulo, moduloParaEditar.getAutor(), moduloParaEditar.getDesafios(), classificacaoNova));
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
                                                System.out.println("Informe o email do professor autor deste desafio: ");
                                                email = sc.nextLine();
                                                professor = listaProfessor.consultarProfessorEmail(email);
                                                listaDesafios.adicionarDesafio(new Desafio(tituloNovoDesafio, tipo, professor));
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

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;
                                    }
                                }

                                break;

                            case 2:
                                System.out.println("Informe o seu nome: ");
                                nome = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                System.out.println("Informe o seu email: ");
                                email = sc.nextLine();
                                Professor professorCadastrado = new Professor(nome, data, email);
                                listaProfessor.adicionar(professorCadastrado);
                                System.out.println("Professor cadastrado com sucesso");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }

                        break;

                    case 3:
                        //MENU ADMIN
                        System.out.println("Você escolheu a opção Admin.");
                        System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                        switch (opcaoMenu) {

                            case 1:
                                System.out.println("Informe seu email: ");
                                email = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                Admin adminLogado = listaAdmin.loginAdmin(email, data);
                                if (adminLogado == null) {
                                    System.out.println("Falha no login. Verifique suas credenciais.");
                                    break;
                                } else {
                                    System.out.println("Login bem-sucedido! Bem-vindo, " + adminLogado.getNome() + ".");
                                    menuAdmin();
                                    int escolhaInterna = sc.nextInt();
                                    sc.nextLine();

                                    switch (escolhaInterna) {

                                        case 1:
                                            System.out.println("Informe o novo email: ");
                                            String AdminASerEditado = sc.nextLine();
                                            Admin admin = listaAdmin.consultarAdminEmail(AdminASerEditado);
                                            System.out.println("Informe o novo nome: ");
                                            nome = sc.nextLine();
                                            System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                            dataNascimento = sc.nextLine();
                                            LocalDate dataNova = LocalDate.parse(dataNascimento);
                                            System.out.println("Informe o novo email: ");
                                            email = sc.nextLine();
                                            listaAdmin.editar(admin, new Admin(nome, dataNova, email));
                                            System.out.println("Admin editado com sucesso");
                                            break;
                                        case 2:
                                            listaModulos.visualizarTodos();
                                            System.out.println("Digite o número do módulo a ser aprovado: ");
                                            indexModulo = sc.nextInt();
                                            listaModulos.adminAprovar(indexModulo);
                                            break;

                                        case 3:
                                            listaModulos.visualizarTodos();
                                            System.out.println("Digite o número do módulo a ser deletado: ");
                                            indexModulo = sc.nextInt();
                                            listaModulos.remover(indexModulo);
                                            System.out.println("Módulo deletado com sucesso!");
                                            break;

                                        case 0:
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;
                                    }
                                }

                                break;

                            case 2:
                                System.out.println("Informe o seu nome: ");
                                nome = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                dataNascimento = sc.nextLine();
                                data = LocalDate.parse(dataNascimento);
                                System.out.println("Informe o seu email: ");
                                email = sc.nextLine();
                                Admin adminCadastrado = new Admin(nome, data, email);
                                listaAdmin.adicionar(adminCadastrado);
                                System.out.println("Admin cadastrado com sucesso");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
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

    }

    private static void exibirMenuInicial() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 se você é um Aluno");
        System.out.println("2. Pressione 2 se você é um Professor");
        System.out.println("3. Pressione 3 se você é um Admin");
        System.out.println("0. Sair");
    }

    private static void menuAluno() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais"); //UPDATE
        System.out.println("2. Pressione 2 para acessar os módulos"); // READ
        System.out.println("3. Pressione 3 para acessar os desafios"); // READ
        System.out.println("4. Pressione 4 para acessar os módulos concluídos"); //READ
        System.out.println("0. Voltar");
    }

    private static void menuProfessor() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais"); //UPDATE
        System.out.println("2. Pressione 2 para criar um novo módulo"); // CREATE //passar por aprovação novamente
        System.out.println("3. Pressione 3 para modificar um módulo"); // UPDATE //passar por aprovação novamente
        System.out.println("4. Pressione 4 para criar um novo desafio"); // CREATE //passar por aprovação novamente
        System.out.println("5. Pressione 5 para modificar um desafio"); // UPDATE //passar por aprovação novamente
        System.out.println("0. Voltar");
    }

    private static void menuAdmin() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para editar seus dados cadastrais"); //UPDATE
        System.out.println("2. Pressione 2 para aprovar um módulo"); // CREATE
        System.out.println("3. Pressione 3 para deletar um módulo"); // DELETE
        System.out.println("0. Voltar");
    }
}
