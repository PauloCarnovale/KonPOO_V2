package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.*;

public class FileManager {

    // Método para gravar caminhões em arquivo CSV
    public void gravarCaminhoesCSV(String caminhoArquivoCaminhoes, List<Caminhao> caminhoes) {
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
    }

    // Método para gravar clientes em arquivo CSV
    public void gravarClientesCSV(String caminhoArquivoClientes, List<Cliente> clientes) {
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

    // Método para gravar destinos em arquivo CSV
    public void gravarDestinosCSV(String caminhoArquivoDestinos, List<Destino> destinos) {
        try (FileWriter fwDestinos = new FileWriter(caminhoArquivoDestinos)) {
            fwDestinos.append("Código, Sigla, Cidade\n");
            for (Destino destino : destinos) {
                fwDestinos.append(String.valueOf(destino.getCodigo()))
                        .append(", ")
                        .append(destino.getSigla())
                        .append(", ")
                        .append(destino.getCidade())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para gravar itinerários em arquivo CSV
    public void gravarItinerariosCSV(String caminhoArquivoItinerarios, List<Itinerario> itinerarios) {
        try (FileWriter fwItinerarios = new FileWriter(caminhoArquivoItinerarios)) {
            fwItinerarios.append("Código, Origem, Destino, Distância\n");
            for (Itinerario itinerario : itinerarios) {
                fwItinerarios.append(itinerario.getCodigo())
                        .append(", ")
                        .append(itinerario.getOrigem().getSigla())
                        .append(", ")
                        .append(itinerario.getDestino().getSigla())
                        .append(", ")
                        .append(String.valueOf(itinerario.getDistancia()))
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gravarTiposDeCargaCSV(String caminhoArquivoTiposCarga, List<TipoCarga> tiposDeCarga) {
        try (FileWriter fwTiposCarga = new FileWriter(caminhoArquivoTiposCarga)) {
            fwTiposCarga.append("Número, Descrição, Detalhes Específicos\n");
            for (TipoCarga tipoCarga : tiposDeCarga) {
                StringBuilder linha = new StringBuilder();
                linha.append(tipoCarga.getNumero())
                        .append(", ")
                        .append(tipoCarga.getDescricao())
                        .append(", ");

                // Verifica se é Perecível ou Durável e adiciona informações específicas
                if (tipoCarga instanceof TipoCarga.Perecivel) {
                    TipoCarga.Perecivel perecivel = (TipoCarga.Perecivel) tipoCarga;
                    linha.append("Origem: ").append(perecivel.getOrigem())
                            .append(", Tempo Máx. Validade: ").append(perecivel.getTempoMaximoValidade())
                            .append(", Temp. Armazenamento: ").append(perecivel.getTemperaturaArmazenamento())
                            .append(", Requer Refrigeração: ").append(perecivel.isRequerRefrigeracao());
                } else if (tipoCarga instanceof TipoCarga.Duravel) {
                    TipoCarga.Duravel duravel = (TipoCarga.Duravel) tipoCarga;
                    linha.append("Setor: ").append(duravel.getSetor())
                            .append(", Material Principal: ").append(duravel.getMaterialPrincipal())
                            .append(", Durabilidade Anos: ").append(duravel.getDurabilidadeAnos())
                            .append(", Frágil: ").append(duravel.isFragil());
                }

                fwTiposCarga.append(linha.toString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
