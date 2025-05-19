import java.util.List;

public class BinarySearchTree {
    private Node raiz;

    public BinarySearchTree() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public Node root() {
        return raiz;
    }

    public boolean isLeaf(Node n) {
        return n != null && n.esquerda == null && n.direita == null;
    }

    public void preOrdem(Node no) {
        if (no == null) return;
        mostraNo(no);
        preOrdem(no.esquerda);
        preOrdem(no.direita);
    }

    public void inOrdem(Node no) {
        if (no == null) return;
        inOrdem(no.esquerda);
        mostraNo(no);
        inOrdem(no.direita);
    }

    public void posOrdem(Node no) {
        if (no == null) return;
        posOrdem(no.esquerda);
        posOrdem(no.direita);
        mostraNo(no);
    }

    public void mostraNo(Node no) {
        if (no != null && no.palavra != null) {
            System.out.println(no.palavra.getPalavra() + " (" + no.palavra.getOcorrencias() + ")");
        }
    }

    public void inserir(String palavra) {
        raiz = inserirRec(raiz, palavra, null);
    }

    private Node inserirRec(Node atual, String palavra, Node parent) {
        if (atual == null) {
            Node novo = new Node(new Palavra(palavra));
            // Adiciona parent se o Node tiver esse campo
            // novo.parent = parent;
            return novo;
        }
        int cmp = palavra.compareTo(atual.palavra.getPalavra());
        if (cmp < 0) {
            atual.esquerda = inserirRec(atual.esquerda, palavra, atual);
        } else if (cmp > 0) {
            atual.direita = inserirRec(atual.direita, palavra, atual);
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

    public Node maximo(Node x) {
        if (x == null) return null;
        while (x.direita != null) {
            x = x.direita;
        }
        return x;
    }

    public Node minimo(Node x) {
        if (x == null) return null;
        while (x.esquerda != null) {
            x = x.esquerda;
        }
        return x;
    }

    // Métodos de estatísticas e utilidades do projeto
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
