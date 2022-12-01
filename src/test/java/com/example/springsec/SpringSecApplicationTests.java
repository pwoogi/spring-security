package com.example.springsec;

import com.example.springsec.student.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSecApplicationTests {

	@LocalServerPort
	int port;

	RestTemplate client = new RestTemplate();


	@DisplayName("1. 학생 조사")
	@Test
	void test_2() throws JsonProcessingException {

		String url = format("http://localhost:%d/api/teacher/students", port);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(
						"choi:1".getBytes()
				)
		);
		// TODO: getBody 값 추출안되는 원인 찾기
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		ResponseEntity<String> resp = client.exchange(url, HttpMethod.GET, entity, String.class);

		List<Student> list = new ObjectMapper().readValue(resp.getBody(),
				new TypeReference<List<Student>>() {
				});

		System.out.println(list);

		assertEquals(3, list.size());
	}

}
