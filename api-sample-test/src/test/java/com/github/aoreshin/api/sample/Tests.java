package com.github.aoreshin.api.sample;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;

import org.junit.jupiter.api.Test;

public class Tests {
  @Test
  void verifyError() {
    apiRequest().get("https://google.com").statusCode(200);
  }
}
