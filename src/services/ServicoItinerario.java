package services;

import model.Destino;
import model.Itinerario;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ServicoItinerario {
    private List<Itinerario> itinerarios;
    private ServicoDestinos servicoDestinos;
    private Scanner scanner;

    public ServicoItinerario(ServicoDestinos servicoDestinos) {
        this.itinerarios = new ArrayList<>();
        this.servicoDestinos = servicoDestinos;
        this.scanner = new Scanner(System.in);
    }

    public void gerenciarItinerarios() {
        System.out.println("Gerenciar Itinerários:");

        List<Destino> destinos = servicoDestinos.getListaDestinos();
        if (destinos.isEmpty() || destinos.size() < 2) {
            System.out.println("É necessário ter pelo menos dois destinos cadastrados para criar um itinerário.");
            return;
        }

        // Seleciona a origem e o destino
        System.out.println("Escolha o destino de origem:");
        Destino origem = servicoDestinos.selecionarDestino("origem");
        System.out.println("Escolha o destino de destino:");
        Destino destino = servicoDestinos.selecionarDestino("destino");

        // Procura se já existe itinerário cadastrado entre esses destinos
        for (Itinerario itinerario : itinerarios) {
            if (itinerario.getOrigem().equals(origem) && itinerario.getDestino().equals(destino)) {
                System.out.println("Distância atual: " + itinerario.getDistancia() + " km. Deseja atualizar? (S/N)");
                String resposta = scanner.next().trim();
                if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("Informe a nova distância em km:");
                    double novaDistancia = scanner.nextDouble();
                    itinerario.setDistancia(novaDistancia);
                    System.out.println("Distância atualizada com sucesso!");
                }
                return;
            }
        }

        // Gera um código único para o novo itinerário
        String codItinerario = "BR-" + (itinerarios.size() + 1);

        // Caso não exista, cadastra um novo itinerário
        System.out.println("Informe a distância em km entre os destinos:");
        double distancia = scanner.nextDouble();
        Itinerario novoItinerario = new Itinerario(codItinerario, origem, destino, distancia);
        itinerarios.add(novoItinerario);
        System.out.println("Itinerário cadastrado com sucesso!");
    }

    // Getter para itinerarios
    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }
}
