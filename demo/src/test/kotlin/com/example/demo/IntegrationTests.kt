package com.example.demo

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println("　>>  Set up")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assert( entity.statusCode == HttpStatus.OK)
        entity.body?.contains("<h1>Demo</h1>")?.let { assert(it) }
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println("  >>  TODO")
    }

    @AfterAll
    fun teardown() {
        println(" >>  Tear down")
    }

}