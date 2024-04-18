FROM ubuntu:22.04 as builder

ADD https://cdn.azul.com/zulu/bin/zulu21.30.23-ca-crac-jdk21.0.1-linux_x64.tar.gz /opt/
RUN cd /opt/ && tar -xzf zulu21.30.23-ca-crac-jdk21.0.1-linux_x64.tar.gz && rm -rf zulu21.30.23-ca-crac-jdk21.0.1-linux_x64.tar.gz
FROM ubuntu:22.04
COPY --from=builder /opt/zulu21.30.23-ca-crac-jdk21.0.1-linux_x64 /opt/zulu21.30.23-ca-crac-jdk21.0.1-linux_x64
ENV JAVA_HOME /opt/zulu21.30.23-ca-crac-jdk21.0.1-linux_x64
ENV PATH $JAVA_HOME/bin:$PATH

# give the root privilage to run CRAC
RUN chown root:root $JAVA_HOME/lib/criu
RUN chmod u+s $JAVA_HOME/lib/criu

COPY target/spring-boot-crac-0.0.1.jar /app/spring-boot-crac-0.0.1.jar

COPY src/scripts/entrypoint.sh /app/entrypoint.sh
RUN chmod 755 /app/entrypoint.sh
