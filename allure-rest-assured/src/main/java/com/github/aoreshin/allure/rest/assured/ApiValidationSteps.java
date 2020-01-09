package com.github.aoreshin.allure.rest.assured;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.function.Executable;

/** Step for validating Rest Assured's Response */
public final class ApiValidationSteps extends StepWrapperSteps<ApiValidationSteps> {
  private final Response response;

  ApiValidationSteps(Response response) {
    this.response = response;
  }

  @Step("Проверка кода {status}")
  public ApiValidationSteps statusCode(int status) {
    assertEquals(status, response.statusCode());
    return this;
  }

  @Step("Проверка Content-Type {contentType}")
  public ApiValidationSteps contentType(ContentType contentType) {
    assertEquals(contentType, ContentType.fromContentType(response.getContentType()));
    return this;
  }

  @Step("Проверка равенства {jsonPath} значению {expected}")
  public <T> ApiValidationSteps assertEqualsJson(String jsonPath, T expected) {
    T actual = response.getBody().jsonPath().get(jsonPath);
    assertEquals(expected, actual);
    return this;
  }

  @Step("Проверка равенства полей соответствующим значениям {values}")
  public <T> ApiValidationSteps assertEqualsJson(Map<String, String> values) {
    List<Executable> executables =
        values.entrySet().stream()
            .map(
                entry -> {
                  String expected = entry.getValue();
                  String actual = response.getBody().jsonPath().get(entry.getKey());
                  return (Executable) () -> assertEquals(expected, actual);
                })
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка содержания в {jsonPath} значения {expected}")
  public ApiValidationSteps assertContainsStringJson(String jsonPath, String expected) {
    String actual = response.getBody().jsonPath().get(jsonPath);
    assertTrue(actual.contains(expected), actual + " не содержит " + expected);
    return this;
  }

  @Step("Проверка содержания полями соответствующих значений {values}")
  public ApiValidationSteps assertContainsStringJson(Map<String, String> values) {
    List<Executable> executables =
        values.entrySet().stream()
            .map(
                entry -> {
                  String expected = entry.getValue();
                  String actual = response.getBody().jsonPath().get(entry.getKey());
                  return (Executable)
                      () ->
                          assertTrue(
                              actual.contains(expected), actual + " не содержит " + expected);
                })
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка пустоты {jsonPath}")
  public ApiValidationSteps assertNullJson(String jsonPath) {
    assertNull(response.getBody().jsonPath().get(jsonPath));
    return this;
  }

  @Step("Проверка {jsonPaths} пустые")
  public ApiValidationSteps assertNullJson(List<String> jsonPaths) {
    List<Executable> executables =
            jsonPaths.stream()
                    .map(
                            jsonPath ->
                                    (Executable) () -> assertNull(response.getBody().jsonPath().get(jsonPath)))
                    .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка {jsonPath} не пуст")
  public ApiValidationSteps assertNotNullJson(String jsonPath) {
    assertNotNull(response.getBody().jsonPath().get(jsonPath));
    return this;
  }

  @Step("Проверка {jsonPaths} не пустые")
  public ApiValidationSteps assertNotNullJson(List<String> jsonPaths) {
    List<Executable> executables =
        jsonPaths.stream()
            .map(
                jsonPath ->
                    (Executable) () -> assertNotNull(response.getBody().jsonPath().get(jsonPath)))
            .collect(toList());

    assertAll(executables);
    return this;
  }

  public ApiRequestSteps next() {
    return apiRequest();
  }

  public ApiExtractingSteps extract() {
    return new ApiExtractingSteps(response);
  }
}
