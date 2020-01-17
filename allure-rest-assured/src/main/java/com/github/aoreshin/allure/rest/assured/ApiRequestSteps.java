package com.github.aoreshin.allure.rest.assured;

import static io.restassured.RestAssured.given;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** Steps for building request and sending it */
public class ApiRequestSteps extends StepWrapperSteps<ApiRequestSteps> {
  private RequestSpecification requestSpecification = given();

  private ApiRequestSteps() {}

  public static ApiRequestSteps apiRequest() {
    return new ApiRequestSteps();
  }

  @Step("Добавление заголовка {name}={value}")
  public ApiRequestSteps header(String name, String value) {
    requestSpecification.header(name, value);
    return this;
  }

  @Step("Добавление заголовков {headers}")
  public ApiRequestSteps headers(Map<String, String> headers) {
    requestSpecification.headers(headers);
    return this;
  }

  @Step("Добавление cookie {cookieName}")
  public ApiRequestSteps cookie(String cookieName) {
    requestSpecification.cookie(cookieName);
    return this;
  }

  @Step("Добавление cookie {cookieName}={value}, {additionalValues}")
  public ApiRequestSteps cookie(String cookieName, Object value, Object... additionalValues) {
    requestSpecification.cookie(cookieName, value, additionalValues);
    return this;
  }

  @Step("Добавление cookies {cookies}")
  public ApiRequestSteps cookies(Map<String, ?> cookies) {
    requestSpecification.cookies(cookies);
    return this;
  }

  @Step("Добавление cookie {firstCookieName}={firstCookieValue}, {cookieNameValuePairs}")
  public ApiRequestSteps cookies(
      String firstCookieName, Object firstCookieValue, Object... cookieNameValuePairs) {
    requestSpecification.cookies(firstCookieName, firstCookieValue, cookieNameValuePairs);
    return this;
  }

  @Step("Добавление параметра {name}={value}")
  public ApiRequestSteps param(String name, String value) {
    requestSpecification.param(name, value);
    return this;
  }

  @Step("Добавление списка параметров {name}={paramList}")
  public ApiRequestSteps param(String name, List<String> paramList) {
    requestSpecification.param(name, paramList);
    return this;
  }

  @Step("Добавление мапы параметров {paramMap}")
  public ApiRequestSteps params(Map<String, ?> paramMap) {
    requestSpecification.params(paramMap);
    return this;
  }

  @Step("Добавление query параметров {name}={parameterValues}")
  public ApiRequestSteps queryParam(String name, Object... parameterValues) {
    requestSpecification.queryParam(name, parameterValues);
    return this;
  }

  @Step("Добавление query параметров {name}={parameterValues}")
  public ApiRequestSteps queryParam(String name, Collection<?> parameterValues) {
    requestSpecification.queryParam(name, parameterValues);
    return this;
  }

  @Step("Добавление мапы query параметров {paramMap}")
  public ApiRequestSteps queryParams(Map<String, ?> paramMap) {
    requestSpecification.queryParams(paramMap);
    return this;
  }

  @Step("Добавление form параметра {parameterName}={parameterValues}")
  public ApiRequestSteps formParam(String parameterName, Object... parameterValues) {
    requestSpecification.formParam(parameterName, parameterValues);
    return this;
  }

  @Step("Добавление form параметра {parameterName}={parameterValues}")
  public ApiRequestSteps formParam(String parameterName, Collection<?> parameterValues) {
    requestSpecification.formParam(parameterName, parameterValues);
    return this;
  }

  @Step("Добавление мапы form параметров {paramMap}")
  public ApiRequestSteps formParams(Map<String, ?> paramMap) {
    requestSpecification.formParams(paramMap);
    return this;
  }

  @Step("Добавление тела запроса")
  public ApiRequestSteps body(Object body) {
    requestSpecification.body(body);
    return this;
  }

  @Step("Обработка и добавление тела запроса с {objectMapper}")
  public ApiRequestSteps body(Object body, ObjectMapper objectMapper) {
    requestSpecification.body(body, objectMapper);
    return this;
  }

  @Step("Отправка POST запроса на {url}")
  public ApiValidationSteps post(String url) {
    return new ApiValidationSteps(requestSpecification.post(url));
  }

  @Step("Отправка PUT запроса на {url}")
  public ApiValidationSteps put(String url) {
    return new ApiValidationSteps(requestSpecification.put(url));
  }

  @Step("Отправка GET запроса на {url}")
  public ApiValidationSteps get(String url) {
    return new ApiValidationSteps(requestSpecification.get(url));
  }

  @Step("Отправка DELETE запроса на {url}")
  public ApiValidationSteps delete(String url) {
    return new ApiValidationSteps(requestSpecification.delete(url));
  }

  @Step("Отправка HEAD запроса на {url}")
  public ApiValidationSteps head(String url) {
    return new ApiValidationSteps(requestSpecification.head(url));
  }

  @Step("Отправка PATCH запроса на {url}")
  public ApiValidationSteps patch(String url) {
    return new ApiValidationSteps(requestSpecification.patch(url));
  }

  /** Only for testing */
  void setRequestSpecification(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
  }
}
