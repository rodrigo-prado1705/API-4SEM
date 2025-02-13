package fatec.grupodois.endurance.constant;

public class SecurityConstant {

    public static final long EXPIRATION_TIME = 432_000_000;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String JWT_TOKEN_HEADER = "Jwt-Token";

    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot be verified";

    public static final String ENDURANCE = "Endurance";

    public static final String ENDURANCE_ADMINISTRATION = "Event Management Portal";

    public static final String AUTHORITIES = "authorities";

    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";

    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";

    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

<<<<<<< HEAD
    public static final String[] PUBLIC_URLS = {"/user/login",
                                                "/user/register",
                                                "/user/resetpassword/**",
                                                "/user/image/**",
                                                "/events/all"};
=======
    /*public static final String[] PUBLIC_URLS = {"/user/login",
                                                "/user/register",
                                                "/user/resetpassword/**",
                                                "/user/image/**",
                                                "/events/all"};*/
    public static final String[] PUBLIC_URLS = {"**"};
>>>>>>> 2bea5a457ac43bd4613ca51b12f002630fb5629f
}
