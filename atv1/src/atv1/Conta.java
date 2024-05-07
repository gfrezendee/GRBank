package atv1;

// Classe "Conta", representa uma conta bancária
class Conta {
    private double saldo; // Saldo da conta
    private String nome; // Nome da conta

    // Construtor da Conta
    public Conta(String nome, double saldoInicial) {
        this.nome = nome; // Inicializa o nome da conta
        this.saldo = saldoInicial; // Inicializa o saldo da conta
    }

    // Método para depositar dinheiro na conta
    public synchronized void depositar(double valor) {
        this.saldo += valor; // Adiciona o valor ao saldo
        // Imprime uma mensagem indicando o valor depositado, o nome da conta e o saldo atual
        System.out.println("Deposito de R$" + valor + " na conta " + nome + ". Saldo atual: R$" + saldo);
    }

    // Método para sacar dinheiro da conta
    public synchronized void sacar(double valor) {
        this.saldo -= valor; // Subtrai o valor do saldo
        // Imprime uma mensagem indicando o valor sacado, o nome da conta e o saldo atual
        System.out.println("Saque de R$" + valor + " da conta " + nome + ". Saldo atual: R$" + saldo);
    }

    // Método para obter o saldo da conta
    public synchronized double getSaldo() {
        return this.saldo; // Retorna o saldo da conta
    }

    // Método para exibir o saldo final da conta
    public void exibirSaldoFinal() {
        // Imprime uma mensagem indicando o saldo final e o nome da conta
        System.out.println("Saldo final da conta " + nome + ": R$" + saldo);
    }
}
