import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Grupo: Breno Torquato, Guilherme Meneghini, Gustavo de Queiros, Thiago Vinicius.

        /*
            Crie uma agenda de contatos onde podem ser registrados vários contatos e seus dados.
            Formato:
            #######################
            ########AGENDA#########
            #######################
            >>>>>>>Contatos<<<<<<<<
            ID | Nome        | Telefone    | E-mail
            1  | Alex Araujo | 11999999999 | alex@gmail.com
            2  | João Gomes  | 11999999999 | joaogomes@gmail.com
            >>>>>>Menu Contato<<<<<<<
            1 Add
            2 Detail
            3 Edit
            4 Remove
            5 out
            RN1 e RN2 IGNORADOS.
            RN1 irrelevante, RN2 pode ser implementado.

         */

        Scanner stdin = new Scanner(System.in);
        List<String> listaDeNomes = new ArrayList<>();
        List<Long> listaDeNumeros = new ArrayList<>();
        List<String> listaDeEmails = new ArrayList<>();
        int indice = -1;

        boolean menuOn = true;

        while (menuOn) {
            titulo();
            if (listaDeNomes.isEmpty()) {
                String red = "\u001B[31m";
                String reset = "\u001B[0m";
                System.out.println(red + "Empty." + reset);
                System.out.println();
            } else {
                for (int i = 0; i < listaDeNumeros.size(); i++) {
                    indice = i;
                    System.out.println(indice + 1 + "  | " + listaDeNomes.get(indice) + " | " + listaDeNumeros.get(indice) + " | " + listaDeEmails.get(indice));
                }
                System.out.println();
            }

            menu();
            try {
                int option = stdin.nextInt();
                stdin.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Informe o nome do contato: ");
                        listaDeNomes.add(stdin.nextLine());
                        boolean numeroValido = false;
                        while (numeroValido == false) {
                            System.out.println("Informe o numero do contato: ");
                            try {
                                listaDeNumeros.add(stdin.nextLong());
                                stdin.nextLine();
                                numeroValido = true;
                            } catch (InputMismatchException errou) {
                                System.out.println("Nosso planeta não utiliza letras em números de telefone.");
                                stdin.nextLine();
                            }
                        }
                        System.out.println("Informe o email do contato: ");
                        listaDeEmails.add(stdin.next());
                        break;

                    case 2:
                        indice = -1;
                        if (listaDeNomes.isEmpty()) {
                            System.err.println("Prezado, a lista está vazia.");
                            break;
                        }
                        System.out.println("Informe o nome do contato a ser detalhado: ");
                        String nameDetail = stdin.nextLine();
                        for (int i = 0; i < listaDeNomes.size(); i++) {
                            if (listaDeNomes.get(i).equalsIgnoreCase(nameDetail)) {
                                indice = i;
                            }
                        }
                        if (indice < 0) {
                            System.out.println(nameDetail + " não localizado.");
                            System.out.println("Prezado(a), erros de digitação não são ignorados.");
                            System.out.println();
                        } else {
                            System.out.println("ID | Nome        | Telefone    | E-mail");
                            System.out.println(indice + 1 + "  | " + listaDeNomes.get(indice) + " | " + listaDeNumeros.get(indice) + " | " + listaDeEmails.get(indice));
                            break;
                        }
                        break;

                    case 3:
                        indice = -1;
                        if (listaDeNomes.isEmpty()) {
                            System.err.println("Prezado, a lista está vazia.");
                            break;
                        }
                        System.out.println("Informe o nome do contato a ser editado: ");
                        String nameEdit = stdin.nextLine();
                        for (int i = 0; i < listaDeNomes.size(); i++) {
                            if (listaDeNomes.get(i).equalsIgnoreCase(nameEdit)) {
                                indice = i;
                            }
                        }
                        if (indice < 0) {
                            System.out.println("Nome não localizado na lista.");
                            System.out.println("Prezado(a), erros de digitação não são ignorados.");
                        } else {
                            System.out.println();
                            System.out.println("Digite o novo nome do contato: ");
                            String newName = stdin.nextLine();
                            listaDeNomes.set(indice, newName);
                            boolean newNumeroValido = false;
                            while (newNumeroValido == false) {
                                try {
                                    System.out.println("Digite o novo Telefone do contato: ");
                                    Long newTelefone = stdin.nextLong();
                                    stdin.nextLine();
                                    listaDeNumeros.set(indice, newTelefone);
                                    newNumeroValido = true;
                                } catch (InputMismatchException errei) {
                                    System.out.println("Nosso planeta não utiliza letras em números de telefone.");
                                    stdin.nextLine();
                                }
                            }
                            System.out.println("Digite o novo E-mail do contato: ");
                            String newEmail = stdin.next();
                            listaDeEmails.set(indice, newEmail);
                        }
                        break;
                    case 4:
                        indice = -1;
                        if (listaDeNomes.isEmpty()) {
                            System.err.println("Prezado, a lista está vazia.");
                            break;
                        }
                        System.out.println("Informe o nome do contato a ser removido: ");
                        String nameRemove = stdin.nextLine();
                        for (int i = 0; i < listaDeNomes.size(); i++) {
                            if (listaDeNomes.get(i).equalsIgnoreCase(nameRemove)) {
                                indice = i;
                            }
                        }
                        if (indice < 0) {
                            System.out.println("Contato não localizado na lista.");
                            System.out.println("Prezado(a), erros de digitação não são ignorados.");
                        } else {
                            listaDeNomes.remove(indice);
                            listaDeNumeros.remove(indice);
                            listaDeEmails.remove(indice);
                            System.out.println("Contato " + nameRemove + " removido com sucesso.");
                        }
                        break;
                    case 5:
                        menuOn = false;
                        System.out.println("Saindo do menu.");
                        break;
                    default:
                        System.err.println("Você é alfabetizado, digite um número coerente com o Menu.");
                        System.out.println();
                }
            } catch (Exception errei) {
                System.err.println("Você é alfabetizado, digite um número coerente com o Menu.");
                stdin.nextLine();
            }
        }
        stdin.close();
    }

    public static void menu() {
        System.out.println("Menu Lista de Contato:");
        System.out.println("1. Inserir Contato");
        System.out.println("2. Detalhar Contato");
        System.out.println("3. Editar Contato");
        System.out.println("4. Remover Contato");
        System.out.println("5. Sair do menu.");
    }

    public static void titulo() {
        System.out.println();
        System.out.println("#######################");
        System.out.println("########AGENDA#########");
        System.out.println("#######################");
        System.out.println();
        System.out.println(">>>>>>>Contatos<<<<<<<<");
        System.out.println("ID | Nome        | Telefone    | E-mail");
    }


}



