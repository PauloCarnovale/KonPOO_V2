package model;

/**
 * Classe que representa um caminhão no sistema.
 *
 * Atributos:
 *  - nome: String representando o nome do caminhão.
 *  - velocidade: double indicando a velocidade máxima do caminhão.
 *  - autonomia: double representando a autonomia do caminhão em quilômetros.
 *  - custoPorKm: double representando o custo por quilômetro percorrido.
 *  - codigo: String com o código identificador do caminhão.
 *  - disponivel: boolean indicando se o caminhão está disponível para uso.
 *  - capacidadeMaxima: int representando a capacidade máxima de carga do caminhão.
 *
 * Métodos:
 *  - Caminhao(nome, velocidade, autonomia, custoPorKm, codigo): Construtor para criar uma nova instância de Caminhao.
 *  - getNome(): Retorna o nome do caminhão.
 *  - setNome(nome): Atualiza o nome do caminhão.
 *  - getVelocidade(): Retorna a velocidade do caminhão.
 *  - setVelocidade(velocidade): Atualiza a velocidade do caminhão.
 *  - getAutonomia(): Retorna a autonomia do caminhão.
 *  - setAutonomia(autonomia): Atualiza a autonomia do caminhão.
 *  - getCustoPorKm(): Retorna o custo por quilômetro.
 *  - setCustoPorKm(custoPorKm): Atualiza o custo por quilômetro.
 *  - getCodigo(): Retorna o código do caminhão.
 *  - setCodigo(codigo): Atualiza o código do caminhão.
 *  - isDisponivel(): Verifica se o caminhão está disponível.
 *  - setDisponivel(disponivel): Define a disponibilidade do caminhão.
 *  - getCapacidadeMaxima(): Retorna a capacidade máxima de carga do caminhão.
 *  - setCapacidadeMaxima(capacidadeMaxima): Atualiza a capacidade máxima de carga do caminhão.
 *  - podeTransportar(carga): Verifica se o caminhão pode transportar a carga fornecida.
 *  - toString(): Retorna uma representação em String do objeto Caminhao, incluindo nome, velocidade, autonomia, custo por quilômetro e código.
 */

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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean podeTransportar(Frete carga) {
        return this.capacidadeMaxima >= carga.getPeso();
    }

    public boolean estaDisponivel() {
        return disponivel;
    }


    @Override
    public String toString() {
        return String.format("Caminhao [nome=%s, velocidade=%.2f, autonomia=%.2f, custoPorKm=%.2f, codigo=%s]",
                nome, velocidade, autonomia, custoPorKm, codigo);
    }
}
