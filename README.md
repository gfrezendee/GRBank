# Sistema Bancário Simulado - GRBank

Este projeto simula um sistema bancário com lojas, funcionários e clientes. As lojas possuem contas bancárias e contratam funcionários. Os funcionários possuem contas salário e contas de investimento. Os clientes possuem contas bancárias e fazem compras nas lojas.

## Funcionamento da aplicação

Como indicado na atividade, foi utilizado o JAVA 17 para a construção do programa. A IDE utilizada foi a Eclipse IDE for Java Developers - 2024-03.

## Funcionalidades

**Lojas**:
 - Possuem contas bancárias.
 - Contratam funcionários.
 - Pagam os funcionários quando o saldo da conta for suficiente.
 - Depositam o valor das compras realizadas pelos clientes.
   
**Funcionários**:
 - Possuem contas salário e contas de investimento.
 - Recebem um salário fixo de R$1.400,00.
 - Investem 20% do salário na conta de investimento após recebê-lo.
  
**Clientes**:
 - Possuem contas bancárias.
 - Fazem compras nas lojas, de R$100,00 ou R$200,00, alternando as lojas, até o saldo da conta estar vazio.

## Classes

O projeto é composto pelas seguintes classes, cada uma em seu próprio arquivo `.java`:

- `Conta.java`: Representa uma conta bancária com métodos para depositar, sacar e obter o saldo.

- `Funcionario.java`: Representa um funcionário que tem uma conta salário e uma conta investimento. O funcionário investe 20% do salário na conta de investimentos logo após seu recebimento.

- `Cliente.java`: Representa um cliente que tem uma conta e faz compras em várias lojas. O cliente realiza compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar vazio.

- `Loja.java`: Representa uma loja que tem uma conta e vários funcionários. A loja paga os funcionários quando o saldo da conta for suficiente.

- `GRBank.java`: Classe principal que inicia o programa.
