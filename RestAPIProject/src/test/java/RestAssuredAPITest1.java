import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.*;

public class RestAssuredAPITest1 {

    @Test
    public void GetBooksDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";

        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");

        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

        // Response.asString method will directly return the content of the body as String.
        System.out.println("Response Body is =>  " + response.asString());

        // Get response getBody and Print
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is ====> " + responseBody);

        // Get Status code and print
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);

        // Get Status line and print
        String statusLine = response.getStatusLine();
        System.out.println("Status line is " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void IteratingHeaders() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
        // Get all the headers and then iterate over allHeaders to print each header
        Headers allHeaders = response.headers();
        // Iterate over all the Headers
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void GetBookHeaders() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        // Access header with a given name.
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);

        // Access header with a given name.
        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);

        // Access header with a given name. Header = Content-Encoding
        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + acceptLanguage);
    }

    @Test
    public void ValidateBookHeaders() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        // Access header with a given name. Header = Content-Type
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType /* actual value */, "application/json; charset=utf-8" /* expected value */);

        // Access header with a given name. Header = Server
        String serverType = response.header("Server");
        Assert.assertEquals(serverType /* actual value */, "nginx/1.17.10 (Ubuntu)" /* expected value */);
    }

    @Test
    public void queryParameter() {
        //Defining the base URI
        RestAssured.baseURI= "https://bookstore.toolsqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();

        //Passing the resource details
        Response res = httpRequest.queryParam("ISBN","9781449325862").get("/Book");

        //Retrieving the response body using getBody() method
        ResponseBody body = res.body();

        //Converting the response body to string object
        String rbdy = body.asString();

        //Creating object of JsonPath and passing the string response body as parameter
        JsonPath jpath = new JsonPath(rbdy);

        //Storing publisher name in a string variable
        String title = jpath.getString("title");
        System.out.println("The book title is - "+title);
    }

    @Ignore
    @Test
    public void PostBooks() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";

        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // request book isbn sending alone with post request
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("isbn", "978-0-525-56835-3");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParameters.toJSONString()); // attach data to the request

        // send request
        Response response = httpRequest.request(Method.POST, "");

        // Get response getBody and Print
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is ====> " + responseBody);

        // Get Status code and print
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(504, statusCode);

        // Success code validation
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "successful operation");

        // Get Status line and print
        String statusLine = response.getStatusLine();
        System.out.println("Status line is " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    public void UserRegistrationSuccessful() {
        RestAssured.baseURI ="https://demoqa.com/Account/v1";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", "test_rest");
        requestParams.put("password", "Testrest@123");
        request.body(requestParams.toJSONString());
        Response response = request.put("/User");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Test
    public void updateBook() {
        String userId = "toolsqa_test";
        String baseUrl = "https://demoqa.com";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRlc3RpbmcxMjMiLCJwYXNzd29yZCI6IlBhc3N3b3JkQDEiLCJpYXQiOjE2Mjg1NjQyMjF9.lW8JJvJF7jKebbqPiHOBGtCAus8D9Nv1BK6IoIIMJQ4";
        String isbn = "9781449325865";

        RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

        RestAssured.baseURI = baseUrl;

        //Calling the Delete API with request body
        Response res = httpRequest.body("{ \"isbn\": \"" + isbn + "\", \"userId\": \"" + userId + "\"}").put("/BookStore/v1/Book/9781449325862");

        //Fetching the response code from the request and validating the same
        System.out.println("The response code - " +res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),404); // 204

    }

    public class PUTMethod {
        String userId= "toolsqa_test";
        String baseUrl="https://demoqa.com";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRlc3RpbmcxMjMiLCJwYXNzd29yZCI6IlBhc3N3b3JkQDEiLCJpYXQiOjE2Mjg1NjQyMjF9.lW8JJvJF7jKebbqPiHOBGtCAus8D9Nv1BK6IoIIMJQ4";
        String isbn ="9781449325865";
    }

    public class DeleteBook {
        String userId = "de5d75d1-59b4-487e-b632-f18bc0665c0d";
        String baseUrl = "https://demoqa.com/swagger/";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRlc3RpbmcxMjMiLCJwYXNzd29yZCI6IlBhc3N3b3JkQDEiLCJpYXQiOjE2Mjg1NjQyMjF9.lW8JJvJF7jKebbqPiHOBGtCAus8D9Nv1BK6IoIIMJQ4";
        String isbn = "9781449337711";

        @BeforeClass
        @AfterClass
        public void getUserData() {
            RestAssured.baseURI = baseUrl;
            RequestSpecification httpRequest =
                    RestAssured.given().header("Authorization", "Bearer " + token)
                            .header("Content-Type", "application/json");

            Response res = httpRequest.get("/Account/v1/User/" + userId);
            ResponseBody body = res.body();
            //Converting the response body to string
            String rbdy = body.asString();
            System.out.println("Data from the GET API- " + rbdy);
        }

        @Test
        public void deleteBook() {
            RestAssured.baseURI = baseUrl;
            RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");

            //Calling the Delete API with request body
            Response res = httpRequest.body("{ \"isbn\": \"" + isbn + "\", \"userId\": \"" + userId + "\"}").delete("/BookStore/v1/Book");

            //Fetching the response code from the request and validating the same
            System.out.println("The response code is - " + res.getStatusCode());
            Assert.assertEquals(res.getStatusCode(), 200); // 204

        }
    }

}
