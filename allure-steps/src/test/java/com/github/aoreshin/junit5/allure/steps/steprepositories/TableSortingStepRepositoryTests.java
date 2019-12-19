package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.NAME;
import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.assertThrowsNotImplemented;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

final class TableSortingStepRepositoryTests {
  private static class TableSortingStepRepositoryImpl
      implements TableSortingStepRepository<TableSortingStepRepositoryImpl> {}

  private final TableSortingStepRepository<?> tableSortingStepRepository =
      new TableSortingStepRepositoryImpl();

  @Test
  void checkColumnSortedTest() {
    assertThrowsNotImplemented(
        () ->
            tableSortingStepRepository.checkColumnSorted(
                NAME, TableSortingStepRepository.Sorting.ASCENDING, Comparator.naturalOrder()));
  }

  @Test
  void checkFilterContainsValueMapTest() {
    assertThrowsNotImplemented(
        () ->
            tableSortingStepRepository.checkDateColumnSorted(
                NAME, TableSortingStepRepository.Sorting.ASCENDING));
  }

  @Test
  void checkDateColumnSortedComparatorTest() {
    assertThrowsNotImplemented(
        () ->
            tableSortingStepRepository.checkDateColumnSorted(
                NAME, TableSortingStepRepository.Sorting.ASCENDING, null, null));
  }
}
