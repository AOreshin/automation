package com.github.aoreshin.junit5.allure.steps.steprepositories;

import com.github.aoreshin.junit5.allure.steps.StepRepository;
import io.qameta.allure.Step;

@StepRepository
public interface WebStepRepository<T> {
  @Step("Переход по ссылке {url}")
  default T openUrl(final String url) {
    throw new NotImplementedException();
  }

  @Step("Проверка отображения формы {name}")
  default T checkFormIsDisplayed(String name) {
    throw new NotImplementedException();
  }
}
