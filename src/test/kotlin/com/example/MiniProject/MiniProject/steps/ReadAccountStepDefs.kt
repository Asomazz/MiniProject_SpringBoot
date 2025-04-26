//package com.example.MiniProject.MiniProject.steps
//
//import com.example.MiniProject.MiniProject.jwt.JwtService
//import com.example.MiniProject.MiniProject.users.UserEntity
//import com.example.MiniProject.MiniProject.users.UserRepository
//import io.cucumber.java.en.Given
//import io.cucumber.java.en.Then
//import io.cucumber.java.en.When
//import org.skyscreamer.jsonassert.JSONAssert
//import org.skyscreamer.jsonassert.JSONCompareMode
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.http.HttpEntity
//import org.springframework.http.HttpMethod
//import org.springframework.http.HttpStatusCode
//import org.springframework.http.ResponseEntity
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.util.MultiValueMap
//import kotlin.test.assertEquals
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ReadAccountStepDefs {
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//    @Autowired
//    private lateinit var testRestTemplate: TestRestTemplate
//
//    lateinit var responseEntity: ResponseEntity<String>
//    private var token = ""
//    private var headersMap = mutableMapOf<String, List<String>>()
//    private var response: ResponseEntity<String>? = null
//
//    @Autowired
//    private lateinit var usersRepository: UserRepository
//
//    @Autowired
//    private lateinit var jwtService: JwtService
//
//    @Autowired
//    private lateinit var passwordEncoder: PasswordEncoder
//
//    @Given("I have a token for username {string}")
//    fun iHaveATokenForUser(username: String) {
//        token = jwtService.generateToken(username)
//        headersMap["Authorization"] = listOf("Bearer $token")
//    }
//
//    @When("I make a GET request to {string}")
//    fun `I make a GET request to`(endpoint: String) {
//        val headers = org.springframework.http.HttpHeaders(
//            MultiValueMap.fromMultiValue(headersMap)
//        )
//        val requestEntity = HttpEntity<String>(headers)
//        responseEntity = testRestTemplate.exchange(
//            endpoint,
//            HttpMethod.GET,
//            requestEntity,
//            String::class.java
//        )
//    }
//
//
//    @Then("the response status should be {int}")
//    fun checkResponseStatus(expectedStatus: Int) {
//        val expected = HttpStatusCode.valueOf(expectedStatus)
//        assertEquals(expected, responseEntity.statusCode)
//    }}