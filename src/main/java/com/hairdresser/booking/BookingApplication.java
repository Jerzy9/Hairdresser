package com.hairdresser.booking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

//	@Bean
//	public ServletRegistrationBean<GraphQlHttpServlet>() {
//		return new ServletRegistrationBean<>(GraphQlHttpServlet.with)
//	}

}
