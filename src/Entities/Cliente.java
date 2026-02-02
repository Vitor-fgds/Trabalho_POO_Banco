package Entities;

import Model.Exception.AgeError;
import Model.Exception.InvalidInputError;

import java.time.Duration;
import java.time.LocalDate;

public class Cliente{
    // Formato esperado: 000.000.000-00
    private static final String REGEX_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final LocalDate DATA_ATUAL = LocalDate.now();
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;

    public Cliente(String nome, String sobrenome, String cpf, LocalDate dataNascimento) throws AgeError, InvalidInputError{
        this.nome = nome.trim();
        this.sobrenome = sobrenome.trim();
        if(cpf.matches(REGEX_CPF)){
            this.cpf = cpf;
        } else {
            throw new InvalidInputError("CPF inválido.");
        }
        Duration idade = Duration.between(dataNascimento.atStartOfDay(), DATA_ATUAL.atStartOfDay());
        if(idade.toDays() / 365 >= 18){
            this.dataNascimento = dataNascimento;
        } else {
            throw new AgeError("Cliente deve ser maior de idade.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws InvalidInputError {
        if(cpf.matches(REGEX_CPF)){
            this.cpf = cpf;
        }
        else {
            throw new InvalidInputError("CPF inválido.");
        }
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) throws AgeError {
        Duration idade = Duration.between(dataNascimento.atStartOfDay(), DATA_ATUAL.atStartOfDay());
        if(idade.toDays() / 365 >= 18){
            this.dataNascimento = dataNascimento;
        } else {
            throw new AgeError("Cliente deve ser maior de idade.");
        }
    }
}
