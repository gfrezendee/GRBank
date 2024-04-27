# Sistema Bancário Simulado - GRBank
Este projeto simula um sistema bancário com lojas, funcionários e clientes. As lojas possuem contas bancárias e contratam funcionários. Os funcionários possuem contas salário e contas de investimento. Os clientes possuem contas bancárias e fazem compras nas lojas.

## Funcionamento da aplicação
Como indicado na ativiade, foi utilizado o JAVA 17 para construção do programa; A IDE utilizada foi a Eclipse IDE for Java Developers - 2024-03

## Funcionalidades

Lojas:
 - Possuem contas bancárias.
 - Contratam funcionários.
 - Pagam os funcionários quando o saldo da conta for suficiente.
 - Depositam o valor das compras realizadas pelos clientes.
   
Funcionários:
 - Possuem contas salário e contas de investimento.
 - Recebem um salário fixo de R$1.400,00.
 - Investem 20% do salário na conta de investimento após recebê-lo.
  
Clientes:
 - Possuem contas bancárias.
 - Fazem compras nas lojas, de R$100,00 ou R$200,00, alternando as lojas, até o saldo da conta estar vazio.

## Classes

O projeto é composto pelas seguintes classes:

- `Conta`: Representa uma conta bancária com métodos para depositar, sacar e obter o saldo.
  
  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/a8eb88d6-b48d-445a-b0ab-f5bdbbac78d0)

- `Funcionario`: Representa um funcionário que tem uma conta salário e uma conta investimento. O funcionário investe 20% do salário na conta de investimentos logo após seu recebimento.

  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/97c5e1f8-2ce6-445e-9fb1-986971096277)

- `Cliente`: Representa um cliente que tem uma conta e faz compras em várias lojas. O cliente realiza compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar vazio.

  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/d782855a-bf56-464c-a0bc-f52859183a69)

- `Loja`: Representa uma loja que tem uma conta e vários funcionários. A loja paga os funcionários quando o saldo da conta for suficiente.

  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/8ca4659b-8c6c-44f1-beac-8561b8310dfb)

- `GRBank`: Classe principal que inicia o programa.

  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/e6f8c298-c0b6-4968-8373-ff885c9f0a45)
  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/fa8371e9-34fe-4b94-9cf2-45f8f3177b98)
  ![image](https://github.com/gfrezendee/GRBank/assets/63487827/a2b0154a-f52a-48e5-a0b5-19e85a743360)



