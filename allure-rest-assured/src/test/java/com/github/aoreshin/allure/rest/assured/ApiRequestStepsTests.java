package com.github.aoreshin.allure.rest.assured;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import io.restassured.builder.ResponseBuilder;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ApiRequestStepsTests {
  static {
    Awaitility.setDefaultTimeout(Duration.ofMillis(500));
    Awaitility.setDefaultPollInterval(Duration.ofMillis(100));
  }

  private static final String CONDITION_MESSAGE = "statusCode equals 200";

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
  void cookieName(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String cookieName = "ILOVECOOKIES";

    apiRequestSteps.cookie(cookieName);

    verify(requestSpec, only()).cookie(cookieName);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void cookieValue(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String cookieName = "ILOVECOOKIES";
    Object value = new Object();
    Object anotherValue = new Object();

    apiRequestSteps.cookie(cookieName, value, anotherValue);

    verify(requestSpec, only()).cookie(cookieName, value, anotherValue);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void cookiesMap(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    Map<String, ?> cookies = Map.of("cookie", 1, "candy", "blah", "chocolate", new Object());

    apiRequestSteps.cookies(cookies);

    verify(requestSpec, only()).cookies(cookies);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void cookiesVarArgs(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps) {
    String firstCookieName = "cookie1";
    String firstCookieValue = "1";

    Object[] otherValues = new Object[] {"cookie", 1, "candy", "blah", "chocolate", new Object()};

    apiRequestSteps.cookies(firstCookieName, firstCookieValue, otherValues);

    verify(requestSpec, only()).cookies(firstCookieName, firstCookieValue, otherValues);
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
  void postPollingPass(
      RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.post(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.post(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).post(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void postPollingFail(
      RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.post(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
        ConditionTimeoutException.class,
        () -> apiRequestSteps.post(url, matcher, CONDITION_MESSAGE));
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void put(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.put(url);

    verify(requestSpec, only()).put(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void putPollingPass(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.put(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.put(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).put(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void putPollingFail(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.put(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
            ConditionTimeoutException.class,
            () -> apiRequestSteps.put(url, matcher, CONDITION_MESSAGE));
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void get(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.get(url);

    verify(requestSpec, only()).get(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void getPollingPass(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.get(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.get(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).get(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void getPollingFail(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.get(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
            ConditionTimeoutException.class,
            () -> apiRequestSteps.get(url, matcher, CONDITION_MESSAGE));
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void delete(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.delete(url);

    verify(requestSpec, only()).delete(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void deletePollingPass(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.delete(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.delete(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).delete(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void deletePollingFail(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.delete(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
            ConditionTimeoutException.class,
            () -> apiRequestSteps.delete(url, matcher, CONDITION_MESSAGE));
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void head(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.head(url);

    verify(requestSpec, only()).head(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void headPollingPass(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.head(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.head(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).head(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void headPollingFail(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.head(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
            ConditionTimeoutException.class,
            () -> apiRequestSteps.head(url, matcher, CONDITION_MESSAGE));
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void patch(RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    apiRequestSteps.patch(url);

    verify(requestSpec, only()).patch(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void patchPollingPass(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    //Setup fixture
    Response firstResponse = getResponseWithStatusCode(500);
    Response secondResponse = getResponseWithStatusCode(200);

    when(requestSpec.patch(url)).thenReturn(firstResponse).thenReturn(secondResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    //Executing and verifying
    assertDoesNotThrow(() -> apiRequestSteps.patch(url, matcher, CONDITION_MESSAGE));;
    verify(requestSpec, times(2)).patch(url);
  }

  @ParameterizedTest
  @MethodSource("requestSpecMockProvider")
  void patchPollingFail(
          RequestSpecification requestSpec, ApiRequestSteps apiRequestSteps, String url) {
    Response serverErrorResponse = getResponseWithStatusCode(500);

    when(requestSpec.patch(url)).then(invocationOnMock -> serverErrorResponse);

    Matcher<Response> matcher = getResponseStatusCodeOkMatcher();

    assertThrows(
            ConditionTimeoutException.class,
            () -> apiRequestSteps.patch(url, matcher, CONDITION_MESSAGE));
  }

  private static Stream<Arguments> requestSpecMockProvider() {
    String url = "https://google.com";

    RequestSpecification requestSpecification = mock(RequestSpecification.class);
    ApiRequestSteps apiRequestSteps = ApiRequestSteps.apiRequest();
    apiRequestSteps.setRequestSpecification(requestSpecification);

    return Stream.of(Arguments.of(requestSpecification, apiRequestSteps, url));
  }

  private Matcher<Response> getResponseStatusCodeOkMatcher() {
    return new BaseMatcher<>() {
      @Override
      public boolean matches(Object actual) {
        return ((Response) actual).getStatusCode() == 200;
      }

      @Override
      public void describeTo(Description description) {}
    };
  }

  private Response getResponseWithStatusCode(int i) {
    return new ResponseBuilder().setStatusCode(i).build();
  }
}
