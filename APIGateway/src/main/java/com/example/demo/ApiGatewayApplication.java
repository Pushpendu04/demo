package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableCircuitBreaker
@EnableHystrix
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	 @Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }
	 @Bean
	    public ZuulFallbackProvider zuulFallbackProvider() {
	        return new ZuulFallbackProvider() {
	            @Override
	            public String getRoute() {
	                return "zuulserver";
	            }

	            @Override
	            public ClientHttpResponse fallbackResponse() {
	                return new ClientHttpResponse() {
	                    @Override
	                    public HttpStatus getStatusCode() throws IOException {
	                        return HttpStatus.OK;
	                    }

	                    @Override
	                    public int getRawStatusCode() throws IOException {
	                        return 200;
	                    }

	                    @Override
	                    public String getStatusText() throws IOException {
	                        return "OK";
	                    }

	                    @Override
	                    public void close() {

	                    }

	                    @Override
	                    public InputStream getBody() throws IOException {
	                        return new ByteArrayInputStream("fallback".getBytes());
	                    }

	                    @Override
	                    public HttpHeaders getHeaders() {
	                        HttpHeaders headers = new HttpHeaders();
	                        headers.setContentType(MediaType.APPLICATION_JSON);
	                        return headers;
	                    }
	                };
	            }
	        };
	    }

	
}
