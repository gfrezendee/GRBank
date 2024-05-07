package atv1;
import java.util.concurrent.*;

class Cliente implements Runnable {
    private Conta conta;
    private Loja[] lojas;

    public Cliente(Conta conta, Loja[] lojas) {
        this.conta = conta;
        this.lojas = lojas;
    }

    public Conta getConta() {
        return this.conta;
    }

    @Override
    public void run() {
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
