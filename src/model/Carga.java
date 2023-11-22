package model;

public class Carga {
    private int codigo;
    private int peso;
    private double valorDeclarado;
    private int tempoMaximo;
    private Destino origem;
    private Destino destino;
    private TipoCarga tipoCarga;
    private Cliente cliente;
    private String situacao;
    private Caminhao caminhaoDesignado;

    public Carga(int codigo, int peso, double valorDeclarado, int tempoMaximo, Destino origem, Destino destino,
                 TipoCarga tipoCarga, Cliente cliente, String situacao, Caminhao caminhaoDesignado) {
        this.codigo = codigo;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tempoMaximo = tempoMaximo;
        this.origem = origem;
        this.destino = destino;
        this.tipoCarga = tipoCarga;
        this.cliente = cliente;
        this.situacao = "Pendente"; // A carga é criada como pendente por padrão
        this.caminhaoDesignado = caminhaoDesignado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public int getTempoMaximo() {
        return tempoMaximo;
    }

    public void setTempoMaximo(int tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }

    public Destino getOrigem() {
        return origem;
    }

    public void setOrigem(Destino origem) {
        this.origem = origem;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Caminhao getCaminhaoDesignado() {
        return caminhaoDesignado;
    }

    public void setCaminhaoDesignado(Caminhao caminhaoDesignado) {
        this.caminhaoDesignado = caminhaoDesignado;
    }

    // Metodo para definir um caminhão para carga, mudando a situação para Locada
    public void definirCaminhao(Caminhao caminhao) {
        if (this.situacao.equals("Pendente")) {
            this.caminhaoDesignado = caminhao;
            this.situacao = "Locada";
            // Supondo que você queira marcar o caminhão como não disponível
            caminhao.setDisponivel(false);
        } else {
            System.out.println("Carga já está locada ou em uma situação que não permite alocação de caminhão.");
        }
    }

    // Metodo para marcar a carga como entregue, mudando a situação para Finalizada
    public void cargaEntregue() {
        if ("Locada".equals(this.situacao)) {
            this.situacao = "Finalizada";
            if (this.caminhaoDesignado != null) {
                this.caminhaoDesignado.setDisponivel(true); // Libera o caminhão
            }
        } else {
            System.out.println("A carga não está locada e não pode ser marcada como entregue.");
        }
    }

    // Metodo para cancelar a carga, mudando a situação para Cancelada
    public void cancelarCarga() {
        if (!"Finalizada".equals(this.situacao)) {
            this.situacao = "Cancelada";
        } else {
            System.out.println("A carga já foi entregue e não pode ser cancelada.");
        }
    }

    // Método para calcular o valor do frete
    public double calcularValorFrete(double precoPorDistancia, double precoPorPeso) {
        double valorFrete = (precoPorDistancia * calcularDistancia()) + (precoPorPeso * peso);
        return valorFrete;
    }

    // Método privado para calcular a distância entre origem e destino
    private double calcularDistancia() {
        // Implemente a lógica para calcular a distância aqui
        return 0;
    }

    // Método para calcular o preço por distância
    public double calcularPrecoPorDistancia(double custoPorKmRodado) {
        double distancia = calcularDistancia(); // Assumindo que você tem um método para calcular a distância
        double precoPorDistancia = distancia * custoPorKmRodado;
        return precoPorDistancia;
    }

    // Método para calcular o preço por peso com base no tipo de carga
    public double calcularPrecoPorPeso() {
        double precoPorPeso;

        // Aqui supomos que TipoCarga tenha um método getDescricao() que retorna uma String
        if ("Perecível".equals(tipoCarga.getDescricao())) {
            precoPorPeso = peso * 2.0;
        } else if ("Durável".equals(tipoCarga.getDescricao())) {
            precoPorPeso = peso * 1.5;
        } else {
            // Outros tipos de carga podem ter outra lógica de preço
            precoPorPeso = peso; // Suposição para um tipo de carga padrão
        }

        return precoPorPeso;
    }

    @Override
    public String toString() {
        return "Carga{" +
                "codigo=" + codigo +
                ", peso=" + peso +
                ", valorDeclarado=" + valorDeclarado +
                ", tipoCarga=" + tipoCarga +
                ", cliente=" + cliente +
                ", situacao='" + situacao + '\'' +
                ", caminhaoDesignado=" + caminhaoDesignado +
                '}';
    }
}
