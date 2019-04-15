# **Desafio do Estacionamento de Carros**

# Motivação
Avaliar o desempenho de desenvolvedores ao utilizar o framework ASP.NET Core para implementar uma API RESTful documentada e testada.

# Introdução 
Você foi procurado para implementar uma API que gerencie um estaciomento privado de veículos. 

# Descrição do sistema
Criar uma API para gerenciamento de carros em um estacionamento, que deve ter pelo menos 15 vagas. 
Através dos métodos da API será possível ver quantas vagas estão disponíveis, estacionar um carro, realizar o pagamento do ticket e emitir um relatório de recebimentos.
|Permanência|Valor (R$)|
|-----------|---------:|
|Até 3 horas|7,00      |
|Hora extra |3,00      |

Métodos a serem criados:
- Consultar quantidade de vagas disponíveis
- Listar posição das vagas disponíveis
- Estacionar um carro numa vaga
- Pagar ticket
- Relatório com ocupação atual do estacionamento
- Relatório com valor arrecadado por período

**Caso algum aspecto do problema não tenha sido detalhado, solucione da forma que achar melhor, e justifique sua decisão.**

# Tecnologias requeridas
- Banco de dados à sua escolha (relacional ou não)
- Testes unitários em todos os métodos da API
- Documentação dos métodos com Swagger/OpenAPI
- *(extra)* CI/CD
- *(extra)* Monitoramento da API
- *(extra)* FrontEnd em um framework à sua escolha

# Detalhe da solução
- API Rest desenvolvida em Spring
- Necessário autenticação para consumir os serviços da API. Utilizado JWT.
  No endpoint de autenticação é necessário informar e-mail e password. Já estão criados por padrão dois usuários, um de perfil ADMIN que pode listar, criar, atualizar e remover outros usuários. E um de perfil TECHNICIAN, que é o perfil responsável pelas interações com o estacionamento, ou seja, ele pode: 
  * Consultar quantidade de vagas disponíveis
  * Listar posição das vagas disponíveis
  * Estacionar um carro numa vaga
  * Pagar ticket
  * Relatório com ocupação atual do estacionamento
  * Relatório com valor arrecadado por período
- Como banco de dados está sendo utilizado o H2, banco que sobe em tempo de execução da aplicação.
- Porta padrão da API: 8888
- Documentação da API utilizando Swagger, disponível em: http://~:8888/doc/index.html

