package it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${ellysse.url.base}")
    private String URL_BASE;

    @Bean
    public WebClient webclient() {

        return WebClient
                .builder()
                .baseUrl(URL_BASE)
                //.defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
