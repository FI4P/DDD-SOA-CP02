# 🏨 Sistema de Reserva de Hotel Simplificado (CP 2 - FIAP)

## 💡 Objetivo do Projeto
Projeto desenvolvido como parte do **Checkpoint 2 (CP2)** da disciplina de Arquitetura Orientada a Serviço (SOA).

- O objetivo principal é desenvolver um sistema simplificado de reservas de hotel, aplicando os seguintes conceitos:
- Arquitetura em 3 Camadas (MVC):** Separação clara de responsabilidades (Controller, Service/Domain, Repository).
- Boas Práticas REST:** Uso correto de recursos, verbos HTTP e códigos de status.
- Migração Versionada de Banco de Dados:** Utilização do Flyway.
- Implementação de Regras de Negócio** e tratamento de erros consistentes.

## 🛠️ Tecnologias Utilizadas
| Categoria | Tecnologia | Versão |
| :--- |:------| :-- |
| **Linguagem** | Java  | 17 |
| **Framework** | Spring Boot | 3.2.5 |
| **Banco de Dados** | PostgreSQL | 
| **Migração DB** | Flyway | `[INCLUÍDO NO POM]` |
| **Validação** | Jakarta Bean Validation | `[INCLUÍDO NO POM]` |
| **Documentação API** | Springdoc OpenAPI / Swagger | `[INCLUÍDO NO POM]` |
| **Build Tool** | Maven |

## 📐 Estrutura de Arquitetura e Pastas
[cite_start]O projeto segue o padrão de arquitetura em 3 camadas (MVC), garantindo a separação de responsabilidades.

| Pacote               | Camada           | Responsabilidade                                                                                   |
|:---------------------|:-----------------|:---------------------------------------------------------------------------------------------------|
| **`controller`**     | Controller       | Define os endpoints REST e as rotas. Recebe a requisição e delega ao Service.                      |
| **`service`**        | Service / Domain | **Regra de Negócio** e validações. Orquestra o fluxo de dados.                                     |
| **`repository`**     | Repository / DAO | Faz a comunicação com o banco de dados via Spring Data JPA.                  |
| **`domain`**         | Domain           | Contém as Entidades (`Room`, `Reservation`) e enums.                                               |
| **`dtos`**           | DTOs             | Objetos de Transferência de Dados, usados para entrada e saída de dados.     |
| **`infrastructure`** | Infraestrutura   | Contém classes de configuração e tratamento de exceções.                                           |
| **`mapper`**         | Mappers          | Contém classes de mapeamento das entidades.                                                        |


## 🔑 Regras de Negócio Implementadas
1.  **Validação de Datas:** A `dataCheckOutPrevisto` deve ser posterior à `dataCheckInPrevisto`. Retorna `400 Bad Request`
2.  **Disponibilidade do Quarto:** Não é permitido criar reservas com datas sobrepostas para o mesmo quarto.Retorna `409 Conflict`.
3.  **Fluxo de Status da Reserva :** A transição de status segue o fluxo permitido (`CREATED` $\rightarrow$ `CHECKED_IN` $\rightarrow$ `CHECKED_OUT` ou `CREATED` $\rightarrow$ `CANCELED`). Qualquer transição incorreta gera `409 Conflict`.

## ⚙️ Como Executar o Projeto

### 1. Pré-requisitos
* Git, Maven e Docker instalados.

### 2. Configuração do Banco de Dados e Flyway
O projeto utiliza **PostgreSQL** e **Flyway**. A conexão é configurada no `application.yml` e o Flyway executa automaticamente o script `V1__init.sql` na inicialização, criando o *schema* e populando com dados de teste.

### 3. Execução via Docker Compose (Recomendado)

O `docker-compose.yml` irá orquestrar o banco de dados (PostgreSQL) e a aplicação Spring Boot.

1.  **Compilar a Aplicação:**
    ```bash
    mnv clean compile
    ```

2.  **Subir os Contêineres:**
    Acesse a pasta raiz do projeto e execute:
    ```bash
    docker-compose up --build -d
    ```
    *Isso criará a imagem do projeto, iniciará o contêiner do PostgreSQL e, em seguida, iniciará o contêiner da aplicação, que se conectará ao banco de dados e executará o Flyway.*

3.  **Acessar a Aplicação:**
    * **API:** `http://localhost:8080`
    * **Swagger UI (Documentação da API):** `http://localhost:8080/swagger-ui.html`

4.  **Parar a Aplicação:**
    ```bash
    docker-compose down
    ```


---
## 🧑‍💻 Autores
* **Nome(s):** `[Enzo Rodrigues (RM553377) | Rafael Cristofali (RM553521) | Hugo Santos (RM553266) | Maria Julia (RM553384)]`
* **Repositório:** `https://github.com/FI4P/DDD-SOA-CP02`