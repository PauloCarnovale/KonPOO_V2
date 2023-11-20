package services;

import model.*;

import java.util.List;

public class SistemaData {
    private List<Caminhao> caminhoes;
    private List<Cliente> clientes;
    private List<Destino> destinos;
    private List<TipoCarga> tiposDeCarga;

    // Atualize o construtor para remover a lista de Itinerarios se não estiver sendo usada.
    public SistemaData(List<Caminhao> caminhoes, List<Cliente> clientes, List<Destino> destinos, List<TipoCarga> tiposDeCarga) {
        this.caminhoes = caminhoes;
        this.clientes = clientes;
        this.destinos = destinos;
        this.tiposDeCarga = tiposDeCarga;
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Destino> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Destino> destinos) {
        this.destinos = destinos;
    }


    public List<TipoCarga> getTiposDeCarga() {
        return tiposDeCarga;
    }

    public void setTiposDeCarga(List<TipoCarga> tiposDeCarga) {
        this.tiposDeCarga = tiposDeCarga;
    }
}
