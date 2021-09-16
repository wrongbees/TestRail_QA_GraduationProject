package models;


import com.github.javafaker.Faker;

import java.util.Random;

public class ModelsFactory {
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static Project getProject() {

        return Project.builder()
                .name(faker.name().fullName() + "_Project.")
                .announcement(faker.lordOfTheRings().location())
                .show_announcement(random.nextBoolean())
                .suite_mode(1)
                .build();
    }

    public static Section getSection() {

        return Section.builder()
                .name(faker.name().fullName() + "_Section.")
                .description(faker.harryPotter().book())
                .build();
    }

    public static Cases getCases() {

        return Cases.builder()
                .title(faker.lordOfTheRings().location())
                .refs(faker.animal().name())
                .build();
    }

    public static String stringGenerator(int numberOfSymbols) {
        String[] symbols = "012345678qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".split("");
        StringBuilder newGeneratedString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            int randomIndex = random.nextInt(symbols.length);
            newGeneratedString.append(symbols[randomIndex]);
        }
        return newGeneratedString.toString();
    }
}
