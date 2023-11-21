package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    public void gravarFretesCSV(String caminhoArquivoFretes, Queue<Frete> cargasPendentes) {
        try (FileWriter fwFretes = new FileWriter(caminhoArquivoFretes)) {
            fwFretes.append("Codigo, Peso, ValorDeclarado, TempoMaximo, Origem, Destino, TipoCarga, Cliente, Situacao\n");
            for (Frete frete : cargasPendentes) {
                fwFretes.append(String.valueOf(frete.getCodigo())) // Converta para String
                        .append(", ")
                        .append(String.valueOf(frete.getPeso())) // Converta para String
                        .append(", ")
                        .append(String.valueOf(frete.getValorDeclarado())) // Converta para String
                        .append(", ")
                        .append(String.valueOf(frete.getTempoMaximo())) // Converta para String
                        .append(", ")
                        .append(frete.getOrigem().getSigla())
                        .append(", ")
                        .append(frete.getDestino().getSigla())
                        .append(", ")
                        .append(frete.getTipoCarga().getDescricao())
                        .append(", ")
                        .append(frete.getCliente().getNome())
                        .append(", ")
                        .append(frete.getSituacao())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void imprimirCadastros(List<Caminhao> caminhoes, List<Cliente> clientes, List<Destino> destinos, List<Itinerario> itinerarios, List<TipoCarga> tiposDeCarga) {
        try {
            if (caminhoes.isEmpty() || clientes.isEmpty() || destinos.isEmpty() || itinerarios.isEmpty() || tiposDeCarga.isEmpty()) {
                throw new IllegalStateException("Uma ou mais listas estão vazias.");
            }

            String caminhoArquivoCaminhoes = "caminhoes.csv";
            String caminhoArquivoClientes = "clientes.csv";
            String caminhoArquivoDestinos = "destinos.csv";
            String caminhoArquivoItinerarios = "itinerarios.csv";
            String caminhoArquivoTiposCarga = "tiposCarga.csv";


            gravarCaminhoesCSV(caminhoArquivoCaminhoes, caminhoes);
            gravarClientesCSV(caminhoArquivoClientes, clientes);
            gravarDestinosCSV(caminhoArquivoDestinos, destinos);
            gravarItinerariosCSV(caminhoArquivoItinerarios, itinerarios);
            gravarTiposDeCargaCSV(caminhoArquivoTiposCarga, tiposDeCarga);


            System.out.println("Dados salvos em arquivos CSV.");
        } catch (IllegalStateException e) {
            System.out.println("Erro ao imprimir cadastros: " + e.getMessage());
        }
    }

    public SistemaData carregarDados(String arquivo) {
        List<Caminhao> caminhoes = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Destino> destinos = new ArrayList<>();
        List<TipoCarga> tiposDeCarga = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String secaoAtual = "";

            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue;

                // Verificar se estamos mudando de seção
                if (linha.equals("caminhoes") || linha.equals("clientes") || linha.equals("destinos") ||
                        linha.equals("tiposdecarga")) {
                    secaoAtual = linha;
                    continue;
                }

                switch (secaoAtual) {
                    case "caminhoes":
                        caminhoes.add(parseCaminhao(linha));
                        break;
                    case "clientes":
                        clientes.add(parseCliente(linha));
                        break;
                    case "destinos":
                        destinos.add(parseDestino(linha));
                        break;
                    case "tiposdecarga":
                        tiposDeCarga.add(parseTipoCarga(linha));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SistemaData(caminhoes, clientes, destinos, tiposDeCarga);
    }

    private Caminhao parseCaminhao(String linha) {
        String[] dados = linha.split(", ");
        String nome = dados[0];
        double velocidade = Double.parseDouble(dados[1]);
        double autonomia = Double.parseDouble(dados[2]);
        double custoPorKm = Double.parseDouble(dados[3]);
        String identificador = dados[4];

        return new Caminhao(nome, velocidade, autonomia, custoPorKm, identificador);
    }

    private Cliente parseCliente(String linha) {
        String[] dados = linha.split(", ");
        String nome = dados[0];
        String telefone = dados[1];
        String cpf = dados[2];
        String codigo = dados[3];

        return new Cliente(codigo, nome, telefone, cpf);
    }

    private Destino parseDestino(String linha) {
        String[] dados = linha.split(", ");
        int codigo = Integer.parseInt(dados[0]);
        String sigla = dados[1];
        String cidade = dados[2];

        return new Destino(codigo, sigla, cidade);
    }

    private TipoCarga parseTipoCarga(String linha) {
        String[] dados = linha.split(", ");
        int numero = Integer.parseInt(dados[0]);
        String descricao = dados[1];

        // Exemplo genérico, ajuste conforme a estrutura de suas subclasses de TipoCarga
        return new TipoCarga(numero, descricao);
    }


}
