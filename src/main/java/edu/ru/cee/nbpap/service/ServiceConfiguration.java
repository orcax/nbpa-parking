package edu.ru.cee.nbpap.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import edu.ru.cee.nbpap.service.impl.DemoServiceImpl;

@Service
public class ServiceConfiguration {
	
	@Bean
	public DemoService demoService() {
		return new DemoServiceImpl();
	}

}
