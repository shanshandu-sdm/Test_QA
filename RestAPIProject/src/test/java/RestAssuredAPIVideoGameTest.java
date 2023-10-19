import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredAPIVideoGameTest {

    // get All VideoGame
    @Test(priority = 1)
    public void test_getAllVideoGames() {

        given()

                .when()
                .get("http://localhost:8080/app/videogames")
                .then()
                .statusCode(200);
    }

    // add a new VideoGame
    @Test(priority = 2)
    public void test_addNewVideoGame() {

        HashMap data = new HashMap();
        data.put("id", "100");
        data.put("name", "Spider-Man");
        data.put("releaseDate", "2015-11-15T07:54:39.518Z");
        data.put("reviewScore", "5");
        data.put("category", "Adventure");
        data.put("rating", "Universal");

        Response res =
                given()
                        .contentType("application/json")
                        .body(data)
                        .when()
                        .post("http://localhost:8080/app/videogames")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);

    }

    // get particular VideoGame
    @Test(priority = 4)
    public void test_updatedVideoGame() {
        HashMap data = new HashMap();
        data.put("id", "100");
        data.put("name", "Pac-man");
        data.put("releaseDate", "2015-11-15T07:54:39.518Z");
        data.put("reviewScore", "4");
        data.put("category", "Adventure");
        data.put("rating", "Universal");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("http://localhost:8080/app/videogames/100")

                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id", equalTo("100"))
                .body("videoGame.name", equalTo("Pac-man"));
    }

    // update existing VideoGame
    @Test(priority = 5)
    public void test_DeleteVideoGame() {
        Response res =
                given()
                        .when()
                        .delete("http://localhost:8080/app/videogames/100")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);

    }

    // delete a VideoGame

}

