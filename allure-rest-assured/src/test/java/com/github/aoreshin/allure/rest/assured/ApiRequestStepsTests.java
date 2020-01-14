package com.github.aoreshin.allure.rest.assured;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import java.util.Collection;
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
  void header(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "header";
    String value = "value";

    apiRequestSteps.header(name, value);

    verify(requestSpec, only()).header(name, value);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void headers(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
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
  void cookie(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String cookieName = "ILOVECOOKIES";

    apiRequestSteps.cookie(cookieName);

    verify(requestSpec, only()).cookie(cookieName);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void cookies(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, ?> cookies = Map.of("cookie", 1, "candy", "blah", "chocolate", new Object());

    apiRequestSteps.cookies(cookies);

    verify(requestSpec, only()).cookies(cookies);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void param(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "parameter";
    String value = "value";

    apiRequestSteps.param(name, value);

    verify(requestSpec, only()).param(name, value);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void paramList(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    List<String> parameters = List.of("value1", "value2", "value3");

    apiRequestSteps.param(name, parameters);

    verify(requestSpec, only()).param(name, parameters);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void params(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
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
  void queryParamObject(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    Object parameterValue1 = new Object();
    Object parameterValue2 = new Object();

    apiRequestSteps.queryParam(name, parameterValue1, parameterValue2);

    verify(requestSpec, only()).queryParam(name, parameterValue1, parameterValue2);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void queryParamCollection(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    Collection<?> parameterValue = List.of("param1", new Object());

    apiRequestSteps.queryParam(name, parameterValue);

    verify(requestSpec, only()).queryParam(name, parameterValue);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void queryParams(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, ?> params = Map.of("param1", new Object(), "param2", 777);

    apiRequestSteps.queryParams(params);

    verify(requestSpec, only()).queryParams(params);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void formParamObject(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    Object parameterValue1 = new Object();
    Object parameterValue2 = new Object();

    apiRequestSteps.formParam(name, parameterValue1, parameterValue2);

    verify(requestSpec, only()).formParam(name, parameterValue1, parameterValue2);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void formParamCollection(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String name = "param";
    Collection<?> parameterValue = List.of("param1", new Object());

    apiRequestSteps.formParam(name, parameterValue);

    verify(requestSpec, only()).formParam(name, parameterValue);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void formParams(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, ?> params = Map.of("param1", new Object(), "param2", 777);

    apiRequestSteps.formParams(params);

    verify(requestSpec, only()).formParams(params);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void body(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
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
  void bodyMapper(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    class Person {
      private final String name;
      private final int age;

      Person(String name, int age) {
        this.name = name;
        this.age = age;
      }
    }

    Person person = new Person("Ozzy Osbourne", 71);
    ObjectMapper mapper = new Jackson2Mapper(null);
    apiRequestSteps.body(person, mapper);

    verify(requestSpec, only()).body(person, mapper);
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
