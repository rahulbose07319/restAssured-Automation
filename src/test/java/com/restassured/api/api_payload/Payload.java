package com.restassured.api.api_payload;

/* we will create POJO class for the payloads
 * with getter and setter methods
*/
public class Payload {

    private String name;
    private String email;
    private String gender;
    private String status;
    private String user_id;
    public String getId() {
        return user_id;
    }
    public void setId(String user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
