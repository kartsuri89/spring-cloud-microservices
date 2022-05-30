package io.suriya.sms.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;

import io.suriya.sms.model.SmsPojo;
import io.suriya.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@Value("${twilio.account_sid}")
	private String acc_sid;
	
	@Value("${twilio.auth_token}")
	private String token;
	
	@PostConstruct
	public void initTwilio() {
		Twilio.init(acc_sid, token);
	}
	
	@PostMapping("/mobileNo")
	public ResponseEntity<String> sendOtp(@RequestBody SmsPojo sms){
		log.info("Inside sendOtp method");
		String response = null;
		try {
			log.info(sms.getPhoneNumber());
			response = smsService.send(sms);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}

}
