# EBI01951 - Technical Test

This application was created by Osman Gunes for EMBL-EBI Java developer job role.
Clone Application From github 
```bash
git clone https://github.com/osmgunes/TechnicalAssignment.git
```
get docker postgresql db image with docker-compase.yml file using terminal.
```bash
docker-compose -f .\docker-compose.yml up
```
Now can run app.


## Technologies

- JAVA 11
- Springboot
- Maven
- Postgresql
- Junit


## End Points And Functionalities(GET)

- Find Mouse Gene Symbol And Synonym by Symbol
```bash
localhost:8080/mouse/symbol/{value}
```
- Find Mouse Gene Symbol And Synonym by Synonym
```bash
localhost:8080/mouse/synonym/{value}
```
- Interrogate Relation Between Mouse and Human By Mouse Gene Symbol
```bash
localhost:8080/relation/symbol/{field}
```
- Interrogate Relation Between Mouse and Human By Mouse Gene Identifier
```bash
localhost:8080/relation/identifier/{field}
```
- Find Map Of Multiple Human Genes With Support Count
```bash
localhost:8080/relation/MouseGeneSymbol/{field}
```
## Authentication

This Application contains spring security basic authentication
In order to send a request, You must have permission
You can send a request by using these predefined users.

- User:osman, password:pass123
- User:James, password:pass123
- User:tudor, password:pass123
- User:Robert, password:pass123
- User:Csaba, password:pass123


## Project Tree


```bash
C:.
├───main
│   ├───java
│   │   └───com
│   │       └───ebi
│   │           └───TechnicalAssignment
│   │               ├───Constant
│   │               ├───controller
│   │               ├───dto
│   │               ├───exception
│   │               ├───model
│   │               │   ├───human
│   │               │   ├───mouse
│   │               │   └───ortholog
│   │               ├───repository
│   │               ├───security
│   │               └───service
│   └───resources
│       ├───static
│       └───templates
└───test
    ├───java
    │   └───com
    │       └───ebi
    │           └───TechnicalAssignment
    │               ├───config
    │               ├───service
    │               └───tesdata
    └───resources
        └───db
            └───h2
                └───data
```

## Unit Testing

96% coverage unit testing on service classes


```bash
─TechnicalAssignment
               │   TechnicalAssignmentApplicationTests.java
               │
               ├───config
               │       TestConfig.java
               │
               ├───service
               │       MouseGeneSynonymRelationServiceTest.java
               │       OrthologServiceTest.java
               │
               └───tesdata
                       TestData.java
```

## Integration Testing

Integration test added by using in-memory h2 database created successfully
schema and table loading successfully by using TestConfig.java file 
```bash
C:.
│   application-test.properties
│
└───db
    └───h2
        │   schema.sql
        │
        └───data
                human_gene.sql
                mouse_gene.sql
                mouse_gene_synonym.sql
                mouse_gene_synonym_relation.sql
                ortholog.sql
```
