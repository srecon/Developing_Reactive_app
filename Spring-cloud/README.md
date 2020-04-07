# microservices-nutsandbolts
### run
1. Build the entire project first
2. Run Eureka: java -jar ./target/springboot-eureka-server-1.0-SNAPSHOT.jar
3. Run Zuul Server : java -jar ./target/springboot-zuul-server-1.0-SNAPSHOT.jar
4. Run Profile-service: java -jar -Dserver.port=8281 ./target/springboot-profile-service-1.0-SNAPSHOT.jar
5. Run Product-service (a few instance): java -jar -Dserver.port=8181 ./target/springboot-product-service-1.0-SNAPSHOT.jar

Use Postman/CURL or browser to invoke the REST API:

http://ZUUL_HOST:ZUUL_PORT/profile/calculatePricing

for example:

http://localhost:8762/profile/calculatePricing
