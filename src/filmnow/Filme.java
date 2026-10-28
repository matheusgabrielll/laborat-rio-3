package filmnow;

import java.util.ArrayList;
import java.util.List;

public class Filme {

    private static final int MAX_LOCAIS = 5;

    private String nome;
    private String ano;
    private List<String> locais;

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
     * Adiciona um local onde o filme pode ser assistido.
     * @param local Local a ser adicionado.
     * @return true se adicionou, false se o filme já tem o máximo de locais.
     * @throws NullPointerException se o local for nulo.
     * @throws IllegalArgumentException se o local for vazio.
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
     * Remove um local do filme. O filme sempre mantém pelo menos um local.
     * @param local Local a ser removido.
     * @return true se removeu, false se o filme só tem um local ou não tem esse local.
     */
    public boolean removeLocal(String local){
        if (this.locais.size() == 1 || local == null){
            return false;
        }
        return this.locais.remove(local.trim());
    }

    public String getDetalhes(){
        return this.toString() + "\n" + String.join(", ", this.locais);
    }

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

    @Override
    public String toString(){
        if (this.ano.isEmpty()){
            return this.nome;
        }
        return this.nome + ", " + this.ano;
    }
}

