package com.blu.std;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Hashtable;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
    private static final String PRODUCT_BASE_URI= "http://product-service/";

    // generate a few user profiles
    private static Hashtable<Long, ProfileDTO> userProfiles;

    @Autowired
    private WebClient.Builder webClientBuilder;


    static{
        userProfiles = new Hashtable<Long, ProfileDTO>(3);
        ProfileDTO user_1 = new ProfileDTO(1L, "Shallon Aroan","Active","VIP",new Date(System.currentTimeMillis()));
        ProfileDTO user_2 = new ProfileDTO(2L, "Kamenua Signous","Active","Standart", new Date(System.currentTimeMillis()));
        ProfileDTO user_3 = new ProfileDTO(3L, "Solavia","NaN", "VIP", new Date(System.currentTimeMillis()));
        ProfileDTO user_4 = new ProfileDTO(4L, "Stephani","Active","NaN",new Date(System.currentTimeMillis()));
        ProfileDTO user_5 = new ProfileDTO(5L, "Lola raina","Active","Standart", new Date(System.currentTimeMillis()));
        ProfileDTO user_6 = new ProfileDTO(6L, "Nana","NaN", "VIP", new Date(System.currentTimeMillis()));

        // add profile one by one
        userProfiles.put(1L, user_1);
        userProfiles.put(2L, user_2);
        userProfiles.put(3L, user_3);
        userProfiles.put(4L, user_4);
        userProfiles.put(5L, user_5);
        userProfiles.put(6L, user_6);
    }

    /***
     *
     * http://localhost:8282/getProfile?profileID=1
     *
     */
    @RequestMapping("/getProfile/{profileID}")
    public ProfileDTO getProfile(@RequestParam("profileID")long profileID ) {
        return userProfiles.get(profileID);

    }
    /**
     * Service chain:
     * choose a random products from catalog
     * select a price according to the profile category
     * If user status is VIP, give him/her a 3% discount
     **/
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/calculatePricing")
    public BigDecimal calculatePricing() {
        LOGGER.info("REST method calculatePricing invoked!");

        final String STAUS_VIP = "VIP";

        BigDecimal finalPrice = new BigDecimal(0);

        // get a random product between ID 1-6
        final int rnd = getRandomNumberInRange(6,1);
        final String GET_PRODUCTBY_URI = PRODUCT_BASE_URI+"getProductByID?productID="+rnd;
        // Pick a random Product
        try {
            ProductDTO product = webClientBuilder.build().get().uri(GET_PRODUCTBY_URI).retrieve().bodyToMono(ProductDTO.class).block();

            LOGGER.info("Product=" + product.toString());
            // get a random user profile
            ProfileDTO user = getProfile(rnd);
            LOGGER.info("User=" + user.toString());

            if (user.getProfileCategory().equalsIgnoreCase(STAUS_VIP)){
                final double price = product.getProductPrice().doubleValue() <=1.0?0:(product.getProductPrice().doubleValue() * 3)/100;
                finalPrice = new BigDecimal(price);
                LOGGER.info("Least price ="+ product.getProductPrice().toString());
                LOGGER.info("Final price ="+ finalPrice.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalPrice;

    }
    private static int getRandomNumberInRange(int max, int min) throws IllegalArgumentException{
        if(max < min)
            throw new IllegalArgumentException("argument %max must be greater than %min");

        return new Random().nextInt((max-min)+1) +min;
    }
    private BigDecimal fallback(Throwable hystrixCommand){

        return new BigDecimal(0);
    }

}