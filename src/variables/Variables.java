package variables;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Variables {
    public static final String PARAMETER;

    static {
        try {
            PARAMETER = "?q=" + URLEncoder.encode(GeneralVariables.ACTOR, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String URL = GeneralVariables.SCHEMA + GeneralVariables.SERVER + GeneralVariables.PART + PARAMETER;
}
