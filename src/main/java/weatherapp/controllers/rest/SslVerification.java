/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@RequestMapping(value = "/.well-known", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
@RestController
public class SslVerification {
    @GetMapping(value = "/acme-challenge/2u00n-Tq6bgXglZmashB6Gu4RSna1HKEDNoZuEqZiWY", consumes = MediaType.ALL_VALUE)
    public String veryfySsl() {
        String verificationCode = "2u00n-Tq6bgXglZmashB6Gu4RSna1HKEDNoZuEqZiWY.WF8k76I8UOzWn87mF6JdtH67c01O_S_5LXNrTDOU4Dw";
        return verificationCode;
    }
}
