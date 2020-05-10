package com.blu.std;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Springboot extension for Apache Ignite example
 * java -jar -Dserver.port=8888 ./target/springboot-ext-for-ignite-1.0-SNAPSHOT.jar
 */
@SpringBootApplication
public class App
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        LOGGER.info("Spring Boot Hello-World app runs!");
        SpringApplication.run(App.class);
    }

    /**
     * Spring CommandLineRunner interface for initializing the table
     * Following snippet will run once after application startup
     **/
    @Bean
    public CommandLineRunner runner(){
        return new CommandLineRunner() {
            @Autowired
            private Ignite ignite;
            @Override
            public void run(String... args) throws Exception {
                LOGGER.info("Test Ignite node setup and run some queries!");
                LOGGER.info("IgniteInstanceName: " + ignite.configuration().getIgniteInstanceName());

                LOGGER.info("Init the table QUOTE!!");
                // get the cache "quote" from the application.yml
                IgniteCache<Long, String> cache_quote = ignite.cache("quote");

                String qry = "MERGE into QUOTE(ID, VAL) values (?, ?)";
                // insert a few rows
                cache_quote.query(new SqlFieldsQuery(qry).setArgs(1L,"Today you are you!")).getAll();
                cache_quote.query(new SqlFieldsQuery(qry).setArgs(2L,"Today was good.")).getAll();
                cache_quote.query(new SqlFieldsQuery(qry).setArgs(3L,"Today is the only day.")).getAll();
                cache_quote.query(new SqlFieldsQuery(qry).setArgs(4L,"What is not started today is never finished tomorrow.")).getAll();
            }
        };
    }
}
