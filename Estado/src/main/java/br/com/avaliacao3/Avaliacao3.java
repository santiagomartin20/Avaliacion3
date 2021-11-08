package br.com.avaliacao3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
public class Avaliacao3 {

	public static void main(String[] args) {
		SpringApplication.run(Avaliacao3.class, args);
	}

}
