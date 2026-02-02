package Entities;

import Model.Exception.InvalidInputError;

import java.util.ArrayList;

public class Banco {
    // Formato Esperado: 00.000.000/0000-00
    private static final String REGEX_CNPJ = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";
    private String nome;
    private String cnpj;
    private ArrayList<Conta> contas;
    private ArrayList<Cliente> clientes;

    public Banco(String nome, String cnpj) throws InvalidInputError {
        this.nome = nome.trim();
        if(cnpj.matches(REGEX_CNPJ)){
            this.cnpj = cnpj.trim();
        } else {
            throw new InvalidInputError("CNPJ inválido.");
        }
        this.contas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws InvalidInputError {
        if(cnpj.matches(REGEX_CNPJ)){
            this.cnpj = cnpj;
        } else {
            throw new InvalidInputError("CNPJ inválido.");
        }
    }

    public void addConta(Conta conta) throws InvalidInputError{
        for(Conta c : contas){
            if(c.getNumeroConta().equals(conta.getNumeroConta())){
                throw new InvalidInputError("Número de conta já existente no banco.");
            }
        }
        contas.add(conta);
    }

    public void addCliente(Cliente cliente) throws InvalidInputError{
        for(Cliente c : clientes){
            if(c.getCpf().equals(cliente.getCpf())){
                throw new InvalidInputError("Cliente com esse CPF já existe no banco.");
            }
        }
        clientes.add(cliente);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
