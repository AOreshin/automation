package com.github.aoreshin.api.sample;

import org.junit.jupiter.api.Test;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;

public class Tests {
    @Test
    void verifyError() {
        apiRequest()
                .get("https://google.com")
                .statusCode(200);
    }
}
