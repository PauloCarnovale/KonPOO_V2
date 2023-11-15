public class Itinerario {
    private Destino origem;
    private Destino destino;
    private double distancia;

    public Itinerario(Destino origem, Destino destino, double distancia) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
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
        return "Itinerario [origem=" + origem.getNome() + ", destino=" + destino.getNome() + ", distancia=" + distancia + " km]";
    }
}
