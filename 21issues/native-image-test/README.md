## Native Image creation on Spring boot 3.2

### Option 1. 
1. Install Docker desktop on local machine
2. Build the image with the `native` profile active
    ```$ mvn -Pnative spring-boot:build-image```
Wait for a while to build and pull the image to repository
3. Create a container and run it by the following command
   ```docker run --rm -p 8080:8080 native-image-test:0.0.1-SNAPSHOT```
4. Invoke the service as shown below:
   ```curl http://192.168.1.143:8080/customers```

### Option 2.
