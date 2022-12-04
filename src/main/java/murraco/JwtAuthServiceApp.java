package murraco;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import murraco.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthServiceApp {

	final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthServiceApp.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
