package br.com.renildosousa.controleponto.documentacao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket pontoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.renildosousa.controleponto"))
                .paths(PathSelectors.ant("/**"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .tags(new Tag("Bater ponto", "Gerenciar ponto"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Desafio Backend | Spring Boot").description(
                        "Criar uma API que permita a realização das seguintes as seguintes ações")
                .version("1.0").build();
    }

}
