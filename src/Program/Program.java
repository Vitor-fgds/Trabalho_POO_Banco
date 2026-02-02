package Program;

import Entities.*;
import Model.Exception.AgeError;
import Model.Exception.BalanceError;
import Model.Exception.InvalidInputError;
import Model.Exception.LimitError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Banco banco = new Banco("Java", "12.345.678/0001-90");
            menu(banco, scanner);
        } catch (InvalidInputError error) {
            System.out.println("Erro: " + error.getMessage());
            return;
        }
    }

    public static void menuContaCorrente(ContaCorrente conta, Banco banco, Scanner scanner) {
        while (true) {
            System.out.println("Menu Conta Corrente:");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Verificar Saldo");
            System.out.println("5 - Realizar Empréstimo");
            System.out.println("6 - Verificar Cheque Especial");
            System.out.println("0 - Sair");
            int opcao;
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor que deseja sacar: ");
                    Double valorSaque = scanner.nextDouble();
                    try {
                        conta.sacar(valorSaque);
                    } catch (InvalidInputError | BalanceError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o valor que deseja depositar: ");
                    Double valorDeposito = scanner.nextDouble();
                    try {
                        conta.depositar(valorDeposito);
                    } catch (InvalidInputError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Digite o número da conta destino: ");
                    Integer numeroContaDestino = scanner.nextInt();
                    System.out.println("Digite o valor que deseja transferir: ");
                    Double valorTransferencia = scanner.nextDouble();
                    Conta contaDestino = null;
                    for (Conta c : banco.getContas()) {
                        if (c.getNumeroConta().equals(numeroContaDestino)) {
                            contaDestino = c;
                            break;
                        }
                    }
                    if (contaDestino != null) {
                        try {
                            conta.transferir(contaDestino, valorTransferencia);
                        } catch (InvalidInputError | BalanceError error) {
                            System.out.println("Erro: " + error.getMessage());
                        }
                    } else {
                        System.out.println("Conta destino não encontrada.");
                    }
                    break;
                case 4:
                    conta.verificarSaldo();
                    break;
                case 5:
                    System.out.println("Digite o valor do empréstimo: ");
                    Double valorEmprestimo = scanner.nextDouble();
                    try {
                        conta.emprestimo(banco, valorEmprestimo);
                    } catch (InvalidInputError | LimitError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 6:
                    conta.verificarChequeEspecial();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }
        }
    }

    public static void menuContaPoupanca(ContaPoupanca conta, Banco banco, Scanner scanner) {
        while (true) {
            System.out.println("Menu Conta Poupança:");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Verificar Saldo");
            System.out.println("5 - Aplicar Juros de Rendimento");
            System.out.println("0 - Sair");
            int opcao;
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor que deseja sacar: ");
                    Double valorSaque = scanner.nextDouble();
                    try {
                        conta.sacar(valorSaque);
                    } catch (InvalidInputError | BalanceError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o valor que deseja depositar: ");
                    Double valorDeposito = scanner.nextDouble();
                    try {
                        conta.depositar(valorDeposito);
                    } catch (InvalidInputError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Digite o número da conta destino: ");
                    Integer numeroContaDestino = scanner.nextInt();
                    System.out.println("Digite o valor que deseja transferir: ");
                    Double valorTransferencia = scanner.nextDouble();
                    Conta contaDestino = null;
                    for (Conta c : banco.getContas()) {
                        if (c.getNumeroConta().equals(numeroContaDestino)) {
                            contaDestino = c;
                            break;
                        }
                    }
                    if (contaDestino != null) {
                        try {
                            conta.transferir(contaDestino, valorTransferencia);
                        } catch (InvalidInputError | BalanceError error) {
                            System.out.println("Erro: " + error.getMessage());
                        }
                    } else {
                        System.out.println("Conta destino não encontrada.");
                    }
                    break;
                case 4:
                    conta.verificarSaldo();
                    break;
                case 5:
                    System.out.println("Digite a taxa de juros (%): ");
                    Double taxaJuros = scanner.nextDouble();
                    conta.jurosRendimento(taxaJuros);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }
        }
    }

    public static void menu(Banco banco, Scanner scanner) {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while(true) {
            System.out.printf("Banco %s / CNPJ: %s %n", banco.getNome(), banco.getCnpj());
            System.out.println("Menu: ");
            System.out.println("1 - Cadastro de Cliente");
            System.out.println("2 - Criar Conta");
            System.out.println("3 - Acessar Conta");
            System.out.println("0 - Sair");
            int opcao;
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    try {
                        scanner.nextLine();
                        System.out.println("Digite o nome do cliente: ");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o sobrenome do cliente: ");
                        String sobrenome = scanner.nextLine();
                        System.out.println("Digite o CPF do cliente (formato: 000.000.000-00): ");
                        String cpf = scanner.nextLine();
                        System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy): ");
                        String dataNascimentoStr = scanner.nextLine();
                        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, fmt1);
                        Cliente novoCliente = new Cliente(nome, sobrenome, cpf, dataNascimento);
                        banco.addCliente(novoCliente);
                        System.out.println("Cliente cadastrado com sucesso.");

                    } catch (InvalidInputError | AgeError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;
                case 2:
                    try {
                        if (banco.getClientes().isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado. Cadastre-se primeiro.");
                            break;
                        }
                        scanner.nextLine();
                        System.out.println("Digite o seu CPF (formato: 000.000.000-00): ");
                        String cpfCliente = scanner.nextLine();
                        Cliente cliente = null;
                        for (Cliente c : banco.getClientes()) {
                            if (c.getCpf().equals(cpfCliente)) {
                                cliente = c;
                                break;
                            }
                        }

                        if (cliente == null) {
                            System.out.println("Cliente não encontrado.");
                            break;
                        }
                        
                        if (cliente != null) {
                            System.out.println("1 - Conta Corrente");
                            System.out.println("2 - Conta Poupança");
                            System.out.println("Escolha o tipo de conta que deseja criar: ");
                            int tipoConta = scanner.nextInt();

                            if (tipoConta == 1) {
                                System.out.println("Digite o número da conta: ");
                                Integer numeroConta = scanner.nextInt();
                                System.out.println("Digite o limite de empréstimo: ");
                                Double limiteEmprestimo = scanner.nextDouble();
                                System.out.println("Digite o valor do cheque especial: ");
                                Double chequeEspecial = scanner.nextDouble();
                                ContaCorrente novaContaCorrente = new ContaCorrente(banco, cliente, numeroConta, limiteEmprestimo, chequeEspecial);
                                banco.addConta(novaContaCorrente);
                            } else if (tipoConta == 2) {
                                System.out.println("Digite o número da conta: ");
                                int numeroConta = scanner.nextInt();
                                ContaPoupanca novaContaPoupanca = new ContaPoupanca(banco, cliente, numeroConta);
                                banco.addConta(novaContaPoupanca);
                            } else {
                                System.out.println("Opção Inválida.");

                            }
                        }
                    } catch (InvalidInputError error) {
                        System.out.println("Erro: " + error.getMessage());
                    }
                    break;

                case 3:
                    if (banco.getContas().isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada.");
                        break;
                    }

                    System.out.println("Digite o número da conta: ");
                    int numeroConta = scanner.nextInt();
                    Conta conta = null;
                    for (Conta c : banco.getContas()) {
                        if (c.getNumeroConta().equals(numeroConta)) {
                            conta = c;
                            break;
                        }
                    }

                    if (conta == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }

                    if (conta != null) {
                        if (conta instanceof ContaCorrente) {
                            ContaCorrente contaCorrente = (ContaCorrente) conta;
                            menuContaCorrente(contaCorrente, banco, scanner);
                        } else if (conta instanceof ContaPoupanca) {
                            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
                            menuContaPoupanca(contaPoupanca, banco, scanner);
                        }
                    }
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }
        }
    }
}
