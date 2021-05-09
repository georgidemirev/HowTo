package com.demirev.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

	Logger log = LoggerFactory.getLogger(SwaggerDocumentationConfig.class);

	@Value("${enable.swagger.plugin:true}")
	private boolean enableSwaggerPlugin;

	ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("demirev Business API")
				.description("Swagger generated demirev API documentation")
				.version("1.0.0")
				.contact(new Contact("demirev", "http://demirev.com", "info@demirev.com"))
				.build();
	}

	@Bean
	public Docket customImplementation() {
		log.info("Swagger configuration initialized.");
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demirev.business"))
				.paths(PathSelectors.any())
				.build()
				.enable(enableSwaggerPlugin)
				.apiInfo(apiInfo());
	}
}