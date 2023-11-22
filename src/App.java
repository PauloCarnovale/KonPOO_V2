import java.util.*;

/**
 * Classe App central do Sistema de Gerenciamento de Cargas.
 *
 * Esta classe é responsável por orquestrar as operações principais do sistema, incluindo
 * a inicialização de todos os serviços e a interação com o usuário através de um menu de opções.
 *
 * Através do menu, o usuário pode realizar diversas ações, como cadastrar novos destinos,
 * caminhões, clientes e tipos de carga, além de gerenciar as cargas, fretes e itinerários.
 *
 * A classe também lida com a persistência de dados, carregando e salvando informações
 * em um arquivo CSV para manter o estado entre diferentes execuções do programa.
 *
 * Os serviços individuais de cada componente do sistema são encapsulados em classes separadas,
 * e a classe App coordena as interações entre esses serviços para executar as operações solicitadas.
 */

import menu.ServicoMenu;
import services.*;
import model.*;


public class App {
    /* Declaração de listas */
    private List<Caminhao> listaCaminhoes;
    private List<Cliente> clientes;
    private List<Destino> destinos;
    private List<Itinerario> itinerarios = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    /* Classes de Serviços */
    private ServicoItinerario servicoItinerario;
    private ServicoFretes servicoFretes;
    private ServicoCargas servicoCargas;
    private ServicoTipoCargas servicoTipoCargas;
    private ServicoCaminhoes servicoCaminhoes = new ServicoCaminhoes();
    private ServicoClientes servicoClientes = new ServicoClientes();
    private ServicoMenu menu = new ServicoMenu();
    private ServicoDestinos servicoDestinos = new ServicoDestinos();
    /* Manipulador de arquivos */
    private GerenciadorDeArquivos gerenciadorDeArquivos = new GerenciadorDeArquivos();

    public App() {
        this.servicoTipoCargas = new ServicoTipoCargas();
        this.servicoCargas = new ServicoCargas(servicoClientes, servicoDestinos, servicoTipoCargas.getTiposDeCarga(), servicoCaminhoes);
        this.servicoItinerario = new ServicoItinerario(new ServicoDestinos());
        inicializarSistema();
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
                        gerenciadorDeArquivos.imprimirCadastros(
                                listaCaminhoes,
                                clientes,
                                destinos,
                                itinerarios,
                                servicoTipoCargas.getTiposDeCarga(),
                                servicoCargas.getCargasPendentes(),
                                servicoFretes.getCargasPendentes()
                        );
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
            scanner.close();
    }

    private void carregaDados() {
        String caminhoArquivo = "dadosiniciais.csv";
        try {
            SistemaData dadosCarregados = gerenciadorDeArquivos.carregarDados(caminhoArquivo);
            if (dadosCarregados != null) {
                this.listaCaminhoes = dadosCarregados.getCaminhoes();
                this.clientes = dadosCarregados.getClientes();
                this.destinos = dadosCarregados.getDestinos();
                // Atualize as listas de cargas pendentes, se necessário

                // Confirmação de sucesso
                System.out.println("Dados carregados com sucesso!");
            } else {
                // Aviso se os dados não foram carregados
                System.out.println("Não foram encontrados dados para carregar.");
            }
        } catch (Exception e) {
            // Mensagem de erro se algo der errado no processo de carregamento
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
