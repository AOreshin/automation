package com.github.aoreshin.allure.rest.assured;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;
import static java.util.stream.Collectors.toMap;

/** Steps for extracting data from Rest Assured's Response */
public class ApiExtractingSteps extends StepWrapperSteps<ApiExtractingSteps> {
  private final ExtractableResponse<Response> extractableResponse;

  ApiExtractingSteps(ExtractableResponse<Response> extractableResponse) {
    this.extractableResponse = extractableResponse;
  }

  @Step("Сохранение ответа как {key}")
  public <T> ApiExtractingSteps saveResponse(String key, Map<String, Object> map) {
    map.put(key, extractableResponse.response());
    return this;
  }

  @Step("Сохранение тела запроса как {key}")
  public <T> ApiExtractingSteps saveBody(String key, Map<String, Object> map, Class<T> type) {
    T value = extractableResponse.body().as(type);
    map.put(key, value);
    return this;
  }

  @Step("Сохранение поля {path} как {key}")
  public <T> ApiExtractingSteps saveBody(
      String path, String key, Map<String, Object> map, Class<T> type) {
    T value = extractableResponse.path(path);
    map.put(key, value);
    return this;
  }

  @Step("Сохранение полей с соответствующими ключами {pathsAndKeys}")
  public <T> ApiExtractingSteps saveBody(
      Map<String, String> pathsAndKeys, Map<String, Object> map, Class<T> type) {
    Map<String, T> values =
        pathsAndKeys.entrySet().stream()
            .collect(
                toMap(
                    Map.Entry::getValue,
                    entry -> {
                      String expression = entry.getKey();
                      return extractableResponse.path(expression);
                    }));
    map.putAll(values);
    return this;
  }

  @Step("Сохранение заголовка {header} как {key}")
  public ApiExtractingSteps saveHeader(String header, String key, Map<String, Object> map) {
    String value = extractableResponse.header(header);
    map.put(key, value);
    return this;
  }

  @Step("Сохранение заголовков как соответствующие им ключи {pathsAndKeys}")
  public ApiExtractingSteps saveHeader(
      Map<String, String> headersAndKeys, Map<String, Object> map) {
    Map<String, String> values =
        headersAndKeys.entrySet().stream()
            .collect(
                toMap(Map.Entry::getValue, entry -> extractableResponse.header(entry.getKey())));
    map.putAll(values);
    return this;
  }

  @Step("Сохранение cookie {cookieName} как {key}")
  public ApiExtractingSteps saveCookie(String cookieName, String key, Map<String, Object> map) {
    String cookie = extractableResponse.cookie(cookieName);
    map.put(key, cookie);
    return this;
  }

  @Step("Сохранение всех cookies как {key}")
  public ApiExtractingSteps saveAllCookies(String key, Map<String, Object> map) {
    Map<String, String> cookies = extractableResponse.cookies();
    map.put(key, cookies);
    return this;
  }

  public ApiRequestSteps next() {
    return apiRequest();
  }
}
