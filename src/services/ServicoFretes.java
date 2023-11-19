package services;

import model.Frete;
import model.Caminhao;
import java.util.Queue;
import java.util.List;

public class ServicoFretes {
    private Queue<Frete> cargasPendentes;
    private List<Caminhao> caminhoesDisponiveis;

    public ServicoFretes(Queue<Frete> cargasPendentes, List<Caminhao> caminhoesDisponiveis) {
        this.cargasPendentes = cargasPendentes;
        this.caminhoesDisponiveis = caminhoesDisponiveis;
    }

    public void fretarCargas() {
        if (cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas na fila de cargas pendentes.");
            return;
        }

        for (Frete carga : cargasPendentes) {
            Caminhao caminhaoDisponivel = encontrarCaminhaoDisponivel(carga);

            if (caminhaoDisponivel != null) {
                carga.setCaminhaoDesignado(caminhaoDisponivel);
                carga.setSituacao("Locada");
                System.out.println("Carga " + carga.getCodigo() + " locada para o caminhão " + caminhaoDisponivel.getNome());
            } else {
                carga.setSituacao("Cancelado");
                System.out.println("Carga " + carga.getCodigo() + " cancelada por falta de caminhões disponíveis.");
            }
        }
    }

    private Caminhao encontrarCaminhaoDisponivel(Frete carga) {
        for (Caminhao caminhao : caminhoesDisponiveis) {
            if (caminhao.podeTransportar(carga) && caminhao.estaDisponivel()) {
                return caminhao;
            }
        }
        return null;
    }

}
