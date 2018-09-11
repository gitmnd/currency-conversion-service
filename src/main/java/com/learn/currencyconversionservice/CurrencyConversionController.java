package com.learn.currencyconversionservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MACHUNAGENDRADURGP on 21/05/2018.
 */

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //FEIGN Proxy
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    /*
    Using RESTTEmplate ,call other MS
    * */
    @RequestMapping(value="/currency-convertor/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversionBean.class,uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();

        logger.info("{}",response);

        CurrencyConversionBean convertedCurrency = new CurrencyConversionBean(response.getId(),
                                            response.getFrom(),response.getTo(),
                                            response.getConversionMultiple(),
                                            quantity.multiply(response.getConversionMultiple()),response.getPort());
        return convertedCurrency;
    }

    /*
    Using FEIGN ,call other MS.
    simplify calling other MS using proxy
    * */

    @RequestMapping(value="/currency-convertor-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){

        //In Proxy interface, we have defined this method which is as exactly as the actual calling MS method signature.
        CurrencyConversionBean response = proxy.retriveExchangeValue(from,to);

        CurrencyConversionBean convertedCurrency = new CurrencyConversionBean(response.getId(),
                response.getFrom(),response.getTo(),
                response.getConversionMultiple(),
                quantity.multiply(response.getConversionMultiple()),response.getPort());
        return convertedCurrency;
    }


    /*
    * Hystrix explanation
    * For the Circuit Breaker to work, Hystix will scan @Component or @Service annotated classes for @HystixCommand annotated methods,
     * implement a proxy for it and monitor its calls.
     *
     * Spring Cloud Netflix Hystrix looks for any method annotated with the @HystrixCommand annotation,
     * and wraps that method in a proxy connected to a circuit breaker so that Hystrix can monitor it
    * */
    @HystrixCommand(fallbackMethod = "faultToleranceHystrix")
    @RequestMapping(value="/strong-url")
    public String convertCurrencyHystrix(){
        throw new RuntimeException();
    }

    public String faultToleranceHystrix(){
         return "There was some error phew!!!";
    }

}
