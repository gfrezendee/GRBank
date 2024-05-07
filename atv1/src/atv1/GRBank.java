package atv1;
import java.util.concurrent.*;

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
            executor.execute(clientes[i]); // Inicia as threads dos clientes
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
