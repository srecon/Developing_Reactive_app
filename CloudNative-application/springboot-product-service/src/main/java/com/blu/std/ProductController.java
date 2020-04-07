package com.blu.std;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Hashtable;


@RestController
public class ProductController {
    private static Hashtable<Long, ProductDTO> PRODUCTS;
    static{
        PRODUCTS = new Hashtable<Long, ProductDTO>(5);
        ProductDTO creditCardPlatinum = new ProductDTO(1l, "Black Edition", "Credit Card Platinum",new BigDecimal(101),"Active");
        ProductDTO DebitCardExpress = new ProductDTO(2l, "No Name Express debit card", "Credit Card Platinum",new BigDecimal(0),"Active");
        ProductDTO Deposit = new ProductDTO(3l, "Deposit", "Deposit with 1% rate",new BigDecimal(1),"InActive");
        ProductDTO BrokerAccount = new ProductDTO(4l, "Broker Account", "Broker account with LCG",new BigDecimal(19),"Active");
        ProductDTO CurrentAccount = new ProductDTO(5l, "Current Account", "Current accout",new BigDecimal(0.1),"InActive");

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