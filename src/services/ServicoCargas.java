package services;

import model.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class ServicoCargas {
    private Queue<Frete> cargasPendentes;
    private ServicoClientes servicoClientes;
    private ServicoDestinos servicoDestinos;
    private List<TipoCarga> tiposDeCarga;
    private Scanner scanner;

    public ServicoCargas(ServicoClientes servicoClientes, ServicoDestinos servicoDestinos, List<TipoCarga> tiposDeCarga) {
        this.cargasPendentes = new LinkedList<>();
        this.servicoClientes = servicoClientes;
        this.servicoDestinos = servicoDestinos;
        this.tiposDeCarga = tiposDeCarga;
        this.scanner = new Scanner(System.in);
        inicializarTiposDeCarga();
        inicializarCargas();

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

    public void cadastrarNovoTipoDeCarga() {
        int numero = tiposDeCarga.size() + 1; // Número é atribuído automaticamente

        System.out.print("Informe a descrição do tipo de carga: ");
        String descricao = scanner.nextLine();

        System.out.print("Escolha o tipo de carga (1 - Perecível, 2 - Durável): ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        TipoCarga novoTipo;
        if (escolha == 1) {
            // Lógica para criar uma carga Perecível
            System.out.print("Informe a origem da carga perecível: ");
            String origem = scanner.nextLine();

            System.out.print("Informe o tempo máximo de validade (em dias): ");
            int tempoMaximoValidade = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("Informe a temperatura de armazenamento (em Celsius): ");
            double temperaturaArmazenamento = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            System.out.print("Requer refrigeração? (sim/nao): ");
            boolean requerRefrigeracao = scanner.nextLine().equalsIgnoreCase("sim");

            novoTipo = new TipoCarga.Perecivel(numero, descricao, origem, tempoMaximoValidade, temperaturaArmazenamento, requerRefrigeracao);
        } else {
            // Lógica para criar uma carga Durável
            System.out.print("Informe o setor da carga durável: ");
            String setor = scanner.nextLine();

            System.out.print("Informe o material principal: ");
            String materialPrincipal = scanner.nextLine();

            System.out.print("Informe a durabilidade esperada (em anos): ");
            int durabilidadeAnos = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("A carga é frágil? (sim/nao): ");
            boolean fragil = scanner.nextLine().equalsIgnoreCase("sim");

            novoTipo = new TipoCarga.Duravel(numero, descricao, setor, materialPrincipal, durabilidadeAnos, fragil);
        }

        tiposDeCarga.add(novoTipo);
        System.out.println("Tipo de carga cadastrado com sucesso!");
    }
    public List<TipoCarga> getTiposDeCarga() {
        return tiposDeCarga;
    }

    public void cadastrarNovaCarga() {
        System.out.println("Cadastro de nova carga:");

        if (tiposDeCarga.isEmpty()) {
            System.out.println("Não há tipos de carga cadastrados. Cadastre um tipo de carga primeiro.");
            return; // Ou redirecionar para o cadastro de tipos de carga
        }

        // Exibir tipos de carga e selecionar
        tiposDeCarga.forEach(tipo -> System.out.println("Número: " + tipo.getNumero() + ", Descrição: " + tipo.getDescricao()));
        System.out.print("Escolha um tipo de carga pelo número: ");
        int numeroTipoCarga = scanner.nextInt();
        TipoCarga tipoCarga = tiposDeCarga.stream()
                .filter(tipo -> tipo.getNumero() == numeroTipoCarga)
                .findFirst()
                .orElse(null);

        if (tipoCarga == null) {
            System.out.println("Tipo de carga não encontrado. Por favor, tente novamente.");
            return;
        }

        System.out.print("Informe o código da carga: ");
        int codigoCarga = scanner.nextInt();

        // Verifica se já existe uma carga com o mesmo código
        if (cargasPendentes.stream().anyMatch(c -> c.getCodigo() == codigoCarga)) {
            System.out.println("Erro: Já existe uma carga cadastrada com esse código.");
            return; // Interrompe a execução se já existir
        }

        System.out.print("Informe o peso da carga (em toneladas): ");
        int peso = scanner.nextInt();

        System.out.print("Informe o valor declarado da carga: ");
        double valorDeclarado = scanner.nextDouble();

        System.out.print("Informe o tempo máximo para o frete (em dias): ");
        int tempoMaximo = scanner.nextInt();

        Cliente cliente = servicoClientes.selecionarCliente();
        Destino origem = servicoDestinos.selecionarDestino("origem");
        Destino destino = servicoDestinos.selecionarDestino("destino");

        Frete novaFrete = new Frete(codigoCarga, peso, valorDeclarado, tempoMaximo, origem, destino, tipoCarga, cliente, "Pendente", null);

        cargasPendentes.offer(novaFrete);

        System.out.println("Carga cadastrada com sucesso e adicionada à fila de pendências.");

    }

    public void inicializarCargas() {
        // Exemplo de inicialização de 5 cargas diferentes
        if (!tiposDeCarga.isEmpty() && !servicoDestinos.getListaDestinos().isEmpty() && !servicoClientes.getListaClientes().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                TipoCarga tipoCarga = tiposDeCarga.get((i - 1) % tiposDeCarga.size());
                Cliente cliente = servicoClientes.getListaClientes().get((i - 1) % servicoClientes.getListaClientes().size());
                Destino origem = servicoDestinos.getListaDestinos().get(0); // assumindo que há pelo menos um destino
                Destino destino = servicoDestinos.getListaDestinos().get(1); // assumindo que há pelo menos dois destinos

                Frete novaCarga = new Frete(i, i * 1000, i * 100.0, i * 2, origem, destino, tipoCarga, cliente, "Pendente", null);
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
            for (Frete carga : cargasPendentes) {
                System.out.println("Código: " + carga.getCodigo() + " - Situação: " + carga.getSituacao());
            }
        }
    }

    public void alterarSituacaoCarga() {
        System.out.print("Informe o código da carga que deseja alterar a situação: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do teclado

        // Encontra a carga com o código especificado
        Frete carga = cargasPendentes.stream()
                .filter(c -> c.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (carga != null) {
            System.out.println("Carga encontrada: " + carga);
            System.out.print("Informe a nova situação da carga (Pendente, Locada, Cancelada, Finalizada): ");
            String novaSituacao = scanner.nextLine().trim();

            switch (novaSituacao.toLowerCase()) {
                case "pendente":
                    // Implementar lógica se necessário
                    carga.setSituacao("Pendente");
                    break;
                case "locada":
                    carga.definirCaminhao();
                    break;
                case "cancelada":
                    carga.cancelarCarga();
                    break;
                case "finalizada":
                    carga.cargaEntregue();
                    break;
                default:
                    System.out.println("Situacao inválida.");
                    return;
            }
            System.out.println("Situação da carga atualizada para: " + novaSituacao);
        } else {
            System.out.println("Carga não encontrada com o código fornecido.");
        }
    }

}
