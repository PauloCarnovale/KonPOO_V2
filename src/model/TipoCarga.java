package model;

public class TipoCarga {
    public int numero;
    public String descricao;

    public class Perecivel extends TipoCarga {

        private String origem;
        private int tempoMaximoValidade;

        public Perecivel(String origem, int tempoMaximoValidade) {
            this.origem = origem;
            this.tempoMaximoValidade = tempoMaximoValidade;
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
    }

    public class Duravel extends TipoCarga {

        private String setor;
        private String materialPrincipal;

        public Duravel(String setor, String materialPrincipal) {
            this.setor = setor;
            this.materialPrincipal = materialPrincipal;
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

    @Override
    public String toString() {
        return "TipoCarga [numero=" + numero + ", descricao=" + descricao + "]";
    }

}
