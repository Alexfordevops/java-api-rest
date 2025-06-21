# API java com spring security + docker secrets

## Features do projeto

- Api restful spring boot
- Spring JPA
- Spring security
- Token JWT
- Exception Handlers
- Dto’s
- Validators
- Banco de dados Postgres
- Docker secrets
- Orquestração Docker-compose
- Scripts de gerenciamento bash

## Endpoints da API

POST http://localhost:8080/auth/register → Registrar usuário

POST http://localhost:8080/auth/login → Realizar login e obter token JWT

GET http://localhost:8080/api/clients → Listar todos os clientes

GET http://localhost:8080/api/clients/{id} → Obter cliente por id

DELETE http://localhost:8080/api/clients{id} → Deletar cliente por id

PUT http://localhost:8080/api/clients/{id} → Atualizar cliente por id

## Passos para executar a aplicação

1. Instalar Docker e Docker compose
2. Clonar o repositório 
3. Iniciar o docker swarm → `docker swarm init`
4. Executar o docker compose → `docker compose up --build`