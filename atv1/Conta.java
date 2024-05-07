package atv1;

class Conta {
    private double saldo;
    private String nome;

    public Conta(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(double valor) {
        this.saldo += valor;
        System.out.println("Deposito de R$" + valor + " na conta " + nome + ". Saldo atual: R$" + saldo);
    }

    public synchronized void sacar(double valor) {
        this.saldo -= valor;
        System.out.println("Saque de R$" + valor + " da conta " + nome + ". Saldo atual: R$" + saldo);
    }

    public synchronized double getSaldo() {
        return this.saldo;
    }

    public void exibirSaldoFinal() {
        System.out.println("Saldo final da conta " + nome + ": R$" + saldo);
    }
}
