package com.restassured.api.api_endpoints;
import static io.restassured.RestAssured.given;

import com.restassured.api.api_payload.Payload;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Implementation extends Payload{

    public static Response getAllUser(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer ae54e295d435dce1cbca5dbb65c46375d666816eae3b19d5a7ef793e80f1a543")
                .when()
                .get(Routes.get_all_users);

                return response;
    }
    public static Response createUser(Payload p){
        Response response = given()
        
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer ae54e295d435dce1cbca5dbb65c46375d666816eae3b19d5a7ef793e80f1a543")
                .body(p)
                .when()
                .post(Routes.create_user);
                return response;

    }

    public static Response updateUser(Payload p, String userId){
         Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer ae54e295d435dce1cbca5dbb65c46375d666816eae3b19d5a7ef793e80f1a543")
                .pathParam("user_id", userId)
                .body(p)
                .when()
                .put(Routes.update_user);
                 return response;

    }

    public static Response deleteUser(String userId){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer ae54e295d435dce1cbca5dbb65c46375d666816eae3b19d5a7ef793e80f1a543")
                .pathParam("user_id", userId)    
                .when()
                .delete(Routes.delete_user);
                return response;
    }

    public static Response getSpecificUser(String userId){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer ae54e295d435dce1cbca5dbb65c46375d666816eae3b19d5a7ef793e80f1a543")
                .pathParam("user_id", userId)
                .when()
                .get(Routes.get_single_user);
                return response;
    }

}
