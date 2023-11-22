package model;

/**
 * Classe que representa um cliente no sistema.
 *
 * Atributos:
 *  - cod: String representando o código do cliente.
 *  - nome: String com o nome do cliente.
 *  - telefone: String com o número de telefone do cliente.
 *  - cpf: String com o CPF do cliente.
 *
 * Métodos:
 *  - Cliente(cod, nome, telefone, cpf): Construtor para criar uma nova instância de Cliente com os valores fornecidos.
 *  - getCod(): Retorna o código do cliente.
 *  - setCod(cod): Atualiza o código do cliente.
 *  - getNome(): Retorna o nome do cliente.
 *  - setNome(nome): Atualiza o nome do cliente.
 *  - getTelefone(): Retorna o número de telefone do cliente.
 *  - setTelefone(telefone): Atualiza o número de telefone do cliente.
 *  - getCpf(): Retorna o CPF do cliente.
 *  - setCpf(cpf): Atualiza o CPF do cliente.
 *  - toString(): Retorna uma representação em String do objeto Cliente, incluindo código, nome e telefone.
 */

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