package adapters;

import com.google.common.reflect.TypeToken;
import endpoints.CasesEndpoint;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Cases;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesAdapter extends BaseAdapter {

    public Cases add(Cases cases, int sectionId) {
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoint.ADD_CASE, sectionId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Cases get(Cases cases) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoint.GET_CASE, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Response getHistory(Cases cases) {
        return given()
                .when()
                .get(String.format(CasesEndpoint.GET_HISTORY_FOR_CASES, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

    }

    public List<Cases> getAll(int projectId, int suitId) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoint.GET_CASE, projectId,suitId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return (List<Cases>) gson.fromJson(response.asString().trim(), new TypeToken<List<Cases>>(){}.getType());
    }

    public Response copy( int sectionId, String case_ids) {
        Response response = given()
                .body(String.format("{ \n" +
                        "  \"case_ids\": \"%s\"\n" +
                        "}",case_ids))
                .when()
                .post(String.format(CasesEndpoint.COPY_CASES, sectionId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return response;
    }

    public Response updateCases(int suitId, String jsonBody){
        Response response = given()
                .body(jsonBody)
                .when()
                .post(String.format(CasesEndpoint.UPDATE_CASES, suitId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return response;
    }

    public Cases updateCase(int caseId, Cases cases){
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoint.UPDATE_CASE, caseId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Response deleteCases(int projectId, int suitId){
        Response response = given()
                .when()
                .post(String.format(CasesEndpoint.DELETE_CASE, projectId, suitId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return response;
    }
}
