package com.github.aoreshin.junit5.allure.steps.steprepositories;

import com.github.aoreshin.junit5.allure.steps.StepRepository;
import io.qameta.allure.Step;
import java.util.List;

@StepRepository
public interface CheckboxStepRepository<T> {
  @Step("Клик чекбокса {name}")
  default T clickCheckbox(final String name) {
    throw new NotImplementedException();
  }

  @Step("Клик чекбоксов {names}")
  default T clickCheckbox(final List<String> names) {
    throw new NotImplementedException();
  }

  @Step("Проверка выбора чекбокса {name}")
  default T checkCheckboxIsSelected(final String name) {
    throw new NotImplementedException();
  }

  @Step("Проверка выбора чекбоксов {names}")
  default T checkCheckboxIsSelected(final List<String> names) {
    throw new NotImplementedException();
  }

  @Step("Проверка что чекбокс {name} не выбран")
  default T checkCheckboxIsNotSelected(final String name) {
    throw new NotImplementedException();
  }

  @Step("Проверка что чекбоксы не выбраны {names}")
  default T checkCheckboxIsNotSelected(final List<String> names) {
    throw new NotImplementedException();
  }

  @Step("Проверка отображения чекбокса {name}")
  default T checkCheckboxIsDisplayed(final String name) {
    throw new NotImplementedException();
  }

  @Step("Проверка отображения чекбоксов {name}")
  default T checkCheckboxIsDisplayed(final List<String> names) {
    throw new NotImplementedException();
  }
}
