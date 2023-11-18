package model;

public class Frete {
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

    public Frete(int codigo, int peso, double valorDeclarado, int tempoMaximo, Destino origem, Destino destino,
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
    public void definirCaminhao() {
        if (this.situacao.equals("Pendente")) {
            this.situacao = "Locada";
        } else {
            System.out.println("A carga não pode ser locada, pois está em uma situação inválida");
        }
    }

    // Metodo para marcar a carga como entregue, mudando a situação para Finalizada
    public void cargaEntregue() {
        if (this.situacao.equals("Locada")) {
            this.situacao = "Finalizada";
        } else {
            System.out.println("A carga não pode ser entregue, pois está locada");
        }
    }

    // Metodo para cancelar a carga, mudando a situação para Cancelada
    public void cancelarCarga() {
        if (this.situacao.equals("Finalizada")) {
            this.situacao = "Cancelada";
        } else {
            System.out.println("A carga não pode ser entregue, pois ja foi entregue");
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
        // Por exemplo, pode ser uma chamada a um serviço externo ou cálculo baseado em coordenadas
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

        if ("Perecível".equals(getTipoCarga())) {
            precoPorPeso = peso * 2.0;
        } else if ("Durável".equals(getTipoCarga())) {
            precoPorPeso = peso * 1.5;
        } else {
            // Caso o tipo de carga não seja "Perecível" nem "Durável", você pode definir um valor padrão ou tratar conforme necessário.
            precoPorPeso = peso;
        }

        return precoPorPeso;
    }

}
