package atv1;
import java.util.concurrent.*;

// Classe "Conta", representa uma conta bancária
class Conta {
    private double saldo;
    private String nome;

    // Construtor da Conta
    public Conta(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
    }

    // Método para depositar dinheiro na conta
    public synchronized void depositar(double valor) {
        this.saldo += valor;
        System.out.println("Deposito de R$" + valor + " na conta " + nome + ". Saldo atual: R$" + saldo);
    }

    // Método para sacar dinheiro da conta
    public synchronized void sacar(double valor) {
        this.saldo -= valor;
        System.out.println("Saque de R$" + valor + " da conta " + nome + ". Saldo atual: R$" + saldo);
    }

    // Método para obter o saldo da conta
    public synchronized double getSaldo() {
        return this.saldo;
    }

    // Método para exibir o saldo final da conta
    public void exibirSaldoFinal() {
        System.out.println("Saldo final da conta " + nome + ": R$" + saldo);
    }
}

// Classe "Funcionario", representa um funcionário que tem uma conta salário e uma conta investimento
class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimento;

    // Construtor do Funcionario
    public Funcionario(Conta contaSalario, Conta contaInvestimento) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    // Método para obter a conta salário do funcionário
    public Conta getContaSalario() {
        return this.contaSalario;
    }

    // Método para obter a conta investimento do funcionário
    public Conta getContaInvestimento() {
        return this.contaInvestimento;
    }

    // Método run() que é chamado quando a thread do funcionário é iniciada
    @Override
    public void run() {
        // A lógica do funcionário é investir 20% do salário na conta de investimentos logo após seu recebimento.
        double salario = contaSalario.getSaldo();
        if (salario > 0) {
            double investimento = salario * 0.2;
            contaSalario.sacar(investimento);
            contaInvestimento.depositar(investimento);
        }
    }
}

// Classe "Cliente", representa um cliente que tem uma conta e faz compras em várias lojas
class Cliente implements Runnable {
    private Conta conta;
    private Loja[] lojas;

    // Construtor do Cliente
    public Cliente(Conta conta, Loja[] lojas) {
        this.conta = conta;
        this.lojas = lojas;
    }

    // Método para obter a conta do cliente
    public Conta getConta() {
        return this.conta;
    }

    // Método run() que é chamado quando a thread do cliente é iniciada
    @Override
    public void run() {
        // A lógica do cliente é realizar compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar vazio.
        int i = 0;
        while (conta.getSaldo() > 0) {
            Loja loja = lojas[i % lojas.length];
            double valorCompra = (i % 2 == 0) ? 100 : 200;
            if (conta.getSaldo() >= valorCompra) {
                conta.sacar(valorCompra);
                loja.depositar(valorCompra);
            }
            i++;
        }
    }
}

// Classe "Loja", representa uma loja que tem uma conta e vários funcionários
class Loja implements Runnable {
    private Conta conta;
    private Funcionario[] funcionarios;

    // Construtor da Loja
    public Loja(Conta conta, Funcionario[] funcionarios) {
        this.conta = conta;
        this.funcionarios = funcionarios;
    }

    // Método para depositar dinheiro na conta da loja
    public void depositar(double valor) {
        this.conta.depositar(valor);
    }

    // Método para pagar os funcionários
    public void pagarFuncionarios() {
        synchronized (this) {
            for (Funcionario funcionario : funcionarios) {
                if (this.conta.getSaldo() >= 1400) {
                    this.conta.sacar(1400);
                    funcionario.getContaSalario().depositar(1400);
                    new Thread(funcionario).start(); // Inicia a thread do funcionário
                }
            }
        }
    }

    // Método run() que é chamado quando a thread da loja é iniciada
    @Override
    public void run() {
        // Pagar os funcionários quando o saldo da conta for suficiente
    	  while (true) {
              if (this.conta.getSaldo() >= 1400) {
                  pagarFuncionarios();
              }
          }
      }
  }

// Classe principal
public class GRBank {
    public static void main(String[] args) {
        // Cria um executor de threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Cria contas para as lojas
        Conta contaLoja1 = new Conta("Loja 1", 0);
        Conta contaLoja2 = new Conta("Loja 2", 0);
        
        // Cria funcionários para as lojas
        Funcionario[] funcionariosLoja1 = {
            new Funcionario(new Conta("Funcionario 1", 0), new Conta("Investimento 1", 0)),
            new Funcionario(new Conta("Funcionario 2", 0), new Conta("Investimento 2", 0))
        };

        Funcionario[] funcionariosLoja2 = {
            new Funcionario(new Conta("Funcionario 3", 0), new Conta("Investimento 3", 0)),
            new Funcionario(new Conta("Funcionario 4", 0), new Conta("Investimento 4", 0))
        };

        // Cria as lojas
        Loja loja1 = new Loja(contaLoja1, funcionariosLoja1);
        Loja loja2 = new Loja(contaLoja2, funcionariosLoja2);

        // Cria um array de lojas
        Loja[] lojas = {loja1, loja2};

        // Cria clientes
        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < 5; i++) {
            clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000), lojas);
            executor.execute(clientes[i]);
        }

        // Inicia as threads dos funcionários
        for (Funcionario funcionario : funcionariosLoja1) {
            executor.execute(funcionario);
        }

        for (Funcionario funcionario : funcionariosLoja2) {
            executor.execute(funcionario);
        }

        // Inicia as threads das lojas
        executor.execute(loja1);
        executor.execute(loja2);
        
        // Inicia as threads dos clientes
        for (Cliente cliente : clientes) {
            executor.execute(cliente);
        }

        // Desliga o executor de threads
        executor.shutdown();
        try {
            // Aguarda até que todas as threads terminem
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibe os saldos finais
        contaLoja1.exibirSaldoFinal();
        contaLoja2.exibirSaldoFinal();
        for (Funcionario funcionario : funcionariosLoja1) {
            funcionario.getContaSalario().exibirSaldoFinal();
            funcionario.getContaInvestimento().exibirSaldoFinal();
        }
        for (Funcionario funcionario : funcionariosLoja2) {
            funcionario.getContaSalario().exibirSaldoFinal();
            funcionario.getContaInvestimento().exibirSaldoFinal();
        }
        for (Cliente cliente : clientes) {
            cliente.getConta().exibirSaldoFinal();
        }
    }
}