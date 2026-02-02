package Entities;

import Model.Exception.BalanceError;
import Model.Exception.InvalidInputError;
import Model.Exception.LimitError;

import java.time.LocalDate;

public class ContaCorrente extends AbstractConta{
    private Double limiteEmprestimo;
    private Double chequeEspecial;

    public ContaCorrente(Banco banco, Cliente cliente, Integer numeroConta, Double limiteEmprestimo, Double chequeEspecial) throws InvalidInputError {
        super(cliente, numeroConta, banco);
        if(limiteEmprestimo >= 0){
        this.limiteEmprestimo = limiteEmprestimo;
        }
        else{
            throw new InvalidInputError("Limite de empréstimo não pode ser negativo.");
        }
        if(chequeEspecial >= 0){
        this.chequeEspecial = chequeEspecial;
        }
        else{
            throw new InvalidInputError("Cheque especial deve ser maior ou igual a zero.");
        }
        this.saldo = 0.0;
        this.dataAbertura = LocalDate.now();
    }

    public Double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Double chequeEspecial) {
        if(chequeEspecial >= 0){
            this.chequeEspecial = chequeEspecial;
        }
        else{
            throw new InvalidInputError("Cheque especial deve ser maior ou igual a zero.");
        }
    }

    public Double getLimiteEmprestimo() {
        return limiteEmprestimo;
    }

    public void setLimiteEmprestimo(Double limiteEmprestimo) {
        if(limiteEmprestimo >= 0){
            this.limiteEmprestimo = limiteEmprestimo;
        }
        else{
            throw new InvalidInputError("Limite de empréstimo não pode ser negativo.");
        }
    }

    @Override
    public void depositar(Double valor) throws InvalidInputError{
        if(valor <= 0){
            throw new InvalidInputError("Você não pode depositar valores menores ou iguais a zero.");
        }
        else{
            this.saldo += valor;
        }
    }

    @Override
    public void sacar(Double valor) throws InvalidInputError, BalanceError{
        if(valor <= 0) {
            throw new InvalidInputError("Você não pode sacar valores menores ou iguais a zero.");
        }
        else if(this.saldo + this.chequeEspecial < valor) {
            throw new BalanceError("Saldo insuficiente para saque.");
        }
        else{
            this.saldo -= valor;
        }
    }

    @Override
    public void transferir(Conta contaDestino, Double valor) throws InvalidInputError,BalanceError{
        if(valor <= 0){
            throw new InvalidInputError("Você não pode transferir valores menores ou iguais a zero.");
        }
        else if(this.saldo + this.chequeEspecial < valor){
            throw new BalanceError("Saldo insuficiente para transferência.");
        }
        else{
            this.saldo -= valor;
            contaDestino.depositar(valor);
        }
    }

    public void emprestimo(Banco banco, Double valor) throws InvalidInputError, LimitError {
        if(valor<=0){
            throw new InvalidInputError("Você não pode solicitar um empréstimo menor ou igual a zero.");
        }
        else if(valor > this.limiteEmprestimo){
            throw new LimitError("Valor do empréstimo excede o limite disponível.");
        }
        else{
            this.saldo += valor;
        }
    }

    public void verificarChequeEspecial(){
        System.out.println("Cheque Especial disponível: R$ " + this.chequeEspecial);
    }
}
