package services;

import model.Destino;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicoDestinos {
    private List<Destino> listaDestinos = new ArrayList<>();
    private Scanner scanner;

    public ServicoDestinos() {
        scanner = new Scanner(System.in);
        inicializarDestinos();
    }

    private void inicializarDestinos() {
        listaDestinos.add(new Destino(1, "DF", "Brasília"));
        listaDestinos.add(new Destino(2, "SP", "São Paulo"));
        listaDestinos.add(new Destino(3, "RJ", "Rio de Janeiro"));
        listaDestinos.add(new Destino(4, "MG", "Belo Horizonte"));
        listaDestinos.add(new Destino(5, "RS", "Porto Alegre"));
    }

    public void cadastrarNovoDestino() {
        System.out.println("Destinos cadastrados:");
        for (Destino destino : listaDestinos) {
            System.out.println("Código: " + destino.getCodigo() + ", Estado: "+ destino.getSigla() + ", Cidade: " + destino.getCidade());
        }

        System.out.println("Cadastrar novo destino");

        // Gera automaticamente o código do destino
        int codigo = listaDestinos.size() + 1;

        System.out.println("Informe a sigla do estado:");
        String siglaCidade = scanner.next().trim();

        System.out.println("Informe a cidade de destino:");
        String nomeCidade = scanner.next().trim();

        // Cria e adiciona o novo destino à lista
        Destino novoDestino = new Destino(codigo, siglaCidade, nomeCidade);
        listaDestinos.add(novoDestino);

        System.out.println("Destino cadastrado com sucesso!");
    }

    public Destino selecionarDestino(String tipo) {
        if (listaDestinos.isEmpty()) {
            System.out.println("Não há destinos cadastrados.");
            return null;
        }

        listaDestinos.forEach(destino -> System.out.println("Código: " + destino.getCodigo() + ", Nome: " + destino.getSigla()));
        System.out.print("Escolha o destino de " + tipo + " pelo código: ");
        int codigo = scanner.nextInt();
        return listaDestinos.stream()
                .filter(destino -> destino.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }


    public List<Destino> getListaDestinos() {
        return listaDestinos;
    }


}