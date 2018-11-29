/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.responses;

public class ErrorResponse {

    public final String url;
    public final String msg;

    public ErrorResponse(String url, String msg) {
        this.url = url;
        this.msg = msg;
    }
}
