package com.blu.std;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductDTO implements Serializable {
    private long productID;
    private String productName;
    private String productDesc;
    private BigDecimal productPrice;
    private String productStatus;

    public ProductDTO(long productID, String productName, String productDesc, BigDecimal productPrice, String productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
    }
    public ProductDTO(long productID, String productName, BigDecimal productPrice){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    public ProductDTO() {
    }


    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return productID == that.productID &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(productStatus, that.productStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productPrice, productStatus);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productPrice=" + productPrice +
                ", productStatus='" + productStatus + '\'' +
                '}';
    }
}
