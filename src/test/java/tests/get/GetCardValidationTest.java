package tests.get;

import arguments.Holders.AuthValidationArgumentsHolder;
import arguments.Holders.CardValidationArgumensHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

public class GetCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardWithValidId(CardValidationArgumensHolder validationArgumens) {

        Response response = requestWithAuth()
                .pathParams(validationArgumens.getPathParams())
                .get(CardsEndpoints.GET_CARD_URL);

        response.then()
                .statusCode(200);

        String msg = response.body().asString().toLowerCase();

        Assertions.assertTrue(
                msg.contains(validationArgumens.getErrorMessage()),
                "Expected: " + validationArgumens.getErrorMessage() + " but got: " + msg
        );

    }


    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams((validationArguments.getAuthParams()))
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL);
        response
                .then()
                .statusCode(401);

        String msg = response.body().asString().toLowerCase();

        Assertions.assertTrue(
                msg.contains(validationArguments.getErrorMessage()),
                "Expected: " + validationArguments.getErrorMessage() + " but got: " + msg
        );
    }
    //get api another credentials
    //these cred do not have access to the board
    @Test
    public void checkGetCardWithAnotherUserCredentials(){
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL);
        response

                .then()
                .statusCode(401);

        String body = response.body().asString().toLowerCase();

        Assertions.assertTrue(
                body.contains("missing scopes"),
                "Expected missing scopes but got: " + body
        );
    }}


