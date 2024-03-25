package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.api.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.util.FakerUtil;

public class AccountTests {
    @Test
    void createAccountTest() {
        User expectedUser = User.builder()
                .username(FakerUtil.getFirstName() + FakerUtil.getRandomNumber())
                .password("Admin7!!")
                .build();
        Response response = RestAssured.given()
                .log().all()
                .baseUri("https://demoqa.com/Account/v1/User")
                .accept("application/json")
                .contentType(ContentType.JSON)
                .auth().basic(expectedUser.getUsername(), expectedUser.getPassword())
                .body(expectedUser)
                .post();
        System.out.println(response.asPrettyString());
        //System.out.println(response.getStatusCode());
        Assertions.assertEquals(expectedUser.getUsername(), response.as(User.class).getUsername());
    }
}
