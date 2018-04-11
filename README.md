# currency-microservices

//Spring cloud configuration
http://localhost:8888/limits-service/default
http://localhost:8888/limits-service/dev
http://localhost:8888/limits-service/qa

//Limit Service
http://localhost:8080/limits

//CURRENCY EXCHANGE SERVICE
//Default port 8000
http://localhost:8000/currency-exchange/from/AUD/to/BLR

//To configure more than one, you just need to go to Eclipse -> Run -> Run Configurations 
//duplicate the currency-exchange-service and on "Arguments" Tab "VM Arguments" set the port to run other instance "-Dserver.port=8001"
http://localhost:8001/currency-exchange/from/AUD/to/BLR

//CURRENCY-CONVERSION-SERVICE
// this URL is without using feign, just to see how much code you need to do that.
http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10000

//This URL is using feign 
http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/10000

//You can check the details in: "/currency-conversion-service/src/main/java/com/andrei/microservices/controller/CurrencyConversionController.java" 

//NETFLIX EUREKA NAMING SERVER
http://localhost:8761

//NETFLIX ZUUL GATEWAY 
//url pattern for Netflix zuul http://localhost:8765/{app-name}/{uri}
//This URL "http://localhost:8000/currency-exchange/from/AUD/to/BLR" cames to using ZUUL:
http://localhost:8765/currency-exchange-service/currency-exchange/from/AUD/to/BLR       

//this URL "http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/10000" cames to using ZUUL:
http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/1000

To configure Zipkin you need to install rabbitmq-server, if you are using Ubuntu just execute "sudo apt-get install rabbitmq-server"
http://localhost:9411/zipkin/

// When you need to update all instances using a command, if you change your limit service property, you just execute the URL below to update it for all servers 
http://localhost:8080/actuator/bus-refresh

//Fault Tolerance with Hytrix if something goes wrong with your service you can throw an exception and call another method to send some default values to your application.
http://localhost:8081/fault-tolerance-sample
