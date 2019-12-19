package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

final class TableFilterStepRepositoryTests {
  private static class TableFilterStepRepositoryImpl
      implements TableFilterStepRepository<TableFilterStepRepositoryImpl> {}

  private final TableFilterStepRepository<?> tableFilterStepRepository =
      new TableFilterStepRepositoryImpl();

  @Test
  void checkFilterContainsValueTest() {
    assertThrowsNotImplemented(
        () -> tableFilterStepRepository.checkFilterContainsValue("blah", "blah"));
  }

  @Test
  void checkFilterContainsValueMapTest() {
    assertThrowsNotImplemented(
        () -> tableFilterStepRepository.checkFilterContainsValue(Map.of("blah", "blah")));
  }

  @Test
  void checkFilterIsEmptyTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.checkFilterIsEmpty(NAME));
  }

  @Test
  void checkFilterIsEmptyListTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.checkFilterIsEmpty(NAME_LIST));
  }

  @Test
  void clearFilterTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.clearFilter(NAME));
  }

  @Test
  void clearFilterListTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.clearFilter(NAME_LIST));
  }

  @Test
  void deselectFilterTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.deselectFilter(NAME));
  }

  @Test
  void deselectFilterListTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.deselectFilter(NAME_LIST));
  }

  @Test
  void fillFilterTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.fillFilter(NAME, VALUE));
  }

  @Test
  void fillFilterMapTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.fillFilter(NAMES_AND_VALUES));
  }

  @Test
  void selectFromFilterTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.selectFromFilter(NAME, VALUE));
  }

  @Test
  void selectFromFilterMapTest() {
    assertThrowsNotImplemented(() -> tableFilterStepRepository.selectFromFilter(NAMES_AND_VALUES));
  }
}
