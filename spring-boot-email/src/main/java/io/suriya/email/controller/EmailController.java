package io.suriya.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.suriya.email.model.EmailDetails;
import io.suriya.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/sendMail")
	public ResponseEntity<String> sendEmail(){
		System.out.println("Inside sendEmail service method");
		EmailDetails details = new EmailDetails("kartsuri89@gmail.com","My Message body","Mail subject","");
		String response = emailService.sendSimpleMail(details);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
