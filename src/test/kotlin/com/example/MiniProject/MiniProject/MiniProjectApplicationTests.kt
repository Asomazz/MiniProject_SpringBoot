package com.example.MiniProject.MiniProject

import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.HttpStatus



class MiniProjectApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate



}
