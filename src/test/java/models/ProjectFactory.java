package models;

import com.github.javafaker.Faker;

import java.util.Random;

public class ProjectFactory {

public static Project getProject(){
    /*
    https://github.com/DiUS/java-faker
     */

    Faker faker = new Faker();
    Random random = new Random();

    Project project = Project.builder()
            .name(faker.name().fullName()+"_Project.")
            .announcement(faker.lordOfTheRings().location())
            .show_announcement(random.nextBoolean())
            .suite_mode(random.nextInt(3)+1)
            .build();

    return project;
}
}
