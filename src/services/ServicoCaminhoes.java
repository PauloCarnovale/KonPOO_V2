package services;

import model.Caminhao;
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
        System.out.println("Caminhões cadastrados:");
        for (Caminhao caminhao : listaCaminhoes) {
            System.out.println("Nome: " + caminhao.getNome() + ", Velocidade: " + caminhao.getVelocidade() + " km/h, Autonomia: " + caminhao.getAutonomia() + " km/l, Custo por Km: " + caminhao.getCustoPorKm() + ", Código: " + caminhao.getCodigo());
        }
        
        System.out.println("Cadastro de novo caminhão:");

        // Solicita os dados do caminhão ao usuário
        System.out.print("Informe o nome do caminhão: ");
        String nome = scanner.next().trim();

        // Verifica se já existe um caminhão com o mesmo nome
        for (Caminhao c : listaCaminhoes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Erro: Já existe um caminhão cadastrado com esse nome.");
                return; // Interrompe a execução se já existir
            }
        }

        // Continua o cadastro se o nome não existir
        System.out.print("Informe a velocidade média do caminhão (km/h): ");
        double velocidade = scanner.nextDouble();

        System.out.print("Informe a autonomia do caminhão (km/l): ");
        double autonomia = scanner.nextDouble();

        System.out.print("Informe o custo por Km rodado: ");
        double custoPorKm = scanner.nextDouble();

        // Gera um código único para o novo caminhão
        int codCaminhao = listaCaminhoes.size() + 1;

        // Cria o novo caminhão e adiciona à lista
        Caminhao novoCaminhao = new Caminhao(nome, velocidade, autonomia, custoPorKm, "TRK-"+codCaminhao);
        listaCaminhoes.add(novoCaminhao);

        // Ordena a lista pelo nome do caminhão
        listaCaminhoes.sort(Comparator.comparing(Caminhao::getNome));

        System.out.println("Caminhão cadastrado com sucesso!");
    }
    public List<Caminhao> getListaCaminhoes() {
        return listaCaminhoes;
    }
}

