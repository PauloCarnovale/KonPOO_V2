/**
 * *** Sistema de Gerenciamento de Cargas ***
 *
 * Este aplicativo é responsável por inicializar e executar o sistema de gerenciamento,
 * permitindo ao usuário interagir com o sistema através de um menu de opções. As funcionalidades
 * incluem o cadastro de cargas, clientes, destinos e caminhões, bem como o fretamento
 * de cargas e a gestão de itinerários.
 *
 * Ao executar o aplicativo, o usuário será apresentado com um menu de opções que permite
 * realizar todas as operações necessárias para o gerenciamento de cargas.
 *
 * Autores: Paulo Cardoso, Arthur e Leonardo
 */

public class Main{
    public static void main(String[] args) {
        App aplicativo = new App();
        aplicativo.executarSistema();

    }
}