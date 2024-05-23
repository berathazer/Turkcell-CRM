package com.turkcell.turkcellcrm.searchService;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SearchServiceApplication {

	public static void main(String[] args) {
		System.out.println("""
				 _  _  ____  ____  ____    _  _   __   ____    _  _  ____  ____  ____\s
				( \\/ )(  __)(_  _)(  __)  / )( \\ / _\\ / ___)  / )( \\(  __)(  _ \\(  __)
				/ \\/ \\ ) _)   )(   ) _)   \\ /\\ //    \\\\___ \\  ) __ ( ) _)  )   / ) _)\s
				\\_)(_/(____) (__) (____)  (_/\\_)\\_/\\_/(____/  \\_)(_/(____)(__\\_)(____)                                                                                                                                                        \s                                                                                                                                                                  \s
				""");
		SpringApplication.run(SearchServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}


}
