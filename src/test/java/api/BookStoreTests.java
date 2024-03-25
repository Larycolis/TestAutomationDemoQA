package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.entity.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookStoreTests {
    @Test
    void getBookTest() {
        Response response = RestAssured.given()
                .log().all()
                .baseUri("https://demoqa.com/BookStore/v1/Books")
                .accept("application/json")
                .get();
        //System.out.println(response.asPrettyString());
        //System.out.println(response.getStatusCode());
        List<Book> createdBooks = response.jsonPath().getList("books", Book.class);
        System.out.println(createdBooks.get(0).getTitle());
    }
}
