package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import org.junit.jupiter.api.Test;

final class ButtonStepRepositoryTests {
  private static class ButtonStepRepositoryImpl
      implements ButtonStepRepository<ButtonStepRepositoryImpl> {}

  private final ButtonStepRepository<?> buttonStepRepository = new ButtonStepRepositoryImpl();

  @Test
  void clickButtonTest() {
    assertThrowsNotImplemented(() -> buttonStepRepository.clickButton(NAME));
  }

  @Test
  void clickButtonListTest() {
    assertThrowsNotImplemented(() -> buttonStepRepository.clickButton(NAME_LIST));
  }

  @Test
  void checkButtonIsDisplayedTest() {
    assertThrowsNotImplemented(() -> buttonStepRepository.checkButtonIsDisplayed(NAME));
  }

  @Test
  void checkButtonIsDisplayedListTest() {
    assertThrowsNotImplemented(() -> buttonStepRepository.checkButtonIsDisplayed(NAME_LIST));
  }
}
