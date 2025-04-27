package com.example.MiniProject.MiniProject.steps

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import com.example.MiniProject.MiniProject.accounts.AccountRepository
import com.example.MiniProject.MiniProject.jwt.JwtService
import com.example.MiniProject.MiniProject.kyc.KycEntity
import com.example.MiniProject.MiniProject.kyc.KycRepository
import com.example.MiniProject.MiniProject.users.UserEntity
import com.example.MiniProject.MiniProject.users.UserRepository
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.MultiValueMap
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommonStepDefs {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    lateinit var responseEntity: ResponseEntity<String>
    private var token = ""
    private var headersMap = mutableMapOf<String, List<String>>()
    private var response: ResponseEntity<String>? = null

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var kycRepository: KycRepository

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Given("A username {string} and password {string} is in the database")
    fun userIsInDatabase(username: String, password: String) {
        val testUser = UserEntity(
            username = username,
            password = passwordEncoder.encode(password)
        )
        userRepository.save(testUser)
    }

    @Given("a user with ID {int} exists and has no profile yet")
    fun aUserExistsWithoutKyc(userId: Int) {
        val user = userRepository.findById(userId.toLong())
        if (user.isEmpty) {
            throw IllegalStateException("User with ID $userId does not exist")
        }
        val existingKyc = kycRepository.findByUserId(userId.toLong())
        if (existingKyc != null) {
            kycRepository.delete(existingKyc)
        }
    }

//    @Given("a user has an active account with account number {string}")
//    fun userHasActiveAccount(accountNumber: String) {
//
//        val testUser = UserEntity(username = "testuser", password = "testpass")
//        userRepository.save(testUser)
//
//        val account = AccountEntity(
//            user = testUser,
//            balance = BigDecimal.valueOf(1000.0),
//            isActive = true,
//            accountNumber = accountNumber,
//        )
//        accountRepository.save(account)
//    }

    @Given("the user with ID {int} already has a KYC profile")
    fun userHasKycProfile(userId: Int) {
        val user = userRepository.findById(userId.toLong())
            .orElseThrow { IllegalArgumentException("User with ID $userId does not exist") }

        val existingKyc = kycRepository.findByUserId(userId.toLong())

        if (existingKyc == null) {
            val newKyc = KycEntity(
                user = user,
                dateOfBirth = LocalDate.parse("2000-01-01"),
                nationality = "InitialNationality",
                salary = 10000f
            )
            kycRepository.save(newKyc)
        }
    }


    @Given("I have a token for username {string}")
    fun iHaveATokenForUser(username: String) {
        token = jwtService.generateToken(username)
        headersMap["Authorization"] = listOf("Bearer $token")
    }

    @Then("the response body should be {string}")
    fun theResponseBodyShouldBe(expectedBody: String) {
        assertEquals(expectedBody, response?.body)
    }

    @Then("the response body should be")
    fun theResponseBodyShouldBeJSON(expectedJsonBody: String) {
        JSONAssert.assertEquals(expectedJsonBody, response?.body, JSONCompareMode.LENIENT)
    }

        @When("I make a GET request to {string}")
    fun `I make a GET request to`(endpoint: String) {
        val headers = org.springframework.http.HttpHeaders(
            MultiValueMap.fromMultiValue(headersMap)
        )
        val requestEntity = HttpEntity<String>(headers)
        responseEntity = testRestTemplate.exchange(
            endpoint,
            HttpMethod.GET,
            requestEntity,
            String::class.java
        )
    }

    @When("I send a POST request to {string}")
    fun sendPostRequest(endpoint: String, payload: String) {
        headersMap["Content-Type"] = listOf("application/json")
        val headers = org.springframework.http.HttpHeaders(
            MultiValueMap.fromMultiValue(
                headersMap
            )
        )
        val requestEntity = HttpEntity<String>(payload, headers)
        responseEntity = testRestTemplate.exchange(
            endpoint,
            HttpMethod.POST,
            requestEntity,
            String::class.java
        )

        //  responseEntity = restTemplate.postForEntity(endpoint, payload, String::class.java)
    }

    @When("I send a POST request to {string} without a body")
    fun sendPostRequestWithoutBody(endpoint: String) {
        val headers = org.springframework.http.HttpHeaders(
            MultiValueMap.fromMultiValue(
                headersMap
            )
        )
        val requestEntity = HttpEntity<String>(headers)
        responseEntity = testRestTemplate.exchange(
            endpoint,
            HttpMethod.POST,
            requestEntity,
            String::class.java
        )
    }


    @Then("the response status should be {int}")
    fun checkResponseStatus(expectedStatus: Int) {
        val expected = HttpStatusCode.valueOf(expectedStatus)
        assertEquals(expected, responseEntity.statusCode)
    }

    @Then("the response should have a token")
    fun checkToken() {
        assert(responseEntity.body?.contains("token") ?: false)
    }

    @And("the response should contain {string}")
    fun responseShouldContain(expectedField: String) {
        val responseBody = responseEntity.body ?: throw AssertionError("Response body is null")

        if (!responseBody.contains(expectedField)) {
            throw AssertionError("Expected field '$expectedField' not found in response body: $responseBody")
        }
    }

}