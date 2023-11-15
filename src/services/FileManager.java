package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.*;

public class FileManager {
    // Método para gravar caminhões e clientes em arquivos CSV
    public void gravarCaminhoesClientesCSV(String caminhoArquivoCaminhoes, String caminhoArquivoClientes, List<Caminhao> caminhoes, List<Cliente> clientes) {
        // Gravar dados dos caminhões
        try (FileWriter fwCaminhoes = new FileWriter(caminhoArquivoCaminhoes)) {
            fwCaminhoes.append("Nome, Velocidade, Autonomia, Custo Por Km, Identificador\n");
            for (Caminhao caminhao : caminhoes) {
                fwCaminhoes.append(caminhao.getNome())
                        .append(", ")
                        .append(String.valueOf(caminhao.getVelocidade()))
                        .append(", ")
                        .append(String.valueOf(caminhao.getAutonomia()))
                        .append(", ")
                        .append(String.valueOf(caminhao.getCustoPorKm()))
                        .append(", ")
                        .append(caminhao.getCodigo())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Gravar dados dos clientes
        try (FileWriter fwClientes = new FileWriter(caminhoArquivoClientes)) {
            fwClientes.append("Nome, Telefone, CPF, Código\n");
            for (Cliente cliente : clientes) {
                fwClientes.append(cliente.getNome())
                        .append(", ")
                        .append(cliente.getTelefone())
                        .append(", ")
                        .append(cliente.getCpf())
                        .append(", ")
                        .append(cliente.getCod())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
