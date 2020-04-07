package com.blu.std;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Hashtable;


@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private Environment env;

    private static Hashtable<Long, ProductDTO> PRODUCTS;

    static{
        PRODUCTS = new Hashtable<Long, ProductDTO>(5);
        ProductDTO creditCardPlatinum = new ProductDTO(1l, "Black Edition", "Credit Card Platinum",new BigDecimal(1001),"Active");
        ProductDTO DebitCardExpress = new ProductDTO(2l, "No Name Express debit card", "Credit Card Platinum",new BigDecimal(703),"Active");
        ProductDTO Deposit = new ProductDTO(3l, "Deposit", "Deposit with 1% rate",new BigDecimal(147),"InActive");
        ProductDTO BrokerAccount = new ProductDTO(4l, "Broker Account", "Broker account with LCG",new BigDecimal(197),"Active");
        ProductDTO CurrentAccount = new ProductDTO(5l, "Current Account", "Current accout",new BigDecimal(337),"InActive");

        PRODUCTS.put(1L, creditCardPlatinum);
        PRODUCTS.put(2L, DebitCardExpress);
        PRODUCTS.put(3L, Deposit);
        PRODUCTS.put(4L, BrokerAccount);
        PRODUCTS.put(5L, CurrentAccount);

    }
    /**
     * http://localhost:8181/getProductByID?productID=1
     * **/
    @RequestMapping("/getProductByID{productID}")

    public ProductDTO getProductByID(@RequestParam("productID")long productID) {
        LOGGER.info("Product service running on port:" + env.getProperty("local.server.port"));
        if (!PRODUCTS.containsKey(productID)) {
            return new ProductDTO(0L,"NOT EXISTS", new BigDecimal(0));
        }

        return PRODUCTS.get(productID);
    }
//    @RequestMapping("/getAllProducts")
//    public Hashtable<Long, ProductDTO> getAllProducts(){
//        return PRODUCTS;
//    }

}