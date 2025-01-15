
# Gestor de Semestres de TI - Backend

Este é o backend do projeto **Gestor de Semestres de Tecnologia da Informação**, responsável pelo gerenciamento de semestres, disciplinas e pré-requisitos. Ele expõe uma API REST que o frontend utiliza para realizar operações de CRUD (Criar, Ler, Atualizar, Deletar).

### Tecnologias Utilizadas

-   **Java**: Linguagem de programação principal.
-   **Spring Boot**: Framework para construção de APIs RESTful.
-   **Spring Security**: Para autenticação e autorização de usuários.
-   **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos semestres, disciplinas e usuários.
-   **JWT (JSON Web Tokens)**: Para a autenticação segura e persistente do lado do cliente.

### Funcionalidades

1.  **Gerenciamento de Semestres e Disciplinas**: APIs para criar, atualizar, excluir e listar semestres e disciplinas.
2.  **Validação de Pré-requisitos**: Regras para garantir que disciplinas não possam ser movidas para semestres que contenham matérias que dependem dela.
3.  **Autenticação e Autorização**: Sistema de login e controle de acesso utilizando JWT.

----------

## **Serviços Principais**

### **UserService**

O `UserService` é responsável pelo gerenciamento de usuários. Ele lida com a criação de usuários, validação de e-mail, e estrutura associada ao usuário.

#### **Principais Responsabilidades**

1.  **Buscar usuário por email ou ID**: Localiza o usuário a partir do seu e-mail ou ID.
2.  **Criar usuário**: Cria um novo usuário a partir de um DTO, validando o e-mail e a data de início.
3.  **Autenticação com Google**: Cria um usuário com as informações fornecidas pelo Google.
4.  **Definir semestres padrão para o usuário**: Associa semestres padrão com base na estrutura do curso do usuário.

#### **Dependências**

-   **UserRepository**: Para acessar e salvar usuários no banco de dados.
-   **StructureService**: Para buscar e associar a estrutura correta ao usuário.
-   **PasswordEncoder**: Para criptografar senhas.
-   **SubjectService**: Para gerenciar disciplinas.
-   **TeacherService**: Para associar professores às disciplinas.
-   **SemesterUserRepository**: Para gerenciar as associações de semestres com os usuários.

### **SemesterUserService**

O `SemesterUserService` é responsável por gerenciar os semestres associados aos usuários. Ele lida com operações de criação, atualização, redefinição e exclusão de semestres de um usuário.

#### **Principais Responsabilidades**

1.  **Salvar semestres de um usuário**: Atualiza os semestres de um usuário, associando as disciplinas e professores informados.
2.  **Obter todos os semestres de um usuário**: Recupera todos os semestres associados a um usuário.
3.  **Obter semestre específico**: Retorna um semestre específico baseado no seu ID.
4.  **Resetar semestres para valores padrão**: Redefine os semestres do usuário para os valores padrão com base na estrutura do curso.
5.  **Excluir semestres**: Remove todos os semestres associados a um usuário.

#### **Dependências**

-   **SemesterUserRepository**: Para gerenciar semestres no banco de dados.
-   **UserRepository**: Para buscar usuários.
-   **SubjectService**: Para buscar ou criar disciplinas.
-   **SemesterSubjectService**: Para criar e associar disciplinas aos semestres.
-   **TeacherService**: Para associar professores às disciplinas.

----------

## **Tratamento de Exceções**

### **ExceptionsHandler**

O `ExceptionsHandler` é um controlador global de exceções que captura erros e os formata de maneira padronizada. Ele utiliza `@RestControllerAdvice` para interceptar e tratar diversas exceções lançadas durante o processamento das requisições.

#### **Exceções Tratadas**

-   **`UserNotFoundException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`EmailAlreadyRegisteredException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`StructureNotFoundException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`TeacherNotFoundException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`SemesterUserNotFoundException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`SubjectNotFoundException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`MethodArgumentNotValidException`**: Retorna código HTTP **400** com a mensagem de erro do campo inválido.
-   **`BadCredentialsException`**: Retorna código HTTP **400** com uma mensagem de erro nas credenciais.
-   **`HttpMessageNotReadableException`**: Retorna código HTTP **400** com mensagem de erro ao deserializar objeto.
-   **`StartTimeNotValidException`**: Retorna código HTTP **400** e a mensagem de erro.
-   **`GoogleEmailException`**: Retorna código HTTP **401** para erros na autenticação com Google.
-   **`RuntimeException`**: Retorna código HTTP **500** com o stack trace da exceção.

#### **Dependências**

-   **RestErrorMessage**: Formata a mensagem de erro retornada na resposta.
-   **Exceções Personalizadas**: `UserNotFoundException`, `EmailAlreadyRegisteredException`, etc.

----------

## **Fluxo de Funcionamento**

1.  **Criação de Usuário**: Quando um novo usuário é criado, ele passa por validações de e-mail, data de início e estrutura. Se estiver tudo correto, ele é salvo com semestres padrão baseados na sua estrutura.
    
2.  **Gerenciamento de Semestres**: O sistema permite que os semestres de um usuário sejam atualizados, resetados para valores padrão ou excluídos. Cada semestre pode ter múltiplas disciplinas associadas, cada uma com um professor.
    
3.  **Autenticação e Autorização**: A autenticação é realizada utilizando JWT, garantindo que as requisições sejam feitas por usuários autenticados.
    
4.  **Tratamento de Erros**: Qualquer exceção durante o processamento de uma requisição é capturada pelo `ExceptionsHandler`, retornando uma resposta padronizada de erro.# Gestor de Semestres de TI - Backend

Este é o backend do projeto **Gestor de Semestres de Tecnologia da Informação**, responsável pelo gerenciamento de semestres, disciplinas e pré-requisitos. Ele expõe uma API REST que o frontend utiliza para realizar operações de CRUD (Criar, Ler, Atualizar, Deletar).

### Tecnologias Utilizadas

-   **Java**: Linguagem de programação principal.
-   **Spring Boot**: Framework para construção de APIs RESTful.
-   **Spring Security**: Para autenticação e autorização de usuários.
-   **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos semestres, disciplinas e usuários.
-   **JWT (JSON Web Tokens)**: Para a autenticação segura e persistente do lado do cliente.

### Funcionalidades

1.  **Gerenciamento de Semestres e Disciplinas**: APIs para criar, atualizar, excluir e listar semestres e disciplinas.
2.  **Validação de Pré-requisitos**: Regras para garantir que disciplinas não possam ser movidas para semestres que contenham matérias que dependem dela.
3.  **Autenticação e Autorização**: Sistema de login e controle de acesso utilizando JWT.

----------
