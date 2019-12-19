package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckboxStepRepositoryTests {
  private static class CheckboxStepRepositoryImpl
      implements CheckboxStepRepository<CheckboxStepRepositoryImpl> {}

  private final CheckboxStepRepository<CheckboxStepRepositoryImpl> checkboxStepRepository =
      new CheckboxStepRepositoryImpl();

  @Test
  void clickCheckbox() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.clickCheckbox(NAME));
  }

  @Test
  void clickCheckboxList() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.clickCheckbox(NAME_LIST));
  }

  @Test
  void checkCheckboxIsSelected() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsSelected(NAME));
  }

  @Test
  void checkCheckboxListIsSelected() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsSelected(NAME_LIST));
  }

  @Test
  void checkCheckboxIsNotSelected() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsNotSelected(NAME));
  }

  @Test
  void checkCheckboxListIsNotSelected() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsNotSelected(NAME_LIST));
  }

  @Test
  void checkCheckboxIsDisplayed() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsDisplayed(NAME));
  }

  @Test
  void checkCheckboxListIsDisplayed() {
    assertThrowsNotImplemented(() -> checkboxStepRepository.checkCheckboxIsDisplayed(NAME_LIST));
  }
}
