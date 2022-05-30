package io.suriya.sms.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.suriya.sms.model.SmsPojo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {

	@Value("${twilio.phone_number}")
	private String phone_number;

	public String send(SmsPojo sms) {

		int number = getOtpValue();

		StringBuilder sb = new StringBuilder();
		sb.append("Your OTP is ").append(number).append(" Please verify this and confirm");

		log.info("Final SMS to deliver : " + sb.toString());

		Message message = Message
				.creator(new PhoneNumber(sms.getPhoneNumber()), new PhoneNumber(phone_number), sb.toString())
				.setMediaUrl(Promoter.listOfOne(URI.create("http://www.domain.com/image.png")))
				.create();
		String status = message.getStatus().toString();
		log.info("Status of message is {}",status);
		
		return status;
	}

	private int getOtpValue() {
		int min = 100000;
		int max = 999999;
		return (int) (Math.random() * (max - min + 1) + min);
	}

	public void receive(MultiValueMap<String, String> smsCallback) {

	}

}
