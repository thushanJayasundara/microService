package org.airretailer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"org.airretailer.*"})
@EntityScan(basePackages = "org.airretailer.*")
@EnableJpaRepositories(basePackages = "org.airretailer.*")
@EnableAutoConfiguration
@OpenAPIDefinition(info = @Info(title = " AirRetailer Flight Amenity API Doc",version = "1.0-SNAPSHOT"))
public class ArFlightAmenityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArFlightAmenityApplication.class, args);
    }

}