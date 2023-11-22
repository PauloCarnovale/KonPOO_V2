import java.util.*;

import menu.ServicoMenu;
import services.*;
import model.*;


public class App {

    /* Declaração das listas */
    private List<Caminhao> listaCaminhoes;
    private List<Cliente> clientes;
    private List<Destino> destinos;
    private List<Itinerario> itinerarios = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private ServicoItinerario servicoItinerario;
    private ServicoFretes servicoFretes;
    private ServicoCargas servicoCargas; // Alterado para camelCase
    private ServicoTipoCargas servicoTipoCargas; // Nova instância para gerenciar tipos de carga

    /* Instanciações das classes de serviço */
    private ServicoCaminhoes servicoCaminhoes = new ServicoCaminhoes(); // Alterado para camelCase e private
    private ServicoClientes servicoClientes = new ServicoClientes(); // Alterado para private
    private ServicoMenu menu = new ServicoMenu(); // Alterado para private
    private ServicoDestinos servicoDestinos = new ServicoDestinos(); // Alterado para private

    private FileManager fileManager = new FileManager(); // Alterado para private

    public App() {
        this.servicoTipoCargas = new ServicoTipoCargas(); // Inicialização do ServicoTipoCargas
        this.servicoCargas = new ServicoCargas(servicoClientes, servicoDestinos, servicoTipoCargas.getTiposDeCarga(), servicoCaminhoes); // Adicione servicoCaminhoes aqui
        this.servicoItinerario = new ServicoItinerario(new ServicoDestinos());
        inicializarSistema(); // Chamada para inicializar o sistema
    }



    public void inicializarSistema() {
        this.listaCaminhoes = servicoCaminhoes.getListaCaminhoes();
        this.clientes = servicoClientes.getListaClientes();
        this.destinos = servicoDestinos.getListaDestinos();
        this.servicoItinerario.inicializarItinerarios();
        this.servicoFretes = new ServicoFretes(servicoCargas.getCargasPendentes(), listaCaminhoes, servicoItinerario); // Passe a instância de ServicoItinerario



    }

    public void executarSistema(){
            int opcao;
            do {
                menu.exibirMenu();
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        servicoDestinos.cadastrarNovoDestino();
                        break;
                    case 2:
                        servicoCaminhoes.cadastrarNovoCaminhao();
                        break;
                    case 3:
                        servicoClientes.cadastrarNovoCliente();
                        break;
                    case 4:
                        servicoTipoCargas.cadastrarNovoTipoDeCarga();
                        break;
                    case 5:
                        servicoCargas.consultarCargas();
                        servicoCargas.cadastrarNovaCarga();
                        break;
                    case 6:
                        servicoCargas.consultarCargas();
                        break;
                    case 7:
                        servicoCargas.exibirCodigoESituacaoDasCargas();
                        servicoCargas.alterarSituacaoCarga();
                        break;
                    case 8:
                        inicializarSistema();
                        break;
                    case 9:
                        servicoFretes.fretarCargas();
                        break;
                    case 10:
                        fileManager.imprimirCadastros(listaCaminhoes, clientes, destinos, itinerarios, servicoTipoCargas.getTiposDeCarga());
                        break;
                    case 11:
                        carregaDados();
                        break;
                    case 12:
                        // Opção reservada para uso futuro
                        break;
                    case 13:
                        servicoItinerario.imprimirItinerarios();
                        servicoItinerario.gerenciarItinerarios();
                        break;
                    case 0:
                        System.out.println("Finalizando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
            scanner.close(); // Fecha o Scanner
    }

    private void carregaDados() {
        String caminhoArquivo = "dadosiniciais.csv";
        SistemaData dadosCarregados = fileManager.carregarDados(caminhoArquivo);
        if (dadosCarregados != null) {
            this.listaCaminhoes = dadosCarregados.getCaminhoes();
            this.clientes = dadosCarregados.getClientes();
            this.destinos = dadosCarregados.getDestinos();

            List<TipoCarga> tiposCarregados = dadosCarregados.getTiposDeCarga();
            if (tiposCarregados != null) {
                this.servicoTipoCargas.setTiposDeCarga(tiposCarregados);
            }
        }
    }
}
