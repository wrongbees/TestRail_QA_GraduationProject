package tests;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import baseEntities.BaseApiTest;
import io.qameta.allure.Description;
import models.Cases;
import models.ModelsFactory;
import models.Project;
import models.Section;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRailApiExecutionTest extends BaseApiTest {
    int numberOfCases = 3;

    @Description("API test for creating a Test Case by POST request. " +
            "This test include step creating project and section by POST request")
    @Test
    public void addCasesTest() {
        Project projectModels = ModelsFactory.getProject();

        project = new ProjectsAdapter().add(projectModels);

        Section sectionModels = ModelsFactory.getSection();

        currentSection = new SectionAdapter().add(sectionModels, project.getId());

        for (int count = 1; count <= numberOfCases; count++) {
            Cases casesModels = ModelsFactory.getCases();
            actualCasesList.add(new CasesAdapter().add(casesModels, currentSection.getId()));
        }
    }

    @Description("API test for getting a Test Cases by GET request")
    @Test(dependsOnMethods = "addCasesTest")
    public void getCasesTest() {
        Cases actualCases = actualCasesList.get(0);
        Cases cases = new CasesAdapter().get(actualCases);

        Assert.assertEquals(actualCases.getTitle(), cases.getTitle());
    }

    @Test(dependsOnMethods = "getCasesTest")
    public void negativeGetCasesTest() {
        Cases actualCases = ModelsFactory.getCases();
        new CasesAdapter().getFailed(actualCases);

    }

    @Test(dependsOnMethods = "negativeGetCasesTest")
    public void getHistoryForCases() {
        Cases actualCases = actualCasesList.get(0);
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

        Cases actual_case = new CasesAdapter().updateCase(actualCasesList.get(0).getId(), expected_cases);
        Assert.assertEquals(expected_cases.getTitle(), actual_case.getTitle());
        System.out.println(project.getName());
    }
}

