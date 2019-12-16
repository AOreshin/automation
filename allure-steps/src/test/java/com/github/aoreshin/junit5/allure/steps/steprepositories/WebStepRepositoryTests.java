package com.github.aoreshin.junit5.allure.steps.steprepositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class WebStepRepositoryTests {
    private static class WebStepRepositoryImpl implements WebStepRepository<WebStepRepositoryImpl> {}

    private final WebStepRepository<?> webStepRepository = new WebStepRepositoryImpl();

    @Test
    void openUrlTest() {
        assertThrows(NotImplementedException.class, () -> webStepRepository
                .openUrl("blah"));
    }

    @Test
    void checkFormIsDisplayedTest() {
        assertThrows(NotImplementedException.class, () -> webStepRepository
                .checkFormIsDisplayed("blah"));
    }
}
