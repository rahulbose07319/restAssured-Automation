package com.restassured.api.utilities;
import com.github.javafaker.Faker;

public class Utility {

    public static String generateRandomEmail(){
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();
        return randomEmail;

    }

}
