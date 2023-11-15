package model;

public class Caminhao{
    String nome;
    double velocidade;
    double autonomia;
    double custoporKm;
    String codigo;

    public Caminhao(String nome, double velocidade, double autonomia, double custoporKm, String codigo){
        this.nome=nome;
        this.velocidade=velocidade;
        this.autonomia=autonomia;
        this.custoporKm=custoporKm;
        this.codigo=codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public double getCustoporKm() {
        return custoporKm;
    }

    public void setCustoporKm(double custoporKm) {
        this.custoporKm = custoporKm;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Caminhao [nome=" + nome + ", velocidade=" + velocidade + ", autonomia=" + autonomia + ", custoporKm="
                + custoporKm + ", codigo=" + codigo + "]";
    }

}
