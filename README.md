# microservice
Spring Cloud Microservice with Yahoo Finance API 

DB_Service
----------
Mysql
JPA
Web
Acutator
DevTools
EurekaDiscovery

Note:
-----
1. @EnableJPARepository(basePackages = {repository packagename} -- All package have same hierarycy like com.robert.db.service, com.robert.db.ropository

2. extend JPARepsitory in repository interface

3. add the mysql connection properties in application.yml

4. add the jpa and hibernate properties in appication.yml

5. hibernate.ddl_auto : update( it will create the the table automatically if table doesn't exsist in the database)

6. create database in mysql 

7. enable autowire the repository insterface in service class

8. @EnableDiscoveryClient - It's used to register the eureka client to the eureka server


application.yml:
--------------
server:
  port: 8300
  
spring:
  application:
    name: db-service
    
  datasource:
    url: jdbc:mysql://localhost:3306/test
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver  

      test-while-idle: true
      validation-query: SELECT 1   
      
  jpa:
    show-sql: true
    
    hibernate:
      ddl-auto: update
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl
        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
   
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
        
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    healthcheck:
      enabled: true
    service-url:
      defaultZone: http://localhost:8271/myeureka/eureka
  instance:
    prefer-ip-address: true

management:
  endpoint:
    shutdown:
      enabled: true
  endpoints:
    web:
      exposure:
        include: "*"
    
    



Mysql:
------
shell> "C:\Program Files\MySQL\MySQL Server 5.0\bin\mysqld"
The path to mysqld may vary depending on the install location of MySQL on your system.

shell> "C:\Program Files\MySQL\MySQL Server 5.0\bin\mysqladmin" -u root shutdown

Stock_Service
----------
Web
Acutator
DevTools
EurekaDiscovery

Note
----
1. @EnableDiscoveryClient

2. Add YahooFinanceAPI dependency in pom.xml

3. calling the one service to another service using restTemplate (e.g calling db-service from stock service)
	@Autowired
	RestTemplate restTemplate;
	
		ResponseEntity<List<String>> response = restTemplate.exchange("http://db-service/rest/db/" + userName,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

4. create RestTemplate bean in Spring Configuration class
	1. create new Appconfig java
	2. @Configuration anotation is used to load the configuration file before spring container load
	3. create a restTemplate method return rest template object
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	

application.yml:
---------------
server:
  port: 8031
  
spring:
  application:
    name: stock-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    healthcheck:
      enabled: true
    service-url:
      defaultZone: http://localhost:8271/myeureka/eureka
  instance:
    prefer-ip-address: true

management:
  endpoint:
    shutdown:
      enabled: true
  endpoints:
    web:
      exposure:
        include: "*"
		
		
service_registery:
-----------------
web
devTools
acutator
eureka server

Note:
-------
1. add the below dependency for throwing unwanted error message
	jaxb-api
	activation
	jaxb-runtime
	jaxb-impl
	jaxb-core

2. @EnableEurekaServer

appication.yml
--------------
server:
  port: 8271
  servlet:
    context-path: /myeureka
  
spring:
  application:
    name: service-registery

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false




