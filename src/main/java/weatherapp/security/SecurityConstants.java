/*
 * Copyright (c) 2018.
 */

package weatherapp.security;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
