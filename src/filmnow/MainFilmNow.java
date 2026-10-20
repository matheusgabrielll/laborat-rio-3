/*
professora, no dia 15/10 recebi um email da senhora com um codigo "feito pela metade",
entao, como eu ja tinha oq foi pedido na primeria etapa do lab3, eu apenas terminei o
codigo da senhora e adiconei oq eu consideirei  que deveria ter sido feito, nesse pro-
cesso usei ia apenas para duvidas ocasionais e tbm para saber pq alguns erros estavam
acontecendo
*/
package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 *
 * @author Matheus Gabriel Barreto Santos
 *
 */
public class MainFilmNow {

    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, fn, scanner);
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário/a.
     * @return O comando escolhido.
     */

    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(E)Exibir HotList\n" +
                        "(H)Atribuir Hot\n" +
                        "(R)Remover Hot\n" +
                        "(S)Sair\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param fn  O sistema FilmNow que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "E":
                exibirHotList(fn);
                break;
            case "H":
                atribuirHot(fn, scanner);
                break;
            case "R":
                removerHot(fn, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Lê um número inteiro digitado pelo usuário.
     *
     * @param scanner Scanner para a leitura.
     * @param prompt  Texto exibido antes da leitura.
     * @return O número digitado, ou -1 se não for um número válido.
     */
    private static int lerInteiro(Scanner scanner, String prompt) {
        System.out.print(prompt);

        try {
            return Integer.parseInt(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Lê uma linha de texto digitada pelo usuário.
     *
     * @param scanner Scanner para a leitura.
     * @param prompt  Texto exibido antes da leitura.
     * @return A linha digitada.
     */
    private static String lerTexto(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Imprime lista de filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarFilmes(FilmNow fn) {
        System.out.println();
        Filme[] filmes = fn.getFilmes();

        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i] != null) {
                System.out.println(formataFilme(i + 1, filmes[i]));
            }
        }
    }

    /**
     * Imprime os detalhes de um dos filmes.
     *
     * @param fn      O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar qual filme.
     */
    private static void detalharFilme(FilmNow fn, Scanner scanner) {
        int posicao = lerInteiro(scanner, "\nFilme> ");

        try {
            System.out.println("\n" + fn.detalhaFilme(posicao));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime a HotList.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void exibirHotList(FilmNow fn) {
        System.out.println();
        Filme[] hot = fn.getHotList();

        for (int i = 0; i < hot.length; i++) {
            if (hot[i] != null) {
                System.out.println((i + 1) + " - " + hot[i]);
            }
        }
    }

    /**
     * Coloca um filme na HotList.
     *
     * @param fn      O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar filme e posição.
     */
    private static void atribuirHot(FilmNow fn, Scanner scanner) {
        int filme = lerInteiro(scanner, "\nFilme> ");
        int posicao = lerInteiro(scanner, "Posicao> ");

        System.out.println(fn.adicionaHot(filme, posicao));
    }

    /**
     * Remove um filme da HotList.
     *
     * @param fn      O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar a posição.
     */
    private static void removerHot(FilmNow fn, Scanner scanner) {
        int posicao = lerInteiro(scanner, "\nPosicao> ");
        String resultado = fn.removeHot(posicao);

        if (!resultado.isEmpty()) {
            System.out.println(resultado);
        }
    }

    /**
     * Formata um filme para impressão.
     *
     * @param posicao A posição do filme (que é exibida).
     * @param filme   O filme a ser impresso.
     * @return A String formatada.
     */
    private static String formataFilme(int posicao, Filme filme) {
        return posicao + " - " + filme;
    }

    /**
     * Cadastra um filme no sistema.
     *
     * @param fn      O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir informações do filme.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) {
        int posicao = lerInteiro(scanner, "\nPosição> ");
        String nome = lerTexto(scanner, "Nome> ");
        String ano = lerTexto(scanner, "Ano> ");
        String local = lerTexto(scanner, "Local> ");

        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê carga de filmes de um arquivo csv.
     *
     * @param arquivoFilmes O caminho para o arquivo.
     * @param fn            O sistema FilmNow a ser populado com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn)
            throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados = leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}