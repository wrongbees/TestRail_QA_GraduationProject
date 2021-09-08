package models;

import com.github.javafaker.Faker;

import java.util.Random;

public class ModelsFactory {
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static Project getProject() {
    /*
    https://github.com/DiUS/java-faker
     */
        Project project = Project.builder()
                .name(faker.name().fullName() + "_Project.")
                .announcement(faker.lordOfTheRings().location())
                .show_announcement(random.nextBoolean())
                .suite_mode(random.nextInt(3) + 1)
                .build();

        return project;
    }

    public String stringGenerator(int numberOfSymbols) {
        String [] symbols = "012345678qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".split("");
        StringBuilder newGeneratedString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            int randomIndex = random.nextInt(symbols.length);
            newGeneratedString.append(symbols[randomIndex]);
        }
        return newGeneratedString.toString();
    }
}
