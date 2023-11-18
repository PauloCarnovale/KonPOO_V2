package model;

public class TipoCarga {
    private int numero;
    private String descricao;

    public TipoCarga(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static class Perecivel extends TipoCarga {
        private String origem;
        private int tempoMaximoValidade;
        private double temperaturaArmazenamento; // em graus Celsius
        private boolean requerRefrigeracao;

        public Perecivel(int numero, String descricao, String origem, int tempoMaximoValidade, double temperaturaArmazenamento, boolean requerRefrigeracao) {
            super(numero, descricao);
            this.origem = origem;
            this.tempoMaximoValidade = tempoMaximoValidade;
            this.temperaturaArmazenamento = temperaturaArmazenamento;
            this.requerRefrigeracao = requerRefrigeracao;
        }

        public String getOrigem() {
            return origem;
        }

        public void setOrigem(String origem) {
            this.origem = origem;
        }

        public int getTempoMaximoValidade() {
            return tempoMaximoValidade;
        }

        public void setTempoMaximoValidade(int tempoMaximoValidade) {
            this.tempoMaximoValidade = tempoMaximoValidade;
        }

        public double getTemperaturaArmazenamento() {
            return temperaturaArmazenamento;
        }

        public void setTemperaturaArmazenamento(double temperaturaArmazenamento) {
            this.temperaturaArmazenamento = temperaturaArmazenamento;
        }

        public boolean isRequerRefrigeracao() {
            return requerRefrigeracao;
        }

        public void setRequerRefrigeracao(boolean requerRefrigeracao) {
            this.requerRefrigeracao = requerRefrigeracao;
        }
    }

    public static class Duravel extends TipoCarga {
        private String setor;
        private String materialPrincipal;
        private int durabilidadeAnos; // Durabilidade esperada em anos
        private boolean fragil;

        public Duravel(int numero, String descricao, String setor, String materialPrincipal, int durabilidadeAnos, boolean fragil) {
            super(numero, descricao);
            this.setor = setor;
            this.materialPrincipal = materialPrincipal;
            this.durabilidadeAnos = durabilidadeAnos;
            this.fragil = fragil;
        }

        public String getSetor() {
            return setor;
        }

        public void setSetor(String setor) {
            this.setor = setor;
        }

        public String getMaterialPrincipal() {
            return materialPrincipal;
        }

        public void setMaterialPrincipal(String materialPrincipal) {
            this.materialPrincipal = materialPrincipal;
        }

        public int getDurabilidadeAnos() {
            return durabilidadeAnos;
        }

        public void setDurabilidadeAnos(int durabilidadeAnos) {
            this.durabilidadeAnos = durabilidadeAnos;
        }

        public boolean isFragil() {
            return fragil;
        }

        public void setFragil(boolean fragil) {
            this.fragil = fragil;
        }
    }

}
