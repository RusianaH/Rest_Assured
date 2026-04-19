package consts;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Map;

public class UrlParamValues {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String VALID_KEY = dotenv.get("KEY");
    public static final String VALID_TOKEN = dotenv.get("TOKEN");

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", dotenv.get("KEY"),
            "token", dotenv.get("TOKEN")
    );

    public static final String EXISTING_BOARD_ID = "688086ee295af9ef3667566c";
    public static final String BOARD_ID_TO_UPDATE = "688086ee295af9ef3667566c";
    public static final String CARD_ID_TO_UPDATE = "688c5e7c336977437123d6dc";
    public static final String EXISTING_CARD_ID = "688c5e7c336977437123d6dc";
    public static final String EXISTING_LIST_ID = "688086ee295af9ef366756a9";

    public static final String USER_NAME = "rusianahlm";
}