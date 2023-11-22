package services;

import model.Caminhao;
import model.Frete;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ServicoCaminhoes {
    private List<Caminhao> listaCaminhoes;
    private Scanner scanner;

    public ServicoCaminhoes() {
        listaCaminhoes = new ArrayList<>();
        scanner = new Scanner(System.in);
        inicializarCaminhoes();
    }

    private void inicializarCaminhoes() {
        listaCaminhoes.add(new Caminhao("Volvo FH16", 80, 10, 2.5, "TRK-1"));
        listaCaminhoes.add(new Caminhao("Scania R Series", 85, 8, 2.8, "TRK-2"));
        listaCaminhoes.add(new Caminhao("Mercedes-Benz Actros", 75, 12, 2.0, "TRK-3"));
        listaCaminhoes.add(new Caminhao("MAN TGX", 90, 9, 3.0, "TRK-4"));
        listaCaminhoes.add(new Caminhao("DAF XF", 88, 11, 2.7, "TRK-5"));
    }

    public void cadastrarNovoCaminhao() {
        exibirCaminhoesCadastrados();
        System.out.println("Cadastro de novo caminhão:");

        Caminhao novoCaminhao = solicitarDadosCaminhao();
        if (novoCaminhao != null) {
            listaCaminhoes.add(novoCaminhao);
            listaCaminhoes.sort(Comparator.comparing(Caminhao::getNome));
            System.out.println("Caminhão cadastrado com sucesso!");
        }
    }

    private Caminhao solicitarDadosCaminhao() {
        System.out.print("Informe o nome do caminhão: ");
        String nome = scanner.next().trim();

        if (isNomeExistente(nome)) {
            System.out.println("Erro: Já existe um caminhão cadastrado com esse nome.");
            return null;
        }

        double velocidade = solicitarValor("Informe a velocidade média do caminhão (km/h): ");
        double autonomia = solicitarValor("Informe a autonomia do caminhão (km/l): ");
        double custoPorKm = solicitarValor("Informe o custo por Km rodado: ");

        int codCaminhao = listaCaminhoes.size() + 1;
        return new Caminhao(nome, velocidade, autonomia, custoPorKm, "TRK-" + codCaminhao);
    }

    private boolean isNomeExistente(String nome) {
        return listaCaminhoes.stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome));
    }

    private double solicitarValor(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Valor inválido. Por favor, tente novamente.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextDouble();
    }

    private void exibirCaminhoesCadastrados() {
        System.out.println("Caminhões cadastrados:");
        for (Caminhao caminhao : listaCaminhoes) {
            System.out.println("Nome: " + caminhao.getNome() + ", Velocidade: " + caminhao.getVelocidade() + " km/h, Autonomia: " + caminhao.getAutonomia() + " km/l, Custo por Km: " + caminhao.getCustoPorKm() + ", Código: " + caminhao.getCodigo());
        }
    }

    public boolean estaDisponivel(Caminhao caminhao) {
        // Lógica para verificar se o caminhão está disponível
        // Pode envolver verificar se ele está atribuído a alguma carga, por exemplo
        return caminhao.isDisponivel();
    }

    public boolean podeTransportar(Caminhao caminhao, Frete carga) {
        // verificar se o peso da carga é menor ou igual à capacidade máxima do caminhão
        return carga.getPeso() <= caminhao.getCapacidadeMaxima();
    }

    public List<Caminhao> getListaCaminhoes() {
        return listaCaminhoes;
    }
}

