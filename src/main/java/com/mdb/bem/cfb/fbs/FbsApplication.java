package com.mdb.bem.cfb.fbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbsApplication.class, args);
	}

}
