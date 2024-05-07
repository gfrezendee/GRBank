package atv1;
import java.util.concurrent.*;

// Classe "Funcionario", representa um funcionário que tem uma conta salário e uma conta investimento
class Funcionario implements Runnable {
    private Conta contaSalario; // Conta salário do funcionário
    private Conta contaInvestimento; // Conta de investimento do funcionário

    // Construtor do Funcionario
    public Funcionario(Conta contaSalario, Conta contaInvestimento) {
        this.contaSalario = contaSalario; // Inicializa a conta salário do funcionário
        this.contaInvestimento = contaInvestimento; // Inicializa a conta de investimento do funcionário
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
        double salario = contaSalario.getSaldo(); // Obtém o saldo da conta salário
        // Se o salário for maior que 0, o funcionário investe 20% do salário na conta de investimento
        if (salario > 0) {
            double investimento = salario * 0.2; // Calcula o valor do investimento
            contaSalario.sacar(investimento); // Sacar o valor do investimento da conta salário
            contaInvestimento.depositar(investimento); // Deposita o valor do investimento na conta de investimento
        }
    }
}
