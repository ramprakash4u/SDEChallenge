package com.paytmlabs.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paytmlabs.demo.Application;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@Profile("local")
public class SwaggerConfig  {
	
    public static final String TITLE = "Demo Moving-Average Service";	
	public static final String DESCRIPTION = "A service to accept Stream & integer N to calculate moving average of the last N elements in Stream" ;
	public static final int VERSION = 1;
	public static final String BASE_PACKAGE = "com.paytmlabs.demo.contoller";
	
	@Bean
	public Docket api() {
		
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage(Application.class.getPackage().getName()))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfoBuilder()
						.title(TITLE)
						.description(DESCRIPTION)
						.version("v" + VERSION)
						.build()
						);
				
				
		attachGlobalOperationResponseMessage(docket);		
		return docket;
	}
	
	private void attachGlobalOperationResponseMessage(Docket docket) {
		
		List<ResponseMessage> responseMessages = newArrayList (
				new ResponseMessageBuilder().code(401).message("Access denied - Authentication required").build(),
				new ResponseMessageBuilder().code(403).message("Access denied - Not authorized").build(),
				new ResponseMessageBuilder().code(401).message("A fatal error has occured").build()
				);
		
		docket.globalResponseMessage(RequestMethod.GET, responseMessages);
		docket.globalResponseMessage(RequestMethod.POST, responseMessages);
	}
	

}
