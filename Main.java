import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static BinarySearchTree arvore = new BinarySearchTree();
    private static boolean discursoCarregado = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            while (!sc.hasNextInt()) {
                System.out.println("Digite uma opção válida!");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    carregarDiscurso();
                    break;
                case 2:
                    if (verificaCarregado())
                        System.out.println("Total de palavras: " + arvore.contarPalavras());
                    break;
                case 3:
                    if (verificaCarregado()) buscarPalavra(sc);
                    break;
                case 4:
                    if (verificaCarregado()) arvore.inOrder();
                    break;
                case 5:
                    if (verificaCarregado()) verificarSinaisDepressao();
                    break;
                case 6:
                    if (verificaCarregado()) estatisticas();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("\n1. Carregar discurso");
        System.out.println("2. Contador de palavras");
        System.out.println("3. Buscar palavra");
        System.out.println("4. Exibir as palavras do discurso em ordem alfabética");
        System.out.println("5. Verificar sinais de depressão");
        System.out.println("6. Estatísticas sobre o texto");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static boolean verificaCarregado() {
        if (!discursoCarregado) {
            System.out.println("Primeiro carregue o discurso (opção 1)!");
            return false;
        }
        return true;
    }

    private static void carregarDiscurso() {
        arvore = new BinarySearchTree();
        try (BufferedReader br = new BufferedReader(new FileReader("Discurso.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = limparTexto(linha);
                String[] palavras = linha.split(" ");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty())
                        arvore.inserir(palavra);
                }
            }
            discursoCarregado = true;
            System.out.println("Discurso carregado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao ler Discurso.txt: " + e.getMessage());
        }
    }

    private static String limparTexto(String texto) {
        texto = texto.toLowerCase();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{L} ]", ""); // Remove pontuação, mantém letras e espaço
        return texto;
    }

    private static void buscarPalavra(Scanner sc) {
        System.out.print("Digite a palavra a buscar: ");
        String palavra = limparTexto(sc.nextLine().trim());
        Palavra p = arvore.buscar(palavra);
        if (p != null) {
            System.out.println("A palavra '" + palavra + "' apareceu " + p.getOcorrencias() + " vez(es).");
        } else {
            System.out.println("Palavra não encontrada no discurso.");
        }
    }

    private static void verificarSinaisDepressao() {
        List<String> palavrasDepressao = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("PalavrasDepressao.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = limparTexto(linha.trim());
                if (!linha.isEmpty()) palavrasDepressao.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler PalavrasDepressao.txt: " + e.getMessage());
            return;
        }
        List<String> encontradas = new ArrayList<>();
        List<Integer> ocorrencias = new ArrayList<>();
        for (String palavra : palavrasDepressao) {
            Palavra p = arvore.buscar(palavra);
            if (p != null) {
                encontradas.add(palavra);
                ocorrencias.add(p.getOcorrencias());
            }
        }
        if (encontradas.size() > 0) {
            System.out.println("Sinais de alerta identificados: " + encontradas.size() + " palavra(s)");
            System.out.println("Palavras encontradas:");
            for (int i = 0; i < encontradas.size(); i++) {
                System.out.println("- " + encontradas.get(i) + " (" + ocorrencias.get(i) + ")");
            }
        } else {
            System.out.println("Nenhuma palavra de alerta encontrada no discurso.");
        }
    }

    private static void estatisticas() {
        System.out.println("Número de palavras distintas: " + arvore.contarDistintas());
        Palavra maisFreq = arvore.palavraMaisFrequente();
        if (maisFreq != null)
            System.out.println("Palavra mais frequente: '" + maisFreq.getPalavra() + "' (" + maisFreq.getOcorrencias() + ")");
        System.out.println("Quantidade de palavras que apareceram apenas uma vez: " + arvore.palavrasUnicas());
    }

    private static void sair() {
        System.out.println("\nIntegrantes: NOME1, NOME2, NOME3");
        System.out.println("Link do vídeo: https://youtu.be/seu_video");
        System.out.println("Encerrando aplicação...");
    }
}
