package com.restassured.api.api_endpoints;

/*We will list all the end point URLs from the Swagger doc or any requirement doc provided.
 * GET-https://gorest.co.in/public/v2/users
 * POST-https://gorest.co.in/public/v2/users
 * PUT-https://gorest.co.in/public/v2/users/{user_id}
 * DELETE-https://gorest.co.in/public/v2/users/{user_id}
 * GET-https://gorest.co.in/public/v2/users/{user_id}
 * 
 * Other querry and path parameters can be added as per the requirement
 */

public class Routes {
    
    public static String base_url = "https://gorest.co.in/public/v2";
    public static String get_all_users = base_url + "/users";   
    public static String create_user = base_url + "/users";
    public static String update_user = base_url + "/users/{user_id}";
    public static String delete_user = base_url + "/users/{user_id}";
    public static String get_single_user = base_url + "/users/{user_id}";

}
