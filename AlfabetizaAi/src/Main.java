import enums.ClassificacaoModulo;
import enums.TipoDesafio;
import models.*;
import services.*;
import utils.MenuNumerico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdminService listaAdmin = new AdminService();
        ModuloService listaModulos = new ModuloService();
        DesafioService listaDesafios = new DesafioService();
        ModuloService modulosConcluidos = new ModuloService();
        AlunoService listaAluno = new services.AlunoService();
        ProfessorService listaProfessor = new services.ProfessorService();


        listaProfessor.adicionar(new Professor("Bruno",LocalDate.parse("1980-10-10"),"bruno@email.com"));
        listaProfessor.adicionar(new Professor("Gabriel",LocalDate.parse("1982-06-10"), "gabriel@email.com"));
        listaProfessor.adicionar(new Professor("Vitoria",LocalDate.parse("1981-10-30"), "vitoria@email.teste"));

        String conteudoABC = "A - Abelha: A abelha faz \"zum zum\" e voa de flor em flor.\n" +
                "B - Borboleta: A borboleta é colorida e voa pelo jardim.\n" +
                "C - Cachorro: O cachorro é amigo e late \"au au\".\n" +
                "D - Dinossauro: O dinossauro é grande e faz \"roar\".\n" +
                "E - Elefante: O elefante tem uma tromba longa e faz \"trrr\".\n" +
                "F - Flor: A flor é bonita e cheira bem.\n" +
                "G - Girafa: A girafa tem um pescoço comprido e come folhas.\n" +
                "H - Helicóptero: O helicóptero voa no céu e faz \"vruum\".\n" +
                "I - Igual: As coisas são iguais quando são parecidas.\n" +
                "J - Jacaré: O jacaré mora na água e tem dentes afiados.\n" +
                "K - Kite (pipa): A pipa voa alto no vento.\n" +
                "L - Leão: O leão é corajoso e faz \"rugir\".\n" +
                "M - Macaco: O macaco é divertido e pula de galho em galho.\n" +
                "N - Nuvem: A nuvem é fofa e está no céu.\n" +
                "O - Ovelha: A ovelha tem lã macia e faz \"bêê\".\n" +
                "P - Pato: O pato nada na água e faz \"quá quá\".\n" +
                "Q - Queijo: O queijo é delicioso e amarelo.\n" +
                "R - Rato: O rato é pequeno e faz \"piu piu\".\n" +
                "S - Sol: O sol brilha no céu e nos dá luz.\n" +
                "T - Tartaruga: A tartaruga é lenta e tem uma casca dura.\n" +
                "U - Urso: O urso é peludo e vive na floresta.\n" +
                "V - Vaca: A vaca dá leite e faz \"moo\".\n" +
                "W - Waffle: O waffle é uma delícia para comer.\n" +
                "X - Xícara: A xícara é usada para tomar chá ou café.\n" +
                "Y - Yo-yo: O yo-yo vai para cima e para baixo.\n" +
                "Z - Zebra: A zebra tem listras pretas e brancas.";

        String conteudoVogais = "A - Abelhinha: A abelhinha adora voar alto e faz \"a-a-a\" como em \"abelha\".\n" +
                "E - Elefantinho: O elefantinho tem orelhas grandes e faz \"e-e-e\" como em \"elefante\".\n" +
                "I - Indiozinho: O indiozinho usa um penacho na cabeça e faz \"i-i-i\" como em \"índio\".\n" +
                "O - Ouriço: O ouriço é redondinho e faz \"o-o-o\" como em \"ouriço\".\n" +
                "U - Ursinho: O ursinho é fofo e faz \"u-u-u\" como em \"ursinho\".";

        Professor professor = new Professor("maria", LocalDate.parse("1987-12-18"), "maria@email");
        Desafio desafioUm = new Desafio("Desafio AEIOU", enums.TipoDesafio.JOGO, professor);
        Desafio desafioDois = new Desafio("ABC", TipoDesafio.QUIZ, professor);
        Desafio desafioTres = new Desafio("A", TipoDesafio.JOGO, professor);
        Modulo moduloDois = new Modulo("Letras Alfabeto",conteudoABC, professor , new ArrayList<>(), enums.ClassificacaoModulo.INICIANTE);
        Modulo moduloUm = new Modulo("Letras Vogais", conteudoVogais, professor , new ArrayList<>(), enums.ClassificacaoModulo.INICIANTE);
        moduloUm.adicionarDesafio(desafioUm);
        moduloUm.adicionarDesafio(desafioTres);
        moduloDois.adicionarDesafio(desafioDois);
        listaModulos.adicionar(moduloUm);
        listaModulos.adicionar(moduloDois);

        Scanner sc = new Scanner(System.in);
        int escolha;

        String nome;
        String dataNascimento;
        String email;
        LocalDate data;
        String tituloNovoDesafio;
        Integer tipoNovoDesafio;
        TipoDesafio tipo;
        String conteudo;
        int indexModulo;

        boolean continuarMenu = true;
        
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
                                try {
                                    //MENU ALUNO
                                    System.out.println("Você escolheu a opção Aluno:");
                                    System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                                    int opcaoMenuAluno = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcaoMenuAluno) {
                                        case 1:
                                            System.out.println("Informe seu email: ");
                                            email = sc.nextLine();
                                            System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                            dataNascimento = sc.nextLine();
                                            data = LocalDate.parse(dataNascimento);
                                            Aluno alunoLogado = listaAluno.loginAluno(email, data);
                                            boolean menuUsuarioLogado = true;

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
                                                            System.out.println("Informe o novo nome: ");
                                                            nome = sc.nextLine();
                                                            System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                                            dataNascimento = sc.nextLine();
                                                            LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                            System.out.println("Informe o novo email: ");
                                                            email = sc.nextLine();
                                                            listaAluno.editarAluno(alunoLogado, new Aluno(nome, dataNova, email));
                                                            System.out.println("Aluno editado com sucesso");
                                                            break;
                                                        case 2:
                                                            listaModulos.visualizarTodos();
                                                            System.out.print("Informe o titulo do módulo que deseja estudar: ");
                                                            Modulo moduloEscolhido = listaModulos.consultarModuloTitulo(sc.nextLine());
                                                            System.out.println(moduloEscolhido.getConteudo());
                                                            System.out.println("Parabéns pelo estudo! Agora já está pronto para os desafios!");
                                                            break;
                                                        case 3:
                                                            listaModulos.visualizarTodos();
                                                            System.out.print("Informe o titulo do módulo que deseja acessar os desafios: ");
                                                            Modulo moduloEscolhidoParaDesafios = listaModulos.consultarModuloTitulo(sc.nextLine());
                                                            DesafioService DesafiosDoModulo = new DesafioService(moduloEscolhidoParaDesafios.getDesafios());
                                                            DesafiosDoModulo.visualizarDesafios();
                                                            System.out.print("Informe o índice do desafio que deseja acessar: ");
                                                            int indiceDesafio = sc.nextInt();
                                                            DesafiosDoModulo.consultarDesafio(indiceDesafio - 1);
                                                            sc.nextLine();
                                                            System.out.println("Parabéns por concluir o desafio!");
                                                            modulosConcluidos.adicionar(moduloEscolhidoParaDesafios);
                                                            break;
                                                        case 4:
                                                            modulosConcluidos.visualizarTodos();
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
                                } catch (Exception e){
                                    System.out.println(" ");
                                    System.out.println("Opcão inválida, digite somente números conforme opção do menu");
                                } finally {
                                    escolha = 1;
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
                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        data = LocalDate.parse(dataNascimento);
                                        Professor professorLogado = listaProfessor.loginProfessor(email, data);
                                        boolean menuUsuarioLogado = true;

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
                                                        System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o novo email: ");
                                                        email = sc.nextLine();
                                                        listaProfessor.editar(professorLogado, new Professor(nome, dataNova, email));
                                                        System.out.println("Professor editado com sucesso");
                                                        break;

                                                    case 2:
                                                        System.out.println("Informe o título do novo Modulo: ");
                                                        String tituloModulo = sc.nextLine();
                                                        System.out.println("Informe o email do autor do novo Modulo: ");
                                                        email = sc.nextLine();
                                                        professor = listaProfessor.consultarProfessorEmail(email);
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
                                                                listaModulos.adicionar(new Modulo(tituloModulo, conteudo, professor, novaListaDesafio.getListaDesafios(), dificuldadeSelecionada));
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
                                                            System.out.println("Digite o novo conteudo do novo Modulo: ");
                                                            conteudo = sc.nextLine();
                                                            System.out.println("Informe a nova classificação (1 - Iniciante, 2 - Intermediário, 3 - Avançado): ");
                                                            int novaClassificacao = sc.nextInt();
                                                            sc.nextLine();

                                                            ClassificacaoModulo classificacaoNova = ClassificacaoModulo.trazEnumPeloOrdinal(novaClassificacao);
                                                            listaModulos.editar(0, new Modulo(novoTitulo, conteudo, moduloParaEditar.getAutor(), moduloParaEditar.getDesafios(), classificacaoNova));
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

                                                        Desafio desafioParaModificar  = null;
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
                            }catch (Exception e){
                                System.out.println(" ");
                                System.out.println("Opcão inválida, digite somente números conforme opção do menu");
                            } finally {
                                escolha = 2;
                            }
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
                                        System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                        dataNascimento = sc.nextLine();
                                        data = LocalDate.parse(dataNascimento);
                                        Admin adminLogado = listaAdmin.loginAdmin(email, data);
                                        boolean menuUsuarioLogado = true;

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
                                                        System.out.println("Informe o novo nome: ");
                                                        nome = sc.nextLine();
                                                        System.out.println("Informe a nova data de nascimento (yyyy-mm-dd): ");
                                                        dataNascimento = sc.nextLine();
                                                        LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                        System.out.println("Informe o novo email: ");
                                                        email = sc.nextLine();
                                                        listaAdmin.editar(adminLogado, new Admin(nome, dataNova, email));
                                                        System.out.println("Admin editado com sucesso");
                                                        break;
                                                    case 2:
                                                        listaModulos.visualizarTodos();
                                                        System.out.println("Digite o número do módulo a ser aprovado: ");
                                                        indexModulo = sc.nextInt();
                                                        sc.nextLine();
                                                        listaModulos.adminAprovar(indexModulo);
                                                        break;

                                                    case 3:
                                                        listaModulos.visualizarTodos();
                                                        System.out.println("Digite o número do módulo a ser deletado: ");
                                                        indexModulo = sc.nextInt();
                                                        sc.nextLine();
                                                        listaModulos.remover(indexModulo);
                                                        System.out.println("Módulo deletado com sucesso!");
                                                        break;

                                                    case 4:
                                                        listaAluno.visualizarTodosAlunos();
                                                        break;

                                                    case 5:
                                                        System.out.println("Digite o ID do aluno:");
                                                        int idAluno = sc.nextInt();
                                                        sc.nextLine();
                                                        listaAluno.consultar(idAluno);
                                                        break;

                                                    case 6:
                                                        listaProfessor.visualizarTodos();
                                                        break;

                                                    case 7:
                                                        System.out.println("Digite o ID do aluno:");
                                                        int idProfessor = sc.nextInt();
                                                        sc.nextLine();
                                                        listaProfessor.consultar(idProfessor);
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
                            } catch (Exception e){
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