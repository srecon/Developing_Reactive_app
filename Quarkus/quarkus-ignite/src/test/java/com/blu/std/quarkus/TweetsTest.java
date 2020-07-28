package com.blu.std.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TweetsTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/tweet/todays")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}