package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ErrorMessageExtractor {

    public static String extract(Response response) {
        return null;
    }

    public static String getMessage(Response response) {
        try {
            JsonPath json = response.jsonPath();
            return json.getString("message");
        } catch (Exception e) {
            return response.getBody().asString();
        }
    }
}
