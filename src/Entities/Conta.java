package Entities;

import Model.Exception.BalanceError;
import Model.Exception.InvalidInputError;

public interface Conta {
    public void sacar(Double valor) throws BalanceError, InvalidInputError;
    public void depositar(Double valor) throws InvalidInputError;
    public void transferir(Conta contaDestino, Double valor) throws BalanceError, InvalidInputError;
    public void verificarSaldo();
    public Integer getNumeroConta();
}
