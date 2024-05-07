package atv1;
import java.util.concurrent.*;

// Classe "Loja", representa uma loja que tem uma conta e vários funcionários
class Loja implements Runnable {
    private Conta conta; // Conta da loja
    private Funcionario[] funcionarios; // Array de funcionários da loja
    private boolean todosPagos; // Variável para verificar se todos os funcionários foram pagos

    // Construtor da Loja
    public Loja(Conta conta, Funcionario[] funcionarios) {
        this.conta = conta; // Inicializa a conta da loja
        this.funcionarios = funcionarios; // Inicializa os funcionários da loja
        this.todosPagos = false; // Inicializa a variável todosPagos como false
    }

    // Método para depositar dinheiro na conta da loja
    public void depositar(double valor) {
        this.conta.depositar(valor); // Deposita o valor na conta da loja
    }

    // Método para pagar os funcionários
    public void pagarFuncionarios() {
        synchronized (this) { // Bloco sincronizado para evitar condições de corrida
            for (Funcionario funcionario : funcionarios) { // Para cada funcionário
                if (this.conta.getSaldo() >= 1400) { // Se o saldo da conta for suficiente para pagar o funcionário
                    this.conta.sacar(1400); // Sacar o salário do funcionário da conta da loja
                    funcionario.getContaSalario().depositar(1400); // Depositar o salário na conta salário do funcionário
                    new Thread(funcionario).start(); // Iniciar a thread do funcionário
                } else {
                    todosPagos = true; // Se não for possível pagar um funcionário, definir todosPagos como true
                }
            }
        }
    }

    // Método run() que é chamado quando a thread da loja é iniciada
    @Override
    public void run() {
        while (!todosPagos) { // Enquanto nem todos os funcionários foram pagos
            if (this.conta.getSaldo() >= 1400) { // Se o saldo da conta for suficiente para pagar um funcionário
                pagarFuncionarios(); // Pagar os funcionários
            }
        }
    }
}
