package com.blu.std;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class DemoController {

    @Autowired
    private Ignite ignite;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
    private static final String QUOTE_CACHE_NAME= "quote";

    @RequestMapping("/")
    public String index() {
        return "Spring Boot Hello-world application for demo!";
    }

    @RequestMapping("/getQuote")
    public String getQuote() {
        LOGGER.info("getQuote method executes");

        final int ID = new Random().nextInt(4)+1;

        IgniteCache<Long, String> cache_quote = ignite.cache(QUOTE_CACHE_NAME);
        // Query the table QUOTE
        String qry = "Select * from \"quote\".QUOTE q where q.ID=?";

        List<List<?>> rows = cache_quote.query(new SqlFieldsQuery(qry).setArgs(ID)).getAll();

        return rows.get(0).get(1).toString();
    }

}