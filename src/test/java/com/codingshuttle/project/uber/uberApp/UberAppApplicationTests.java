package com.codingshuttle.project.uber.uberApp;

import com.codingshuttle.project.uber.uberApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail(
				"getes96496@chysir.com",
				"Regarding subject of the email",
				"The body of my email"
		);
	}

	@Test
	void sendMultipleEmail(){
		String email[] = {
				"getes96496@chysir.com",
				"aakshptw8530@gmail.com",
				"ptwaakash@gmail.com",
				"anujptw61@gmail.com",
				"stalokpatwa@gmail.com",
				"sakshatetc08.ss@gmail.com"
		};
		emailSenderService.sendEmail(email, "Greetings from Ride booking Application made by Aakash!",
				"Chalti-Ka-Naam-Gaadi App is waiting for you to onboard!");
	}

}
