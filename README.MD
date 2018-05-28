https://github.com/in28minutes/

Topics covered :

1) FEIGN




-> Any service request need to go through API Gate must follow tye below URI structure

http://localhost:{zuul server port}/{application_name}/{application uri}

http://localhost:8765/currency-exchange-service/currency-exchange/from/{from}/to/{to}