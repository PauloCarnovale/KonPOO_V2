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
        //inicializarTiposDeCarga();
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
        System.out.println("\nCadastro de nova carga:");

        // Verifica se existem tipos de carga para cadastrar uma nova carga.
        if (this.tiposDeCarga.isEmpty()) {
            System.out.println("Não há tipos de carga cadastrados.");
            return;
        }

        // Solicita informações da carga.
        System.out.print("Informe o código da carga: ");
        int codigo = scanner.nextInt();
        System.out.print("Informe o peso da carga (em toneladas): ");
        int peso = scanner.nextInt();
        System.out.print("Informe o valor declarado da carga: ");
        double valor = scanner.nextDouble();
        System.out.print("Informe o tempo máximo para o frete (em dias): ");
        int tempoFrete = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner.

        // Seleciona o tipo de carga.
        System.out.print("Escolha o tipo de carga pelo número: ");
        tiposDeCarga.forEach(tipo -> System.out.println(tipo.getNumero() + " - " + tipo.getDescricao()));
        int escolhaTipo = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner.
        TipoCarga tipoCargaSelecionada = tiposDeCarga.get(escolhaTipo - 1);

        // Cria a carga com status "Pendente".
        Carga novaCarga = new Carga(codigo, peso, valor, tempoFrete, null, null, tipoCargaSelecionada, null, "Pendente", null);

        // Adiciona a carga na fila de cargas pendentes.
        cargasPendentes.add(novaCarga);
        System.out.println("Carga cadastrada com sucesso e status definido como 'Pendente'.");
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
            String nomeCaminhao = carga.getCaminhaoDesignado() != null ? carga.getCaminhaoDesignado().getNome() : "Não Atribuído";
            System.out.println("Caminhão Designado: " + nomeCaminhao);
            System.out.println("-----------------------------------");
        }
    }
    public Queue<Carga> getCargasPendentes() {
        return cargasPendentes;
    }
}
