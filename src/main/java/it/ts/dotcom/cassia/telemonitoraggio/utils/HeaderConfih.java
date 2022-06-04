package it.ts.dotcom.cassia.telemonitoraggio.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfih {

    @Bean
    @ConfigurationProperties(prefix = "passi")
    public ExsportHeaderPassi headerPassi(){
        return new ExsportHeaderPassi();
    }


    @Bean
    @ConfigurationProperties(prefix = "user-export")
    public ExsportHeaderUser headerUser(){
        return new ExsportHeaderUser();
    }
}
