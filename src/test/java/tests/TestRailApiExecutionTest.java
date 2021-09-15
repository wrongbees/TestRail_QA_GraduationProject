package tests;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import baseEntities.BaseApiTest;
import io.restassured.response.Response;
import models.Cases;
import models.ModelsFactory;
import models.Project;
import models.Section;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestRailApiExecutionTest extends BaseApiTest {
    int numberOfCases = 3;
   // Project project;
   // Section currentSection;

   // List<Cases> actualCaseslist = new ArrayList<>();

    @Test
    public void addCasesTest() {
        Project projectModels = ModelsFactory.getProject();

       this.project = new ProjectsAdapter().add(projectModels);

        Section sectionModels = ModelsFactory.getSection();

        this.currentSection = new SectionAdapter().add(sectionModels, project.getId());

        for (int count = 1; count <= numberOfCases; count++) {
            Cases casesModels = ModelsFactory.getCases();
            actualCaseslist.add(new CasesAdapter().add(casesModels, currentSection.getId()));
        }
    }

    @Test(dependsOnMethods = "addCasesTest")
    public void getCasesTest() {
        Cases actualCases = actualCaseslist.get(0);
        Cases cases = new CasesAdapter().get(actualCases);

        Assert.assertTrue(cases.getTitle().equals(actualCases.getTitle()));
    }

    @Test(dependsOnMethods = "getCasesTest")
    public void negativeGetCasesTest() {
        Cases actualCases = ModelsFactory.getCases();
        new CasesAdapter().getFailed(actualCases);

    }

    @Test(dependsOnMethods = "negativeGetCasesTest")
    public void getHistoryForCases() {
        Cases actualCases = actualCaseslist.get(0);
        new CasesAdapter().getHistory(actualCases);
    }

    @Test(dependsOnMethods = "getHistoryForCases")
    public void negativeGetHistoryForCasesTest() {
        Cases actualCases = ModelsFactory.getCases();
        new CasesAdapter().getHistoryFailed(actualCases);
    }

    @Test(dependsOnMethods = "negativeGetHistoryForCasesTest")
    public void updateCaseTest() {
        Cases expected_cases = Cases.builder()
                .title("TITLE №00")
                .refs("Какое то обновленное поле...")
                .build();

        Cases actual_case = new CasesAdapter().updateCase(actualCaseslist.get(0).getId(), expected_cases);
        Assert.assertTrue(actual_case.getTitle().equals(expected_cases.getTitle()));
        System.out.println(project.getName());
    }

//    @Test(dependsOnMethods = "updateCaseTest")
//    public void deleteProject(){
//        new ProjectsAdapter().delete(project.getId());
//    }
}

