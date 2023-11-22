package services;

import model.Carga;
import model.Caminhao;
import java.util.Queue;
import java.util.List;
import java.util.Iterator;

public class ServicoFretes {
    private Queue<Carga> cargasPendentes;
    private List<Caminhao> caminhoesDisponiveis;

    public ServicoFretes(Queue<Carga> cargasPendentes, List<Caminhao> caminhoesDisponiveis) {
        this.cargasPendentes = cargasPendentes;
        this.caminhoesDisponiveis = caminhoesDisponiveis;
    }

    public Queue<Carga> getCargasPendentes() {
        return cargasPendentes;
    }

    public void fretarCargas() {
        if (cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas na fila de cargas pendentes.");
            return;
        }

        Iterator<Carga> iterator = cargasPendentes.iterator();
        while (iterator.hasNext()) {
            Carga carga = iterator.next();
            Caminhao caminhaoDisponivel = encontrarCaminhaoDisponivel();

            if (caminhaoDisponivel != null) {
                carga.setCaminhaoDesignado(caminhaoDisponivel);
                carga.setSituacao("Locada");
                caminhaoDisponivel.setDisponivel(false); // Marcar o caminhão como não disponível
                System.out.println("Carga " + carga.getCodigo() + " locada para o caminhão " + caminhaoDisponivel.getNome());
                iterator.remove(); // Remove a carga da fila
            } else {
                carga.setSituacao("Cancelado");
                System.out.println("Carga " + carga.getCodigo() + " cancelada por falta de caminhões disponíveis.");
                iterator.remove(); // Remove a carga da fila
            }
        }
    }

    private Caminhao encontrarCaminhaoDisponivel() {
        for (Caminhao caminhao : caminhoesDisponiveis) {
            if (caminhao.estaDisponivel()) {
                return caminhao;
            }
        }
        return null;
    }

}
