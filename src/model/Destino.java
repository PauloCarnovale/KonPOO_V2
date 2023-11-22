package model;

/**
 * Classe que representa um destino no sistema.
 *
 * Atributos:
 *  - codigo: int representando o código do destino.
 *  - sigla: String com a sigla do destino.
 *  - cidade: String com o nome da cidade do destino.
 *
 * Métodos:
 *  - Destino(codigo, sigla, cidade): Construtor para criar uma nova instância de Destino com os valores fornecidos.
 *  - getCodigo(): Retorna o código do destino.
 *  - setCodigo(codigo): Atualiza o código do destino.
 *  - getSigla(): Retorna a sigla do destino.
 *  - setSigla(sigla): Atualiza a sigla do destino.
 *  - getCidade(): Retorna o nome da cidade do destino.
 *  - setCidade(cidade): Atualiza o nome da cidade do destino.
 *  - toString(): Retorna uma representação em String do objeto Destino, incluindo código, sigla e cidade.
 */

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
