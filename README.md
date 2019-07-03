#Exchange API


### Requirements
* java 8.0
* maven

### Build Project
```bash
mvn spring-boot:run
```
### Build Tests
#####To run all tests
```bash
mvn test
```
#####To run one specific test

```bash
mvn -Dtest=CurrencyControllerTest test
```
## Database
Database file will be stored ~/exchange-WF

It will be automatically deployed every time you build the project

Migration is in file src/main/resources/Data.sql

To see it on your local machine http://localhost:8080/h2-console/
set the JDBC URL to:
 
 jdbc:h2:~/exchange-WF

    user sa
    password 

[For more information](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)

## End points
### UI
All the end points are listed here:

[http://localhost:8080/swagger-ui.html]()

##### 1.currencies
**GET** 

[http://localhost:8080/v1/currencies]()

##### 2.rates
**GET** 

[http://localhost:8080/v1/rates]()

##### 3.transactions
**GET** 

[http://localhost:8080/v1/transactions/orderStatus/1]()

**POST** 

[http://localhost:8080/v1/transactions]()

    {
    "userId":1,
    "amount":100,
    "currencyAnnotationId":1,
    "orderStatusId":3,
    "orderTypeId":2
    }

**PATCH** 

[http://localhost:8080/v1/transactions]()

    {
      "userId":2,
      "orderStatusId":2,
      "orderId":2
    }
    
## Wiremock Integration Tests
Uses 2 folders in the resources __files and mappings
* **__files** folder contains body of the HTTP response
* **mappings** folder contains HTTP request