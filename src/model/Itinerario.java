package model;

/**
 * Classe que representa um itinerário no sistema de transporte.
 *
 * Atributos:
 *  - codigoItinerario: String representando o código identificador do itinerário.
 *  - origem: Destino representando o ponto de origem do itinerário.
 *  - destino: Destino representando o ponto de destino do itinerário.
 *  - distancia: double indicando a distância entre a origem e o destino em quilômetros.
 *
 * Métodos:
 *  - Itinerario(codigoItinerario, origem, destino, distancia): Construtor para criar uma nova instância de Itinerario.
 *  - getCodigo(): Retorna o código identificador do itinerário.
 *  - getOrigem(): Retorna o ponto de origem do itinerário.
 *  - getDestino(): Retorna o ponto de destino do itinerário.
 *  - getDistancia(): Retorna a distância do itinerário.
 *  - setDistancia(distancia): Atualiza a distância do itinerário.
 *  - toString(): Retorna uma representação em String do objeto Itinerario, incluindo código, origem, destino e distância.
 */

public class Itinerario {
    private String codigoItinerario;
    private Destino origem;
    private Destino destino;
    private double distancia;

    public Itinerario(String codigoItinerario, Destino origem, Destino destino, double distancia) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.codigoItinerario = codigoItinerario;
    }
    public String getCodigo() {
        return codigoItinerario;
    }

    public Destino getOrigem() {
        return origem;
    }

    public Destino getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Itinerario [codigo=" + codigoItinerario + ", origem=" + origem.getSigla() + ", destino=" + destino.getSigla() + ", distancia=" + distancia + " km]";
    }
}
