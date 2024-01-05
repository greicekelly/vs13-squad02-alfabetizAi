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

        while (true) {
            exibirMenuInicial();
            System.out.print("Escolha uma opção (0 para sair): ");

            if (sc.hasNextInt()) {
                escolha = sc.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.println("Você escolheu a opção 1.");

                        menuAluno();
                        int escolhaInterna = sc.nextInt();
                        sc.nextLine();

                        switch (escolhaInterna) {
                            case 1:
                                System.out.println("Informe seu email: ");
                                String email = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                String dataNascimento = sc.nextLine();
                                LocalDate data = LocalDate.parse(dataNascimento);
//                                Aluno alunoLogin = AlunoService.consultarAlunoEmail(email);

                                }
                                break;
                            case 2:
                                System.out.println("Informe o seu nome: ");
                                String nomeCadastro = sc.nextLine();
                                System.out.println("Informe sua data de nascimento: yyyy-mm-dd");
                                String dataNascimentoCadastro = sc.nextLine();
                                LocalDate data = LocalDate.parse(dataNascimentoCadastro);
                                System.out.println("Informe o seu email: ");
                                String email = sc.nextLine();
                                Aluno alunoCadastrado = new Aluno(nomeCadastro, data, email);
                                listaAluno.adicionarAluno(alunoCadastrado);
                                System.out.println("Aluno cadastrado com sucesso");
                                break;
                            case 3:
                                System.out.println("");
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }








                        break;
                    case 2:
                        System.out.println("Você escolheu a opção 2.");
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
        System.out.println("1. Pressione 1 para fazer o login");  // READ
        System.out.println("2. Pressione 2 para fazer o cadastro"); // CREATE
        System.out.println("3. Pressione 3 para editar seus dados cadastrais"); //UPDATE
        System.out.println("4. Pressione 4 para acessar os módulos"); // READ
        System.out.println("5. Pressione 5 para acessar os desafios"); // READ
        System.out.println("6. Pressione 6 para acessar os módulos concluídos"); //READ
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
