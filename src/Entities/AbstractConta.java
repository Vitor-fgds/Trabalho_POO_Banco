package Entities;

import Model.Exception.BalanceError;
import Model.Exception.InvalidInputError;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.time.LocalDate;

public abstract class AbstractConta implements Conta{
    protected Double saldo;
    protected Cliente proprietario;
    protected LocalDate dataAbertura;
    protected Integer numeroConta;

    public AbstractConta(Cliente proprietario, Integer numeroConta, Banco banco) throws InvalidInputError{
        this.proprietario = proprietario;

        for(Conta conta : banco.getContas()){
            if(conta.getNumeroConta().equals(numeroConta)){
                throw new InvalidInputError("Número de conta já existente no banco.");
            }
        }

        this.numeroConta = numeroConta;
        this.saldo = 0.0;
        this.dataAbertura = LocalDate.now();
    }

    public Double getSaldo() {
        return saldo;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    @Override
    public void verificarSaldo() {
        System.out.println("Saldo atual: R$ " + this.saldo);
    }

    // Abstrato
    @Override
    public void sacar(Double valor) throws InvalidInputError, BalanceError {
    }

    // Abstrato
    @Override
    public void depositar(Double valor) throws InvalidInputError {
    }

    // Abstrato
    @Override
    public void transferir(Conta contaDestino, Double valor) throws InvalidInputError, BalanceError{
    }
}
