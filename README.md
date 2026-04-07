# 🚀 JavaStore - Microservices Ecosystem

Bem-vindo ao **JavaStore**, um ecossistema de e-commerce robusto construído com a stack Java mais moderna. Este projeto foca em escalabilidade, segurança centralizada e comunicação assíncrona, aplicando princípios de **Clean Architecture** e **SOLID**.

---

## 🏗️ Arquitetura do Sistema

O projeto é baseado em uma arquitetura de microsserviços, onde cada serviço possui sua própria responsabilidade e banco de dados isolado (**Padrão Database per Service**).



### Componentes do Ecossistema:

* **API Gateway (Porta 8080):** O único ponto de entrada para o mundo externo. Gerencia o roteamento e a autenticação centralizada.
* **Identity Service (Porta 8081):** Responsável pelo cadastro de usuários, login e geração de tokens JWT assinalados com HMAC256.
* **Catalog Service (Porta 8082):** Gerencia a vitrine de produtos e escuta eventos de baixa de estoque via mensageria.
* **Orders Service (Porta 8083):** Processa pedidos, recupera a identidade do usuário via Headers injetados pelo Gateway e notifica o estoque via RabbitMQ.
* **Frontend (Porta 5173):** Interface moderna em React + TypeScript com Vite.

---

## 🛠️ Tecnologias Utilizadas

### **Back-end**
* **Java 23 (Valhalla Project):** Performance de ponta e recursos modernos da linguagem.
* **Spring Boot 4.0.5:** Framework principal para o desenvolvimento dos microsserviços.
* **Spring Cloud Gateway:** Roteamento dinâmico e filtros de autenticação.
* **Spring Data JPA / Hibernate 7:** Persistência de dados e mapping objeto-relacional.
* **PostgreSQL:** Banco de dados relacional (instâncias isoladas via Docker).
* **RabbitMQ:** Message Broker para comunicação assíncrona e desacoplada.

### **Front-end**
* **React + TypeScript:** UI tipada, segura e performática.
* **Vite:** Tooling ultra-rápido para desenvolvimento web.
* **Axios:** Consumo de APIs com interceptors para injeção automática de JWT.

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Docker e Docker Compose instalados.
* Java JDK 23.
* Node.js (v20+).

### 1. Subindo a Infraestrutura (Banco e Mensageria)
Na raiz do workspace, onde está o arquivo `docker-compose.yml`, execute:
```bash
docker-compose up -d
```
## 🚀 2. Rodando os Microsserviços

No IntelliJ IDEA, utilize a configuração **Compound** para iniciar os serviços simultaneamente ou inicie na seguinte ordem:

1.  `identity-service`
2.  `gateway-service`
3.  `catalog-service` e `orders-service`

---

## 💻 3. Subindo o Front-end

```bash
cd frontend
npm install
npm run dev
```

## 🔐 Fluxo de Autenticação e Segurança

1. **Login:** O usuário realiza o login no **Identity Service** através do Gateway.
2. **Validação:** O serviço valida as credenciais (senha criptografada com **BCrypt**) e retorna um **token JWT**.
3. **Interceptação:** Em cada requisição subsequente para rotas protegidas (como `/api/orders`), o Gateway valida o token no `AuthenticationFilter`.
4. **Propagação de Contexto:** O Gateway extrai o e-mail do usuário do token e o injeta no header HTTP `X-User-Email` antes de repassar a requisição para o microsserviço de destino.

> [!IMPORTANT]
> **Nota de Engenharia:** Essa abordagem permite que os serviços de negócio permaneçam agnósticos ao método de autenticação, confiando apenas no contexto propagado pelo Gateway.

---

### 👨‍💻 Autor
**Lucas Sabino da Silva** *Estudante de Engenharia de Software - 4º Período @ UniSenai (Curitiba-PR)*
