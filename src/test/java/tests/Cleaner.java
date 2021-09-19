package tests;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.AllProjects;
import models.Project;
import org.testng.annotations.Test;
import java.util.List;

/***
 * Doesn't participate in testing.
 * Used for periodic cleaning of projects.
 */
public class Cleaner extends BaseApiTest {
    @Test
    public void cleanerTestRail(){

        AllProjects projects = new ProjectsAdapter().get();

        for (Project project : projects.getProjects()){
            new ProjectsAdapter().delete(project.getId());
        }
    }
}
