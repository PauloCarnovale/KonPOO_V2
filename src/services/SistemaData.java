package services;

import model.*;

import java.util.List;

/**
 * Classe SistemaData serve como um contêiner para armazenar dados do sistema.
 * Ela mantém listas de caminhões, clientes e destinos que são carregadas
 * do arquivo de dados iniciais ou modificadas durante a execução do programa.
 *
 * Essa classe é utilizada principalmente para inicializar o sistema com dados
 * pré-definidos e para transferir esses dados entre diferentes componentes
 * do sistema de forma organizada.
 */

public class SistemaData {
    private List<Caminhao> caminhoes;
    private List<Cliente> clientes;
    private List<Destino> destinos;
    private List<TipoCarga> tiposDeCarga;

    /**
     * Construtor da classe SistemaData.
     *
     * @param caminhoes Lista de caminhões a serem adicionados ao sistema.
     * @param clientes  Lista de clientes a serem adicionados ao sistema.
     * @param destinos  Lista de destinos a serem adicionados ao sistema.
     */

    public SistemaData(List<Caminhao> caminhoes, List<Cliente> clientes, List<Destino> destinos) {
        this.caminhoes = caminhoes;
        this.clientes = clientes;
        this.destinos = destinos;
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

}
