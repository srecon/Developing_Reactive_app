## Native Image creation on Spring boot 3.2 [^1]

### Option 1. Spring buildpacks with Maven

With a single Maven command you will get a docker image on your local docker daemon. The image will not contain any JVM rather than static binary to run on the machine directly.

1. Install Docker desktop on local machine
2. Build the image with the `native` profile active
    ```$ mvn -Pnative spring-boot:build-image```
Wait for a while to build and pull the image to repository
3. Create a container and run it by the following command
   ```docker run --rm -p 8080:8080 native-image-test:0.0.1-SNAPSHOT```

Result:

```2024-04-04T09:19:50.712Z  INFO 1 --- [           main] c.b.r.n.NativeImageTestApplication       : Started NativeImageTestApplication in 0.164 seconds (process running for 0.174)```

4. Invoke the service as shown below:
   ```curl http://192.168.1.143:8080/customers```

### Option 2. GraalVM Native build tool.

GraalVM native build tools for Maven to generate native image.

1. Install Java GraalVM

```sdk install java 21.0.2-graalce```
```sdk use 21.0.2-graalce```

2. Check the Java verison

```java --version```

4. Generate the native image with the profile `-Pnative`

```mvn -Pnative native:compile```

The native image executable file will be found in the `targer` directory. In my case, the name of the native-image is `native-image-test`, file size is 80,9 MB.

5. Run the executable file 

```./target/native-image-test```

Result:

```2024-04-04T12:30:35.461+03:00  INFO 8399 --- [           main] c.b.r.n.NativeImageTestApplication       : Started NativeImageTestApplication in 0.07 seconds (process running for 0.081)```

6. Invoke the service as shown below:

```curl http://192.168.1.143:8080/customers```

[^1]: GraalVM Native Image Support https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html