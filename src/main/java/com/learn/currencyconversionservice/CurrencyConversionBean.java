package com.learn.currencyconversionservice;

import java.math.BigDecimal;

/**
 * Created by MACHUNAGENDRADURGP on 21/05/2018.
 */
public class CurrencyConversionBean {

     private Long id;

     private String from;

     private String to;

     private BigDecimal conversionMultiple;

     private BigDecimal totalPrice;

     private int port;

    public CurrencyConversionBean(){

    }
    public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal totalPrice,int port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.totalPrice = totalPrice;
        this.port = port;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
