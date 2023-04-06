package com.felipearruda.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/api/cliente/**").uri("lb://msclientes"))
					.route(r -> r.path("/api/cartao/**").uri("lb://mscartoes"))
					.route(r -> r.path("/api/avaliadorcredito/**").uri("lb://msavaliadorcredito"))
				.build();
	}

}
