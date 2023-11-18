package model;

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
