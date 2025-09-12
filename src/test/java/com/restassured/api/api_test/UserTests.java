package com.restassured.api.api_test;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.restassured.api.utilities.Utility;

import com.restassured.api.api_endpoints.Implementation;
import com.restassured.api.api_payload.Payload;
import com.restassured.api.utilities.ExcelUtils;


public class UserTests extends Payload{
    Payload p = new Payload();
    //Properties props = new Properties();
    String excelPath = "C:\\Users\\User\\Documents\\restAssured\\restassured\\UserData.xlsx";
    ExcelUtils excel = new ExcelUtils(excelPath);
    private String sheetName;
    private String tcId;
    Map<String, String> data;
    private String userId;

    @BeforeClass
    public void setup(){

        // Fetching data from excel sheet
        tcId = "TC_2";
        sheetName = "createUser";
        data = excel.getRowDataByTC(sheetName, tcId);
        p.setName(data.get("name"));
        p.setEmail(Utility.generateRandomEmail());
        //p.setEmail(data.get("email"));
        p.setGender(data.get("gender"));
        p.setStatus(data.get("status"));


        /*  p.setName("TestUser");
            p.setEmail(Utility.generateRandomEmail());
            p.setGender("Male");
            p.setStatus("active");
         * 
        */
        
        
    }
    @Test(priority = 1)
    public void testGetAllUsers(){
        Response response=Implementation.getAllUser();
        response.then().statusCode(200)
        .extract().response();
        System.out.println("GET ALL USER VALIDATION");
        System.out.println("Response Body: "+response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testCreateUser(){
        Response response=Implementation.createUser(p);
        response.then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 201);
        
        //Deserialization using POJO class
        Payload responseBody = response.as(Payload.class);
        userId = responseBody.getId();
        System.out.println("User created with ID: "+ userId);
    }
    @Test(priority = 3)
    public void testGetSpecificUser(){
        Response response=Implementation.getSpecificUser(userId);
        response.then().statusCode(200)
        .extract().response();
        System.out.println("GET USER Validation for user ID: "+userId);
        System.out.println("Response Body: "+response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("id").toString(), userId);
        Assert.assertEquals(response.jsonPath().get("name"), p.getName());
        Assert.assertEquals(response.jsonPath().get("email"), p.getEmail());
    }
    @Test(priority = 4)
    public void testUpdateUser(){
        //updating email
        p.setEmail(Utility.generateRandomEmail());
        // tcId = "TC_2";
        // sheetName = "updateUser";
        // data = excel.getRowDataByTC(sheetName, tcId);
        // p.setEmail(data.get("email"));


        Response response=Implementation.updateUser(p, userId);
        response.then().extract().response();
        System.out.println("UPDATE USER VALIDATION: for user ID: "+userId);
        System.out.println("Response Body: "+response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("email"), p.getEmail());
    }
    @Ignore
    @Test(priority = 5)
    public void testDeleteUser(){
        Response response=Implementation.deleteUser(userId);
        response.then().extract().response();
        System.out.println("DELETE USER VALIDATION: for user ID: "+userId);
        System.out.println("Response Body: "+response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 204);
    }

}
