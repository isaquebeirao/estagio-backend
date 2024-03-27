# API Backend Desafio

Esta é uma API backend desenvolvida como parte de um desafio, com o objetivo de criar e listar produtos, além de permitir que os usuários selecionem um produto para compra, gerando uma URL de pagamento usando o MercadoPago.

## Como utilizar o programa

### Configuração do Banco de Dados

Certifique-se de ter um banco de dados MySQL chamado `intern_challenge` configurado e em execução antes de iniciar o aplicativo. As configurações de conexão com o banco de dados podem ser ajustadas no arquivo `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/intern_challenge
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
### Criar Produtos

Para criar produtos, você pode enviar uma solicitação POST para o endpoint /product, fornecendo os detalhes do produto no corpo da solicitação no formato JSON. Por exemplo:

```
{
  "name": "Produto 1",
  "description": "Descrição do Produto 1",
  "value": 10.0
}
```
### Listar Produtos
Para listar todos os produtos disponíveis, você pode enviar uma solicitação GET para o endpoint /product.

### Selecionar um Produto para Compra
Para selecionar um produto e iniciar o processo de compra, você pode enviar uma solicitação GET para o endpoint /product/{id}, onde {id} é o ID do produto desejado. Isso retornará uma URL de pagamento do MercadoPago para o produto selecionado.

Exemplo:

```
GET /product/1
```
Isso retornará a URL de pagamento para o produto com ID 1.

### Tecnologias Utilizadas
- Java
- Spring Boot
- MySQL
- MercadoPago SDK
