package models;


import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class ModelsFactory {
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static Project getProject() {
        log.info("Start build project with Faker");
        Project generatedFakerProject = Project.builder()
                .name(faker.name().fullName() + "_Project.")
                .announcement(faker.lordOfTheRings().location())
                .show_announcement(random.nextBoolean())
                .suite_mode(1)
                .build();
        log.info("The project generated " + generatedFakerProject.toString());
        return generatedFakerProject;
    }

    public static Section getSection() {
        log.info("Start section with Faker");
        Section generatedFakerSection = Section.builder()
                .name(faker.name().fullName() + "_Section.")
                .description(faker.harryPotter().book())
                .build();
        log.info("The section generated " + generatedFakerSection.toString());
        return generatedFakerSection;
    }

    public static Cases getCases() {
        log.info("Start build Test Case with Faker");
        Cases generatedFakerCase = Cases.builder()
                .title(faker.lordOfTheRings().location())
                .refs(faker.animal().name())
                .build();
        log.info("The section generated " + generatedFakerCase.toString());
        return generatedFakerCase;
    }

    public static String stringGenerator(int numberOfSymbols) {
        log.info("Start generate random String");
        String[] symbols = "012345678qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".split("");
        StringBuilder newGeneratedString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            int randomIndex = random.nextInt(symbols.length);
            newGeneratedString.append(symbols[randomIndex]);
        }
        log.info("The string generated " + newGeneratedString.toString());
        return newGeneratedString.toString();
    }
}
