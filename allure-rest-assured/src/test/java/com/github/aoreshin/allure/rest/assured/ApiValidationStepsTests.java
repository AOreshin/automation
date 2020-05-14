package com.github.aoreshin.allure.rest.assured;

import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class ApiValidationStepsTests {
  private static final Matcher<String> DUMMY_STRING_MATCHER = equalTo("");
  private static final Matcher<Long> DUMMY_LONG_MATCHER = equalTo(1L);

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void statusCode(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    apiValidationSteps.statusCode(SC_OK);
    verify(response, only()).statusCode(SC_OK);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void statusCodeMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Matcher<Integer> matcher = equalTo(1);
    apiValidationSteps.statusCode(matcher);
    verify(response, only()).statusCode(matcher);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void contentType(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    apiValidationSteps.contentType(ContentType.JSON);
    verify(response, only()).contentType(ContentType.JSON);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void contentTypeString(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String contentType = "sweetContent";
    apiValidationSteps.contentType(contentType);
    verify(response, only()).contentType(contentType);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void contentTypeStringMatcher(
      ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    apiValidationSteps.contentType(DUMMY_STRING_MATCHER);
    verify(response, only()).contentType(DUMMY_STRING_MATCHER);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void statusLineStringMatcher(
      ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    apiValidationSteps.statusLine(DUMMY_STRING_MATCHER);
    verify(response, only()).statusLine(DUMMY_STRING_MATCHER);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void statusLine(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String statusLine = "statusLine";
    apiValidationSteps.statusLine(statusLine);
    verify(response, only()).statusLine(statusLine);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void headersMap(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Map<String, String> headers =
        Map.of(
            "header1", "value1",
            "header2", "value2",
            "header3", "value3");
    apiValidationSteps.headers(headers);
    verify(response, only()).headers(headers);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void headersString(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String header1 = "header1";
    String value1 = "value1";
    String header2 = "header2";
    String value2 = "value2";
    String header3 = "header3";
    String value3 = "value3";

    apiValidationSteps.headers(header1, value1, header2, value2, header3, value3);
    verify(response, only()).headers(header1, value1, header2, value2, header3, value3);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void headersMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String header = "header";
    apiValidationSteps.headers(header, DUMMY_STRING_MATCHER);
    verify(response, only()).headers(header, DUMMY_STRING_MATCHER);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void header(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String header = "header";
    String value = "value";
    apiValidationSteps.header(header, value);
    verify(response, only()).header(header, value);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookiesMap(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Map<String, String> cookies =
        Map.of(
            "cookie1", "value1",
            "cookie2", "value2",
            "cookie3", "value3");
    apiValidationSteps.cookies(cookies);
    verify(response, only()).cookies(cookies);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookiesString(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String cookie1 = "cookie1";
    String value1 = "value1";
    String cookie2 = "cookie2";
    String value2 = "value2";
    String cookie3 = "cookie3";
    String value3 = "value3";

    apiValidationSteps.cookies(cookie1, value1, cookie2, value2, cookie3, value3);
    verify(response, only()).cookies(cookie1, value1, cookie2, value2, cookie3, value3);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookie(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String cookie = "cookie";
    apiValidationSteps.cookie(cookie);
    verify(response, only()).cookie(cookie);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookieMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String cookie = "cookie";
    apiValidationSteps.cookie(cookie, DUMMY_STRING_MATCHER);
    verify(response, only()).cookie(cookie, DUMMY_STRING_MATCHER);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookieDetailedMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String cookie = "cookie";
    DetailedCookieMatcher matcher = mock(DetailedCookieMatcher.class);
    apiValidationSteps.cookie(cookie, matcher);
    verify(response, only()).cookie(cookie, matcher);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void cookieValue(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String cookie = "cookie";
    String value = "value";
    apiValidationSteps.cookie(cookie, value);
    verify(response, only()).cookie(cookie, value);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void time(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    apiValidationSteps.time(DUMMY_LONG_MATCHER);
    verify(response, only()).time(DUMMY_LONG_MATCHER);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void timeTimeUnit(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    TimeUnit timeUnit = TimeUnit.MICROSECONDS;
    apiValidationSteps.time(DUMMY_LONG_MATCHER, timeUnit);
    verify(response, only()).time(DUMMY_LONG_MATCHER, timeUnit);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void bodyPathArgsMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "some.field";
    List<Argument> args = List.of();
    Object[] additionalKeyMatcherPairs = new Object[0];

    apiValidationSteps.body(path, args, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
    verify(response, only()).body(path, args, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void bodyArgsMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    List<Argument> args = List.of();
    Object[] additionalKeyMatcherPairs = new Object[0];

    apiValidationSteps.body(args, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
    verify(response, only()).body(args, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void bodyMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Matcher<?>[] additionalMatchers = new Matcher[0];

    apiValidationSteps.body(DUMMY_STRING_MATCHER, additionalMatchers);
    verify(response, only()).body(DUMMY_STRING_MATCHER, additionalMatchers);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void bodyPathMatcher(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "some.field";
    Object[] additionalKeyMatcherPairs = new Object[0];

    apiValidationSteps.body(path, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
    verify(response, only()).body(path, DUMMY_STRING_MATCHER, additionalKeyMatcherPairs);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertEquals(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "some.field";
    String value = "value";

    apiValidationSteps.assertEquals(path, value);
    verify(response, only()).body(eq(path), equalsMatcher(value));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertEqualsMap(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Map<String, String> values =
        Map.of(
            "path1", "value1",
            "path2", "value2",
            "path3", "value3");

    apiValidationSteps.assertEquals(values);
    values.forEach(
        (path, value) -> verify(response, times(1)).body(eq(path), equalsMatcher(value)));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertContains(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "some.field";
    Object value = new Object();

    apiValidationSteps.assertContains(path, value);
    verify(response, only()).body(eq(path), containsMatcher(List.of(value)));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertContainsMap(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Map<String, Object> values =
        Map.of(
            "path1", new Object(),
            "path2", new Object(),
            "path3", new Object());

    apiValidationSteps.assertContains(values);
    values.forEach(
        (path, value) ->
            verify(response, times(1)).body(eq(path), containsMatcher(List.of(value))));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertContainsString(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "some.field";
    String value = "value";

    apiValidationSteps.assertContainsString(path, value);
    verify(response, only()).body(eq(path), containsStringMatcher(value));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertContainsStringMap(
      ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    Map<String, String> values =
        Map.of(
            "path1", "value1",
            "path2", "value2",
            "path3", "value3");

    apiValidationSteps.assertContainsString(values);
    values.forEach(
        (path, value) -> verify(response, times(1)).body(eq(path), containsStringMatcher(value)));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertNull(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "somePath";
    apiValidationSteps.assertNull(path);
    verify(response, only()).body(eq(path), nullMatcher());
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertNullList(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    List<String> paths = List.of("path1", "path2", "path3");
    apiValidationSteps.assertNull(paths);
    paths.forEach(path -> verify(response, times(1)).body(eq(path), nullMatcher()));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertNotNull(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    String path = "somePath";
    apiValidationSteps.assertNotNull(path);
    verify(response, only()).body(eq(path), notNullMatcher());
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void assertNotNullList(ApiValidationSteps apiValidationSteps, ValidatableResponse response) {
    List<String> paths = List.of("path1", "path2", "path3");

    apiValidationSteps.assertNotNull(paths);

    paths.forEach(path -> verify(response, times(1)).body(eq(path), notNullMatcher()));
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void next(ApiValidationSteps apiValidationSteps) {
    assertDoesNotThrow(apiValidationSteps::next);
  }

  @ParameterizedTest
  @MethodSource("stepsAndResponse")
  void extract(ApiValidationSteps apiValidationSteps) {
    assertDoesNotThrow(apiValidationSteps::extract);
  }

  private static Stream<Arguments> stepsAndResponse() {
    ValidatableResponse response = mock(ValidatableResponse.class);
    ApiValidationSteps validationSteps = new ApiValidationSteps(response);
    return Stream.of(Arguments.of(validationSteps, response));
  }

  private <T> Matcher<T> matcherOf(T value, Class<?> matcherClass) {
    return argThat(argument -> argument.matches(value) && argument.getClass().equals(matcherClass));
  }

  private Matcher<String> equalsMatcher(String value) {
    return matcherOf(value, IsEqual.class);
  }

  private Matcher<Object> containsMatcher(Object value) {
    return matcherOf(value, IsIterableContainingInOrder.class);
  }

  private Matcher<String> containsStringMatcher(String value) {
    return matcherOf(value, StringContains.class);
  }

  private Matcher<String> nullMatcher() {
    return matcherOf(null, IsNull.class);
  }

  private Matcher<String> notNullMatcher() {
    return argThat(argument -> !argument.matches(null));
  }
}
