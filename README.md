# Protocol Buffers Serializer

This is a spring boot app. 

It exposes one REST endpoint which accepts a json Event object. It then serializes it to protobuf and sends it using HTTP POST to an external service. See swagger API doc for more details.

### Description
- spring boot creates executable jar
- protobuf-maven-plugin compiles src/main/proto
- check src/main/resources/application.properties for detailed app settings

### Execution
- build test run ```mvn clean test spring-boot:run```
- http://localhost:8080/collector/swagger-ui.html 

