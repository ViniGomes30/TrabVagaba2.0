public class Palavra {
    private String palavra;
    private int ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = 1;
    }

    public String getPalavra() {
        return palavra;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void incrementaOcorrencias() {
        this.ocorrencias++;
    }
}
