package PeopleAllocationTool.Api.Tests.steps;

import PeopleAllocationTool.Api.Tests.utils.SharedContext;
import PeopleAllocationTool.Api.Tests.utils.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Steps extends BaseStep {

    public Steps(TestContext testContext) {
        super(testContext);
    }

    private RequestSpecification requestSpec;
    private Response response;

    @Given("I Make A {string} Request For {string} With {string} Specifications")
    public void iMakeARequest(String method, String urlPath, String spec) {

        Integer paramId = (Integer) getScenarioContext().getContext(SharedContext.id);

        switch (spec) {
            case "basic":
                makeRequest(getBasicRequestSpec(), method, getUrl(urlPath));
                break;
            case "basicWithParam":
                makeRequest(getBasicRequestSpec(), method, getUrl(urlPath, paramId));
                break;
        }
    }


    @Given("I Make A {string} Request For {string} With Specifications {string} And Body {string}")
    public void iMakeARequestWithBody(String method, String urlPath, String spec, String bodyFileName) throws IOException {

        String bodyPath = "src/test/resources/PeopleAllocationTool.Api.Tests/bodyFiles/" + bodyFileName + ".json";
        Integer paramId = (Integer) getScenarioContext().getContext(SharedContext.id);
        switch (spec) {
            case "withoutAuth":
                makeRequest(getRequestSpecWithoutAuth(bodyPath), method, getUrl(urlPath));
                break;
            case "specWithBody":
                makeRequest(getRequestSpecWithBody(bodyPath), method, getUrl(urlPath));
                break;
            case "specWithBodyAndParam":
                makeRequest(getRequestSpecWithBody(bodyPath), method, getUrl(urlPath,paramId));
                break;
        }
        if(!((response.statusCode() >= 200) && (response.statusCode() < 300))){
            throw new IOException("TheProject was not Generated!");
        }
    }

    @Then("I Verify That the Status Code Is {string}")
    public void iVerifyStatusCode(String statusCode) {
        response.then().log().all()
                .statusCode(Integer.parseInt(statusCode));
    }

    @Given("I Have An Authorization Token")
    public void iHaveAnAuthorizationToken() throws IOException {
        String bodyPath = "src/test/resources/PeopleAllocationTool.Api.Tests/bodyFiles/authBody.json";
        response = makeRequest(getRequestSpecWithoutAuth(bodyPath), "POST", getUrl("endpoints.withoutParam.auth"));
        String authorizationToken = "Bearer " + response.path("token");
        getScenarioContext().setContext(SharedContext.token, authorizationToken);

        if(response.statusCode() != 200){
            throw new IOException("The Authorization Token Was Not Generated!");
        }
    }


    ////////////////////-METHODS-//////////////////////


    public RequestSpecification getBasicRequestSpec(){
        requestSpec = given().log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", getScenarioContext().getContext(SharedContext.token))
                .header("PeopleAllocation-Contexts", 1);
        return requestSpec;
    }

    public RequestSpecification getRequestSpecWithBody(String bodyFilePath) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get(bodyFilePath));
        String bodyFile = new String(jsonData);

        requestSpec = given().log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", getScenarioContext().getContext(SharedContext.token))
                .header("PeopleAllocation-Contexts", 1)
                .body(bodyFile);
        return requestSpec;
    }

    public RequestSpecification getRequestSpecWithoutAuth(String bodyFilePath) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get(bodyFilePath));
        String bodyFile = new String(jsonData);

        requestSpec = given().log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(bodyFile);
        return requestSpec;
    }

    public Response makeRequest(RequestSpecification requestSpec, String methods, String url) {
        switch (methods) {
            case "GET":
                response = requestSpec.get(url);
                break;
            case "POST":
                response = requestSpec.post(url);
                Integer projectID = response.then().extract().body().path("projectId");
                getScenarioContext().setContext(SharedContext.id, projectID);

                break;
            case "PUT":
                response = requestSpec.put(url);
                break;
            case "DELETE":
                response = requestSpec.delete(url);
                break;
        }
        return response;
    }
}
