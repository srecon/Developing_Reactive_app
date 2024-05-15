package com.blu.junodb.javaexamples;

import com.paypal.juno.client.JunoClient;
import com.paypal.juno.client.JunoClientFactory;
import com.paypal.juno.client.io.JunoResponse;
import com.paypal.juno.exception.JunoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

@Component
public class DAOImpl implements DAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(DAOImpl.class);
    @Inject
    private JunoClient junoClient;

    @Override
    public String getEntryByKey(String key) {
        LOGGER.info("getEntry method invoked!");
        LOGGER.info("key-> " + key);
        String val="";
        try{
            JunoResponse junoResponse = junoClient.get(key.getBytes(StandardCharsets.UTF_8));
            LOGGER.info("Success! Entry get");

            if (junoResponse!=null){
                val = new String(junoResponse.getValue());
                LOGGER.info("Response-> "+ val);
            } else {
                LOGGER.error("Response null!");
            }
        } catch (JunoException e) {
            LOGGER.error("Exception -> " + e.toString());
        }

        return val;
    }

    @Override
    public void addEnrty(String key, String value) throws JunoException {
        LOGGER.info("key-> " + key);
        LOGGER.info("Value-> " + value);

        try{
            JunoResponse junoResponse = junoClient.create(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
            LOGGER.info("Success! Entry Added.");

            if (junoResponse!=null){
                LOGGER.info("Response-> "+ junoResponse.getStatus().toString());

            } else {
                LOGGER.error("Response null!");
            }
        } catch (JunoException e) {
            LOGGER.error("Exception -> " + e.toString());
        }


    }
}
