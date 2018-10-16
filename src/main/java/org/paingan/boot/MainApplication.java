package org.paingan.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableAutoConfiguration
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {
	
	@Value("${cloudkarafka.topic}")
	private String topic = "paingan-topic";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public static void main(String[] args) { 
		//sendMessage("Hi Welcome to Spring For Apache Kafka");
		SpringApplication.run(MainApplication.class, args);
	}
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send(topic, msg);
		System.out.println("Sent sample message [" + msg + "] to " + topic);
	}
	
	@KafkaListener(topics = "${cloudkarafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void listen(String message) {
		System.out.println("Received Messasge in group - group-id: " + message);
	}

	public void run(ApplicationArguments args) throws Exception {
		sendMessage("Hi Welcome to Spring For Apache Kafka");
	}
}
