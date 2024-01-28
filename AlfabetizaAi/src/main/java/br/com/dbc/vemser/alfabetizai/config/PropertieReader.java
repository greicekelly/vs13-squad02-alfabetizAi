package br.com.dbc.vemser.alfabetizai.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertieReader {

    @Value("${spring.application.name}")
    private String app;

    @Value("${spring.mail.username}")
    private String from;


}
