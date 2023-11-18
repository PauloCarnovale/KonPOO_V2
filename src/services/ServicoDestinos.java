package services;

import model.Destino;
import java.util.ArrayList;
import java.util.List;

public class ServicoDestinos {
    private List<Destino> listaDestinos = new ArrayList<>();

    public ServicoDestinos() {
        inicializarDestinos();
    }

    private void inicializarDestinos() {
        listaDestinos.add(new Destino(1, "DF", "Brasília"));
        listaDestinos.add(new Destino(2, "SP", "São Paulo"));
        listaDestinos.add(new Destino(3, "RJ", "Rio de Janeiro"));
        listaDestinos.add(new Destino(4, "MG", "Belo Horizonte"));
        listaDestinos.add(new Destino(5, "RS", "Porto Alegre"));
    }

    public List<Destino> getListaDestinos() {
        return listaDestinos;
    }
}