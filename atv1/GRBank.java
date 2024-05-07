package atv1;
import java.util.concurrent.*;

public class GRBank {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Conta contaLoja1 = new Conta("Loja 1", 0);
        Conta contaLoja2 = new Conta("Loja 2", 0);
        
        Funcionario[] funcionariosLoja1 = {
            new Funcionario(new Conta("Funcionario 1", 0), new Conta("Investimento 1", 0)),
            new Funcionario(new Conta("Funcionario 2", 0), new Conta("Investimento 2", 0))
        };

        Funcionario[] funcionariosLoja2 = {
            new Funcionario(new Conta("Funcionario 3", 0), new Conta("Investimento 3", 0)),
            new Funcionario(new Conta("Funcionario 4", 0), new Conta("Investimento 4", 0))
        };

        Loja loja1 = new Loja(contaLoja1, funcionariosLoja1);
        Loja loja2 = new Loja(contaLoja2, funcionariosLoja2);

        Loja[] lojas = {loja1, loja2};

        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < 5; i++) {
            clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000), lojas);
            executor.execute(clientes[i]);
        }

        for (Funcionario funcionario : funcionariosLoja1) {
            executor.execute(funcionario);
        }

        for (Funcionario funcionario : funcionariosLoja2) {
            executor.execute(funcionario);
        }

        executor.execute(loja1);
        executor.execute(loja2);
        
        for (Cliente cliente : clientes) {
            executor.execute(cliente);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
