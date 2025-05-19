import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node raiz;

    public BinarySearchTree() {
        this.raiz = null;
    }

    public void inserir(String palavra) {
        raiz = inserirRec(raiz, palavra);
    }

    private Node inserirRec(Node atual, String palavra) {
        if (atual == null) {
            return new Node(new Palavra(palavra));
        }
        int cmp = palavra.compareTo(atual.palavra.getPalavra());
        if (cmp < 0) {
            atual.esquerda = inserirRec(atual.esquerda, palavra);
        } else if (cmp > 0) {
            atual.direita = inserirRec(atual.direita, palavra);
        } else {
            atual.palavra.incrementaOcorrencias();
        }
        return atual;
    }

    public Palavra buscar(String palavra) {
        Node n = buscarRec(raiz, palavra);
        return n == null ? null : n.palavra;
    }

    private Node buscarRec(Node atual, String palavra) {
        if (atual == null) return null;
        int cmp = palavra.compareTo(atual.palavra.getPalavra());
        if (cmp < 0) return buscarRec(atual.esquerda, palavra);
        else if (cmp > 0) return buscarRec(atual.direita, palavra);
        else return atual;
    }

    public void inOrder() {
        inOrderRec(raiz);
    }

    private void inOrderRec(Node atual) {
        if (atual != null) {
            inOrderRec(atual.esquerda);
            System.out.println(atual.palavra.getPalavra() + " (" + atual.palavra.getOcorrencias() + ")");
            inOrderRec(atual.direita);
        }
    }

    public int contarPalavras() {
        return contarPalavrasRec(raiz);
    }

    private int contarPalavrasRec(Node atual) {
        if (atual == null) return 0;
        return atual.palavra.getOcorrencias() + contarPalavrasRec(atual.esquerda) + contarPalavrasRec(atual.direita);
    }

    public int contarDistintas() {
        return contarDistintasRec(raiz);
    }

    private int contarDistintasRec(Node atual) {
        if (atual == null) return 0;
        return 1 + contarDistintasRec(atual.esquerda) + contarDistintasRec(atual.direita);
    }

    public Palavra palavraMaisFrequente() {
        return palavraMaisFrequenteRec(raiz, null);
    }

    private Palavra palavraMaisFrequenteRec(Node atual, Palavra maisFreq) {
        if (atual == null) return maisFreq;
        if (maisFreq == null || atual.palavra.getOcorrencias() > maisFreq.getOcorrencias()) {
            maisFreq = atual.palavra;
        }
        maisFreq = palavraMaisFrequenteRec(atual.esquerda, maisFreq);
        maisFreq = palavraMaisFrequenteRec(atual.direita, maisFreq);
        return maisFreq;
    }

    public int palavrasUnicas() {
        return palavrasUnicasRec(raiz);
    }

    private int palavrasUnicasRec(Node atual) {
        if (atual == null) return 0;
        int soma = 0;
        if (atual.palavra.getOcorrencias() == 1) soma = 1;
        return soma + palavrasUnicasRec(atual.esquerda) + palavrasUnicasRec(atual.direita);
    }

    public void palavrasEmLista(List<Palavra> lista) {
        palavrasEmListaRec(raiz, lista);
    }

    private void palavrasEmListaRec(Node atual, List<Palavra> lista) {
        if (atual != null) {
            palavrasEmListaRec(atual.esquerda, lista);
            lista.add(atual.palavra);
            palavrasEmListaRec(atual.direita, lista);
        }
    }
}
