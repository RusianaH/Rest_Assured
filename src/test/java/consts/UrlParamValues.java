package consts;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Map;

public class UrlParamValues {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String VALID_KEY = dotenv.get("KEY");
    public static final String VALID_TOKEN = dotenv.get("TOKEN");

    static {
        System.out.println("KEY = " + VALID_KEY);
        System.out.println("TOKEN = " + VALID_TOKEN);
    }

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", dotenv.get("ANOTHER_KEY"),
            "token", dotenv.get("ANOTHER_TOKEN")
    );

    public static final String EXISTING_BOARD_ID = "6947fb6d9cd8d0e89aa127ee";

    public static final String BOARD_ID_TO_UPDATE = "6947fb6d9cd8d0e89aa127ee";

    public static final String EXISTING_LIST_ID = "6947fb85c9d5ca5b73207c60";

    public static final String CARD_ID_TO_UPDATE = "6947fc42b45c789bd50ef244";

    public static final String EXISTING_CARD_ID = "6947fc42b45c789bd50ef244";

    public static final String USER_NAME = "rusianahlm";
}