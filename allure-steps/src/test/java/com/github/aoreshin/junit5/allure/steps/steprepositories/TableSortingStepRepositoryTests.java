package com.github.aoreshin.junit5.allure.steps.steprepositories;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class TableSortingStepRepositoryTests {
    private static class TableSortingStepRepositoryImpl implements
            TableSortingStepRepository<TableSortingStepRepositoryImpl> {}

    private final TableSortingStepRepository<?> tableSortingStepRepository = new TableSortingStepRepositoryImpl();

    @Test
    void checkColumnSortedTest() {
        assertThrows(NotImplementedException.class, () -> tableSortingStepRepository
                        .checkColumnSorted("blah", TableSortingStepRepository.Sorting.ASCENDING,
                                Comparator.naturalOrder()));
    }

    @Test
    void checkFilterContainsValueMapTest() {
        assertThrows(NotImplementedException.class,
                () -> tableSortingStepRepository
                        .checkDateColumnSorted("blah", TableSortingStepRepository.Sorting.ASCENDING));
    }

    @Test
    void checkDateColumnSortedComparatorTest() {
        assertThrows(NotImplementedException.class,
                () -> tableSortingStepRepository
                        .checkDateColumnSorted("blah", TableSortingStepRepository.Sorting.ASCENDING,
                                null, null));
    }
}
