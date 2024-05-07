package atv1;
import java.util.concurrent.*;

class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimento;

    public Funcionario(Conta contaSalario, Conta contaInvestimento) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    public Conta getContaSalario() {
        return this.contaSalario;
    }

    public Conta getContaInvestimento() {
        return this.contaInvestimento;
    }

    @Override
    public void run() {
        double salario = contaSalario.getSaldo();
        if (salario > 0) {
            double investimento = salario * 0.2;
            contaSalario.sacar(investimento);
            contaInvestimento.depositar(investimento);
        }
    }
}
