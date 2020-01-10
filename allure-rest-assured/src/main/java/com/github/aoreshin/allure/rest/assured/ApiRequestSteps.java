package com.github.aoreshin.allure.rest.assured;

import static io.restassured.RestAssured.given;

import com.github.aoreshin.junit5.allure.steps.StepWrapperSteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

/** Steps for building request and sending it */
public final class ApiRequestSteps extends StepWrapperSteps<ApiRequestSteps> {
  private RequestSpecification requestSpecification = given();

  private ApiRequestSteps() {}

  public static ApiRequestSteps apiRequest() {
    return new ApiRequestSteps();
  }

  @Step("Добавление заголовка {name}={value}")
  public ApiRequestSteps headers(String name, String value) {
    requestSpecification.header(name, value);
    return this;
  }

  @Step("Добавление заголовков {headers}")
  public ApiRequestSteps headers(Map<String, String> headers) {
    requestSpecification.headers(headers);
    return this;
  }

  @Step("Добавление cookie файла")
  public ApiRequestSteps cookie(String cookieName) {
    requestSpecification.cookie(cookieName);
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
  public ApiRequestSteps params(Map<String, String> paramMap) {
    requestSpecification.params(paramMap);
    return this;
  }

  @Step("Добавление тела запроса")
  public ApiRequestSteps body(Object body) {
    requestSpecification.body(body);
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
