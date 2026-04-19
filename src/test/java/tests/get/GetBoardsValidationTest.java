package tests.get;

import arguments.Holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import arguments.Holders.BoardIdValidationArgumentsHolder;
import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import utils.ErrorMessageExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

public class GetBoardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardWithValidId(BoardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());

        String actualMessage = ErrorMessageExtractor.extract(response);

        System.out.println("=== DEBUG INVALID ID ===");
        System.out.println("Raw body   : " + response.getBody().asString());
        System.out.println("Actual msg : " + actualMessage);
        System.out.println("Expected   : " + validationArguments.getErrorMessage());
        Assertions.assertEquals(
                validationArguments.getErrorMessage(),
                actualMessage,
                "Expected different error message"
        );
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParams("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        String msg = ErrorMessageExtractor.extract(response).toLowerCase();

        Assertions.assertTrue(
                msg.contains(validationArguments.getErrorMessage()) || msg.contains("unauthorized"),
                "Expected: " + validationArguments.getErrorMessage() + "but got: " +msg);


    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        String msg = ErrorMessageExtractor.extract(response).toLowerCase();

        System.out.println("=== DEBUG INVALID AUTH ===");
        System.out.println("Raw body : " + response.getBody().asString());
        System.out.println("Actual msg: " + msg);

        Assertions.assertTrue(
                msg.contains("invalid") || msg.contains("unauthorized"),
                "Expected invalid auth message, but got: " + msg
        );
    }
}