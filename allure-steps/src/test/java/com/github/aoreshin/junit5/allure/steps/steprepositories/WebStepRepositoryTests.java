package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.NAME;
import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.assertThrowsNotImplemented;

import org.junit.jupiter.api.Test;

final class WebStepRepositoryTests {
  private static class WebStepRepositoryImpl implements WebStepRepository<WebStepRepositoryImpl> {}

  private final WebStepRepository<?> webStepRepository = new WebStepRepositoryImpl();

  @Test
  void openUrlTest() {
    assertThrowsNotImplemented(() -> webStepRepository.openUrl("blah"));
  }

  @Test
  void checkFormIsDisplayedTest() {
    assertThrowsNotImplemented(() -> webStepRepository.checkFormIsDisplayed(NAME));
  }
}
