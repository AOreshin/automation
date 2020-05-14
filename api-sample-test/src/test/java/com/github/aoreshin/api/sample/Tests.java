package com.github.aoreshin.api.sample;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Tests {
  @Disabled
  @Test
  void verifyError() {
    apiRequest()
        .get("https://jsonplaceholder.typicode.com/todos/1")
        .statusCode(200)
        .extract()
        .saveBody(Map.of("userId", "userId"), new HashMap<>(), String.class);
  }
}
