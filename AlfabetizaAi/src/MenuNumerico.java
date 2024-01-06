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

        listaAluno.adicionarAluno(new Aluno("Lucas", LocalDate.parse("2018-07-01"), "lucas@email.com", new ArrayList<>()));

        String nome;
        String dataNascimento;
        String email;
        LocalDate data;



        while (true) {
            exibirMenuInicial();
            System.out.print("Escolha uma opção (0 para sair): ");

            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                sc.nextLine();

                switch (escolha) {
                    case 1:
                        System.out.println("Você escolheu a opção Aluno:");
                        System.out.println("1 - Login / 2 - Cadastrar / 0 - Voltar");
                        int opcaoMenu = sc.nextInt();
                        sc.nextLine();

                            switch (opcaoMenu){
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
                                                System.out.println("Informe seu email: ");
                                                String alunoASerEditado = sc.nextLine();
                                                Aluno aluno = listaAluno.consultarAlunoEmail(alunoASerEditado);
                                                System.out.println("Informe o seu nome: ");
                                                nome = sc.nextLine();
                                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                                dataNascimento = sc.nextLine();
                                                LocalDate dataNova = LocalDate.parse(dataNascimento);
                                                System.out.println("Informe o seu email: ");
                                                email = sc.nextLine();
                                                listaAluno.editarAluno(aluno, new Aluno(nome, dataNova, email));
                                                System.out.println("Aluno editado com sucesso");
                                                break;
                                            case 2:

                                                break;
                                            case 3:
                                                break;
                                            case 4:
                                                break;
                                            default:
                                                System.out.println("Opção inválida. Tente novamente.");
                                                break;
                                        }
                                    }
                                case 2:
                                    System.out.println("Informe o seu nome: ");
                                    String nomeCadastro = sc.nextLine();
                                    System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                    String dataNascimentoCadastro = sc.nextLine();
                                    data = LocalDate.parse(dataNascimentoCadastro);
                                    System.out.println("Informe o seu email: ");
                                    email = sc.nextLine();
                                    Aluno alunoCadastrado = new Aluno(nomeCadastro, data, email);
                                    listaAluno.adicionarAluno(alunoCadastrado);
                                    System.out.println("Aluno cadastrado com sucesso");
                                    break;

                                case 0:
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                        }
                    case 2:
                        System.out.println("Você escolheu a opção Professor.");
                        // Adicione o código correspondente à opção 2 aqui
                        break;
                    case 3:
                        System.out.println("Você escolheu a opção 3.");
                        // Adicione o código correspondente à opção 3 aqui
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até logo!");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
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
        System.out.println("1. Pressione 1 para fazer o login");  // READ
        System.out.println("2. Pressione 2 para fazer o cadastro"); // CREATE
        System.out.println("3. Pressione 3 para editar seus dados cadastrais"); //UPDATE
        System.out.println("4. Pressione 4 para criar um novo módulo"); // CREATE //passar por aprovação novamente
        System.out.println("5. Pressione 5 para modificar um módulo"); // UPDATE //passar por aprovação novamente
        System.out.println("4. Pressione 6 para criar um novo desafio"); // CREATE //passar por aprovação novamente
        System.out.println("5. Pressione 7 para modificar um desafio"); // UPDATE //passar por aprovação novamente
        System.out.println("0. Voltar");


    }

    private static void menuAdmin() {
        System.out.println("\n---- Menu ----");
        System.out.println("1. Pressione 1 para fazer o login");  // READ
        System.out.println("2. Pressione 2 para fazer o cadastro"); // CREATE
        System.out.println("3. Pressione 3 para editar seus dados cadastrais"); //UPDATE
        System.out.println("4. Pressione 4 para aprovar um módulo"); // CREATE
        System.out.println("5. Pressione 5 para deletar um módulo"); // DELETE
        System.out.println("0. Voltar");

    }

}
