import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class RestAssuredAPITest2 {

    @Test
    public void GetPetAccountDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/Account/v1/User/";
        // Get the RequestSpecification of the request to be sent to the server
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("test");

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
//        Assert.assertEquals(200, statusCode);
        Assert.assertEquals(401, statusCode);
    }

    @Test
    public void GetPetDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store/inventory";

        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");

        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

    }

    @Test
    public void PostStoreOrder() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store";

        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // request book isbn sending alone with post request
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("id", "1");
        requestParameters.put("petId", "1");
        requestParameters.put("quantity", "1");
        requestParameters.put("shipDate", "2022-10-11T08:58:08.615Z");
        requestParameters.put("status", "placed");
        requestParameters.put("complete", "true");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParameters.toJSONString()); // attach data to the request

        // send request
        Response response = httpRequest.request(Method.POST, "/order");

        // Get response getBody and Print
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is ====> " + responseBody);

        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

        // Get Status code and print
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(200, statusCode);

//        // Success code validation
//        String successCode = response.jsonPath().get("successCode");
//        Assert.assertEquals("successful operation", successCode);

    }
}
