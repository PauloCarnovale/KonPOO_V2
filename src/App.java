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
    private List<Itinerario> itinerarios = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private ServicoItinerario servicoItinerario;
    private ServicoFretes servicoFretes;
    private ServicoCargas servicoCargas;

    /* Instanciações das classes de serviço */
    ServicoCaminhoes caminhoes = new ServicoCaminhoes();
    ServicoClientes clientes = new ServicoClientes();
    ServicoMenu menu = new ServicoMenu();
    ServicoDestinos cidades = new ServicoDestinos();
    ServicoCargas cargas = new ServicoCargas(clientes, cidades, tiposDeCarga);

    FileManager fileManager = new FileManager();


    public void inicializarSistema() {
        this.listaCaminhoes = caminhoes.getListaCaminhoes();
        this.listaClientes = clientes.getListaClientes();
        this.destinos = cidades.getListaDestinos();
        this.tiposDeCarga = cargas.getTiposDeCarga();
        this.servicoCargas = new ServicoCargas(clientes, cidades, tiposDeCarga);
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
                        servicoCargas.consultarCargas();
                        break;
                    case 7:
                        cargas.exibirCodigoESituacaoDasCargas();
                        cargas.alterarSituacaoCarga();
                        break;
                    case 8:
                        inicializarSistema();
                        break;
                    case 9:
                        servicoFretes.fretarCargas();
                        break;
                    case 10:
                        fileManager.imprimirCadastros(listaCaminhoes, listaClientes, destinos, itinerarios, tiposDeCarga);
                        break;
                    case 11:
                        carregaDados();
                        break;
                    case 12:
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
            scanner.close(); // Fecha o Scanner

    }

    private void carregaDados(){
        String caminhoArquivo = "dadosiniciais.csv";
        SistemaData dadosCarregados = fileManager.carregarDados(caminhoArquivo);
        if (dadosCarregados != null) {
            this.listaCaminhoes = dadosCarregados.getCaminhoes();
            this.listaClientes = dadosCarregados.getClientes();
            this.destinos = dadosCarregados.getDestinos();
            this.tiposDeCarga = dadosCarregados.getTiposDeCarga();
        }
    }
}
