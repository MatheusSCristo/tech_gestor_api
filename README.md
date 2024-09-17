# Gestor de Semestres de TI - Backend

Este é o backend do projeto Gestor de Semestres de Tecnologia da Informação, responsável pelo gerenciamento de semestres, disciplinas e pré-requisitos. Ele expõe uma API REST que o frontend utiliza para realizar operações de CRUD (Criar, Ler, Atualizar, Deletar).

## Repositório do Frontend
- [Gestor de Semestres de TI - Frontend](https://github.com/MatheusSCristo/tech_front)


## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de APIs RESTful.
- **Spring Security**: Para autenticação e autorização de usuários.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos semestres, disciplinas e usuários.
- **JWT (JSON Web Tokens)**: Para a autenticação segura e persistente do lado do cliente.

## Funcionalidades

- **Gerenciamento de Semestres e Disciplinas**: APIs para criar, atualizar, excluir e listar semestres e disciplinas.
- **Validação de Pré-requisitos**: Regras para garantir que disciplinas não possam ser movidas para semestres que contenham matérias que dependem dela.
- **Autenticação e Autorização**: Sistema de login e controle de acesso utilizando JWT.
