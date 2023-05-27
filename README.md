# API Spring Boot processo seletivo Datahub - vaga Desenvolvedor (a) Java Jr

Este projeto tem como objetivo receber alguns dados de um cliente, e retornar o nome escrito invertido. 

## 🚀 Começando
Para iniciar o projeto, é necessário:
 - Ter uma IDE que rode Java - foi utilizado o Intellij;
 - Ter o docker e o docker Compose instalados;
 - Ter uma plataforma para teste de API - indicado Postman;
 - Ter uma plataforma para visualizar o banco de dados - indicado DBeaver.

### 🔧 Instalação

Antes de começar, é necessário realizar o clone do projeto em uma pasta exclusiva para este fim. 

Após selecionar o projeto dentro da IDE de preferência, é importante rodar o comando para subir a aplicação docker: 
```
docker-compose up -d
```
 ![image](https://github.com/RaquelSPereira/datahub-processo-seletivo-vaga-backend-junior/assets/106705759/870a2d35-c4ac-4d16-8843-a08ee7eb225b)

Em uma plataforma para testar a API, será necessário criar httpRequest do tipo POST, passando a seguinte URL:

```
http://localhost:8080/datahub/information-client
```
O tipo de entrada é 'raw' e JSON.
O corpo da requisição precisa ser: 

```
{
    "nameClient": "John Doe",
    "cpfClient":"70467465784"
}
```

Para subir a API, será necessário rodar a datahubPrototypeApplication.
Para testar o funcionamento do projeto, basta enviar a requisição via Postman - ou plataforma de escolha - aguardar o retorno da mensagem.

## ⚙️ Executando os testes
Para realizar os testes unitários, será necessário rodar a classe clientInformationControllerTest.java e aguardar que os testes passem. 

## 🛠️ Construído com

Mencione as ferramentas que você usou para criar seu projeto

* [Spring Boot](https://spring.io/projects/spring-boot) - O framework web usado
* [Docker](https://spring.io/projects/spring-boot) - Database
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [DBeaver](https://dbeaver.io/) - Interface do database

