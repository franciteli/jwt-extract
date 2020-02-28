package br.com.provider.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.provider")
public class AppStart {
    public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}