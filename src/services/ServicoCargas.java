package services;

import model.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class ServicoCargas {
    private Queue<Carga> cargasPendentes;
    private ServicoClientes servicoClientes;
    private ServicoDestinos servicoDestinos;
    private List<TipoCarga> tiposDeCarga;
    private ServicoCaminhoes servicoCaminhoes;
    private Scanner scanner;

    public ServicoCargas(ServicoClientes servicoClientes, ServicoDestinos servicoDestinos, List<TipoCarga> tiposDeCarga, ServicoCaminhoes servicoCaminhoes) {
        this.cargasPendentes = new LinkedList<>();
        this.servicoClientes = servicoClientes;
        this.servicoDestinos = servicoDestinos;
        this.tiposDeCarga = tiposDeCarga;
        this.servicoCaminhoes = servicoCaminhoes;
        this.scanner = new Scanner(System.in);
        inicializarTiposDeCarga();
        //inicializarCargas();

    }

    private void inicializarTiposDeCarga() {
        TipoCarga.Perecivel perecivel = new TipoCarga.Perecivel(1, "Carga Perecível", "Brasil", 5, 4.0, true);
        tiposDeCarga.add(perecivel);

        TipoCarga.Duravel duravel = new TipoCarga.Duravel(2, "Carga Durável", "Eletrônicos", "Metal", 10, false);
        tiposDeCarga.add(duravel);

        TipoCarga.Perecivel farmaceuticos = new TipoCarga.Perecivel(3, "Produtos Farmacêuticos", "Índia", 15, 2.5, true);
        tiposDeCarga.add(farmaceuticos);

        TipoCarga.Duravel moveis = new TipoCarga.Duravel(4, "Móveis", "Decoração", "Madeira", 20, false);
        tiposDeCarga.add(moveis);

        TipoCarga.Perecivel congelados = new TipoCarga.Perecivel(5, "Produtos Congelados", "Noruega", 30, -18.0, true);
        tiposDeCarga.add(congelados);
    }

    public void cadastrarNovaCarga() {
        System.out.println("Cadastro de nova carga:");

        // Verificar se há tipos de carga cadastrados.
        if (tiposDeCarga.isEmpty()) {
            System.out.println("Não há tipos de carga cadastrados. Cadastre um tipo de carga primeiro.");
            return;
        }

        // Selecionar o tipo de carga.
        tiposDeCarga.forEach(tipo -> System.out.println("Número: " + tipo.getNumero() + ", Descrição: " + tipo.getDescricao()));
        System.out.print("Escolha um tipo de carga pelo número: ");
        int numeroTipoCarga = scanner.nextInt();
        TipoCarga tipoCargaSelecionada = tiposDeCarga.stream()
                .filter(tipo -> tipo.getNumero() == numeroTipoCarga)
                .findFirst()
                .orElse(null);

        if (tipoCargaSelecionada == null) {
            System.out.println("Tipo de carga não encontrado. Por favor, tente novamente.");
            return;
        }

        // Coletar dados da carga.
        System.out.print("Informe o código da carga: ");
        int codigoCarga = scanner.nextInt();
        System.out.print("Informe o peso da carga (em toneladas): ");
        int pesoCarga = scanner.nextInt();
        System.out.print("Informe o valor declarado da carga: ");
        double valorDeclarado = scanner.nextDouble();
        System.out.print("Informe o tempo máximo para o frete (em dias): ");
        int tempoMaximo = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner após ler um número.

        // Selecionar cliente.
        Cliente cliente = servicoClientes.selecionarCliente();
        // Selecionar destinos de origem e destino.
        Destino origem = servicoDestinos.selecionarDestino("origem");
        Destino destino = servicoDestinos.selecionarDestino("destino");

        // Encontrar um caminhão disponível.
        Caminhao caminhaoDisponivel = servicoCaminhoes.encontrarCaminhaoDisponivel();
        if (caminhaoDisponivel == null) {
            System.out.println("Não há caminhões disponíveis no momento. A carga não pode ser cadastrada.");
            return;
        }

        // Criar a nova carga.
        Carga novaCarga = new Carga(codigoCarga, pesoCarga, valorDeclarado, tempoMaximo, origem, destino, tipoCargaSelecionada, cliente, "Pendente", caminhaoDisponivel);

        // Definir o caminhão e alterar a situação da carga para 'Locada'.
        novaCarga.definirCaminhao(caminhaoDisponivel);

        // Adicionar a nova carga à lista de cargas pendentes.
        cargasPendentes.offer(novaCarga);

        System.out.println("Carga cadastrada com sucesso e adicionada à fila de pendências. Caminhão designado: " + caminhaoDisponivel.getNome());
    }


    public void inicializarCargas() {
        // Exemplo de inicialização de 5 cargas diferentes
        if (!tiposDeCarga.isEmpty() && !servicoDestinos.getListaDestinos().isEmpty() && !servicoClientes.getListaClientes().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                TipoCarga tipoCarga = tiposDeCarga.get((i - 1) % tiposDeCarga.size());
                Cliente cliente = servicoClientes.getListaClientes().get((i - 1) % servicoClientes.getListaClientes().size());
                Destino origem = servicoDestinos.getListaDestinos().get(0); // assumindo que há pelo menos um destino
                Destino destino = servicoDestinos.getListaDestinos().get(1); // assumindo que há pelo menos dois destinos

                Carga novaCarga = new Carga(i, i * 1000, i * 100.0, i * 2, origem, destino, tipoCarga, cliente, "Pendente", null);
                cargasPendentes.offer(novaCarga);
            }
        } else {
            System.out.println("Não é possível inicializar cargas sem tipos de carga, destinos e clientes pré-definidos.");
        }
    }

    public void exibirCodigoESituacaoDasCargas() {
        if (cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas cadastradas.");
        } else {
            System.out.println("Código e Situação das cargas cadastradas:");
            for (Carga carga : cargasPendentes) {
                System.out.println("Código: " + carga.getCodigo() + " - Situação: " + carga.getSituacao());
            }
        }
    }

    public void alterarSituacaoCarga() {
        System.out.print("Informe o código da carga que deseja alterar a situação: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do teclado

        // Encontra a carga com o código especificado
        Carga carga = cargasPendentes.stream()
                .filter(c -> c.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (carga != null) {
            System.out.println("Carga encontrada: " + carga);
            System.out.print("Informe a nova situação da carga (Pendente, Locada, Cancelada, Finalizada): ");
            String novaSituacao = scanner.nextLine().trim();

            switch (novaSituacao.toLowerCase()) {
                case "pendente":
                    carga.setSituacao("Pendente");
                    break;
                case "locada":
                    // Precisamos encontrar um caminhão disponível antes de definir a carga como locada
                    Caminhao caminhaoDisponivel = servicoCaminhoes.encontrarCaminhaoDisponivel();
                    if (caminhaoDisponivel != null) {
                        carga.definirCaminhao(caminhaoDisponivel);
                        System.out.println("Caminhão " + caminhaoDisponivel.getNome() + " foi designado para a carga.");
                    } else {
                        System.out.println("Não há caminhões disponíveis para locar a carga.");
                    }
                    break;
                case "cancelada":
                    carga.cancelarCarga();
                    break;
                case "finalizada":
                    carga.cargaEntregue();
                    // Aqui, você também deve liberar o caminhão se for o caso
                    if (carga.getCaminhaoDesignado() != null) {
                        carga.getCaminhaoDesignado().setDisponivel(true);
                    }
                    break;
                default:
                    System.out.println("Situação inválida.");
                    return;
            }
            System.out.println("Situação da carga atualizada para: " + novaSituacao);
        } else {
            System.out.println("Carga não encontrada com o código fornecido.");
        }
    }


    public void consultarCargas() {
        if (cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas pendentes cadastradas.");
            return;
        }

        System.out.println("Lista de Cargas Pendentes:");
        for (Carga carga : cargasPendentes) {
            System.out.println("Código da Carga: " + carga.getCodigo());
            System.out.println("Cliente: " + (carga.getCliente() != null ? carga.getCliente().getNome() : "Cliente não atribuído"));
            System.out.println("Peso: " + carga.getPeso() + " toneladas");
            System.out.println("Valor Declarado: R$ " + carga.getValorDeclarado());
            System.out.println("Tempo Máximo para o Frete: " + carga.getTempoMaximo() + " dias");
            System.out.println("Tipo de Carga: " + (carga.getTipoCarga() != null ? carga.getTipoCarga().getDescricao() : "Tipo de carga não atribuído"));
            System.out.println("Origem: " + (carga.getOrigem() != null ? carga.getOrigem().getSigla() : "Origem não atribuída"));
            System.out.println("Destino: " + (carga.getDestino() != null ? carga.getDestino().getSigla() : "Destino não atribuído"));
            String nomeCaminhao = carga.getCaminhaoDesignado() != null ? carga.getCaminhaoDesignado().getNome() : "Não Atribuído";
            System.out.println("Caminhão Designado: " + nomeCaminhao);
            System.out.println("-----------------------------------");
        }
    }
    public Queue<Carga> getCargasPendentes() {
        return cargasPendentes;
    }
}
