package services;

import model.*;
import model.Caminhao;
import java.util.Queue;
import java.util.List;
import java.util.Scanner;


public class ServicoFretes {
    private Queue<Carga> cargasPendentes;
    private List<Caminhao> caminhoesDisponiveis;
    private ServicoItinerario servicoItinerario;
    private Scanner scanner = new Scanner(System.in);

    public ServicoFretes(Queue<Carga> cargasPendentes, List<Caminhao> caminhoesDisponiveis, ServicoItinerario servicoItinerario) {
        this.cargasPendentes = cargasPendentes;
        this.caminhoesDisponiveis = caminhoesDisponiveis;
        this.servicoItinerario = servicoItinerario;
    }

    public Queue<Carga> getCargasPendentes() {
        return cargasPendentes;
    }

    public void fretarCargas() {
        System.out.println("\n");
        if (cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas pendentes para serem fretadas.");
            return;
        }

        // Lista as cargas pendentes.
        for (Carga carga : cargasPendentes) {
            System.out.println("Carga: " + carga);
        }

        System.out.print("Digite o código da carga para fretar: ");
        int codigoCarga = scanner.nextInt();
        Carga cargaEscolhida = null;

        for (Carga carga : cargasPendentes) {
            if (carga.getCodigo() == codigoCarga) {
                cargaEscolhida = carga;
                break;
            }
        }

        if (cargaEscolhida == null || !cargaEscolhida.getSituacao().equalsIgnoreCase("Pendente")) {
            System.out.println("Carga inválida ou não está pendente.");
            return;
        }

        System.out.print("Escolha o código do itinerário: ");
        int codigoItinerario = scanner.nextInt();
        Itinerario itinerarioEscolhido = null;

        for (Itinerario itin : servicoItinerario.getItinerarios()) {
            if (itin.getCodigo() == codigoItinerario) {
                itinerarioEscolhido = itin;
                break;
            }
        }

        if (itinerarioEscolhido == null) {
            System.out.println("Itinerário inválido ou não encontrado.");
            return;
        }

        // Atribui um caminhão disponível.
        Caminhao caminhaoDisponivel = null;
        for (Caminhao caminhao : caminhoesDisponiveis) {
            if (caminhao.isDisponivel()) {
                caminhaoDisponivel = caminhao;
                break;
            }
        }

        if (caminhaoDisponivel == null) {
            System.out.println("Não há caminhões disponíveis.");
            cargaEscolhida.setSituacao("Cancelada");
            return;
        }

        // Calcula o valor do frete e apresentar ao usuário.
        double valorFrete = calcularValorFrete(
                itinerarioEscolhido.getDistancia(),
                cargaEscolhida.getPeso(),
                caminhaoDisponivel.getCustoPorKm(),
                cargaEscolhida.getTipoCarga()
        );

        System.out.printf(
                "Resumo do Frete:%n- Itinerário: %s para %s%n- Distância: %.2f km%n- Caminhão: %s (%s), Custo por Km: R$ %.2f%n- Carga: %s, Peso: %d toneladas, Valor declarado: R$ %.2f%n- Valor do frete: R$ %.2f Reais.%n",
                itinerarioEscolhido.getOrigem().getCidade(),
                itinerarioEscolhido.getDestino().getCidade(),
                itinerarioEscolhido.getDistancia(),
                caminhaoDisponivel.getNome(),
                caminhaoDisponivel.getCodigo(),
                caminhaoDisponivel.getCustoPorKm(),
                cargaEscolhida.getTipoCarga().getDescricao(),
                cargaEscolhida.getPeso(),
                cargaEscolhida.getValorDeclarado(),
                valorFrete
        );

        System.out.print("Deseja confirmar o frete? (sim/não): ");
        String confirmacao = scanner.next().trim();

        if (confirmacao.equalsIgnoreCase("sim")) {
            cargaEscolhida.setSituacao("Locada");
            cargaEscolhida.setCaminhaoDesignado(caminhaoDisponivel);
            System.out.println("Frete confirmado e caminhão designado: " + caminhaoDisponivel.getNome());
        } else {
            System.out.println("Frete não confirmado.");
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
    private double calcularValorFrete(double distancia, int peso, double custoPorKm, TipoCarga tipoCarga) {
        double precoPorDistancia = distancia * custoPorKm;
        double precoPorPeso = 100;
        return precoPorDistancia + precoPorPeso;
    }

}
