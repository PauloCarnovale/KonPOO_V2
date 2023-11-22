package model;

/**
 * Classe que representa os tipos de carga no sistema.
 *
 * Atributos:
 *  - numero: int representando o número identificador do tipo de carga.
 *  - descricao: String com a descrição do tipo de carga.
 *
 * Métodos:
 *  - TipoCarga(numero, descricao): Construtor para criar uma nova instância de TipoCarga.
 *  - getNumero(): Retorna o número identificador do tipo de carga.
 *  - setNumero(numero): Atualiza o número identificador do tipo de carga.
 *  - getDescricao(): Retorna a descrição do tipo de carga.
 *  - setDescricao(descricao): Atualiza a descrição do tipo de carga.
 *
 * Subclasses:
 *  - Perecivel: Representa um tipo de carga perecível, com atributos e métodos adicionais relacionados.
 *  - Duravel: Representa um tipo de carga durável, com atributos e métodos adicionais relacionados.
 */

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

    /**
     * Classe interna que representa um tipo de carga perecível.
     *
     * Atributos adicionais:
     *  - origem: String representando a origem da carga.
     *  - tempoMaximoValidade: int indicando o tempo máximo de validade em dias.
     *  - temperaturaArmazenamento: double representando a temperatura de armazenamento em graus Celsius.
     *  - requerRefrigeracao: boolean indicando se a carga requer refrigeração.
     *
     * Métodos adicionais:
     *  - Perecivel(numero, descricao, origem, tempoMaximoValidade, temperaturaArmazenamento, requerRefrigeracao): Construtor.
     *  - getOrigem(): Retorna a origem da carga.
     *  - setOrigem(origem): Atualiza a origem da carga.
     *  - getTempoMaximoValidade(): Retorna o tempo máximo de validade.
     *  - setTempoMaximoValidade(tempoMaximoValidade): Atualiza o tempo máximo de validade.
     *  - getTemperaturaArmazenamento(): Retorna a temperatura de armazenamento.
     *  - setTemperaturaArmazenamento(temperaturaArmazenamento): Atualiza a temperatura de armazenamento.
     *  - isRequerRefrigeracao(): Verifica se requer refrigeração.
     *  - setRequerRefrigeracao(requerRefrigeracao): Define se requer refrigeração.
     */

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

    /**
     * Classe interna que representa um tipo de carga durável.
     *
     * Atributos adicionais:
     *  - setor: String representando o setor da carga.
     *  - materialPrincipal: String com o material principal da carga.
     *  - durabilidadeAnos: int indicando a durabilidade esperada em anos.
     *  - fragil: boolean indicando se a carga é frágil.
     *
     * Métodos adicionais:
     *  - Duravel(numero, descricao, setor, materialPrincipal, durabilidadeAnos, fragil): Construtor.
     *  - getSetor(): Retorna o setor da carga.
     *  - setSetor(setor): Atualiza o setor da carga.
     *  - getMaterialPrincipal(): Retorna o material principal da carga.
     *  - setMaterialPrincipal(materialPrincipal): Atualiza o material principal da carga.
     *  - getDurabilidadeAnos(): Retorna a durabilidade esperada em anos.
     *  - setDurabilidadeAnos(durabilidadeAnos): Atualiza a durabilidade esperada.
     *  - isFragil(): Verifica se a carga é frágil.
     *  - setFragil(fragil): Define se a carga é frágil.
     */


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
