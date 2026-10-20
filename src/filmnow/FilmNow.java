package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author eliane
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHO_HOT = 10;

    private String[] filmes; //uma representacao simploria da lista de filmes
    private Filme[] hotList
    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.hotList = new Filme[TAMANHO_HOT];
        this.filmes = new String[TAMANHO];
    }

    /**
     * Acessa a lista de filmes mantida.
     * @return O array de filmes.
     */
    public String[] getFilmes() {
        return this.filmes.clone();
    }

    /**
     * Acessa os dados de um filme específico.
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. Null se não há filme na posição.
     */
    public String getFilme(int posicao) {
        if (posicao < 1 || posicao > TAMANHO || this.filmes[posicao-1] == null){
            throw new ArrayIndexOutOfBoundsException("posicao invalida");
        }
        return this.filmes[posicao-1];
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assitido.
     */
    public void cadastraFilme(int posicao, String nome, String ano, String local) {
        this.filmes[posicao] = nome;
    }

}