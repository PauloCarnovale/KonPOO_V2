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

    public void inicializarItinerarios() {
        List<Destino> destinos = servicoDestinos.getListaDestinos();
        if (destinos.size() >= 2) {
            int codigoItinerario1 = 1;
            Destino origem1 = destinos.get(0);
            Destino destino1 = destinos.get(1);
            double distancia1 = calcularDistancia(origem1, destino1);
            itinerarios.add(new Itinerario(codigoItinerario1, origem1, destino1, distancia1));

            if (destinos.size() >= 3) {
                int codigoItinerario2 = 2;
                Destino origem2 = destinos.get(1);
                Destino destino2 = destinos.get(2);
                double distancia2 = calcularDistancia(origem2, destino2);
                itinerarios.add(new Itinerario(codigoItinerario2, origem2, destino2, distancia2));
            }
        }
    }

    private double calcularDistancia(Destino origem, Destino destino) {
        return Math.random() * 1000; // Retorna um valor fictício para exemplo
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
        int codItinerario = itinerarios.size() + 1;

        // Caso não exista, cadastra um novo itinerário
        System.out.println("Informe a distância em km entre os destinos:");
        double distancia = scanner.nextDouble();
        Itinerario novoItinerario = new Itinerario(codItinerario, origem, destino, distancia);
        itinerarios.add(novoItinerario);
        System.out.println("Itinerário cadastrado com sucesso!");
    }

    public void imprimirItinerarios() {
        List<Itinerario> listaDeItinerarios = getItinerarios();
        if (listaDeItinerarios.isEmpty()) {
            System.out.println("Não há itinerários cadastrados.");
        } else {
            System.out.println("Lista de Itinerários:");
            for (Itinerario itinerario : listaDeItinerarios) {
                System.out.println(itinerario);
            }
        }
    }
    // Get para itinerarios
    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }
}
