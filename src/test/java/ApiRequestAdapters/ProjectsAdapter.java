package ApiRequestAdapters;

import ApiRequestAdapters.endpoints.ProjectEndpoints;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.AllProjects;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ProjectsAdapter extends BaseAdapter {

    public AllProjects get() {

        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        System.out.println(response.jsonPath().get("projects").toString());

//        return (List<Project>) gson.fromJson(response.jsonPath().get("projects").asString().trim(), new TypeToken<List<Project>>() {
//        }.getType());
        return gson.fromJson(response.asString().trim(), AllProjects.class);


    }

    public Project get(int ID) {

        Response response = given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, ID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);
    }

    @Step("Create a project by POST API request")
    public Project add(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);

    }

    public void delete(int projectId) {
        given()
                .when()
                .post(String.format(ProjectEndpoints.DELETE_PROJECTS, projectId))
                .then()
                .statusCode(HttpStatus.SC_OK);

    }
}
