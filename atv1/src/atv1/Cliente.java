package atv1;
import java.util.concurrent.*;

// Classe "Cliente", representa um cliente que tem uma conta e faz compras em várias lojas
class Cliente implements Runnable {
    private Conta conta; // Conta do cliente
    private Loja[] lojas; // Array de lojas onde o cliente faz compras

    // Construtor do Cliente
    public Cliente(Conta conta, Loja[] lojas) {
        this.conta = conta; // Inicializa a conta do cliente
        this.lojas = lojas; // Inicializa as lojas onde o cliente faz compras
    }

    // Método para obter a conta do cliente
    public Conta getConta() {
        return this.conta;
    }

    // Método run() que é chamado quando a thread do cliente é iniciada
    @Override
    public void run() {
        int i = 0; // Variável para alternar entre as lojas
        // Enquanto o saldo da conta for maior que 0, o cliente continua fazendo compras
        while (conta.getSaldo() > 0) {
            Loja loja = lojas[i % lojas.length]; // Seleciona a loja onde o cliente fará a compra
            double valorCompra = (i % 2 == 0) ? 100 : 200; // O valor da compra alterna entre 100 e 200
            // Se o saldo da conta for maior ou igual ao valor da compra, o cliente faz a compra
            if (conta.getSaldo() >= valorCompra) {
                conta.sacar(valorCompra); // O cliente saca o valor da compra da sua conta
                loja.depositar(valorCompra); // O valor da compra é depositado na conta da loja
            }
            i++; // Incrementa a variável para alternar entre as lojas
        }
    }
}
