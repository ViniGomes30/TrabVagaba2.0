public class Node {
    Palavra palavra;
    Node esquerda;
    Node direita;

    public Node(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}
