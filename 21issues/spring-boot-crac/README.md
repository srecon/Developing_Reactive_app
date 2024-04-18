## Spring boot 3.2 CRaC [^1]

### Limitations:
1. The feature is not included in OpenJDK version; you have to use JDK with CraC support, like Zulu [2]
2. The feature is available on Linux only. Azul Zulu Build of OpenJDK with CRaC support for MacOS and Windows only provides a simulated checkpoint/restore mechanism to be used for development and testing
3. SDK Manager on MacOS doesn't provide any Open JDK version with CRaC support.
4. The open JDK version with CRaC should be installed/extracted with sudo.
5. org.crac Maven/Gradle dependency
6. A folder location for storing the snapshot

### Steps:

1. Download and install the Azul Zulu Build of OpenJDK with CRaC support 

2. Download or pull the sample project from the Git Hub repository. Build the project as shown below:

```mvn clean package```

3. Run the application with the following command:

```java -XX:CRaCCheckpointTo=./checkpoint_store -jar ./target/spring-boot-crac-0.0.1-SNAPSHOT.jar```

4. On another terminal, run the following command 

```jcmd ./target/spring-boot-crac-0.0.1-SNAPSHOT.jar JDK.checkpoint```

5. Restore:

```java -XX:CRaCRestoreFrom=./checkpoint_store```

## Deploy on kubernates (minikube)

### Steps:

1. Download and install the Azul Zulu Build of OpenJDK with CRaC support

2. Download or pull the sample project from the Git Hub repository. Build the project as shown below:

```mvn clean package```

3. Create a docker image. Run the following command from the root directory of the project

```docker build -t spring-boot-crac:0.0.1 .```

4. Create a PersistentVolumeClaim on kubernates cluster
 
```kubectl apply -f ./k8s/PersistenceVolumeClaim.yaml```

5. Create a Job to create the snapshot of the Spring boot application

```kubectl apply -f ./k8s/job.yaml```

6. Create containers based on the snapshot from the persistence store.

```kubectl apply -f ./k8s/deployment-crac.yaml```


[^1]: Speed up the Java app startup time, part -2 : using CRaC with Spring boot 3.2 https://shamimbhuiyan.ru/blogs/speed-up-the-java-app-startup-time-part----using-crac-with-spring-boot-
[^2]: Speed up the Java app startup time, part -3 : deploying on Kubernates https://shamimbhuiyan.ru/blogs/speed-up-the-java-app-startup-time-part--3--deploying-on-kubernates