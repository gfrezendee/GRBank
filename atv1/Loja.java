package atv1;
import java.util.concurrent.*;

class Loja implements Runnable {
    private Conta conta;
    private Funcionario[] funcionarios;
    private boolean todosPagos;

    public Loja(Conta conta, Funcionario[] funcionarios) {
        this.conta = conta;
        this.funcionarios = funcionarios;
        this.todosPagos = false;
    }

    public void depositar(double valor) {
        this.conta.depositar(valor);
    }

    public void pagarFuncionarios() {
        synchronized (this) {
            for (Funcionario funcionario : funcionarios) {
                if (this.conta.getSaldo() >= 1400) {
                    this.conta.sacar(1400);
                    funcionario.getContaSalario().depositar(1400);
                    new Thread(funcionario).start();
                } else {
                    todosPagos = true;
                }
            }
        }
    }

    @Override
    public void run() {
        while (!todosPagos) {
            if (this.conta.getSaldo() >= 1400) {
                pagarFuncionarios();
            }
        }
    }
}
