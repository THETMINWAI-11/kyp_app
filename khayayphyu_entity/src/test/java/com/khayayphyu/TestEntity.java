package com.khayayphyu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
@PropertySource(value = { "classpath:application.properties"})
public class TestEntity {
	
	@Test
	public void main() {
		System.out.println("Hello World");
	}
}
