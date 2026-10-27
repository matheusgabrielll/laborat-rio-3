package filmnow;

import java.nio.channels.FileLockInterruptionException;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author Matheus Gabriel Barreto Santos
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHO_HOT = 10;

    private Filme[] filmes;
    private Filme[] hotList;
    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.hotList = new Filme[TAMANHO_HOT];
        this.filmes = new Filme[TAMANHO];
    }

    /**
     * Acessa a lista de filmes mantida.
     * @return O array de filmes.
     */
    public Filme[] getFilmes() {
        return this.filmes.clone();
    }

    /**
     * Acessa os dados de um filme específico.
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. Null se não há filme na posição.
     */
    public Filme getFilme(int posicao) {
        if (posicao < 1 || posicao > TAMANHO || this.filmes[posicao-1] == null){
            throw new ArrayIndexOutOfBoundsException("posicao invalida");
        }
        return this.filmes[posicao-1];
    }

    public Filme[] getHotList() {
        return this.hotList.clone();
    }

    private boolean posicaoValida(int posicao){
        return posicao >= 1 && posicao <= TAMANHO;
    }

    private boolean posicaoHotValida(int posicao){
        return posicao >= 1 && posicao <= TAMANHO_HOT;
    }

    private boolean estaNaHotList(Filme filme){
        for (Filme h : this.hotList){
            if (filme.equals(h)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assitido.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (!posicaoValida(posicao)) {
            return "POSIÇÃO INVÁLIDA";
        }

        Filme novo;
        try {
            novo = new Filme(nome, ano, local);
        } catch (IllegalArgumentException | NullPointerException e) {
            return "FILME INVALIDO";
        }

        for (Filme f : this.filmes) {
            if (novo.equals(f)) {
                return "FILME JA ADICIONADO";
            }
        }
        this.filmes[posicao - 1] = novo;
        return "FILME ADICIONADO";
    }
    /**
     * Detalhes de um filme, com 🔥 se estiver na HotList.
     * @throws IllegalArgumentException se não tiver filme na pqsição.
     */
    public String detalhaFilme(int posicao){
        if (!posicaoValida(posicao) || this.filmes[posicao-1] == null){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
        }
        Filme f = this.filmes[posicao - 1];
        if (estaNaHotList(f)){
            return "🔥 " + f.getDetalhes();
        }
        return f.getDetalhes();
    }
    public String adicionaHot(int posicaoFilme, int posicaoHot){
        if(!posicaoValida(posicaoFilme) || this.filmes[posicaoFilme - 1] == null){
            return "POSIÇÃO INVÁLIDA";
        }
        if (!posicaoHotValida(posicaoHot)){
            return "POSIÇÃO INVÁLIDA";

        }
        Filme f = this.filmes[posicaoFilme - 1];
        if(estaNaHotList(f)) {
            return "FILME JA ESTA NA HOTLIST";

        }
        this.hotList[posicaoHot - 1] = f;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posicaoHot + "!";
    }

    public String removeHot(int posicaoHot){
        if (!posicaoHotValida(posicaoHot)){
            return "POSIÇÃO INVÁLIDA";
        }

        this.hotList[posicaoHot - 1] = null;
        return "";
    }

}
