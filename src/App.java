import java.util.*;

import menu.ServicoMenu;
import services.*;
import model.*;


public class App {

    /* Declaração das listas */
    private List<Caminhao> listaCaminhoes;
    private List<Cliente> listaClientes;
    private List<Destino> destinos;
    private List<TipoCarga> tiposDeCarga = new ArrayList<>();
    private Queue<Frete> cargasPendentes;
    private List<Itinerario> itinerarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ServicoItinerario servicoItinerario;


    /* Instanciações das classes de serviço */
    ServicoCaminhoes caminhoes = new ServicoCaminhoes();
    ServicoClientes clientes = new ServicoClientes();
    ServicoMenu menu = new ServicoMenu();
    ServicoDestinos cidades = new ServicoDestinos();
    ServicoCargas cargas = new ServicoCargas();
    FileManager fileManager = new FileManager();





    public void inicializarSistema() {
        this.listaCaminhoes = caminhoes.getListaCaminhoes();
        this.listaClientes = clientes.getListaClientes();
        this.destinos = cidades.getListaDestinos();
        this.tiposDeCarga = cargas.getTiposDeCarga();
        this.cargas = new ServicoCargas(clientes, cidades, tiposDeCarga);
        this.servicoItinerario = new ServicoItinerario(cidades);

    }

    public void executarSistema(){
            int opcao;
            do {
                menu.exibirMenu();
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        cidades.cadastrarNovoDestino();
                        break;
                    case 2:
                        caminhoes.cadastrarNovoCaminhao();
                        break;
                    case 3:
                        clientes.cadastrarNovoCliente();
                        break;
                    case 4:
                        cargas.cadastrarNovoTipoDeCarga();
                        break;
                    case 5:
                        cargas.cadastrarNovaCarga();
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
                        imprimirCadastros();
                        break;
                    case 13:
                        servicoItinerario.gerenciarItinerarios();
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
        for (Frete frete : cargasPendentes) {
            System.out.println("Código da Carga: " + frete.getCodigo());
            System.out.println("Cliente: " + frete.getCliente().getNome());
            System.out.println("Peso: " + frete.getPeso() + " toneladas");
            System.out.println("Valor Declarado: R$ " + frete.getValorDeclarado());
            System.out.println("Tempo Máximo para o Frete: " + frete.getTempoMaximo() + " dias");
            System.out.println("Tipo de Carga: " + frete.getTipoCarga().getDescricao());
            System.out.println("Origem: " + frete.getOrigem().getSigla());
            System.out.println("Destino: " + frete.getDestino().getSigla());
            String nomeCaminhao = frete.getCaminhaoDesignado() != null ? frete.getCaminhaoDesignado().getNome() : "Não Atribuído";
            System.out.println("Caminhão Designado: " + nomeCaminhao);
            System.out.println("-----------------------------------");
        }
    }


    private void imprimirCadastros() {
        String caminhoArquivoCaminhoes = "caminhoes.csv";
        String caminhoArquivoClientes = "clientes.csv";
        String caminhoArquivoDestinos = "destinos.csv";
        String caminhoArquivoItinerarios = "itinerarios.csv";
        String caminhoArquivoTiposCarga = "tiposCarga.csv";

        fileManager.gravarCaminhoesCSV(caminhoArquivoCaminhoes, listaCaminhoes);
        fileManager.gravarClientesCSV(caminhoArquivoClientes, listaClientes);
        fileManager.gravarDestinosCSV(caminhoArquivoDestinos, destinos);
        fileManager.gravarItinerariosCSV(caminhoArquivoItinerarios, itinerarios);
        fileManager.gravarTiposDeCargaCSV(caminhoArquivoTiposCarga, cargas.getTiposDeCarga());


        System.out.println("Dados salvos em arquivos CSV.");
    }


}
