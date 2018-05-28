package com.learn.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MACHUNAGENDRADURGP on 22/05/2018.
 *
 * This class is used to create proxy for the other microservice,RIBBON config
 * Method signature matches the calling controller method.
 * FeignClient matches the calling microservice (i.e the spring.application.name from application.properties of calling application).
 * Method return declaration may get changed based on the class type exists in the project or not.
 */

/*
Enable below when RIBBON not used.. In RIBBON we don't need to hard code URL.we would configure it in different way. If we use RIBBON the load distribution is across
server and cant be hardcoded/
@FeignClient(name="currency-exchange-service",url="localhost:8000")
*/

/*
Enable below when RIBBON is used that will distribute the load to different instances dynamically through eureka.since all the requests go through API gate below can be commented.
and point Feign client to API gateway application name s
@FeignClient(name="currency-exchange-service")
* */
@FeignClient("netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service") //RIBBON helps to distribute the calls to between different instances of a  service
public interface CurrencyExchangeServiceProxy {

    /*
    * Feign needs to specify the exact path variable.
    * i.e @PathVariable("from") String from
    * */
    /*
    when called with out API Gatway
    * @RequestMapping(value="/currency-exchange/from/{from}/to/{to}")
    * */
    //call via API Gate  annotation syntax
    @RequestMapping(value="/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
