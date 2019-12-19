package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import org.junit.jupiter.api.Test;

final class RadioButtonStepRepositoryTests {
  private static class RadioButtonStepRepositoryImpl
      implements RadioButtonStepRepository<RadioButtonStepRepositoryImpl> {}

  private final RadioButtonStepRepository<?> radioButtonStepRepository =
      new RadioButtonStepRepositoryImpl();

  @Test
  void checkRadioButtonIsDisplayedTest() {
    assertThrowsNotImplemented(() -> radioButtonStepRepository.checkRadioButtonIsDisplayed(NAME));
  }

  @Test
  void checkRadioButtonIsDisplayedListTest() {
    assertThrowsNotImplemented(
        () -> radioButtonStepRepository.checkRadioButtonIsDisplayed(NAME_LIST));
  }

  @Test
  void checkRadioButtonIsNotSelectedTest() {
    assertThrowsNotImplemented(() -> radioButtonStepRepository.checkRadioButtonIsNotSelected(NAME));
  }

  @Test
  void checkRadioButtonIsNotSelectedListTest() {
    assertThrowsNotImplemented(
        () -> radioButtonStepRepository.checkRadioButtonIsNotSelected(NAME_LIST));
  }

  @Test
  void checkRadioButtonIsSelectedTest() {
    assertThrowsNotImplemented(() -> radioButtonStepRepository.checkRadioButtonIsSelected(NAME));
  }

  @Test
  void checkRadioButtonIsSelectedListTest() {
    assertThrowsNotImplemented(
        () -> radioButtonStepRepository.checkRadioButtonIsSelected(NAME_LIST));
  }

  @Test
  void clickRadioButtonTest() {
    assertThrowsNotImplemented(() -> radioButtonStepRepository.clickRadioButton(NAME));
  }

  @Test
  void clickRadioButtonListTest() {
    assertThrowsNotImplemented(() -> radioButtonStepRepository.clickRadioButton(NAME_LIST));
  }
}
