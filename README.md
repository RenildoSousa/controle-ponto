<h1  align="center">Desafio Backend | Spring Boot</h1>  

## Descrição do Projeto:

<p  align="justify"> Crie uma web API para controle de ponto, seguindo o contrato, que permite realizar as
seguintes ações:</p> 

### Registrar os horários da jornada diária de trabalho:

- [x] Apenas 4 horários podem ser registrados por dia.

- [x] Deve haver no mínimo 1 hora de almoço.

- [x] Sábado e domingo não são permitidos como dia de trabalho.

- ### Validações:
- [X] Horário já registrado.
- [X] Data e hora em formato inválido.
- [X] Campo obrigatório não informado.

## Tecnologias utilizadas:

- Java 11;
- Spring Boot;
- Spring Data JPA;
- H2 Database in-memory;

### Endpoints:

Para inserção de uma data, recomendo utilizar uma API Client, tais como **Postman** ou **Insomnia**
> Registrar um ponto
> **POST** http://localhost:8080/batidas
>
> Obs*: Registrar ponto através do formato JASON

> Listar pontos
> **GET** http://localhost:8080/batidas
>
> Obs*: Criado apenas para visualizar todos os horarios

> Buscar ponto por id
> **GET** http://localhost:8080/batidas/{id}
>
> Obs*: Criado para buscar um unico ponto por id

### URL  Swagger UI:
>http://localhost:8080/swagger-ui.html