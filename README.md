# Virtual Machine Systems

API para gerenciamento de máquinas virtuais, construída com **Spring Boot**, com suporte a banco de dados H2 para desenvolvimento rápido e PostgreSQL para produção.

A API possui endpoints documentados via **Swagger** e validações para garantir integridade dos dados.

---

## Tecnologias

- Java 25
- Spring Boot 4.0.1
- Spring Data JPA
- H2 (Dev)
- PostgreSQL (Prod)
- Swagger / OpenAPI (springdoc-openapi)
- Maven
- Lombok

---

## Funcionalidades

- CRUD de máquinas virtuais (`Machine`)
- Validações de entrada:
    - Nome não nulo, mínimo 5 caracteres
    - CPU, memória e disco positivos
- Controle de status (`STARTED`, `STOPPED`, `SUSPENDED`)
- Documentação Swagger

---

## Endpoints

A documentação completa está disponível via **Swagger UI** após rodar a aplicação:
* [**Swagger-UI**](http://localhost:8080/swagger-ui/index.html)


---

## Configuração do banco de dados

### Propriedades da Aplicação:
```properties
spring.application.name=vmsystems
spring.profiles.active=prod

#  dev - develop using: h2 in memory
#  prod - production using: postgresql
```


### Desenvolvimento (H2)

`application-dev.properties`:
```properties
#H2 Console - DEVELOPMENT
spring.datasource.url=jdbc:h2:mem:vmsystembd
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

#JPA and Hibernate Configuration
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### Produção (PostgreSQL)

`application-prod.properties`:
```properties
# POSTGRESQL - PRODUCTION
spring.datasource.url=jdbc:postgresql://localhost:5432/vmsystem_bd
spring.datasource.username=postgres
spring.datasource.password=2311
spring.datasource.driver-class-name=org.postgresql.Driver

#JPA and Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```
- Obs: Certifique-se de criar o banco vmsystem_bd no PostgreSQL antes de rodar a aplicação.

---

# Como iniciar a aplicação

1. **Clone o repositório:**
> Obs: Certifique-se de pegar a versão mais atual, v2-Authentication from branch
``` properties
https://github.com/lucasptrick/VirtualMachineSystems.git
```

2. **Escolher um dos perfis nas propriedades da aplicação:**
``` properties
src/main/resources/application.properties
```

3. **Iniciar aplicação pressionando o atalho ou através do terminal usando Maven:**
````properties
ctrl + F5   ||   mvn spring-boot:run  ou  .\mvn spring-boot:run
````

---

# Estrutura do Projeto
````markdown
src/
 └─ main/
     ├─ java/com/lpro/vmsystems/
     │   ├─ controller/     # Endpoints REST
     │   ├─ business/       # Lógica de negócio / services
     │   ├─ infrastruture/  # Entidades, repositórios
     │   └─ spring/config/  # Configurações, Swagger
     └─ resources/
         ├─ application.properties
         ├─ application-dev.properties
         └─ application-prod.properties

````

---
# Contato

- **Autor:** Lucas Oliveira

- **Email:** lucasptrick7@gmail.com

- **LinkedIn:** https://www.linkedin.com/in/lucasptrck/


---
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.1/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.1/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.1/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/4.0.1/reference/io/validation.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

