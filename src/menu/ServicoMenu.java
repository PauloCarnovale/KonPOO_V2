package menu;
public class ServicoMenu {
    public void exibirMenu() {
        System.out.println("\n*** Sistema de Gerenciamento de Cargas ***");
        System.out.println("---------------------------------------------");
        System.out.println("*** Cadastros ***");
        System.out.println("1. Cadastrar novo destino");
        System.out.println("2. Cadastrar novo caminhão");
        System.out.println("3. Cadastrar novo cliente");
        System.out.println("4. Cadastrar novo tipo de carga");
        System.out.println("5. Cadastrar nova carga");
        System.out.println("*** Consultas e Alterações ***");
        System.out.println("6. Consultar todas as cargas");
        System.out.println("7. Alterar a situação de uma carga");
        System.out.println("8. Inicializar Sistema");
        System.out.println("9. Fretar cargas");
        System.out.println("*** Configurações ***");
        System.out.println("10. Salvar dados");
        System.out.println("11. Carregar dados");
        System.out.println("12. Imprimir cadastro");
        System.out.println("13. Gerenciar Itinerarios");
        System.out.println("0. Finalizar sistema");
        System.out.print("Escolha uma opção: ");
    }

}
