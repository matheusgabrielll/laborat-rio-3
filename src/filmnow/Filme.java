package filmnow;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um filme, com nome, ano e os locais onde dá para assistir.
 */
public class Filme {

    private static final int MAX_LOCAIS = 5;

    private String nome;
    private String ano;
    private List<String> locais;

    /**
     * Cria um filme com um local inicial.
     * Se algum parametro for nulo, lanca NullPointerException.
     * Se o nome ou o local forem vazios, lanca IllegalArgumentException.
     *
     * @param nome nome do filme
     * @param ano ano de lancamento (pode ser vazio)
     * @param local primeiro local onde dá para assistir
     */
    public Filme(String nome, String ano, String local){

        if (nome == null || ano == null || local == null){
            throw new NullPointerException("filme invalido");
        }

        if (nome.isBlank() || local.isBlank()){
            throw new IllegalArgumentException("filme invalido");
        }

        this.nome = nome.trim();
        this.ano = ano.trim();
        this.locais = new ArrayList<>();
        this.locais.add(local.trim());
    }

    public String getNome(){
        return this.nome;
    }

    public String getAno(){
        return this.ano;
    }

    public List<String> getLocais(){
        return new ArrayList<>(this.locais);
    }

    /**
     * Adiciona um local ao filme (no maximo 5).
     *
     * @param local local a ser adicionado
     * @return true se adicionou, false se o filme ja tem 5 locais
     */
    public boolean adicionaLocal(String local){
        if (local == null){
            throw new NullPointerException("local invalido");
        }
        if (local.isBlank()){
            throw new IllegalArgumentException("local invalido");
        }
        if (this.locais.size() >= MAX_LOCAIS){
            return false;
        }
        this.locais.add(local.trim());
        return true;
    }

    /**
     * Remove um local do filme. O filme nunca fica sem local.
     *
     * @param local local a ser removido
     * @return true se removeu, false se so tem um local ou se o local nao existe
     */
    public boolean removeLocal(String local){
        if (this.locais.size() == 1 || local == null){
            return false;
        }
        return this.locais.remove(local.trim());
    }

    /**
     * Devolve o nome e ano numa linha e os locais na outra, separados por virgula.
     *
     * @return os detalhes do filme
     */
    public String getDetalhes(){
        return this.toString() + "\n" + String.join(", ", this.locais);
    }

    /**
     * Dois filmes sao iguais se tem o mesmo nome e o mesmo ano.
     */
    @Override
    public boolean equals(Object o){

        if (this == o){
            return true;
        }
        if (!(o instanceof Filme)){
            return false;
        }
        Filme outro = (Filme) o;
        return this.nome.equals(outro.nome) && this.ano.equals(outro.ano);
    }

    // usei ia aqui, pois, ate entao, eu ainda nao tinha usado o hash code com mais de um parametro
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.nome, this.ano);
    }

    /**
     * Mostra "nome, ano". Se nao tiver ano, mostra so o nome.
     */
    @Override
    public String toString(){
        if (this.ano.isEmpty()){
            return this.nome;
        }
        return this.nome + ", " + this.ano;
    }
}