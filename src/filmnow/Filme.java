package filmnow;

public class Filme {

    private String nome;
    private String ano;
    private String local;

    public Filme(String nome, String ano, String local){

        if (nome == null || ano == null || local == null){
            throw new NullPointerException("filme invalido");
        }

        if(nome.trim().isEmpty() || local.trim().isEmpty()){
            throw new IllegalArgumentException("filme invalido");
        }

        this.nome = nome.trim();
        this.ano = ano.trim();
        this.local = local.trim();
    }

    public String getNome(){
        return this.nome;
    }

    public String getAno(){
        return this.ano;
    }

    public String getLocal(){
        return this.local;
    }
    public String getDetalhes(){
        return this.toString() + "\n" + this.local;
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
        return this.nome + ", " + this.ano;
    }
}
