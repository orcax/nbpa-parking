package edu.ru.cee.nbpap.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import edu.ru.cee.nbpap.dao.impl.HbmDemoDao;

@Repository
public class DaoRepository {
	
	@Bean
	public DemoDao demoDao() {
		return new HbmDemoDao();
	}

}
