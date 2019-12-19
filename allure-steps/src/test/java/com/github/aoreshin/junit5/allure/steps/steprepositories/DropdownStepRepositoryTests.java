package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import org.junit.jupiter.api.Test;

final class DropdownStepRepositoryTests {
  private static class DropdownStepRepositoryImpl
      implements DropdownStepRepository<DropdownStepRepositoryImpl> {}

  private final DropdownStepRepository<?> dropdownStepRepository = new DropdownStepRepositoryImpl();

  @Test
  void clearDropdownTest() {
    assertThrowsNotImplemented(() -> dropdownStepRepository.clearDropdownList(NAME));
  }

  @Test
  void clearDropdownListTest() {
    assertThrowsNotImplemented(() -> dropdownStepRepository.clearDropdownList(NAME_LIST));
  }

  @Test
  void checkButtonIsDisplayedTest() {
    assertThrowsNotImplemented(() -> dropdownStepRepository.selectFromDropdownList(NAME, VALUE));
  }

  @Test
  void checkButtonIsDisplayedListTest() {
    assertThrowsNotImplemented(
        () -> dropdownStepRepository.selectFromDropdownList(NAMES_AND_VALUES));
  }
}
