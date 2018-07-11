# Protocol Buffers Serializer

This app exposes one REST endpoint. 

- http://localhost:8080/collector/event
    - <-- it takes in a json Event object 
    - serializes it using protobuf 
    - --> sends it over HTTP POST to an external service

To test it see Swagger API doc (here)[http://localhost:8080/collector/swagger-ui.html] 

### Technical description
- App is built on top of spring boot
- src/main/proto compiling is performed using protobuf-maven-plugin 
- EventController is the REST endpoint mapped to /event. It is mapped to HTTP POST to create an event
     * it saves the event to an EventRepository
     * the implementation for the Repository is the C++ service 
         * is serializes the event to protobuf
         * the stream is sent over HTTP POST to the persistence service
- check src/main/resources/application.properties for detailed app settings (app port, web context, c++ service url)
- tests validate protobuf serialization and REST endpoint execution

### Execution
- app requires maven +3.1
- build test run ```mvn clean test spring-boot:run```
- REST API is at http://localhost:8080/collector/event
- swagger allows API execution
- curl execution
```  
curl -X POST -i http://localhost:8080/collector/event \
       -H 'Content-Type: application/json'  \
       --data '{ "userId":1, "timestamp":2, "event":"event description" }' 
```

