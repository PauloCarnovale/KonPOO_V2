package model;

public class Cliente {
    private String cod;
    private String nome;
    private String telefone;
    private String cpf;

    public Cliente(String cod, String nome, String telefone, String cpf) {
        this.cod = cod;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf=cpf;
    }
    public String getCod() {
        return cod;
    }
    public void setCod(String cod) {
        this.cod = cod;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente [cod=" + cod + ", nome=" + nome + ", telefone=" + telefone + "]";
    }



}