## Spring boot 3.2 CRaC [^1]

### Limitations:
1. The feature is not included in OpenJDK version; you have to use JDK with CraC support, like Zulu [2]
2. The feature is available on Linux only. Azul Zulu Build of OpenJDK with CRaC support for MacOS and Windows only provides a simulated checkpoint/restore mechanism to be used for development and testing
3. SDK Manager on MacOS doesn't provide any Open JDK version with CRaC support.
4. The open JDK version with CRaC should be installed/extracted with sudo.
5. org.crac Maven/Gradle dependency
6. A folder location for storing the snapshot

1. Download and install the Azul Zulu Build of OpenJDK with CRaC support 

2. Download or pull the sample project from the Git Hub repository. Build the project as shown below:

```mvn clean package```

4. Run the application with the following command:

```java -XX:CRaCCheckpointTo=./checkpoint_store -jar ./target/spring-boot-crac-0.0.1-SNAPSHOT.jar```

5. On another terminal, run the following command 

```jcmd ./target/spring-boot-crac-0.0.1-SNAPSHOT.jar JDK.checkpoint```

6. Restore:

```java -XX:CRaCRestoreFrom=./checkpoint_store```

[^1]: Speed up the Java app startup time, part -2 : using CRaC with Spring boot 3.2 https://shamimbhuiyan.ru/blogs/speed-up-the-java-app-startup-time-part----using-crac-with-spring-boot-