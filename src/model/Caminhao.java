package model;

public class Caminhao {
    private String nome;
    private double velocidade;
    private double autonomia;
    private double custoPorKm;
    private String codigo;
    private boolean disponivel;
    private int capacidadeMaxima;

    public Caminhao(String nome, double velocidade, double autonomia, double custoPorKm, String codigo) {
        setNome(nome);
        setVelocidade(velocidade);
        setAutonomia(autonomia);
        setCustoPorKm(custoPorKm);
        setCodigo(codigo);
    }

    // Método para verificar se o caminhão está disponível
    public boolean estaDisponivel() {
        return disponivel;
    }

    // Método para verificar se o caminhão pode transportar a carga
    public boolean podeTransportar(Frete carga) {
        // verificar se o peso da carga é menor ou igual à capacidade máxima do caminhão
        return carga.getPeso() <= capacidadeMaxima;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do caminhão não pode ser vazio.");
        }
        this.nome = nome;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        if (velocidade <= 0) {
            throw new IllegalArgumentException("Velocidade deve ser maior que zero.");
        }
        this.velocidade = velocidade;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        if (autonomia <= 0) {
            throw new IllegalArgumentException("Autonomia deve ser maior que zero.");
        }
        this.autonomia = autonomia;
    }

    public double getCustoPorKm() {
        return custoPorKm;
    }

    public void setCustoPorKm(double custoPorKm) {
        if (custoPorKm < 0) {
            throw new IllegalArgumentException("Custo por Km não pode ser negativo.");
        }
        this.custoPorKm = custoPorKm;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do caminhão não pode ser vazio.");
        }
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.format("Caminhao [nome=%s, velocidade=%.2f, autonomia=%.2f, custoPorKm=%.2f, codigo=%s]",
                nome, velocidade, autonomia, custoPorKm, codigo);
    }
}
