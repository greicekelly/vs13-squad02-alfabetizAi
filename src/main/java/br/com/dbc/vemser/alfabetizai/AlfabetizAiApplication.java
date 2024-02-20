package br.com.dbc.vemser.alfabetizai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AlfabetizAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfabetizAiApplication.class, args);
    }

}