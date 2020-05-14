package com.github.aoreshin.allure.rest.assured;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ApiExtractingStepsTests {
  @Test
  void saveResponse() {
    // Fixture setup
    String key = "key";

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> extractableResponse = mock(ExtractableResponse.class);
    Response response = mock(Response.class);

    when(extractableResponse.response()).thenReturn(response);

    ApiExtractingSteps steps = new ApiExtractingSteps(extractableResponse);

    // Executing SUT
    steps.saveResponse(key, map);

    assertEquals(response, map.get(key));
  }

  @Test
  void saveWholeBody() {
    // Fixture setup
    String key = "key";
    Class<Object> objectClass = Object.class;
    Object body = new Object();
    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class, RETURNS_DEEP_STUBS);

    when(response.body().as(objectClass)).thenReturn(body);

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveBody(key, map, objectClass);

    assertEquals(body, map.get(key));
  }

  @Test
  void saveBody() {
    // Fixture setup
    String path = "path";
    String key = "key";
    Integer value = 1;

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);

    when(response.path(path)).thenReturn(value);

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveBody(path, key, map, Integer.class);

    assertEquals(value, map.get(key));
  }

  @Test
  void saveBodyMap() {
    // Fixture setup
    Map<String, String> pathsAndKeys =
        Map.of(
            "expression1", "key1",
            "expression2", "key2",
            "expression3", "key3");

    Map<String, String> values =
        Map.of(
            "expression1", "value1",
            "expression2", "value2",
            "expression3", "value3");

    Map<String, Object> expected =
        Map.of(
            "key1", "value1",
            "key2", "value2",
            "key3", "value3");

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);

    values.forEach((expression, value) -> when(response.path(expression)).thenReturn(value));

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveBody(pathsAndKeys, map, String.class);

    assertEquals(expected, map);
  }

  @Test
  void saveHeader() {
    // Fixture setup
    String header = "authorization";
    String key = "authorization";
    String value = "password";

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);

    when(response.header(header)).thenReturn(value);

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveHeader(header, key, map);

    assertEquals(value, map.get(key));
  }

  @Test
  void saveHeaderMap() {
    // Fixture setup
    Map<String, String> headersAndKeys =
        Map.of(
            "header1", "key1",
            "header2", "key2",
            "header3", "key3");

    Map<String, String> values =
        Map.of(
            "header1", "value1",
            "header2", "value2",
            "header3", "value3");

    Map<String, Object> expected =
        Map.of(
            "key1", "value1",
            "key2", "value2",
            "key3", "value3");

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);

    values.forEach((header, value) -> when(response.header(header)).thenReturn(value));

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveHeader(headersAndKeys, map);

    assertEquals(expected, map);
  }

  @Test
  void next() {
    assertDoesNotThrow(() -> new ApiExtractingSteps(null).next());
  }

  @Test
  void saveCookie() {
    // Fixture setup
    String cookieName = "deliciousCookie";
    String key = "key";
    String value = "oreo";

    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);
    when(response.cookie(cookieName)).thenReturn(value);

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveCookie(cookieName, key, map);

    assertEquals(value, map.get(key));
  }

  @Test
  void saveAllCookies() {
    // Fixture setup
    Map<String, String> cookies = Map.of("one", "two", "three", "four");
    String key = "keyyy";
    Map<String, Object> map = new HashMap<>();

    ExtractableResponse<Response> response = mock(ExtractableResponse.class);
    when(response.cookies()).thenReturn(cookies);

    ApiExtractingSteps steps = new ApiExtractingSteps(response);

    // Executing SUT
    steps.saveAllCookies(key, map);

    assertEquals(cookies, map.get(key));
  }
}
