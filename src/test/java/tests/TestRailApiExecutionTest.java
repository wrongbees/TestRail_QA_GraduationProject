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

import java.util.ArrayList;
import java.util.List;

public class TestRailApiExecutionTest extends BaseApiTest {
    int numberOfCases = 3;
    Project project;
    Section currentSection;

    List<Cases> actualCaseslist = new ArrayList<>();


    @Test
    public void addCasesTest() {
        Project projectModels = ModelsFactory.getProject();

        project = new ProjectsAdapter().add(projectModels);

        Section sectionModels = ModelsFactory.getSection();

        currentSection = new SectionAdapter().add(sectionModels, project.getId());

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
    public void getHistoryForCases() {
        Cases actualCases = actualCaseslist.get(0);
        Response response = new CasesAdapter().getHistory(actualCases);
    }

    @Test(dependsOnMethods = "getHistoryForCases")
    public void getHistoryForCasesFailedTest() {
        Response response = new CasesAdapter().getHistory(ModelsFactory.getCases());
    }

    @Test(dependsOnMethods = "getHistoryForCasesFailedTest", alwaysRun = true)
    public void copyCasesToSectionTest() {
        Section sectionModels = ModelsFactory.getSection();
            
        Section newSection = new SectionAdapter().add(sectionModels, project.getId());

        String case_ids = "";
        for (Cases item : actualCaseslist)
            case_ids = case_ids + ", " + item.getId();
        String caseIds = case_ids.substring(2);
        System.out.println(caseIds);

        new CasesAdapter().copy(newSection.getId(), caseIds);
    }



    @Test(dependsOnMethods = "copyCasesToSectionTest")
  // @Test(dependsOnMethods = "updateCasesTest")
    public void updateCaseTest() {
        Cases expected_cases = Cases.builder()
                .title("TITLE №00")
                .refs("Какое то обновленное поле...")
                .build();


        Cases actual_case = new CasesAdapter().updateCase(actualCaseslist.get(0).getId(), expected_cases);
        Assert.assertTrue(actual_case.getTitle().equals(expected_cases.getTitle()));
    }

    @Test(dependsOnMethods = "updateCaseTest")
    public void deleteCasesTest() {
        System.out.println(currentSection.getSuit_id());
        System.out.println(currentSection.getId());
        int suitId = new SectionAdapter().getSuitID(currentSection.getSuit_id());
        System.out.println(suitId);

        Response response = new CasesAdapter().deleteCases(project.getId(), suitId);
    }
    @Test(dependsOnMethods = "updateCaseTest")
  //  @Test(dependsOnMethods = "deleteCasesTest")
    public void deleteProject(){
        new ProjectsAdapter().delete(project.getId());
    }

}

