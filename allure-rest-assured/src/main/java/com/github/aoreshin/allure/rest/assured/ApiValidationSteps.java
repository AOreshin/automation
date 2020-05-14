package com.github.aoreshin.allure.rest.assured;

import static com.github.aoreshin.allure.rest.assured.ApiRequestSteps.apiRequest;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.Argument;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.function.Executable;

/** Step for validating Rest Assured's Response */
public class ApiValidationSteps extends StepWrapperSteps<ApiValidationSteps> {
  private final ValidatableResponse response;

  ApiValidationSteps(ValidatableResponse response) {
    this.response = response;
  }

  @Step("Проверка кода {status}")
  public ApiValidationSteps statusCode(int status) {
    response.statusCode(status);
    return this;
  }

  @Step("Проверка кода {expectedStatusCode}")
  public ApiValidationSteps statusCode(Matcher<? super Integer> expectedStatusCode) {
    response.statusCode(expectedStatusCode);
    return this;
  }

  @Step("Проверка Content-Type {contentType}")
  public ApiValidationSteps contentType(ContentType contentType) {
    response.contentType(contentType);
    return this;
  }

  @Step("Проверка Content-Type {contentType}")
  public ApiValidationSteps contentType(String contentType) {
    response.contentType(contentType);
    return this;
  }

  @Step("Проверка Content-Type {contentType}")
  public ApiValidationSteps contentType(Matcher<? super String> contentType) {
    response.contentType(contentType);
    return this;
  }

  @Step("Проверка статуса {expectedStatusLine}")
  public ApiValidationSteps statusLine(Matcher<? super String> expectedStatusLine) {
    response.statusLine(expectedStatusLine);
    return this;
  }

  @Step("Проверка статуса {expectedStatusLine}")
  public ApiValidationSteps statusLine(String expectedStatusLine) {
    response.statusLine(expectedStatusLine);
    return this;
  }

  @Step("Проверка заголовков {expectedHeaders}")
  public ApiValidationSteps headers(Map<String, ?> expectedHeaders) {
    response.headers(expectedHeaders);
    return this;
  }

  @Step(
      "Проверка заголовков {firstExpectedHeaderName}, {firstExpectedHeaderValue}, {expectedHeaders}")
  public ApiValidationSteps headers(
      String firstExpectedHeaderName, Object firstExpectedHeaderValue, Object... expectedHeaders) {
    response.headers(firstExpectedHeaderName, firstExpectedHeaderValue, expectedHeaders);
    return this;
  }

  @Step("Проверка заголовков {headerName}, {expectedValueMatcher}")
  public ApiValidationSteps headers(String headerName, Matcher<?> expectedValueMatcher) {
    response.headers(headerName, expectedValueMatcher);
    return this;
  }

  @Step("Проверка заголовка {headerName}, {expectedValue}")
  public ApiValidationSteps header(String headerName, String expectedValue) {
    response.header(headerName, expectedValue);
    return this;
  }

  @Step("Проверка Cookie {expectedCookies}")
  public ApiValidationSteps cookies(Map<String, ?> expectedCookies) {
    response.cookies(expectedCookies);
    return this;
  }

  @Step(
      "Проверка Cookie {firstExpectedCookieName}, {firstExpectedCookieValue}, {expectedCookieNameValuePairs}")
  public ApiValidationSteps cookies(
      String firstExpectedCookieName,
      Object firstExpectedCookieValue,
      Object... expectedCookieNameValuePairs) {
    response.cookies(
        firstExpectedCookieName, firstExpectedCookieValue, expectedCookieNameValuePairs);
    return this;
  }

  @Step("Проверка Cookie {cookieName}")
  public ApiValidationSteps cookie(String cookieName) {
    response.cookie(cookieName);
    return this;
  }

  @Step("Проверка Cookie {cookieName}, {expectedValueMatcher}")
  public ApiValidationSteps cookie(String cookieName, Matcher<?> expectedValueMatcher) {
    response.cookie(cookieName, expectedValueMatcher);
    return this;
  }

  @Step("Проверка Cookie {cookieName}, {detailedCookieMatcher}")
  public ApiValidationSteps cookie(String cookieName, DetailedCookieMatcher detailedCookieMatcher) {
    response.cookie(cookieName, detailedCookieMatcher);
    return this;
  }

  @Step("Проверка Cookie {cookieName}, {expectedValue}")
  public ApiValidationSteps cookie(String cookieName, Object expectedValue) {
    response.cookie(cookieName, expectedValue);
    return this;
  }

  @Step("Проверка времени ответа {matcher}")
  public ApiValidationSteps time(Matcher<Long> matcher) {
    response.time(matcher);
    return this;
  }

  @Step("Проверка времени ответа {matcher}, {timeUnit}")
  public ApiValidationSteps time(Matcher<Long> matcher, TimeUnit timeUnit) {
    response.time(matcher, timeUnit);
    return this;
  }

  @Step("Проверка {path}, {arguments}, {matcher}, {additionalKeyMatcherPairs}")
  public ApiValidationSteps body(
      String path,
      List<Argument> arguments,
      Matcher<?> matcher,
      Object... additionalKeyMatcherPairs) {
    response.body(path, arguments, matcher, additionalKeyMatcherPairs);
    return this;
  }

  @Step("Проверка {arguments}, {matcher}, {additionalKeyMatcherPairs}")
  public ApiValidationSteps body(
      List<Argument> arguments, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
    response.body(arguments, matcher, additionalKeyMatcherPairs);
    return this;
  }

  @Step("Проверка {matcher}, {additionalMatchers}")
  public ApiValidationSteps body(Matcher<?> matcher, Matcher<?>... additionalMatchers) {
    response.body(matcher, additionalMatchers);
    return this;
  }

  @Step("Проверка {path}, {matcher}, {additionalKeyMatcherPairs}")
  public ApiValidationSteps body(
      String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
    response.body(path, matcher, additionalKeyMatcherPairs);
    return this;
  }

  @Step("Проверка равенства {path} значению {expected}")
  public <T> ApiValidationSteps assertEquals(String path, T expected) {
    response.body(path, equalTo(expected));
    return this;
  }

  @Step("Проверка равенства полей соответствующим значениям {values}")
  public <T> ApiValidationSteps assertEquals(Map<String, String> values) {
    List<Executable> executables =
        values.entrySet().stream()
            .map(
                entry -> {
                  String path = entry.getKey();
                  String expected = entry.getValue();
                  return (Executable) () -> response.body(path, equalTo(expected));
                })
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка содержания в {path} значения {expected}")
  public <T> ApiValidationSteps assertContains(String path, T expected) {
    response.body(path, contains(expected));
    return this;
  }

  @Step("Проверка содержания полями соответствующих значений {values}")
  public <T> ApiValidationSteps assertContains(Map<String, T> values) {
    List<Executable> executables =
        values.entrySet().stream()
            .map(
                entry -> {
                  String path = entry.getKey();
                  T expected = entry.getValue();
                  return (Executable) () -> response.body(path, contains(expected));
                })
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка содержания в {path} значения {expected}")
  public ApiValidationSteps assertContainsString(String path, String expected) {
    response.body(path, containsString(expected));
    return this;
  }

  @Step("Проверка содержания полями соответствующих значений {values}")
  public ApiValidationSteps assertContainsString(Map<String, String> values) {
    List<Executable> executables =
        values.entrySet().stream()
            .map(
                entry -> {
                  String path = entry.getKey();
                  String expected = entry.getValue();
                  return (Executable) () -> response.body(path, containsString(expected));
                })
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка пустоты {path}")
  public ApiValidationSteps assertNull(String path) {
    response.body(path, nullValue());
    return this;
  }

  @Step("Проверка пустоты {paths}")
  public ApiValidationSteps assertNull(List<String> paths) {
    List<Executable> executables =
        paths.stream()
            .map(path -> (Executable) () -> response.body(path, nullValue()))
            .collect(toList());

    assertAll(executables);
    return this;
  }

  @Step("Проверка {path} не пуст")
  public ApiValidationSteps assertNotNull(String path) {
    response.body(path, notNullValue());
    return this;
  }

  @Step("Проверка {paths} не пустые")
  public ApiValidationSteps assertNotNull(List<String> paths) {
    List<Executable> executables =
        paths.stream()
            .map(path -> (Executable) () -> response.body(path, notNullValue()))
            .collect(toList());

    assertAll(executables);
    return this;
  }

  public ApiRequestSteps next() {
    return apiRequest();
  }

  public ApiExtractingSteps extract() {
    return new ApiExtractingSteps(response.extract());
  }
}
