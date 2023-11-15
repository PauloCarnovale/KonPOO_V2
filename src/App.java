import java.util.*;

import Services.ServicoCaminhoes;
import Services.ServicoClientes;
import Services.ServicoMenu;

public class App {

    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Caminhao> listaCaminhoes = new ArrayList<>();
    private List<TipoCarga> tiposDeCarga = new ArrayList<>();
    private List<Destino> destinos = new ArrayList<>();
    private Queue<Carga> cargasPendentes;
    private List<Itinerario> itinerarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    ServicoCaminhoes caminhoes = new ServicoCaminhoes();
    ServicoClientes clientes = new ServicoClientes();
    ServicoMenu menu = new ServicoMenu();

    public void inicializarSistema() {
        caminhoes.inicializarCaminhoes();
        clientes.inicializarClientes();
    }

    public void executarSistema(){
            int opcao;
            do {
                menu.exibirMenu();
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        cadastrarNovoDestino();
                        break;
                    case 2:
                        cadastrarNovoCaminhao();
                        break;
                    case 3:
                        cadastrarNovoCliente();
                        break;
                    case 4:
                        // cadastrarNovoTipoDeCarga();
                        break;
                    case 5:
                        cadastrarNovaCarga();
                        break;
                    case 6:
                        consultarCargas();
                        break;
                    case 7:
                        // alterarSituacaoCarga();
                        break;
                    case 8:
                        // carregarDadosIniciais();
                        break;
                    case 9:
                        // fretarCargas();
                        break;
                    case 10:
                        // salvarDados();
                        break;
                    case 11:
                        // carregarDados();
                        break;
                    case 12:
                        //imprimirCadastros()
                    case 13:
                        gerenciarItinerarios();
                        break;
                    case 0:
                        System.out.println("Finalizando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
            scanner.close(); // Importante fechar o Scanner quando não for mais usado

    }

    private void cadastrarNovoDestino() {
        System.out.println("Cadastrar novo destino");

        //Solicita os dados do destino
        System.out.println("Informe o código do destino:");
        int codigo = scanner.nextInt(); // Supõe que o usuário sempre digita uma string

        // Verifica se já existe um destino com o mesmo código
        for (Destino destino : destinos) {
            if (destino.getCodigo() == codigo) {
                System.out.println("Erro: Já existe um destino cadastrado com esse código.");
                return; // Interrompe a execução se já existir
            }
        }

        System.out.println("Informe o nome do destino:");
        String nome = scanner.next().trim();

        System.out.println("Informe a cidade de destino:");
        String cidade = scanner.next().trim();

    }

    private void cadastrarNovoCaminhao() {
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


    public void cadastrarNovoCliente() {
        System.out.println("Cadastro de novo cliente:");

        String cpf;
        do {
            System.out.print("Informe o CPF do cliente: ");
            cpf = scanner.next().trim(); // Lê o CPF como String
            if (!ServicoClientes.verificarCpf(cpf)) {
                System.out.println("CPF inválido! Por favor, tente novamente.");
            }
        } while (!ServicoClientes.verificarCpf(cpf));

        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado!");
                return; // Encerra o método se o CPF já estiver cadastrado
            }
        }

        // Continua o cadastro se o CPF não existir
        System.out.print("Informe o nome do cliente: ");
        scanner.nextLine(); // Consumir a linha restante após ler o número
        String nome = scanner.nextLine(); // Agora lê a linha inteira

        System.out.print("Informe o telefone do cliente: ");
        String telefone = scanner.next().trim();

        String codCliente = "CLT-" + (listaClientes.size() + 1);
        Cliente novoCliente = new Cliente(codCliente, nome, telefone, cpf);
        listaClientes.add(novoCliente);

        // Ordena a lista pelo código do cliente
        listaClientes.sort(Comparator.comparing(Cliente::getCod));

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void cadastrarNovaCarga() {
        System.out.println("Cadastro de nova carga:");

        // Solicita os dados da carga ao usuário
        System.out.print("Informe o código da carga: ");
        int codigo = scanner.nextInt();

        // Verifica se já existe uma carga com o mesmo código
        if (cargasPendentes.stream().anyMatch(c -> c.getCodigo() == codigo)) {
            System.out.println("Erro: Já existe uma carga cadastrada com esse código.");
            return; // Interrompe a execução se já existir
        }

        System.out.print("Informe o peso da carga (em toneladas): ");
        int peso = scanner.nextInt();

        System.out.print("Informe o valor declarado da carga: ");
        double valorDeclarado = scanner.nextDouble();

        System.out.print("Informe o tempo máximo para o frete (em dias): ");
        int tempoMaximo = scanner.nextInt();

        Cliente cliente = selecionarCliente();
        TipoCarga tipoCarga = selecionarTipoCarga();
        Destino origem = selecionarDestino("origem");
        Destino destino = selecionarDestino("destino");

        // Cria a nova carga
        // 'null' é passado para 'caminhaoDesignado' porque um caminhão ainda não foi atribuído à carga
        Carga novaCarga = new Carga(codigo, peso, valorDeclarado, tempoMaximo, origem, destino, tipoCarga, cliente, "Pendente", null);

        // Adiciona à fila de cargas pendentes
        if (cargasPendentes == null) {
            cargasPendentes = new LinkedList<>(); // Inicializa a fila se ainda não foi inicializada
        }
        cargasPendentes.offer(novaCarga);

        System.out.println("Carga cadastrada com sucesso e adicionada à fila de pendências.");
    }

    private Cliente selecionarCliente() {
        if (listaClientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
            return null;
        }

        listaClientes.forEach(cliente -> System.out.println("Código: " + cliente.getCod() + ", Nome: " + cliente.getNome()));
        System.out.print("Escolha um cliente pelo código: ");
        String codigo = scanner.next();
        return listaClientes.stream()
                .filter(cliente -> Objects.equals(cliente.getCod(), codigo))
                .findFirst()
                .orElse(null);
    }

    private TipoCarga selecionarTipoCarga() {
        if (tiposDeCarga.isEmpty()) {
            System.out.println("Não há tipos de carga cadastrados.");
            return null;
        }

        tiposDeCarga.forEach(tipo -> System.out.println("Número: " + tipo.getNumero() + ", Descrição: " + tipo.getDescricao()));
        System.out.print("Escolha um tipo de carga pelo número: ");
        int numero = scanner.nextInt();
        return tiposDeCarga.stream()
                .filter(tipo -> tipo.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    private Destino selecionarDestino(String tipo) {
        if (destinos.isEmpty()) {
            System.out.println("Não há destinos cadastrados.");
            return null;
        }

        destinos.forEach(destino -> System.out.println("Código: " + destino.getCodigo() + ", Nome: " + destino.getSigla()));
        System.out.print("Escolha o destino de " + tipo + " pelo código: ");
        int codigo = scanner.nextInt();
        return destinos.stream()
                .filter(destino -> destino.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    private void consultarCargas() {
        if (cargasPendentes == null || cargasPendentes.isEmpty()) {
            System.out.println("Não há cargas pendentes cadastradas.");
            return;
        }

        System.out.println("Lista de Cargas Pendentes:");
        for (Carga carga : cargasPendentes) {
            System.out.println("Código da Carga: " + carga.getCodigo());
            System.out.println("Cliente: " + carga.getCliente().getNome());
            System.out.println("Peso: " + carga.getPeso() + " toneladas");
            System.out.println("Valor Declarado: R$ " + carga.getValorDeclarado());
            System.out.println("Tempo Máximo para o Frete: " + carga.getTempoMaximo() + " dias");
            System.out.println("Tipo de Carga: " + carga.getTipoCarga().getDescricao());
            System.out.println("Origem: " + carga.getOrigem().getSigla());
            System.out.println("Destino: " + carga.getDestino().getSigla());
            String nomeCaminhao = carga.getCaminhaoDesignado() != null ? carga.getCaminhaoDesignado().getNome() : "Não Atribuído";
            System.out.println("Caminhão Designado: " + nomeCaminhao);
            System.out.println("-----------------------------------");
        }
    }

    private void gerenciarItinerarios() {
        System.out.println("Gerenciar Itinerários:");

        if (destinos.isEmpty() || destinos.size() < 2) {
            System.out.println("É necessário ter pelo menos dois destinos cadastrados para criar um itinerário.");
            return;
        }

        // Seleciona a origem e o destino
        System.out.println("Escolha o destino de origem:");
        Destino origem = selecionarDestino("origem");
        System.out.println("Escolha o destino de destino:");
        Destino destino = selecionarDestino("destino");

        // Procura se já existe itinerário cadastrado entre esses destinos
        for (Itinerario itinerario : itinerarios) {
            if (itinerario.getOrigem().equals(origem) && itinerario.getDestino().equals(destino)) {
                System.out.println("Distância atual: " + itinerario.getDistancia() + " km. Deseja atualizar? (Sim/Não)");
                String resposta = scanner.next().trim();
                if (resposta.equalsIgnoreCase("Sim")) {
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



}
