package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import org.junit.jupiter.api.Test;

final class MessagesStepRepositoryTests {
  private static class MessagesStepRepositoryImpl
      implements MessagesStepRepository<MessagesStepRepositoryImpl> {}

  private final MessagesStepRepository<?> messagesStepRepository = new MessagesStepRepositoryImpl();

  @Test
  void errorIsDisplayedTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.errorIsDisplayed(NAME));
  }

  @Test
  void errorIsDisplayedListTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.errorIsDisplayed(NAME_LIST));
  }

  @Test
  void textIsDisplayedTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.textIsDisplayed(NAME));
  }

  @Test
  void textIsDisplayedListTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.textIsDisplayed(NAME_LIST));
  }

  @Test
  void warningIsDisplayedTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.warningIsDisplayed(NAME));
  }

  @Test
  void warningIsDisplayedListTest() {
    assertThrowsNotImplemented(() -> messagesStepRepository.warningIsDisplayed(NAME_LIST));
  }
}
