package Entities;

import Model.Exception.BalanceError;
import Model.Exception.InvalidInputError;

import java.time.LocalDate;

public class ContaPoupanca extends AbstractConta {
    public ContaPoupanca(Banco banco, Cliente cliente, int numeroConta) throws InvalidInputError{
        super(cliente, numeroConta, banco);
        this.saldo = 0.0;
        this.dataAbertura = LocalDate.now();
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
    public void sacar(Double valor) throws InvalidInputError, BalanceError {
        if(valor <= 0){
            throw new InvalidInputError("Você não pode sacar valores menores ou iguais a zero.");
        }
        else if(this.saldo < valor){
            throw new BalanceError("Saldo insuficiente para saque.");
        }
        else{
            this.saldo -= valor;
        }

    }

    @Override
    public void transferir(Conta contaDestino, Double valor) throws InvalidInputError, BalanceError {
        if(valor <= 0){
            throw new InvalidInputError("Você não pode transferir valores menores ou iguais a zero.");
        }
        else if(this.saldo < valor){
            throw new BalanceError("Saldo insuficiente para transferência.");
        }
        else{
            this.saldo -= valor;
            contaDestino.depositar(valor);
        }
    }

    public void jurosRendimento(Double taxaJuros){
        if(taxaJuros > 0){
            double rendimento = this.saldo * (taxaJuros / 100);
            this.saldo += rendimento;
        }
    }

}
