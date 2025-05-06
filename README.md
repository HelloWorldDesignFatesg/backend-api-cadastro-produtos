
# Sistema de Cadastro de Produtos – Backend

Este repositório contém o **backend** do Sistema de Cadastro de Produtos, desenvolvido em **Java** com o framework **Spring Boot**, persistindo os dados em um banco **PostgreSQL**.

---

## 📌 Visão Geral

Este serviço RESTful permite realizar operações de cadastro, consulta, atualização e exclusão de produtos. Ele é responsável por expor a API que será consumida pelo frontend Angular.

---

## ⚙ Tecnologias Utilizadas

| Camada     | IDE     | Framework     | Linguagem | Banco de Dados |
|------------|---------|---------------|-----------|----------------|
| Backend    | Eclipse | Spring Boot   | Java      | PostgreSQL     |

---

## 🗄 Configuração do Banco de Dados

Antes de executar o backend, configure a conexão com o banco:

1. Crie um banco no PostgreSQL:

```sql
CREATE DATABASE produtos_db;
```

2. No diretório `src/main/resources`, abra o arquivo `application.properties` (ou `application.yml`) e defina:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/produtos_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶ Como Executar

1. Abra o projeto na IDE **Eclipse**.
2. Localize a classe principal com a anotação `@SpringBootApplication`.
3. Execute como uma aplicação Spring Boot.

Por padrão, a aplicação será iniciada na porta `8080`.

---

## 📖 Principais Endpoints

| Método | Endpoint            | Descrição                        |
|--------|---------------------|---------------------------------|
| GET    | /api/produtos       | Lista todos os produtos         |
| POST   | /api/produtos       | Cadastra um novo produto        |
| PUT    | /api/produtos/{id}  | Atualiza os dados de um produto |
| DELETE | /api/produtos/{id}  | Remove um produto               |

---

## 📝 Testes Automatizados

Para rodar os testes (se estiverem implementados):

```bash
mvn test
```

---

## 🧩 Integração com o Frontend

Certifique-se de que o frontend (Angular) esteja apontando para a API no endereço `http://localhost:8080/api`.

---
