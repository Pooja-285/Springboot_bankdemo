package com.demo.bank.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Component
@ConfigurationProperties("bankdemo")
@Data
public class Configuration {

	int id;
	String message;
	
	public Configuration() {
		this.id = 0;
		this.message = "";
	}
	
	
}
