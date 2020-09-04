package com.data.remote;


/**
 * Keep all the service related constants here.
 */
public class ApiConstants {
    public static final String BASE_URL = "https://itunes.apple.com/";
    public static final long CONNECT_TIMEOUT = 30;
    public static final long READ_TIMEOUT = 30;
    public static final long WRITE_TIMEOUT = 30;

    private ApiConstants() {
        // Private constructor to hide the implicit one
    }

}
