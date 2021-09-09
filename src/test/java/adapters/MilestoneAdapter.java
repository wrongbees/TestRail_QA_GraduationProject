package adapters;

import com.google.common.reflect.TypeToken;
import endpoints.MilestoneEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class MilestoneAdapter extends BaseAdapter {

    public Milestone add(Milestone milestone, int projectId) {
        Response response = given()
                .body(milestone, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestoneEndpoints.ADD_MILESTONE, projectId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Milestone.class);
    }

    public Milestone get(int milestoneId){
        Response response = given()
                .get(String.format(MilestoneEndpoints.GET_MILESTONE, milestoneId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Milestone.class);
    }

    public List<Milestone> getAll(int projectId) {

        Response response = given()
                .get(String.format(MilestoneEndpoints.GET_MILESTONES, projectId))
                .then()
                .log().status()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return (List<Milestone>) gson.fromJson(response.asString().trim(), new TypeToken<List<Milestone>>(){}.getType());
    }

    public Milestone update(Milestone actualMilestone, Milestone expectedMilestone){
        Response response = given()
                .body(expectedMilestone, ObjectMapperType.GSON)
                .post(String.format(MilestoneEndpoints.UPDATE_MILESTONES, actualMilestone.getId()))
                .then()
                .log().status()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Milestone.class);
    }

    public void delete(Milestone milestone) {

        given()
                .post(String.format(MilestoneEndpoints.DELETE_MILESTONES, milestone.getId()))
                .then()
                .log().status()
                .statusCode(org.apache.hc.core5.http.HttpStatus.SC_OK);
    }
}
