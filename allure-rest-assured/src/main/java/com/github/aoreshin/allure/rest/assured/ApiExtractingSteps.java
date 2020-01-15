package com.github.aoreshin.allure.rest.assured;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;
import static java.util.stream.Collectors.toMap;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

/** Steps for extracting data from Rest Assured's Response */
public class ApiExtractingSteps extends StepWrapperSteps<ApiExtractingSteps> {
  private final Response response;

  ApiExtractingSteps(Response response) {
    this.response = response;
  }

  @Step("Сохранение {jsonPath} как {key}")
  public <T> ApiExtractingSteps saveBodyJsonPath(String jsonPath, String key, Map<String, Object> map, Class<T> type) {
    T value = response.getBody().jsonPath().get(jsonPath);
    map.put(key, value);
    return this;
  }

  @Step("Сохранение полей с соответствующими ключами {pathsAndKeys}")
  public <T> ApiExtractingSteps saveBodyJsonPath(
      Map<String, String> pathsAndKeys, Map<String, Object> map, Class<T> type) {
    Map<String, T> values =
        pathsAndKeys.entrySet().stream()
            .collect(
                toMap(
                    Map.Entry::getValue,
                    entry -> {
                      String expression = entry.getKey();
                      return response.getBody().jsonPath().get(expression);
                    }));
    map.putAll(values);
    return this;
  }

  @Step("Сохранение заголовка {header} как {key}")
  public ApiExtractingSteps saveHeader(String header, String key, Map<String, Object> map) {
    String value = response.getHeader(header);
    map.put(key, value);
    return this;
  }

  @Step("Сохранение заголовков как соответствующие им ключи {pathsAndKeys}")
  public ApiExtractingSteps saveHeader(
      Map<String, String> headersAndKeys, Map<String, Object> map) {
    Map<String, String> values =
        headersAndKeys.entrySet().stream()
            .collect(toMap(Map.Entry::getValue, entry -> response.getHeader(entry.getKey())));
    map.putAll(values);
    return this;
  }

  @Step("Сохранение cookie {cookieName} как {key}")
  public ApiExtractingSteps saveCookie(String cookieName, String key, Map<String, Object> map) {
    String cookie = response.cookie(cookieName);
    map.put(key, cookie);
    return this;
  }

  @Step("Сохранение всех cookies как {key}")
  public ApiExtractingSteps saveAllCookies(String key, Map<String, Object> map) {
    Map<String, String> cookies = response.cookies();
    map.put(key, cookies);
    return this;
  }

  public ApiRequestSteps next() {
    return apiRequest();
  }
}
