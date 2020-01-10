package com.github.aoreshin.allure.rest.assured;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ApiRequestStepsTests {
  @Test
  void apiRequest() {
    assertDoesNotThrow(ApiRequestSteps::apiRequest);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addHeader(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "header";
    String value = "value";

    apiRequestSteps.headers(name, value);

    verify(requestSpec, only()).header(name, value);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addHeaderMap(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, String> headers =
        Map.of(
            "header1", "value1",
            "header2", "value2",
            "header3", "value3");

    apiRequestSteps.headers(headers);

    verify(requestSpec, only()).headers(headers);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addCookie(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String cookieName = "ILOVECOOKIES";

    apiRequestSteps.cookie(cookieName);

    verify(requestSpec, only()).cookie(cookieName);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addParam(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "parameter";
    String value = "value";

    apiRequestSteps.param(name, value);

    verify(requestSpec, only()).param(name, value);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addParamList(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    List<String> parameters = List.of("value1", "value2", "value3");

    apiRequestSteps.param(name, parameters);

    verify(requestSpec, only()).param(name, parameters);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void addParamMap(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, String> parameters =
        Map.of(
            "header1", "value1",
            "header2", "value2",
            "header3", "value3");

    apiRequestSteps.params(parameters);

    verify(requestSpec, only()).params(parameters);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void setBody(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    class Person {
      private final String name;
      private final int age;

      Person(String name, int age) {
        this.name = name;
        this.age = age;
      }
    }

    Person person = new Person("Ozzy Osbourne", 71);

    apiRequestSteps.body(person);

    verify(requestSpec, only()).body(person);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void post(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.post(url);

    verify(requestSpec, only()).post(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void put(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.put(url);

    verify(requestSpec, only()).put(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void get(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.get(url);

    verify(requestSpec, only()).get(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void delete(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.delete(url);

    verify(requestSpec, only()).delete(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void head(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.head(url);

    verify(requestSpec, only()).head(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void patch(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.patch(url);

    verify(requestSpec, only()).patch(url);
  }

  private static Stream<Arguments> requestSpecMockProvider() {
    String url = "https://google.com";

    RequestSpecification requestSpecification = mock(RequestSpecification.class);
    ApiRequestSteps apiRequestSteps = ApiRequestSteps.apiRequest();
    apiRequestSteps.setRequestSpecification(requestSpecification);

    return Stream.of(Arguments.of(requestSpecification, apiRequestSteps, url));
  }
}
