package model;

public class Destino {
    private int codigo;
    private String sigla;
    private String cidade;


    public Destino(int codigo, String sigla, String cidade) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    @Override
    public String toString() {
        return "Destino [codigo=" + codigo + ", nome=" + sigla + ", cidade=" + cidade + "]";
    }

}
